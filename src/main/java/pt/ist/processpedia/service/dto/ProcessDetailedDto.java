package pt.ist.processpedia.service.dto;

public class ProcessDetailedDto extends ProcessDto {

  private UserDto creatorDto;

  public ProcessDetailedDto(Integer id, String title, UserDto creatorDto) {
    super(id, title);
    this.creatorDto = creatorDto;
  }

  public UserDto getCreatorDto() {
    return this.creatorDto;
  }

}
