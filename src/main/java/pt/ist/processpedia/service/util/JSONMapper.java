package pt.ist.processpedia.service.util;

import pt.ist.processpedia.service.dto.*;

public class JSONMapper implements Mapper {

  public String fromUserDto(UserDto userDto) {
    return "{\"id\": \"urn:pt:ist:ciist:user:"+userDto.getId()+", \"name\": \""+userDto.getName()+"\"}";
  }

  public String fromUserDetailedDto(UserDetailedDto userDetailedDto) {
    return "{\"id\": \"urn:pt:ist:ciist:user:"+userDetailedDto.getId()+", \"name\": \""+userDetailedDto.getName()+"\", \"email\": \""+userDetailedDto.getEmail()+"}";
  }
  
  public String fromProcessDto(ProcessDto processDto) {
    return "{\"id\": \"urn:pt:ist:ciist:process:"+processDto.getId()+", \"title\": \""+processDto.getTitle()+"\", \"open\": "+processDto.isOpen()+"}";
  }

  public String fromQueueDto(QueueDto queueDto) {
    return "{\"id\": \"urn:pt:ist:ciist:queue:"+queueDto.getId()+"\", \"name\": \""+queueDto.getName()+"\"}";
  }

  public String fromQueueDetailedDto(QueueDetailedDto queueDetailedDto) {
    String childQueuesJson = "";
    Integer numChildQueues = queueDetailedDto.getChildQueueDtoSet().size();
    Integer i = 1;
    for(QueueDetailedDto childQueueDetailedDto : queueDetailedDto.getChildQueueDtoSet()) {
      childQueuesJson+= fromQueueDetailedDto(childQueueDetailedDto);
      if(i<numChildQueues) {
        childQueuesJson+=", ";
      }
      i++;
    }
    return "{\"id\": \"urn:pt:ist:ciist:queue:"+queueDetailedDto.getId()+", \"name\": \""+queueDetailedDto.getName()+"\", \"childQueues\": ["+childQueuesJson+"] }";
  }

  public String fromRequestDto(RequestDto requestDto) {
    return "{\"id\": \"urn:pt:ist:ciist:request:"+requestDto.getId()+", \"title\": \""+requestDto.getTitle()+"\", \"description\": \""+requestDto.getDescription()+"\", \"initiator\": "+fromUserDto(requestDto.getInitiator())+"}";
  }

}
