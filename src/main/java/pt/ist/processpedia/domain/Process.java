/*
 * Copyright 2011 ESW Software Engineering Group
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package pt.ist.processpedia.domain;

import java.util.Set;
import java.util.HashSet;

import org.joda.time.DateTime;

import pt.ist.processpedia.domain.exception.NoPermissionToCreateProcessDomainException;
import pt.ist.processpedia.domain.exception.UserDoesNotOwnProcessDomainException;
import pt.ist.processpedia.domain.exception.UserIsNotExecutingParentRequestDomainException;

/**
 * This class represents a business process execution supported by the Processpedia Workflow System environment.
 */
public class Process extends Process_Base {

  /**
   * Creates a new process without description at the current time and in the draft state.
   * @param creator the user creating the process
   * @param title the title of the process
   * @throws NoPermissionToCreateProcessDomainException when the user has no privileges to create processes
   */
  public Process(User creator, String title) throws NoPermissionToCreateProcessDomainException {
    this(creator, title, null, new DateTime(), ProcessState.DRAFT);
  }

  /**
   * Creates a new process.
   * @param creator the user creating the process
   * @param title the title of the process
   * @param description the description of the process
   * @param creationTimestamp the timestamp for when the process was created
   * @param state the state of the business process
   * @throws NoPermissionToCreateProcessDomainException when the user has no privileges to create processes
   */
  public Process(User creator, String title, String description, DateTime creationTimestamp,
                 ProcessState state) throws NoPermissionToCreateProcessDomainException {
    setCreator(creator);
    addOwner(creator);
    setTitle(title);
    setDescription(description);
    setCreationTimestamp(creationTimestamp);
    setState(state);
  }

  /**
   * Defines the title of the process.
   * @param title the string containing the title of the process
   */
  public void setTitle(String title) {
    this.setTitleTag(new Tag(title));
  }

  /**
   *
   * @return
   */
  public String getTitle() {
    return getTitleTag().getKeyword();
  }

  /**
   * Obtains a simple description of the process.
   * @return the description of the process
   */
  public String getDescription() {
    return this.getDescriptionComment().getCommentText();
  }

  /**
   * Associates a simple description of the process.
   * @param description the description of the process
   */
  public void setDescription(String description) {
    setDescriptionComment(new Comment(getCreator(), description));
  }
  
  /**
   * Creates a new request in the context of the process.
   * @param creator the creator of the request
   * @param title the title of the request
   * @param description the description of the request
   * @return the created request
   * @throws UserDoesNotOwnProcessDomainException when the creator is not listed as an owner of the process
   */
  public Request createNewRequest(User creator, String title, String description) throws
      UserDoesNotOwnProcessDomainException {
    if(userOwnsProcess(creator)) {
      Request request = new Request(creator, title, description);
      request.setProcess(this);
      return request;
    } else {
      throw new UserDoesNotOwnProcessDomainException(creator, this);
    }
  }

  /**
   * Creates a new request in the context of another request.
   * @param creator The creator of the request.
   * @param title The title of the request.
   * @param description The description of the request.
   * @param parentRequest The request from which the request being created is originated.
   * @throws UserIsNotExecutingParentRequestDomainException If the creator is has not made a commitment to executing
   * the parentRequest.
   */
  public Request createNewRequest(User creator, String title, String description,
                                  Request parentRequest) throws UserIsNotExecutingParentRequestDomainException {
    if(parentRequest.hasCommitmentFrom(creator)) {
      Request request = new Request(creator, title, description, parentRequest);
      addRequest(request);
      return request;
    } else {
      throw new UserIsNotExecutingParentRequestDomainException(creator, this, parentRequest);
    }
  }
  
  /**
   * Verifies if the user owns the process.
   * @param user The user to verify as owning the process.
   * @return True if the provided user is one of the process owners, false otherwise.
   */
  private Boolean userOwnsProcess(User user) {
    for(User owner : getOwnerSet()) {
      if(owner.equals(user)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Verifies if a given user may open the process.
   * @return True if the user is allowed to open the process, false otherwise.
   */
  public Boolean canBeOpenedByUser(User user) {
    return getCreator().equals(user);
  }
  
  /**
   * Verifies if a given user may close the process.
   * @return True if the user is allowed to close the process, false otherwise.
   */
  public Boolean canBeClosedByUser(User user) {
    return getCreator().equals(user) || userOwnsProcess(user);
  }
  
  /**
   * Opens the process.
   */
  protected void open() {
    setState(ProcessState.OPEN);
  }
  
  /**
   * Verifies if the process is open.
   * @return True if the process is open, false otherwise.
   */
  public Boolean isOpen() {
    return getState().equals(ProcessState.OPEN);
  }
  
  /**
   * Closes the process.
   */
  protected void close() {
    setState(ProcessState.CLOSED);
  }
  
  /**
   * Checks if the process is closed.
   * @return true if the process is closed, false otherwise
   */
  public Boolean isClosed() {
    return getState().equals(ProcessState.CLOSED);
  }

  /**
   * Checks if the provided user is a current participant of the process, or if he owns the process.
   * @param user The user for which is being checked a current participation
   * @return true if the user is either currently participating in the process or if he is defined as one of its
   * owners, false otherwise
   */
  public Boolean hasParticipant(User user) {
    if(isOwnedBy(user)) {
      return true;
    }
    for(Request request : this.getRequestSet()) {
      if(request.hasUserInvolved(user)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if the provided user is a current owner of the process.
   * @param user the user being checked as owner of the process
   * @return true if the provided user owns the process, false otherwise
   */
  public Boolean isOwnedBy(User user) {
    return super.hasOwner(user);
  }

  /**
   * Computes the set of current users participating in the process (creator, owners, initiators and executors).
   * @return the set of users currently participating in the process
   */
  public Set<User> getParticipantSet() {
    Set<User> participantSet = new HashSet<User>();
    participantSet.addAll(getOwnerSet());
    for(Request request : getRequestSet()) {
      participantSet.addAll(request.getParticipantSet());
    }
    return participantSet;
  }

}