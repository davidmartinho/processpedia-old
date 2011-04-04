package pt.ist.processpedia.service.dto;

public class RequestDto extends Dto {

  private String title;
  private String description;
  private UserDto initiator;

  public RequestDto(Integer id, String title, String description, UserDto initiator) {
    super(id);
    this.initiator = initiator;
    this.title = title;
    this.description = description;
  }
  
  public String getTitle() {
    return this.title;
  }
  
  public String getDescription() {
    return this.description;
  }
  
  public UserDto getInitiator() {
    return this.initiator;
  }

}