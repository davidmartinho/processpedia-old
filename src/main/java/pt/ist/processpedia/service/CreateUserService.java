package pt.ist.processpedia.service;

import pt.ist.processpedia.domain.Processpedia;
import pt.ist.processpedia.domain.User;

import pt.ist.processpedia.domain.exception.EmailAlreadyRegisteredDomainException;
import pt.ist.processpedia.service.exception.EmailAlreadyRegisteredServiceException;

public class CreateUserService extends ProcesspediaService<Boolean> {
  
  private String name;
  private String email;
  private String passwordHash;
  
  public CreateUserService(String name, String email, String passwordHash) {
    this.name = name;
    this.email = email;
    this.passwordHash = passwordHash;
  }
  
  @Override
  public Boolean dispatch() throws EmailAlreadyRegisteredServiceException {
    Processpedia processpedia = getProcesspedia();
    try {
      processpedia.createNewUser(this.name, this.email, this.passwordHash);
    } catch(EmailAlreadyRegisteredDomainException e) {
      User user = e.getUser();
      throw new EmailAlreadyRegisteredServiceException(user.getName(), user.getEmail());
    }
    return true;
  }
  
}