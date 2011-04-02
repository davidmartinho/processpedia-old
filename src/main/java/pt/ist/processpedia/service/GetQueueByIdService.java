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