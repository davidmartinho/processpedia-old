package pt.ist.processpedia.service;

import pt.ist.processpedia.domain.Processpedia;
import pt.ist.processpedia.domain.User;

import pt.ist.processpedia.service.dto.UserDto;

import pt.ist.processpedia.service.exception.UserIdNotFoundServiceException;

public class GetUserByIdService extends ProcesspediaService<UserDto> {
  
  private Integer userId;
  
  public GetUserByIdService(Integer userId) {
    this.userId = userId;
  }
  
  @Override
  public UserDto dispatch() throws UserIdNotFoundServiceException {
    Processpedia processpedia = getProcesspedia();
    User user = processpedia.getUserById(this.userId);
    if(user==null) {
      throw new UserIdNotFoundServiceException(this.userId);
    }
    UserDto userDto = new UserDto(user.getId(), user.getName());
    return userDto;
  }
  
}