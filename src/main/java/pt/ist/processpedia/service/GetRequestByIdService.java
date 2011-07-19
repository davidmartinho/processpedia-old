/*
 * Copyright 2011 ESW Software Engineering Group
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
 */

package pt.ist.processpedia.service;

import pt.ist.processpedia.domain.Processpedia;
import pt.ist.processpedia.domain.Request;
import pt.ist.processpedia.domain.User;
import pt.ist.processpedia.service.dto.DtoMapper;
import pt.ist.processpedia.service.dto.RequestDto;
import pt.ist.processpedia.service.exception.NoPermissionToReadRequestServiceException;
import pt.ist.processpedia.service.exception.RequestIdNotFoundServiceException;
import pt.ist.processpedia.service.exception.UserIdNotFoundServiceException;

public class GetRequestByIdService extends ProcesspediaService<RequestDto> {

  private final String requestId;
  private final String userId;

  public GetRequestByIdService(String requestId, String userId) {
    this.requestId = requestId;
    this.userId = userId;
  }

  @Override
  public RequestDto dispatch() throws UserIdNotFoundServiceException, RequestIdNotFoundServiceException, NoPermissionToReadRequestServiceException {
    Processpedia processpedia = getProcesspedia();
    User user = processpedia.getUserById(userId);
    if(user == null) {
      throw new UserIdNotFoundServiceException(userId);
    }
    Request request = processpedia.getRequestById(requestId);
    if(request == null) {
      throw new RequestIdNotFoundServiceException(requestId);
    }
    if(request.isInitiator(user) || request.isExecutor(user)) {
      return DtoMapper.createRequestDtoFromRequest(request);
    } else {
      throw new NoPermissionToReadRequestServiceException(DtoMapper.createUserDtoFromUser(user), requestId);
    }
  }

}
