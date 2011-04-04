package pt.ist.processpedia.service;

import pt.ist.processpedia.domain.Processpedia;
import pt.ist.processpedia.domain.Process;
import pt.ist.processpedia.domain.User;

import pt.ist.processpedia.service.dto.ProcessDto;

import pt.ist.processpedia.service.exception.UserIdNotFoundServiceException;

public class CreateProcessService extends ProcesspediaService<ProcessDto> {
  
  private Integer userId;
  private String title;
  
  public CreateProcessService(Integer userId, String title) {
    this.userId = userId;
    this.title = title;
  }
  
  @Override
  public ProcessDto dispatch() throws UserIdNotFoundServiceException {
    Processpedia processpedia = getProcesspedia();
    User creator = processpedia.getUserById(this.userId);
    if(creator==null) {
      throw new UserIdNotFoundServiceException(this.userId);
    }
    Process process = processpedia.createNewProcess(creator, this.title);
    return new ProcessDto(process.getId(), process.getTitle(), process.isOpen());
  }
  
}