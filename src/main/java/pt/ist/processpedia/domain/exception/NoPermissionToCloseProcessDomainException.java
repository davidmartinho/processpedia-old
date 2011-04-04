package pt.ist.processpedia.domain.exception;

import pt.ist.processpedia.domain.Process;
import pt.ist.processpedia.domain.User;

public class NoPermissionToCloseProcessDomainException extends ProcesspediaDomainException {
    
  private Process process;
  private User user;
  
  public NoPermissionToCloseProcessDomainException(Process process, User user) {
    this.process = process;
    this.user = user;
  }
  
  public Process getProcess() {
    return this.process;
  }
  
  public User getUser() {
    return this.user;
  }
  
}