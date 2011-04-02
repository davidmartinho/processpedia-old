package pt.ist.processpedia.service;

import pt.ist.processpedia.domain.Processpedia;
import pt.ist.processpedia.domain.User;

import pt.ist.processpedia.service.exception.UserIdNotFoundServiceException;

public class CreateProcessService extends ProcesspediaService<Boolean> {
  
  private Integer userId;
  private String title;
  
  public CreateProcessService(Integer userId, String title) {
    this.userId = userId;
    this.title = title;
  }
  
  @Override
  public Boolean dispatch() throws UserIdNotFoundServiceException {
    Processpedia processpedia = getProcesspedia();
    User creator = processpedia.getUserById(this.userId);
    if(creator==null) {
      throw new UserIdNotFoundServiceException(this.userId);
    }
    processpedia.createNewProcess(creator, this.title);
    return true;
  }
  
}