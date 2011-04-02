package pt.ist.processpedia.service;

import pt.ist.processpedia.domain.Processpedia;
import pt.ist.processpedia.domain.Process;
import pt.ist.processpedia.domain.User;

import pt.ist.processpedia.service.dto.ProcessDto;
import pt.ist.processpedia.service.dto.UserDto;

import pt.ist.processpedia.domain.exception.ProcessAlreadyOpenDomainException;
import pt.ist.processpedia.domain.exception.NoPermissionToOpenProcessDomainException;

import pt.ist.processpedia.service.exception.ProcessIdNotFoundServiceException;
import pt.ist.processpedia.service.exception.UserIdNotFoundServiceException;

import pt.ist.processpedia.service.exception.ProcessAlreadyOpenServiceException;
import pt.ist.processpedia.service.exception.NoPermissionToOpenProcessServiceException;

public class OpenProcessService extends ProcesspediaService<Boolean> {
  
  private Integer userId;
  private Integer processId;
  
  public OpenProcessService(Integer userId, Integer processId) {
    this.userId = userId;
    this.processId = processId;
  }
  
  @Override
  public Boolean dispatch() throws UserIdNotFoundServiceException, ProcessIdNotFoundServiceException, ProcessAlreadyOpenServiceException, NoPermissionToOpenProcessServiceException {
    Processpedia processpedia = getProcesspedia();
    User user = processpedia.getUserById(this.userId);
    if(user==null) {
      throw new UserIdNotFoundServiceException(this.userId);
    }
    Process process = processpedia.getProcessById(this.processId);
    if(process==null) {
      throw new ProcessIdNotFoundServiceException(this.processId);
    }
    try {
      processpedia.openProcess(process, user);
    } catch(NoPermissionToOpenProcessDomainException e) {
      process = e.getProcess();
      user = e.getUser();
      ProcessDto processDto = new ProcessDto(process.getId(), process.getTitle());
      UserDto userDto = new UserDto(user.getId(), user.getName());
      throw new NoPermissionToOpenProcessServiceException(processDto, userDto);
    } catch(ProcessAlreadyOpenDomainException e) {
      process = e.getProcess();
      ProcessDto processDto = new ProcessDto(process.getId(), process.getTitle());
      throw new ProcessAlreadyOpenServiceException(processDto);
    }
    return true;
  }
  
}