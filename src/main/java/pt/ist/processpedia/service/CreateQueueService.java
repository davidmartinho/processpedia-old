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
import pt.ist.processpedia.domain.Queue;
import pt.ist.processpedia.domain.User;
import pt.ist.processpedia.domain.exception.NoPermissionToCreateQueueDomainException;
import pt.ist.processpedia.service.dto.DtoMapper;
import pt.ist.processpedia.service.dto.QueueDetailedDto;
import pt.ist.processpedia.service.exception.NoPermissionToCreateQueueServiceException;
import pt.ist.processpedia.service.exception.UserIdNotFoundServiceException;

public class CreateQueueService extends ProcesspediaService<QueueDetailedDto> {

  private String name;
  private String userId;

  public CreateQueueService(String name, String userId) {
    this.name = name;
    this.userId = userId;
  }

  @Override
  public QueueDetailedDto dispatch() throws UserIdNotFoundServiceException, NoPermissionToCreateQueueServiceException {
    Processpedia processpedia = getProcesspedia();
    Queue queue = null;
    User user = processpedia.getUserById(userId);
    if(user == null) {
      throw new UserIdNotFoundServiceException(userId);
    }
    try {
      queue = processpedia.createNewQueue(name, user);
    } catch(NoPermissionToCreateQueueDomainException e) {
      throw new NoPermissionToCreateQueueServiceException(DtoMapper.createUserDtoFromUser(e.getUser()));
    }
    return DtoMapper.createQueueDetailedDtoFromQueue(queue);
  }

}
