package pt.ist.processpedia.service;

import pt.ist.processpedia.domain.Processpedia;
import pt.ist.processpedia.domain.Process;
import pt.ist.processpedia.domain.User;

import pt.ist.processpedia.service.dto.ProcessDto;
import pt.ist.processpedia.service.dto.UserDto;

import pt.ist.processpedia.service.exception.ProcessIdNotFoundServiceException;
import pt.ist.processpedia.service.exception.ProcesspediaServiceException;
import pt.ist.processpedia.service.exception.UserDoesNotParticipateInProcessServiceException;
import pt.ist.processpedia.service.exception.UserIdNotFoundServiceException;

public class GetProcessByIdService extends ProcesspediaService<ProcessDto> {

  private Integer processId;
  private Integer userId;

  public GetProcessByIdService(Integer processId, Integer userId) {
    this.processId = processId;
    this.userId = userId;
  }

  @Override
  public ProcessDto dispatch() throws ProcesspediaServiceException {
    Processpedia processpedia = getProcesspedia();
    User user = processpedia.getUserById(this.userId);
    if(user==null) {
      throw new UserIdNotFoundServiceException(this.userId);
    }
    Process process = processpedia.getProcessById(this.processId);
    if(process==null) {
      throw new ProcessIdNotFoundServiceException(this.processId);
    }
    if(process.hasParticipant(user)) {
      return new ProcessDto(process.getId(), process.getTitle());
    } else {
      UserDto userDto = new UserDto(user.getId(), user.getName());
      throw new UserDoesNotParticipateInProcessServiceException(userDto, this.processId);
    }
  }
}
