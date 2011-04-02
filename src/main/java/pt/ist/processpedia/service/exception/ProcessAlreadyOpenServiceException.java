package pt.ist.processpedia.service.exception;

import pt.ist.processpedia.service.dto.ProcessDto;

public class ProcessAlreadyOpenServiceException extends ProcesspediaServiceException {
  
  private static long serialVersionUID = 1L;
  
  private ProcessDto processDto;
    
  public ProcessAlreadyOpenServiceException(ProcessDto processDto) {
    this.processDto = processDto;
  }
  
  public ProcessDto getProcessDto() {
    return this.processDto;
  }
  
}