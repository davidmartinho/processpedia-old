package pt.ist.processpedia.service.exception;

public class ProcessIdNotFoundServiceException extends ProcesspediaServiceException {
  
  private static final long serialVersionUID = 1L;

  private Integer processId;
  
  public ProcessIdNotFoundServiceException(Integer processId) {
    this.processId = processId;
  }
  
  public Integer getProcessId() {
    return this.processId;
  }
  
}