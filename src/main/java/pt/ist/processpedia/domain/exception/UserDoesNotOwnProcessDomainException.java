package pt.ist.processpedia.domain.exception;

import pt.ist.processpedia.domain.Process;
import pt.ist.processpedia.domain.User;

public class UserDoesNotOwnProcessDomainException extends ProcesspediaDomainException {
    
  private Process process;
  private User user;
  
  public UserDoesNotOwnProcessDomainException(User user, Process process) {
    this.user = user;
    this.process = process;
  }
  
  public User getUser() {
    return this.user;
  }

  public Process getProcess() {
    return this.process;
  }
  
}