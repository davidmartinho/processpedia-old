package pt.ist.processpedia.service.dto;

import java.util.Set;

public class QueueDetailedDto extends QueueSimpleDto {

  private Set<QueueDetailedDto> childQueueDtoSet;
  
  public QueueDetailedDto(Integer id, String name, Set<QueueDetailedDto> childQueueDtoSet) {
    super(id, name);
    this.childQueueDtoSet = childQueueDtoSet;
  }

  public Set<QueueDetailedDto> getChildQueueDtoSet() {
    return this.childQueueDtoSet;
  }
  
  @Override
  public String toJson() {
    String childQueuesJson = "";
    for(QueueDetailedDto childQueueDto : this.childQueueDtoSet) {
      childQueuesJson+= childQueueDto.toJson();
    }
    return "{id: "+this.getId()+", name: \""+this.getName()+"\", childQueues: ["+childQueuesJson+"] }";
  }
  
}