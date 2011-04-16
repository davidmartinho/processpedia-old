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
