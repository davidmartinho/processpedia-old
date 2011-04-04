package pt.ist.processpedia.domain;

import pt.ist.processpedia.domain.exception.NoPermissionToCreateRequestDomainException;

import org.joda.time.DateTime;

public class Request extends Request_Base {

  /**
   * Creates a new request.
   * @param initiator The user initiating the request.
   * @param title The title of the request.
   * @param description A more detailed description of the request rationale.
   */
  public Request(User initiator, String title, String description) {
    setInitiator(initiator);
    setTitle(title);
    setDescription(description);
    setState(RequestState.UNPUBLISHED);
    setCreationTimestamp(new DateTime());
  }

  /**
   * Creates a new sub-request in the context of a particular request.
   * @param initiator The user initiating the request.
   * @param title The title of the request.
   * @param description A more detailed description of the request rationale.
   * @param parentRequest The request in which this request is being created.
   */
  public Request(User initiator, String title, String description, Request parentRequest) {
    this(initiator, title, description);
    setParentRequest(parentRequest);
  }

  /**
   * Checks if a given user is either the initiator or the executor of the request or recursively in any nested request.
   * @param user The user to check direct or indirect involvement in the request.
   * @return True if the user is either the initiator or executor of the request or sub-request, false otherwise.
   */
  public boolean hasUserInvolved(User user) {
    if(isInitiator(user) || isExecutor(user)) {
      return true;
    } else {
      for(Request childRequest : getChildRequestSet()) {
        if(childRequest.hasUserInvolved(user)) {
          return true;
        }
      }
      return false;
    }
  }

  /**
   * Checks if a given user is the initiator of the request.
   * @param user The user to be checked
   * @return True if the given user is the initiator of the request, false otherwise.
   */
  public boolean isInitiator(User user) {
    return getInitiator().equals(user);
  }

  /**
   * Checks if a given user is the executor of the request.
   * @param user The user to be checked
   * @return True if the given user is the executor of the request, false otherwise.
   */
  public Boolean isExecutor(User user) {
    return hasExecutor() && getExecutor().equals(user);
  }


}