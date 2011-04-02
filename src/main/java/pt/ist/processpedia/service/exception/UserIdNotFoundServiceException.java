package pt.ist.processpedia.service.exception;

public class UserIdNotFoundServiceException extends ProcesspediaServiceException {

  private static final long serialVersionUID = 1L;

  private Integer userId;

  public UserIdNotFoundServiceException(Integer userId) {
    this.userId = userId;
  }
  
  public Integer getUserId() {
    return this.userId;
  }

}