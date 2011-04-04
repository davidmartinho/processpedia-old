package pt.ist.processpedia.service.util;

import pt.ist.processpedia.service.dto.ProcessDto;
import pt.ist.processpedia.service.dto.QueueDto;
import pt.ist.processpedia.service.dto.RequestDto;
import pt.ist.processpedia.service.dto.UserDto;

public interface Mapper {

  public String fromUserDto(UserDto userDto);

  public String fromProcessDto(ProcessDto processDto);

  public String fromQueueDto(QueueDto queueDto);

  public String fromRequestDto(RequestDto requestDto);
  
}
