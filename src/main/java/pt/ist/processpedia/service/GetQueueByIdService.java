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

import java.util.Set;
import java.util.HashSet;

import pt.ist.processpedia.domain.Processpedia;
import pt.ist.processpedia.domain.Queue;

import pt.ist.processpedia.service.dto.QueueDetailedDto;

import pt.ist.processpedia.service.exception.QueueIdNotFoundServiceException;

public class GetQueueByIdService extends ProcesspediaService<QueueDetailedDto> {
  
  private Integer queueId;
  
  public GetQueueByIdService(Integer queueId) {
    this.queueId = queueId;
  }
  
  @Override
  public QueueDetailedDto dispatch() throws QueueIdNotFoundServiceException {
    Processpedia processpedia = getProcesspedia();
    Queue queue = processpedia.getQueueById(this.queueId);
    if(queue==null) {
      throw new QueueIdNotFoundServiceException(this.queueId);
    }
    return getQueueDto(queue);
  }
  
  private QueueDetailedDto getQueueDto(Queue queue) {
    Set<QueueDetailedDto> childQueueDtos = new HashSet<QueueDetailedDto>();
    for(Queue childQueue : queue.getChildQueueSet()) {
      childQueueDtos.add(getQueueDto(childQueue));
    }
    return new QueueDetailedDto(queue.getId(), queue.getName(), childQueueDtos);
  }
  
}