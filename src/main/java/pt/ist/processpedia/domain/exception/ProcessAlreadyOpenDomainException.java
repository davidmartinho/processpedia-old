package pt.ist.processpedia.domain.exception;

import pt.ist.processpedia.domain.Process;

public class ProcessAlreadyOpenDomainException extends ProcesspediaDomainException {

  private static final long serialVersionUID = 1L;

  private Process process;

  public ProcessAlreadyOpenDomainException(Process process) {
    this.process = process;
  }
  
  public Process getProcess() {
    return this.process;
  }

}