package pt.ist.processpedia.service.exception;

public class EmailNotFoundServiceException extends ProcesspediaServiceException {

  private static final long serialVersionUID = 1L;

  private String email;

  public EmailNotFoundServiceException(String email) {
    this.email = email;
  }
  
  public String getEmail() {
    return this.email;
  }

}