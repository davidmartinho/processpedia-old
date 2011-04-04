package pt.ist.processpedia.service.exception;

import pt.ist.processpedia.service.dto.ProcessDto;
import pt.ist.processpedia.service.dto.UserDto;

public class UserDoesNotOwnProcessServiceException extends ProcesspediaServiceException {
  
  private static final long serialVersionUID = 1L;

  private UserDto userDto;
  private ProcessDto processDto;

  public UserDoesNotOwnProcessServiceException(UserDto userDto, ProcessDto processDto) {
    this.processDto = processDto;
    this.userDto = userDto;
  }
  
  public UserDto getUserDto() {
    return this.userDto;
  }

  public ProcessDto getProcessDto() {
    return this.processDto;
  }

}