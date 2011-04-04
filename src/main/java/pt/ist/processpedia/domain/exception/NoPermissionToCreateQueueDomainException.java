package pt.ist.processpedia.domain.exception;

import pt.ist.processpedia.domain.User;

public class NoPermissionToCreateQueueDomainException extends ProcesspediaDomainException  {

  private User user;

  public NoPermissionToCreateQueueDomainException(User user) {
    this.user = user;
  }

  public User getUser() {
    return this.user;
  }

}
