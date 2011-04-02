package pt.ist.processpedia.service.exception;

public class EmailAlreadyRegisteredServiceException extends ProcesspediaServiceException {

  private static final long serialVersionUID = 1L;

  private String name;
  private String email;

  public EmailAlreadyRegisteredServiceException(String name, String email) {
    this.name = name;
    this.email = email;
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getEmail() {
    return this.email;
  }

}