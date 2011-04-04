package pt.ist.processpedia.domain.exception;

import pt.ist.processpedia.domain.User;

public class NoPermissionToCreateProcessDomainException extends ProcesspediaDomainException {

  private User user;

  public NoPermissionToCreateProcessDomainException(User user) {
    this.user = user;
  }

  public User getUser() {
    return this.user;
  }
  
}