package pt.ist.processpedia.service.exception;

public class RequestIdNotFoundServiceException extends ProcesspediaServiceException {

  private static long serialVersionUID = 1L;

  private Integer requestId;

  public RequestIdNotFoundServiceException(Integer requestId) {
    this.requestId = requestId;
  }

  public Integer getRequestId() {
    return this.requestId;
  }

}
