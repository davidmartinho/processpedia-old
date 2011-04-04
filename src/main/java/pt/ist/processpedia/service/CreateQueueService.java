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
  private Integer userId;
  
  public CreateQueueService(String name, Integer userId) {
    this.name = name;
    this.userId = userId;
  }

  @Override
  public QueueDetailedDto dispatch() throws UserIdNotFoundServiceException, NoPermissionToCreateQueueServiceException {
    Processpedia processpedia = getProcesspedia();
    Queue queue = null;
    User user = processpedia.getUserById(this.userId);
    if(user == null) {
      throw new UserIdNotFoundServiceException(this.userId);
    }
    try {
      queue = processpedia.createNewQueue(this.name, user);
    } catch(NoPermissionToCreateQueueDomainException e) {
      throw new NoPermissionToCreateQueueServiceException(DtoMapper.createUserDtoFromUser(e.getUser()));
    }
    return DtoMapper.createQueueDetailedDtoFromQueue(queue);
  }
  
}
