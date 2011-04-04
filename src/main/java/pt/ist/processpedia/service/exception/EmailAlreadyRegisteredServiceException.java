package pt.ist.processpedia.service.exception;

import pt.ist.processpedia.service.dto.UserDetailedDto;

public class EmailAlreadyRegisteredServiceException extends ProcesspediaServiceException {

  private static final long serialVersionUID = 1L;

  private UserDetailedDto userDto;

  public EmailAlreadyRegisteredServiceException(UserDetailedDto userDto) {
    this.userDto = userDto;
  }
  
  public UserDetailedDto getUserDto() {
    return this.userDto;
  }

}