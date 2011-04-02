package pt.ist.processpedia.service.exception;

public class ProcessIdNotFoundException extends ProcesspediaServiceException {

  private static final long serialVersionUID = 1L;

  private Integer processId;

  public ProcessIdNotFoundException(Integer processId) {
    this.processId = processId;
  }
  
  public Integer getProcessId() {
    return this.processId;
  }

}