/**
 * Copyright (c) 2011. Instituto Superior Técnico.
 *
 * Author: David Martinho (davidmartinho@ist.utl.pt)
 **/

package pt.ist.processpedia.service;

import pt.ist.processpedia.domain.Processpedia;
import pt.ist.processpedia.domain.User;
import pt.ist.processpedia.domain.Process;

import pt.ist.processpedia.domain.exception.UserDoesNotOwnProcessDomainException;
import pt.ist.processpedia.service.dto.DtoMapper;
import pt.ist.processpedia.service.exception.ProcessIdNotFoundServiceException;
import pt.ist.processpedia.service.exception.UserDoesNotOwnProcessServiceException;
import pt.ist.processpedia.service.exception.UserIdNotFoundServiceException;

public class RemoveProcessOwnerService extends ProcesspediaService<Boolean> {

  private Integer userId;
  private Integer ownerToBeRemovedUserId;
  private Integer processId;

  public RemoveProcessOwnerService(Integer userId, Integer ownerToBeRemovedUserId, Integer processId) {
    this.userId = userId;
    this.ownerToBeRemovedUserId = ownerToBeRemovedUserId;
    this.processId = processId;
  }

  @Override
  public Boolean dispatch() throws UserIdNotFoundServiceException, ProcessIdNotFoundServiceException {
    Processpedia processpedia = getProcesspedia();
    User user = processpedia.getUserById(this.userId);
    if(user == null) {
      throw new UserIdNotFoundServiceException(this.userId);
    }
    User ownerToBeRemovedUser = processpedia.getUserById(this.ownerToBeRemovedUserId);
    if(ownerToBeRemovedUser == null) {
      throw new UserIdNotFoundServiceException(this.ownerToBeRemovedUserId);
    }
    Process process = processpedia.getProcessById(this.processId);
    if(process == null) {
      throw new ProcessIdNotFoundServiceException(this.processId);
    }
    try {
      processpedia.removeProcessOwner(ownerToBeRemovedUser, process, user);
    } catch(UserDoesNotOwnProcessDomainException e) {
      throw new UserDoesNotOwnProcessServiceException(DtoMapper.createUserDtoFromUser(e.getUser()), DtoMapper.createProcessDtoFromProcess(e.getProcess()));
    }
    return true;
  }
  
}
