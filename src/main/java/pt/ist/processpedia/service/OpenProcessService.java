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
import pt.ist.processpedia.domain.Process;
import pt.ist.processpedia.domain.User;
import pt.ist.processpedia.service.dto.DtoMapper;
import pt.ist.processpedia.service.dto.ProcessDto;
import pt.ist.processpedia.service.dto.UserDto;
import pt.ist.processpedia.domain.exception.ProcessAlreadyOpenDomainException;
import pt.ist.processpedia.domain.exception.NoPermissionToOpenProcessDomainException;
import pt.ist.processpedia.service.exception.ProcessIdNotFoundServiceException;
import pt.ist.processpedia.service.exception.UserIdNotFoundServiceException;
import pt.ist.processpedia.service.exception.ProcessAlreadyOpenServiceException;
import pt.ist.processpedia.service.exception.NoPermissionToOpenProcessServiceException;

public class OpenProcessService extends ProcesspediaService<Boolean> {

  private final String userId;
  private final String processId;

  public OpenProcessService(String userId, String processId) {
    this.userId = userId;
    this.processId = processId;
  }

  @Override
  public Boolean dispatch() throws UserIdNotFoundServiceException, ProcessIdNotFoundServiceException, ProcessAlreadyOpenServiceException, NoPermissionToOpenProcessServiceException {
    Processpedia processpedia = getProcesspedia();
    User user = processpedia.getUserById(userId);
    if(user==null) {
      throw new UserIdNotFoundServiceException(userId);
    }
    Process process = processpedia.getProcessById(processId);
    if(process==null) {
      throw new ProcessIdNotFoundServiceException(processId);
    }
    try {
      processpedia.openProcess(process, user);
    } catch(NoPermissionToOpenProcessDomainException e) {
      process = e.getProcess();
      user = e.getUser();
      ProcessDto processDto = DtoMapper.createProcessDtoFromProcess(process);
      UserDto userDto = DtoMapper.createUserDtoFromUser(user);
      throw new NoPermissionToOpenProcessServiceException(processDto, userDto);
    } catch(ProcessAlreadyOpenDomainException e) {
      process = e.getProcess();
      ProcessDto processDto = DtoMapper.createProcessDtoFromProcess(process);
      throw new ProcessAlreadyOpenServiceException(processDto);
    }
    return true;
  }

}