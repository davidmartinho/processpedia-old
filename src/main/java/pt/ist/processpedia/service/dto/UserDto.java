/**
 * Copyright (c) 2011. Instituto Superior Técnico.
 *
 * Author: David Martinho (davidmartinho@ist.utl.pt)
 **/

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
  
}