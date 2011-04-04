package pt.ist.processpedia.service;

import pt.ist.processpedia.domain.Processpedia;
import pt.ist.processpedia.domain.Process;
import pt.ist.processpedia.domain.Request;
import pt.ist.processpedia.domain.User;

import pt.ist.processpedia.domain.exception.UserDoesNotOwnProcessDomainException;

import pt.ist.processpedia.service.dto.DtoMapper;

import pt.ist.processpedia.service.dto.RequestDto;

import pt.ist.processpedia.service.exception.ProcessIdNotFoundServiceException;
import pt.ist.processpedia.service.exception.UserIdNotFoundServiceException;
import pt.ist.processpedia.service.exception.UserDoesNotOwnProcessServiceException;

public class CreateNewRequestService extends ProcesspediaService<RequestDto> {
  
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
  public RequestDto dispatch() throws ProcessIdNotFoundServiceException, UserIdNotFoundServiceException, UserDoesNotOwnProcessServiceException {
    Processpedia processpedia = getProcesspedia();
    Request request = null;
    User user = processpedia.getUserById(this.userId);
    if(user==null) {
      throw new UserIdNotFoundServiceException(this.userId);
    }
    Process process = processpedia.getProcessById(this.processId);
    if(process==null) {
      throw new ProcessIdNotFoundServiceException(this.processId);
    }
    try {
      request = processpedia.createNewRequest(process, user, this.title, this.description);
    } catch(UserDoesNotOwnProcessDomainException e) {
      throw new UserDoesNotOwnProcessServiceException(DtoMapper.createUserDtoFromUser(e.getUser()), DtoMapper.createProcessDtoFromProcess(e.getProcess()));
    }
    return new RequestDto(request.getId(), request.getTitle(), request.getDescription(), DtoMapper.createUserDtoFromUser(user));
  }
  
}