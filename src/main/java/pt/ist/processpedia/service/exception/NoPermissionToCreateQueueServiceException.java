package pt.ist.processpedia.service.exception;

import pt.ist.processpedia.service.dto.UserDto;

public class NoPermissionToCreateQueueServiceException extends ProcesspediaServiceException {

  private static long serialVersionUID = 1L;

  private UserDto userDto;

  public NoPermissionToCreateQueueServiceException(UserDto userDto) {
    this.userDto = userDto;
  }

  public UserDto getUserDto() {
    return this.userDto;
  }

}


