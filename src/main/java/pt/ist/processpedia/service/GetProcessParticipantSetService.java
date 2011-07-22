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

import java.util.Set;

import pt.ist.processpedia.domain.Process;
import pt.ist.processpedia.domain.Processpedia;
import pt.ist.processpedia.service.dto.DtoMapper;
import pt.ist.processpedia.service.dto.UserDto;
import pt.ist.processpedia.service.exception.ProcessIdNotFoundServiceException;

public class GetProcessParticipantSetService extends ProcesspediaService<Set<UserDto>> {

  private final String processId;

  public GetProcessParticipantSetService(String processId) {
    this.processId = processId;
  }

  @Override
  public Set<UserDto> dispatch() throws ProcessIdNotFoundServiceException {
    Processpedia processpedia = getProcesspedia();
    Process process = processpedia.getProcessById(processId);
    if(process == null) {
      throw new ProcessIdNotFoundServiceException(processId);
    }
    return DtoMapper.createUserDtoSetFromUserSet(process.getParticipantSet());
  }

}
