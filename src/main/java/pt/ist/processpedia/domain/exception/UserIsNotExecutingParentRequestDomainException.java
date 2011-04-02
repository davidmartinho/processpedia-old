package pt.ist.processpedia.domain.exception;

import pt.ist.processpedia.domain.Process;
import pt.ist.processpedia.domain.User;
import pt.ist.processpedia.domain.Request;


public class UserIsNotExecutingParentRequestDomainException extends ProcesspediaDomainException {
  
  private static final long serialVersionUID = 1L;
  
  private Process process;
  private User user;
  private Request parentRequest;
  
  public UserIsNotExecutingParentRequestDomainException(Process process, User user, Request parentRequest) {
    this.process = process;
    this.user = user;
    this.parentRequest = parentRequest;
  }
  
  public Process getProcess() {
    return this.process;
  }
  
  public User getUser() {
    return this.user;
  }
  
  public Request getParentRequest() {
    return this.parentRequest;
  }
  
}