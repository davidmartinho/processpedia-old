package pt.ist.processpedia.service.dto;

import java.util.Set;

public class QueueSimpleDto extends Dto {
  
  private Integer id;
  private String name;
  
  public QueueSimpleDto(Integer id, String name) {
    this.id = id;
    this.name = name;
  }
  
  public Integer getId() {
    return this.id;
  }
  
  public String getName() {
    return this.name;
  }
  
  @Override
  public String toJson() {
    return "{id: "+this.id+", name: \""+this.name+"\" }";
  }
  
}
