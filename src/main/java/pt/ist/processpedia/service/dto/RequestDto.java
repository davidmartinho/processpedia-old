package pt.ist.processpedia.service.dto;

public class RequestDto {
  
  private UserDto initiator;
  private String title;
  private String description;
  
  public RequestDto(UserDto initiator, String title, String description) {
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
  
}