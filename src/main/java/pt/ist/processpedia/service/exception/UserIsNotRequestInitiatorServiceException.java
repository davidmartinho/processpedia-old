/**
 * Copyright (c) 2011. Instituto Superior Técnico.
 *
 * Author: David Martinho (davidmartinho@ist.utl.pt)
 **/

package pt.ist.processpedia.service.exception;

import pt.ist.processpedia.service.dto.RequestDto;
import pt.ist.processpedia.service.dto.UserDto;

public class UserIsNotRequestInitiatorServiceException extends ProcesspediaServiceException {

  private static long serialVersionUID = 1L;

  private UserDto userDto;
  private RequestDto requestDto;

  public UserIsNotRequestInitiatorServiceException(UserDto userDto, RequestDto requestDto) {
    this.userDto = userDto;
    this.requestDto = requestDto;
  }

  public UserDto getUserDto() {
    return this.userDto;
  }

  public RequestDto getRequestDto() {
    return this.requestDto;
  }

}
