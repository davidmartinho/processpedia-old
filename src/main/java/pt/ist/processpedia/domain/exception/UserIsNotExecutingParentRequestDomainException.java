package pt.ist.processpedia.domain.exception;

import pt.ist.processpedia.domain.Process;
import pt.ist.processpedia.domain.User;
import pt.ist.processpedia.domain.Request;

public class UserIsNotExecutingParentRequestDomainException extends ProcesspediaDomainException {
    
  private Process process;
  private User user;
  private Request parentRequest;
  
  public UserIsNotExecutingParentRequestDomainException(User user, Process process, Request parentRequest) {
    this.user = user;
    this.process = process;
    this.parentRequest = parentRequest;
  }
  
  public User getUser() {
    return this.user;
  }
    
  public Process getProcess() {
    return this.process;
  }

  public Request getParentRequest() {
    return this.parentRequest;
  }
  
}