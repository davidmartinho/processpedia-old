package pt.ist.processpedia.service.dto;

public class ProcessDto extends Dto {
  
  String title;
  Boolean isOpen;
  
  public ProcessDto(Integer id, String title, Boolean isOpen) {
    super(id);
    this.title = title;
    this.isOpen = isOpen;
  }
  
  public String getTitle() {
    return this.title;
  }

  public Boolean isOpen() {
    return isOpen;
  }
  
}