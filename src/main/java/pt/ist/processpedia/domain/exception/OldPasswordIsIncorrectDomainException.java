package pt.ist.processpedia.domain.exception;

import pt.ist.processpedia.domain.User;

public class OldPasswordIsIncorrectDomainException extends ProcesspediaDomainException {

  private User user;

  public OldPasswordIsIncorrectDomainException(User user) {
    this.user = user;
  }

  public User getUser() {
    return this.user;
  }
}