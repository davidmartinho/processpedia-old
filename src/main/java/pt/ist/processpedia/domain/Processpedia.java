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

import pt.ist.fenixframework.FenixFramework;
import pt.ist.processpedia.domain.exception.*;

import pt.ist.processpedia.domain.DomainObject.IdFactory;

public class Processpedia extends Processpedia_Base implements IdFactory {

  /**
   * Initializes the Processpedia application.
   */
  public Processpedia() {
    setNextProcessId(new Id("1"));
    setNextUserId(new Id("1"));
    setNextRequestId(new Id("1"));
    setNextCommentId(new Id("1"));
    setNextTagId(new Id("1"));
    setNextResponseId(new Id("1"));
    setNextQueueId(new Id("1"));
    setNextCommitmentId(new Id("1"));
  }
  
  /**
   * Creates a new process.
   * @param creator The user creating the process.
   * @return The created process.
   */
  public Process createNewProcess(User creator, String title) {
    Process process = new Process(Processpedia.getIdFactory().reserveId(Process.class), creator, title);
    process.setId(getNextProcessId().toString());
    setNextProcessId(getNextProcessId().getNextId());
    addProcess(process);
    return process;
  }
  
  /**
   * Closes a process that is in the open state.
   * @param process The process being closed.
   * @param user The user that is trying to close the process.
   * @throws ProcessAlreadyClosedDomainException If the process is already in the closed state.
   * @throws NoPermissionToCloseProcessDomainException If the user has no privileges to close the process.
   */
  public void closeProcess(Process process, User user) throws ProcessAlreadyClosedDomainException, NoPermissionToCloseProcessDomainException {
    if(process.isClosed()) {
      throw new ProcessAlreadyClosedDomainException(process);
    } else {
      if(process.canBeClosedByUser(user)) {
        process.close();
      } else {
        throw new NoPermissionToCloseProcessDomainException(process, user);
      }
    }
  }
  
  /**
   * Opens a process that is in the closed state.
   * @param process The process being opened.
   * @param user The user trying to open the process.
   * @throws NoPermissionToOpenProcessDomainException If the process is already in the open state.
   * @throws NoPermissionToCloseProcessDomainException If the user has no privileges to open the process.
   */
  public void openProcess(Process process, User user) throws ProcessAlreadyOpenDomainException, NoPermissionToOpenProcessDomainException {
    if(process.isOpen()) {
      throw new ProcessAlreadyOpenDomainException(process);
    }
    if(process.canBeOpenedByUser(user)) {
      process.open();
    } else {
      throw new NoPermissionToOpenProcessDomainException(user, process);
    }
  }
  
  
  /**
   * Creates a new request in the context of the process.
   * @param process The process in which the request is being created.
   * @param creator The user that is creating the request.
   * @param title The title of the request.
   * @param description A more detailed specification of the request.
   * @return The created request.
   * @throws UserDoesNotOwnProcessDomainException If the creator is not listed as an owner of the process.
   */
  public Request createNewRequest(Process process, User creator, String title, String description) throws UserDoesNotOwnProcessDomainException {
    Request request = process.createNewRequest(creator, title, description);
    request.setId(getNextRequestId().toString());
    setNextRequestId(getNextRequestId().getNextId());
    process.addRequest(request);
    return request;
  }
  
  /**
   * Creates a new request in the context of another request.
   * @param creator The creator of the request.
   * @param title The title of the request.
   * @param description The description of the request.
   * @param parentRequest The request from which the request being created is originated.
   * @throws UserIsNotExecutingParentRequestDomainException If the creator is not the current executor of the parentRequest.
   */
  public Request createNewRequest(Process process, User creator, String title, String description, Request parentRequest) throws UserIsNotExecutingParentRequestDomainException {
    Request request = process.createNewRequest(creator, title, description, parentRequest);
    request.setId(getNextRequestId().toString());
    setNextRequestId(getNextRequestId().getNextId());
    process.addRequest(request);
    return request;
  }
  
  /**
   * Creates a new user.
   * @param name The name of the user.
   * @param email The email address of the user.
   * @param passwordHash The hash function of the user's password.
   * @return The created user.
   * @throws EmailAlreadyRegisteredDomainException If the email already belongs to another processpedia user.
   */
  public User createNewUser(String name, String email, String passwordHash) throws EmailAlreadyRegisteredDomainException {
    for(User user: this.getUserSet()) {
      if(user.getEmail().equals(email)) {
        throw new EmailAlreadyRegisteredDomainException(user);
      }
    }
    String newUserId = getNextUserId().toString();
    User user = new User(newUserId, name, email, passwordHash);
    setNextUserId(getNextUserId().getNextId());
    addUser(user);
    Queue queue = new Queue(user.getName()+"'s Private Queue");
    queue.setId(getNextQueueId().toString());
    setNextQueueId(getNextQueueId().getNextId());
    user.setPrivateQueue(queue);
    return user;
  }
  
  /**
   * Claims an unclaimed request.
   * @param claimer The user claiming the request.
   * @param request The request being claimed.
   * @throws RequestAlreadyClaimedDomainException If the request is already claimed.
   */
  public void claimRequest(User claimer, Request request) throws RequestAlreadyClaimedDomainException {
    if(request.hasExecutor()) {
      throw new RequestAlreadyClaimedDomainException(request);
    }
    request.setExecutor(claimer);
  }


  /**
   * Obtains a request given its identifier.
   * @param requestId The identifier of the request
   * @return The request if the id matches, null otherwise
   */
  public Request getRequestById(String requestId) {
    for(Process process : this.getProcessSet()) {
      for(Request request : process.getRequestSet()) {
        if(request.getId().equals(requestId)) {
          return request;
        }
      }
    }
    return null;
  }

  /**
   * Obtains a queue given its identifier.
   * @param queueId the identifier of the queue
   * @return the queue if the identifier matches, null otherwise
   */
  public Queue getQueueById(String queueId) {
    for(Queue queue : this.getQueueSet()) {
      if(queue.getId().equals(queueId)) {
        return queue;
      }
    }
    return null;
  }

  /**
   * Obtains a user given its email address.
   * @param email The user's email address.
   * @return The user if the email matches, null otherwise.
   */
  public User getUserByEmail(String email) {
    for(User user : this.getUserSet()) {
      if(user.getEmail().equals(email)) {
        return user;
      }
    }
    return null;
  }

  /**
   * Obtains a user given its identifier.
   * @param userId the identifier of the user
   * @return the user if the identifier matches, null otherwise
   */
  public User getUserById(String userId) {
    for(User user : this.getUserSet()) {
      if(user.getId().equals(userId)) {
        return user;
      }
    }
    return null;
  }

  /**
   * Obtains a process given its identifier.
   * @param processId the identifier of the process
   * @return The process if the identifier matches, or null otherwise
   */
  public Process getProcessById(String processId) {
    for(Process process : getProcessSet()) {
      if(process.getId().equals(processId)) {
        return process;
      }
    }
    return null;
  }

  /**
   * Adds the user to the list of process owners.
   * @param toBeOwnerUser The user to be the new owner of the process.
   * @param process The process to be owned by <code>toBeOwnerUser</code>.
   * @param ownerUser The user attempting to provide ownership privileges to <code>toBeOwnerUser</code>.
   */
  public void addProcessOwner(User toBeOwnerUser, Process process, User ownerUser) {
    if(process.hasOwner(toBeOwnerUser)) {
      throw new UserAlreadyOwnsProcessDomainException(toBeOwnerUser, process);
    }
    if(process.hasOwner(ownerUser)) {
    } else {
      throw new UserDoesNotOwnProcessDomainException(ownerUser, process);
    }
  }

  /**
   * Creates a new queue.
   * @param name The name of the queue.
   * @param user The user creating the queue.
   * @return The created queue.
   */
  public Queue createNewQueue(String name, User user) {
    Queue queue = new Queue(name);
    queue.setId(getNextQueueId().toString());
    setNextQueueId(getNextQueueId().getNextId());
    return queue;
  }
  
  public void removeProcessOwner(User ownerToBeRemovedUser, Process process, User user) {
    if(!process.isOwnedBy(user)) {
      throw new UserDoesNotOwnProcessDomainException(user, process);
    }
    if(!process.isOwnedBy(ownerToBeRemovedUser)) {
      throw new UserDoesNotOwnProcessDomainException(ownerToBeRemovedUser, process);
    }
  }

  public String reserveId(Class domainClass) {
    Id id = null;
    if(domainClass.equals(Comment.class)) {
      id = getNextCommentId();
      setNextCommentId(id.getNextId());
    } else if(domainClass.equals(Process.class)) {
      id = getNextProcessId();
      setNextProcessId(id.getNextId());
    } else if(domainClass.equals(Request.class)) {
      id = getNextRequestId();
      setNextRequestId(id.getNextId());
    } else if(domainClass.equals(Commitment.class)) {
      id = getNextCommitmentId();
      setNextCommitmentId(id.getNextId());
    } else if(domainClass.equals(Response.class)) {
      id = getNextResponseId();
      setNextResponseId(id.getNextId());
    } else if(domainClass.equals(Tag.class)) {
      id = getNextTagId();
      setNextTagId(id.getNextId());
    } else if(domainClass.equals(Queue.class)) {
      id = getNextQueueId();
      setNextQueueId(id.getNextId());
    }
    return id.toString();
  }

  public static IdFactory getIdFactory() {
    return (IdFactory)FenixFramework.getRoot();
  }

}
