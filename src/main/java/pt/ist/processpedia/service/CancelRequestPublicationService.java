/**
 * Copyright (c) 2011. Instituto Superior Técnico.
 *
 * Author: David Martinho (davidmartinho@ist.utl.pt)
 **/

package pt.ist.processpedia.service;

import pt.ist.processpedia.domain.Processpedia;
import pt.ist.processpedia.domain.Request;
import pt.ist.processpedia.domain.User;

import pt.ist.processpedia.domain.exception.UserIsNotRequestInitiatorDomainException;
import pt.ist.processpedia.service.dto.DtoMapper;
import pt.ist.processpedia.service.exception.ProcesspediaServiceException;
import pt.ist.processpedia.service.exception.RequestIdNotFoundServiceException;
import pt.ist.processpedia.service.exception.UserIdNotFoundServiceException;
import pt.ist.processpedia.service.exception.UserIsNotRequestInitiatorServiceException;

public class CancelRequestPublicationService extends ProcesspediaService<Boolean> {

  private Integer userId;
  private Integer requestId;

  public CancelRequestPublicationService(Integer requestId, Integer userId) {
    this.userId = userId;
    this.requestId = requestId;
  }

  @Override
  public Boolean dispatch() throws ProcesspediaServiceException {
    Processpedia processpedia = getProcesspedia();
    User user = processpedia.getUserById(this.userId);
    if(user == null) {
      throw new UserIdNotFoundServiceException(this.userId);
    }
    Request request = processpedia.getRequestById(this.requestId);
    if(request == null) {
      throw new RequestIdNotFoundServiceException(this.requestId);
    }
    try {
      user.unpublishRequest(request);
    } catch(UserIsNotRequestInitiatorDomainException e) {
      throw new UserIsNotRequestInitiatorServiceException(DtoMapper.createUserDtoFromUser(e.getUser()), DtoMapper.createRequestDtoFromRequest(e.getRequest()));
    }
    return true;
  }
  
}
