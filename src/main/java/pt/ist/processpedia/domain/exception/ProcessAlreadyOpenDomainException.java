package pt.ist.processpedia.domain.exception;

import pt.ist.processpedia.domain.Process;

public class ProcessAlreadyOpenDomainException extends ProcesspediaDomainException {

  private Process process;

  public ProcessAlreadyOpenDomainException(Process process) {
    this.process = process;
  }
  
  public Process getProcess() {
    return this.process;
  }

}