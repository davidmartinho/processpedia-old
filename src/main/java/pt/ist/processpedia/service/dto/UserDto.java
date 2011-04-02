package pt.ist.processpedia.service.dto;

public class UserDto extends Dto {
  
  Integer id;
  String name;
  
  public UserDto(Integer id, String name) {
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
    return "{id: "+this.id+", name: \""+this.name+"\"}";
  }
  
}