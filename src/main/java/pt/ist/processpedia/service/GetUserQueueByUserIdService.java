package pt.ist.processpedia.service;

import java.util.Set;
import java.util.HashSet;

import pt.ist.processpedia.domain.Processpedia;
import pt.ist.processpedia.domain.User;
import pt.ist.processpedia.domain.Queue;

import pt.ist.processpedia.service.dto.QueueSimpleDto;

import pt.ist.processpedia.service.exception.UserIdNotFoundServiceException;

public class GetUserQueueByUserIdService extends ProcesspediaService<QueueSimpleDto> {
  
  private Integer userId;
  
  public GetUserQueueByUserIdService(Integer userId) {
    this.userId = userId;
  }
  
  @Override
  public QueueSimpleDto dispatch() throws UserIdNotFoundServiceException {
    Processpedia processpedia = getProcesspedia();
    User user = processpedia.getUserById(this.userId);
    if(user==null) {
      throw new UserIdNotFoundServiceException(this.userId);
    }
    Queue queue = user.getPrivateQueue();
    return getQueueDto(queue);
  }
  
  private QueueSimpleDto getQueueDto(Queue queue) {
    return new QueueSimpleDto(queue.getId(), queue.getName());
  }
  
}