package pt.ist.processpedia.service.exception;

import pt.ist.processpedia.service.dto.ProcessDto;
import pt.ist.processpedia.service.dto.UserDto;

public class NoPermissionToOpenProcessServiceException extends ProcesspediaServiceException {
  
  private static long serialVersionUID = 1L;
  
  private ProcessDto processDto;
  private UserDto userDto;
  
  public NoPermissionToOpenProcessServiceException(ProcessDto processDto, UserDto userDto) {
    this.processDto = processDto;
    this.userDto = userDto;
  }
  
  public ProcessDto getProcessDto() {
    return this.processDto;
  }
  
  public UserDto getUserDto() {
    return this.userDto;
  }

}