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
import pt.ist.processpedia.service.dto.ProcessDto;
import pt.ist.processpedia.service.exception.UserIdNotFoundServiceException;

public class CreateProcessService extends ProcesspediaService<ProcessDto> {

  private final String userId;
  private final String title;

  public CreateProcessService(String userId, String title) {
    this.userId = userId;
    this.title = title;
  }

  @Override
  public ProcessDto dispatch() throws UserIdNotFoundServiceException {
    Processpedia processpedia = getProcesspedia();
    User creator = processpedia.getUserById(userId);
    if(creator==null) {
      throw new UserIdNotFoundServiceException(userId);
    }
    Process process = processpedia.createNewProcess(creator, title);
    return new ProcessDto(process.getId(), process.getTitle(), process.isOpen());
  }

}