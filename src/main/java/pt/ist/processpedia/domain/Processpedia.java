package pt.ist.processpedia.domain;

import pt.ist.processpedia.domain.exception.EmailAlreadyRegisteredDomainException;
import pt.ist.processpedia.domain.exception.UserDoesNotOwnProcessDomainException;
import pt.ist.processpedia.domain.exception.UserIsNotExecutingParentRequestDomainException;
import pt.ist.processpedia.domain.exception.ProcessAlreadyOpenDomainException;
import pt.ist.processpedia.domain.exception.RequestAlreadyClaimedDomainException;
import pt.ist.processpedia.domain.exception.ProcessAlreadyClosedDomainException;
import pt.ist.processpedia.domain.exception.NoPermissionToOpenProcessDomainException;
import pt.ist.processpedia.domain.exception.NoPermissionToCloseProcessDomainException;

public class Processpedia extends Processpedia_Base {

  /**
   * Initializes the Processpedia application.
   */
  public Processpedia() {
    setNextProcessId(1);
    setNextUserId(1);
    setNextRequestId(1);
  }
  
  /**
   * Creates a new process.
   * @param creator The user creating the process.
   * @return The created process.
   */
  public Process createNewProcess(User creator, String title) {
    Process process = new Process(creator, title);
    process.setId(getNextProcessId());
    setNextProcessId(getNextProcessId()+1);
    return process;
  }
  
  /**
   * Closes a process that is in the open state.
   * @param process The process being closed.
   * @param user The user that is trying to close the process.
   * @throws ProcessAlreadyClosedException If the process is already in the closed state.
   * @throws NoPermissionToCloseProcessException If the user has no privileges to close the process.
   */
  public void closeProcess(Process process, User user) throws ProcessAlreadyClosedDomainException, NoPermissionToCloseProcessDomainException {
    if(process.isClosed()) {
      throw new ProcessAlreadyClosedDomainException();
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
      throw new NoPermissionToOpenProcessDomainException(process, user);
    }
  }
  
  
  /**
   * Creates a new request in the context of the process.
   * @param process The process in which the request is being created.
   * @param creator The user that is creating the request.
   * @param title The title of the request.
   * @param description A more detailed specification of the request.
   * @return The created request.
   * @throws UserDoesNotOwnProcessException If the creator is not listed as an owner of the process.
   */
  public Request createNewRequest(Process process, User creator, String title, String description) throws UserDoesNotOwnProcessDomainException {
    Request request = process.createNewRequest(creator, title, description);
    request.setId(getNextRequestId());
    setNextRequestId(getNextRequestId()+1);
    return request;
  }
  
  /**
   * Creates a new request in the context of another request.
   * @param creator The creator of the request.
   * @param title The title of the request.
   * @param description The description of the request.
   * @param parentRequest The request from which the request being created is originated.
   * @throws UserIsNotExecutingParentRequestException If the creator is not the current executor of the parentRequest.
   */
  public Request createNewRequest(Process process, User creator, String title, String description, Request parentRequest) throws UserIsNotExecutingParentRequestDomainException {
    Request request = process.createNewRequest(creator, title, description, parentRequest);
    request.setId(getNextRequestId());
    setNextRequestId(getNextRequestId()+1);
    return request;
  }
  
  /**
   * Creates a new user.
   * @param name The name of the user.
   * @param email The email address of the user.
   * @param passwordHash The hash function of the user's password.
   * @return The created user.
   * @throws EmailAlreadyRegisteredException If the email already belongs to another processpedia user.
   */
  public User createNewUser(String name, String email, String passwordHash) throws EmailAlreadyRegisteredDomainException {
    for(User user: this.getUserSet()) {
      if(user.getEmail().equals(email)) {
        throw new EmailAlreadyRegisteredDomainException(user);
      }
    }
    User user = new User(name, email, passwordHash);
    user.setId(getNextUserId());
    setNextUserId(getNextUserId()+1);
    this.addUser(user);
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
   * Obtains a queue given its id.
   * @param queueId The id of the queue.
   * @return The queue if the id matches, null otherwise.
   */
  public Queue getQueueById(Integer queueId) {
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
   * Obtains a user given its id.
   * @param The id of the user.
   * @return The user if the userId matches, null otherwise.
   */
  public User getUserById(Integer userId) {
    for(User user : this.getUserSet()) {
      if(user.getId()==userId) {
        return user;
      }
    }
    return null;
  }
  
  /**
   * Obtains a process given its id.
   * @param processId The id of the process.
   * @return The process if the processId matches, or null otherwise.
   */
  public Process getProcessById(Integer processId) {
    for(Process process : getProcessSet()) {
      if(process.getId()==processId) {
        return process;
      }
    }
    return null;
  }

}
