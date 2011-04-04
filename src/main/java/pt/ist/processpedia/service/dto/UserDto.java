package pt.ist.processpedia.service.dto;

public class UserDto extends Dto {
  
  String name;
  
  public UserDto(Integer id, String name) {
    super(id);
    this.name = name;
  }
  
  public String getName() {
    return this.name;
  }
  
  @Override
  public String toJson() {
    return "{id: "+this.getId()+", name: \""+this.getName()+"\"}";
  }
  
}