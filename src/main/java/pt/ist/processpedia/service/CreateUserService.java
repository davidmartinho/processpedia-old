package pt.ist.processpedia.service;

import pt.ist.processpedia.domain.Processpedia;
import pt.ist.processpedia.domain.User;

import pt.ist.processpedia.domain.exception.EmailAlreadyRegisteredDomainException;

import pt.ist.processpedia.service.dto.UserDetailedDto;
import pt.ist.processpedia.service.dto.UserDto;

import pt.ist.processpedia.service.exception.EmailAlreadyRegisteredServiceException;

public class CreateUserService extends ProcesspediaService<UserDto> {
  
  private String name;
  private String email;
  private String passwordHash;
  
  public CreateUserService(String name, String email, String passwordHash) {
    this.name = name;
    this.email = email;
    this.passwordHash = passwordHash;
  }
  
  @Override
  public UserDto dispatch() throws EmailAlreadyRegisteredServiceException {
    Processpedia processpedia = getProcesspedia();
    User user = null;
    try {
      user = processpedia.createNewUser(this.name, this.email, this.passwordHash);
    } catch(EmailAlreadyRegisteredDomainException e) {
      user = e.getUser();
      UserDetailedDto userDto = new UserDetailedDto(user.getId(), user.getName(), user.getEmail());
      throw new EmailAlreadyRegisteredServiceException(userDto);
    }
    return new UserDetailedDto(user.getId(), user.getName(), user.getEmail());
  }
  
}