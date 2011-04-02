package pt.ist.processpedia.service;

import pt.ist.processpedia.domain.Processpedia;
import pt.ist.processpedia.domain.Process;
import pt.ist.processpedia.domain.User;


import pt.ist.processpedia.domain.exception.UserDoesNotOwnProcessDomainException;

import pt.ist.processpedia.service.exception.ProcessIdNotFoundServiceException;
import pt.ist.processpedia.service.exception.UserIdNotFoundServiceException;
import pt.ist.processpedia.service.exception.UserDoesNotOwnProcessServiceException;

public class CreateNewRequestService extends ProcesspediaService<Boolean> {
  
  private Integer processId;
  private Integer userId;
  private String title;
  private String description;
  
  public CreateNewRequestService(Integer processId, Integer userId, String title, String description) {
    this.processId = processId;
    this.userId = userId;
    this.title = title;
    this.description = description;
  }
  
  @Override
  public Boolean dispatch() throws ProcessIdNotFoundServiceException, UserIdNotFoundServiceException, UserDoesNotOwnProcessServiceException {
    Processpedia processpedia = getProcesspedia();
    Process process = processpedia.getProcessById(this.processId);
    if(process==null) {
      throw new ProcessIdNotFoundServiceException(this.processId);
    }
    User user = processpedia.getUserById(this.userId);
    if(user==null) {
      throw new UserIdNotFoundServiceException(this.userId);
    }
    try {
      processpedia.createNewRequest(process, user, this.title, this.description);
    } catch(UserDoesNotOwnProcessDomainException e) {
      user = e.getUser();
      process = e.getProcess();
      UserDoesNotOwnProcessServiceException serviceException = new UserDoesNotOwnProcessServiceException(process.getTitle(), user.getName());
      throw serviceException;
    }
    return true;
  }
  
}