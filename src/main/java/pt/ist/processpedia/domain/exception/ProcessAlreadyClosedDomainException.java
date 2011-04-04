package pt.ist.processpedia.domain.exception;

import pt.ist.processpedia.domain.Process;

public class ProcessAlreadyClosedDomainException extends ProcesspediaDomainException {
  
  private Process process;

  public ProcessAlreadyClosedDomainException(Process process) {
    this.process = process;
  }

  public Process getProcess() {
    return process;
  }
  
}