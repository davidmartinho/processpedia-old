/**
 * Copyright (c) 2011. Instituto Superior Técnico.
 *
 * Author: David Martinho (davidmartinho@ist.utl.pt)
 **/

package pt.ist.processpedia.service.dto;

import java.util.Set;

public class QueueDetailedDto extends QueueDto {

  private Set<QueueDetailedDto> childQueueDtoSet;
  
  public QueueDetailedDto(Integer id, String name, Set<QueueDetailedDto> childQueueDtoSet) {
    super(id, name);
    this.childQueueDtoSet = childQueueDtoSet;
  }

  public Set<QueueDetailedDto> getChildQueueDtoSet() {
    return this.childQueueDtoSet;
  }
  
}