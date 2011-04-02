package pt.ist.processpedia.service.exception;

public class UserDoesNotOwnProcessServiceException extends ProcesspediaServiceException {
  
  private static final long serialVersionUID = 1L;

  private String processTitle;
  private String userName;

  public UserDoesNotOwnProcessServiceException(String processTitle, String userName) {
    this.processTitle = processTitle;
    this.userName = userName;
  }
  
  public String getProcessTitle() {
    return this.processTitle;
  }
  
  public String getUserName() {
    return this.userName;
  }

}