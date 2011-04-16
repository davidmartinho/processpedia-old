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
import pt.ist.processpedia.domain.User;

import pt.ist.processpedia.domain.exception.UserAlreadyOwnsProcessDomainException;
import pt.ist.processpedia.domain.exception.UserDoesNotOwnProcessDomainException;

import pt.ist.processpedia.service.dto.DtoMapper;

import pt.ist.processpedia.service.exception.ProcessIdNotFoundServiceException;
import pt.ist.processpedia.service.exception.UserAlreadyOwnsProcessServiceException;
import pt.ist.processpedia.service.exception.UserDoesNotOwnProcessServiceException;
import pt.ist.processpedia.service.exception.UserIdNotFoundServiceException;

public class AddProcessOwnerService extends ProcesspediaService<Boolean> {

  private Integer processId;
  private Integer ownerUserId;
  private Integer toBeOwnerUserId;

  public AddProcessOwnerService(Integer toBeOwnerUserId, Integer processId, Integer ownerUserId) {
    this.ownerUserId = ownerUserId;
    this.processId = processId;
    this.toBeOwnerUserId = toBeOwnerUserId;
  }

  public Integer getProcessId() {
    return this.processId;
  }

  public Integer getOwnerUserId() {
    return this.ownerUserId;
  }

  public Integer getToBeOwnerUserId() {
    return this.toBeOwnerUserId;
  }


  @Override
  public Boolean dispatch() throws UserIdNotFoundServiceException, ProcessIdNotFoundServiceException, UserDoesNotOwnProcessServiceException, UserAlreadyOwnsProcessServiceException {
    Processpedia processpedia = getProcesspedia();
    User ownerUser = null;
    Process process = null;
    User toBeOwnerUser = null;
    toBeOwnerUser = processpedia.getUserById(this.toBeOwnerUserId);
    if(toBeOwnerUser == null) {
      throw new UserIdNotFoundServiceException(this.toBeOwnerUserId);
    }
    process = processpedia.getProcessById(this.processId);
    if(process == null) {
      throw new ProcessIdNotFoundServiceException(this.processId);
    }
    ownerUser = processpedia.getUserById(this.ownerUserId);
    if(ownerUser == null) {
      throw new UserIdNotFoundServiceException(this.ownerUserId);
    }
    try {
      processpedia.addProcessOwner(toBeOwnerUser, process, ownerUser);
    } catch(UserDoesNotOwnProcessDomainException e) {
      throw new UserDoesNotOwnProcessServiceException(DtoMapper.createUserDtoFromUser(e.getUser()), DtoMapper.createProcessDtoFromProcess(e.getProcess()));
    } catch(UserAlreadyOwnsProcessDomainException e) {
      throw new UserAlreadyOwnsProcessServiceException(DtoMapper.createUserDtoFromUser(e.getUser()), DtoMapper.createProcessDtoFromProcess(e.getProcess()));
    }
    return true;
  }
}


