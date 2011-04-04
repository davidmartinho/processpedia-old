package pt.ist.processpedia.service.dto;

import java.util.Set;

public class QueueSimpleDto extends Dto {

  private String name;
  
  public QueueSimpleDto(Integer id, String name) {
    super(id);
    this.name = name;
  }
  
  public String getName() {
    return this.name;
  }
  
  @Override
  public String toJson() {
    return "{id: "+this.getId()+", name: \""+this.getName()+"\" }";
  }
  
}
