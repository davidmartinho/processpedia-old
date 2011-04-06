package pt.ist.processpedia.service;

import pt.ist.processpedia.domain.Processpedia;
import pt.ist.processpedia.domain.User;
import pt.ist.processpedia.domain.Queue;

import pt.ist.processpedia.service.dto.DtoMapper;
import pt.ist.processpedia.service.dto.QueueDto;

import pt.ist.processpedia.service.exception.UserIdNotFoundServiceException;

public class GetUserPrivateQueueByUserIdService extends ProcesspediaService<QueueDto> {
  
  private Integer userId;
  
  public GetUserPrivateQueueByUserIdService(Integer userId) {
    this.userId = userId;
  }
  
  @Override
  public QueueDto dispatch() throws UserIdNotFoundServiceException {
    Processpedia processpedia = getProcesspedia();
    User user = processpedia.getUserById(this.userId);
    if(user==null) {
      throw new UserIdNotFoundServiceException(this.userId);
    }
    Queue queue = user.getPrivateQueue();
    return DtoMapper.createQueueDtoFromQueue(queue);
  }

}