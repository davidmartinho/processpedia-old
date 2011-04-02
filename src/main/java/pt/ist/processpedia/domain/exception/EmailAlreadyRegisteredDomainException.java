package pt.ist.processpedia.domain.exception;

import pt.ist.processpedia.domain.User;

public class EmailAlreadyRegisteredDomainException extends ProcesspediaDomainException {
  
  private static final long serialVersionUID = 1L;
  
  private User user;
  
  public EmailAlreadyRegisteredDomainException(User user) {
    this.user = user;
  }
  
  public User getUser() {
    return this.user;
  }
  
}