package pt.ist.processpedia.service.exception;

import pt.ist.processpedia.service.dto.UserDto;

public class UserDoesNotParticipateInProcessServiceException extends ProcesspediaServiceException {

  private UserDto userDto;
  private Integer processId;

  public UserDoesNotParticipateInProcessServiceException(UserDto userDto, Integer processId) {
    this.userDto = userDto;
    this.processId = processId;
  }

  public UserDto getUserDto() {
    return this.userDto;
  }

  public Integer getProcessId() {
    return this.processId;
  }

}
