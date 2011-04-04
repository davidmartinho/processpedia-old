package pt.ist.processpedia.domain.exception;

import pt.ist.processpedia.domain.User;
import pt.ist.processpedia.domain.Process;

public class NoPermissionToOpenProcessDomainException extends ProcesspediaDomainException {
    
  private Process process;
  private User user;
  
  public NoPermissionToOpenProcessDomainException(User user, Process process) {
    this.process = process;
    this.user = user;
  }

  public User getUser() {
    return this.user;
  }

  public Process getProcess() {
    return this.process;
  }
  
}