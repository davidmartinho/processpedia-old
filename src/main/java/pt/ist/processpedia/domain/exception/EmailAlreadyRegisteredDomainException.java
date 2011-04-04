package pt.ist.processpedia.domain.exception;

import pt.ist.processpedia.domain.User;

public class EmailAlreadyRegisteredDomainException extends ProcesspediaDomainException {
    
  private User user;
  
  public EmailAlreadyRegisteredDomainException(User user) {
    this.user = user;
  }
  
  public User getUser() {
    return this.user;
  }
  
}