package pt.ist.processpedia.service.exception;

import pt.ist.processpedia.domain.User;
import pt.ist.processpedia.domain.Request;

import java.net.UnknownServiceException;

public class NoPermissionToReadRequestServiceException extends ProcesspediaServiceException {

  private User user;
  private Integer requestId;

  public NoPermissionToReadRequestServiceException(User user, Integer requestId) {
    this.user = user;
    this.requestId = requestId;
  }

  public User getUser() {
    return this.user;
  }

  public Integer getRequestId() {
    return this.requestId;
  }
  
}
