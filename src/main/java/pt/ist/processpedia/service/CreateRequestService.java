/**
 * Processpedia
 * Copyright (C) 2011 ESW Software Engineering Group
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 **/

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

public class CreateRequestService extends ProcesspediaService<RequestDto> {

  private final String processId;
  private final String userId;
  private final String subject;
  private final String description;
  
  public CreateRequestService(String processId, String userId, String subject, String description) {
    this.processId = processId;
    this.userId = userId;
    this.subject = subject;
    this.description = description;
  }
  
  @Override
  public RequestDto dispatch() throws ProcessIdNotFoundServiceException, UserIdNotFoundServiceException, UserDoesNotOwnProcessServiceException {
    Processpedia processpedia = getProcesspedia();
    Request request = null;
    User user = processpedia.getUserById(userId);
    if(user==null) {
      throw new UserIdNotFoundServiceException(userId);
    }
    Process process = processpedia.getProcessById(processId);
    if(process==null) {
      throw new ProcessIdNotFoundServiceException(processId);
    }
    try {
      request = processpedia.createNewRequest(process, user, subject, description);
    } catch(UserDoesNotOwnProcessDomainException e) {
      throw new UserDoesNotOwnProcessServiceException(DtoMapper.createUserDtoFromUser(e.getUser()), DtoMapper.createProcessDtoFromProcess(e.getProcess()));
    }
    return new RequestDto(request.getId(), request.getSubject(), request.getDescription(), DtoMapper.createUserDtoFromUser(user));
  }
  
}