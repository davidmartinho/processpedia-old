package pt.ist.processpedia.domain.exception;

import pt.ist.processpedia.domain.User;
import pt.ist.processpedia.domain.Process;

public class UserAlreadyOwnsProcessDomainException extends ProcesspediaDomainException {

  private User user;
  private Process process;

  public UserAlreadyOwnsProcessDomainException(User user, Process process) {
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
