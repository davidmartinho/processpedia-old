/**
 * Copyright (c) 2011. Instituto Superior Técnico.
 *
 * Author: David Martinho (davidmartinho@ist.utl.pt)
 **/

package pt.ist.processpedia.service.dto;

public class RequestDto extends Dto {

  private String title;
  private String description;
  private UserDto initiator;

  /**
   * Create a new Request DTO containing data about the request's id, title, description and the dto of its initiator.
   * @param id The id of the request.
   * @param title The title of the request.
   * @param description The description of the request.
   * @param initiator The User dto of the request initiator.
   */
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