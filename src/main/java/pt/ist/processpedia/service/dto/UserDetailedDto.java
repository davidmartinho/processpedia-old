/**
 * Copyright (c) 2011. Instituto Superior Técnico.
 *
 * Author: David Martinho (davidmartinho@ist.utl.pt)
 **/

package pt.ist.processpedia.service.dto;

public class UserDetailedDto extends UserDto {

 private String email;

 public UserDetailedDto(Integer id, String name, String email) {
   super(id, name);
   this.email = email;
 }

 public String getEmail() {
   return this.email;
 }

}
