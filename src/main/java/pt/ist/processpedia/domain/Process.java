package pt.ist.processpedia.domain;

import org.joda.time.DateTime;

import pt.ist.processpedia.domain.exception.NoPermissionToCloseProcessDomainException;
import pt.ist.processpedia.domain.exception.NoPermissionToCreateProcessDomainException;
import pt.ist.processpedia.domain.exception.UserDoesNotOwnProcessDomainException;
import pt.ist.processpedia.domain.exception.UserIsNotExecutingParentRequestDomainException;

public class Process extends Process_Base {

  /**
   * Creates a new process.
   * @param creator The user that is trying to create the process.
   * @throws NoPermissionToCreateProcessDomainException If the user has no privileges to create processes.
   */
  public Process(User creator, String title) throws NoPermissionToCreateProcessDomainException {
    setCreator(creator);
    addOwner(creator);
    setTitle(title);
    setCreationTimestamp(new DateTime());
    setState(ProcessState.OPEN);
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
  public void open() {
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
  public void close() {
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
    for(User owner : this.getOwnerSet()) {
      if(owner.equals(user)) {
        return true;
      }
    }
    for(Request request : this.getRequestSet()) {
      if(request.hasUserInvolved(user)) {
        return true;
      }
    }
    return false;
  }
  
}
