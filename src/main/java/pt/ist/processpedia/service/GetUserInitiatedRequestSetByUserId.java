/**
 * Copyright (c) 2011. Instituto Superior Técnico.
 *
 * Author: David Martinho (davidmartinho@ist.utl.pt)
 **/

package pt.ist.processpedia.service;

import pt.ist.processpedia.domain.Processpedia;
import pt.ist.processpedia.domain.User;
import pt.ist.processpedia.service.dto.DtoMapper;
import pt.ist.processpedia.service.dto.RequestDto;
import pt.ist.processpedia.service.exception.UserIdNotFoundServiceException;

import java.util.Set;


public class GetUserInitiatedRequestSetByUserId extends ProcesspediaService<Set<RequestDto>> {

  private Integer userId;

  public GetUserInitiatedRequestSetByUserId(Integer userId) {
    this.userId = userId;
  }

  @Override
  public Set<RequestDto> dispatch() throws UserIdNotFoundServiceException {
    Processpedia processpedia = getProcesspedia();
    User user = processpedia.getUserById(this.userId);
    if(user == null) {
      throw new UserIdNotFoundServiceException(this.userId);
    }
    return DtoMapper.createRequestDtoSetFromRequestSet(user.getInitiatedRequestSet());
  }
  
}
