package pt.ist.processpedia.service.exception;

public class QueueIdNotFoundServiceException extends ProcesspediaServiceException {

  private static final long serialVersionUID = 1L;

  private Integer queueId;

  public QueueIdNotFoundServiceException(Integer queueId) {
    this.queueId = queueId;
  }
  
  public Integer getQueueId() {
    return this.queueId;
  }

}