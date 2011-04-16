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

import pt.ist.processpedia.domain.*;

import pt.ist.processpedia.domain.Process;

import pt.ist.processpedia.domain.exception.NoPermissionToCloseProcessDomainException;

import pt.ist.processpedia.service.dto.DtoMapper;

import pt.ist.processpedia.service.exception.NoPermissionToCloseProcessServiceException;
import pt.ist.processpedia.service.exception.ProcessIdNotFoundServiceException;
import pt.ist.processpedia.service.exception.UserIdNotFoundServiceException;

public class CloseProcessService extends ProcesspediaService<Boolean> {

  private Integer processId;
  private Integer userId;

  public CloseProcessService(Integer processId, Integer userId) {
    this.processId = processId;
    this.userId = userId;
  }

  @Override
  public Boolean dispatch() throws NoPermissionToCloseProcessServiceException {
    Processpedia processpedia = getProcesspedia();
    User user = null;
    Process process = null;
    user = processpedia.getUserById(this.userId);
    if(user == null) {
      throw new UserIdNotFoundServiceException(this.userId);
    }
    process = processpedia.getProcessById(this.processId);
    if(process == null) {
      throw new ProcessIdNotFoundServiceException(this.processId);
    }
    try {
      processpedia.closeProcess(process, user);
    } catch(NoPermissionToCloseProcessDomainException e) {
      throw new NoPermissionToCloseProcessServiceException(DtoMapper.createUserDtoFromUser(e.getUser()), DtoMapper.createProcessDtoFromProcess(e.getProcess()));
    }
    return true;
  }
}
