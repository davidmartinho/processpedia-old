package pt.ist.processpedia.service.exception;

import pt.ist.processpedia.service.dto.ProcessDto;
import pt.ist.processpedia.service.dto.UserDto;

public class UserAlreadyOwnsProcessServiceException extends ProcesspediaServiceException {

  private static long serialVersionUID = 1L;

  private UserDto userDto;
  private ProcessDto processDto;

  public UserAlreadyOwnsProcessServiceException(UserDto userDto, ProcessDto processDto) {
    this.userDto = userDto;
    this.processDto = processDto;
  }

  public UserDto getUserDto() {
    return this.userDto;
  }

  public ProcessDto getProcessDto() {
    return this.processDto;
  }
  
}
