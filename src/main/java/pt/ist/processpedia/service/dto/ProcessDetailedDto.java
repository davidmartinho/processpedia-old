/**
 * Copyright (c) 2011. Instituto Superior Técnico.
 *
 * Author: David Martinho (davidmartinho@ist.utl.pt)
 **/

package pt.ist.processpedia.service.dto;

public class ProcessDetailedDto extends ProcessDto {

  private UserDto creatorDto;

  public ProcessDetailedDto(Integer id, String title, Boolean isOpen, UserDto creatorDto) {
    super(id, title, isOpen);
    this.creatorDto = creatorDto;
  }

  public UserDto getCreatorDto() {
    return this.creatorDto;
  }

}
