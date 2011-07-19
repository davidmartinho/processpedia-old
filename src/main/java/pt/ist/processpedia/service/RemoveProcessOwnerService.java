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
import pt.ist.processpedia.domain.User;
import pt.ist.processpedia.domain.Process;
import pt.ist.processpedia.domain.exception.UserDoesNotOwnProcessDomainException;
import pt.ist.processpedia.service.dto.DtoMapper;
import pt.ist.processpedia.service.exception.ProcessIdNotFoundServiceException;
import pt.ist.processpedia.service.exception.UserDoesNotOwnProcessServiceException;
import pt.ist.processpedia.service.exception.UserIdNotFoundServiceException;

public class RemoveProcessOwnerService extends ProcesspediaService<Boolean> {

  private final String userId;
  private final String ownerToBeRemovedUserId;
  private final String processId;

  public RemoveProcessOwnerService(String userId, String ownerToBeRemovedUserId, String processId) {
    this.userId = userId;
    this.ownerToBeRemovedUserId = ownerToBeRemovedUserId;
    this.processId = processId;
  }

  @Override
  public Boolean dispatch() throws UserIdNotFoundServiceException, ProcessIdNotFoundServiceException {
    Processpedia processpedia = getProcesspedia();
    User user = processpedia.getUserById(userId);
    if(user == null) {
      throw new UserIdNotFoundServiceException(userId);
    }
    User ownerToBeRemovedUser = processpedia.getUserById(ownerToBeRemovedUserId);
    if(ownerToBeRemovedUser == null) {
      throw new UserIdNotFoundServiceException(ownerToBeRemovedUserId);
    }
    Process process = processpedia.getProcessById(processId);
    if(process == null) {
      throw new ProcessIdNotFoundServiceException(processId);
    }
    try {
      processpedia.removeProcessOwner(ownerToBeRemovedUser, process, user);
    } catch(UserDoesNotOwnProcessDomainException e) {
      throw new UserDoesNotOwnProcessServiceException(DtoMapper.createUserDtoFromUser(e.getUser()), DtoMapper.createProcessDtoFromProcess(e.getProcess()));
    }
    return true;
  }

}