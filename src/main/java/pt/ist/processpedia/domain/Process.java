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

import pt.ist.fenixframework.FenixFramework;
import pt.ist.processpedia.domain.exception.NoPermissionToCreateProcessDomainException;
import pt.ist.processpedia.domain.exception.UserDoesNotOwnProcessDomainException;
import pt.ist.processpedia.domain.exception.UserIsNotExecutingParentRequestDomainException;

/**
 * This class represents a business process execution supported by the Processpedia Workflow System environment.
 */
public class Process extends Process_Base {

  /**
   * Creates a new process.
   * @param processId the identifier of the new process
   * @param creator the user creating the process
   * @throws NoPermissionToCreateProcessDomainException when the user has no privileges to create processes
   */
  public Process(String processId, User creator, String title) throws NoPermissionToCreateProcessDomainException {
    init(processId);
    setCreator(creator);
    addOwner(creator);
    setTitle(title);
    setCreationTimestamp(new DateTime());
    setState(ProcessState.OPEN);
  }

  /**
   * Creates a new process.
   * @param processId the identifier of the new process
   * @param creator the user creating the process
   * @param title the title of the process
   * @param description the description of the process
   * @param creationTimestamp the timestamp for when the process was created
   * @throws NoPermissionToCreateProcessDomainException when the user has no privileges to create processes
   */
  public Process(String processId, User creator, String title, String description, DateTime creationTimestamp, ProcessState  state) throws NoPermissionToCreateProcessDomainException {
    init(processId);
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
    this.setTitleTag(new Tag(Processpedia.getIdFactory().reserveId(Tag.class), title));
  }

  public String getTitle() {
    return getTitleTag().getKeyword();
  }

  public String getDescription() {
    return this.getDescriptionComment().getCommentText();
  }

  /**
   * Defines the description of the process.
   * @param description the string describing the process
   */
  public void setDescription(String description) {
    setDescriptionComment(new Comment(Processpedia.getIdFactory().reserveId(Comment.class), getCreator(), description));
  }
  
  /**
   * Creates a new request in the context of the process.
   * @param creator The creator of the request.
   * @param title The title of the request.
   * @param description The description of the request.
   * @return The created request.
   * @throws UserDoesNotOwnProcessDomainException If the creator is not listed as an owner of the process.
   */
  public Request createNewRequest(User creator, String title, String description) throws UserDoesNotOwnProcessDomainException {
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
   * @throws UserIsNotExecutingParentRequestDomainException If the creator is not the current executor of the parentRequest.
   */
  public Request createNewRequest(User creator, String title, String description, Request parentRequest) throws UserIsNotExecutingParentRequestDomainException {
    if(parentRequest.getExecutor().equals(creator)) {
      Request request = new Request(creator, title, description, parentRequest);
      request.setProcess(this);
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
   * Verifies if a process is open.
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
   * Verifies if a process is closed.
   * @return True if the process is closed, false otherwise.
   */
  public Boolean isClosed() {
    return getState().equals(ProcessState.CLOSED);
  }

  /**
   * Checks if the provided user is a current participant of the process, or if he owns the process.
   * @param user The user for which is being checked a current participation.
   * @return True if the user is either currently participating in the process or is one of its owners, false otherwise.
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
   * @param user The user for which is being checked if he owns the process.
   * @return True if the provided user owns the process, false otherwise.
   */
  public Boolean isOwnedBy(User user) {
    for(User owner : this.getOwnerSet()) {
      if(owner.equals(user)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Computes the set of current users participating in the process (creator, owners, initiators and executors).
   * @return The set of users participating in the process.
   */
  public Set<User> getParticipantSet() {
    Set<User> participantSet = new HashSet<User>();
    participantSet.addAll(getOwnerSet());
    for(Request request : getRequestSet()) {
      //TODO: ADD ALL THE OTHER CURRENT PARTICIPANTS TO THE SET
    }
    return participantSet;
  }

}
