package pt.ist.processpedia.service.dto;

public class RequestDto extends Dto {
  
  private UserDto initiator;
  private String title;
  private String description;
  
  public RequestDto(Integer id, UserDto initiator, String title, String description) {
    super(id);
    this.initiator = initiator;
    this.title = title;
    this.description = description;
  }
  
  public UserDto getInitiator() {
    return this.initiator;
  }
  
  public String getTitle() {
    return this.title;
  }
  
  public String getDescription() {
    return this.description;
  }

  @Override
  public String toJson() {
    return "{ id: "+this.getId()+", initiator: "+this.initiator.toJson()+", title: \""+this.getTitle()+"\", description: \""+this.getDescription()+"\"}";
  }
  
}