package pt.ist.processpedia.service.dto;

public class ProcessDto {
  
  Integer id;
  String title;
  
  public ProcessDto(Integer id, String title) {
    this.id = id;
    this.title = title;
  }
  
  public Integer getId() {
    return this.id;
  }
  
  public String getTitle() {
    return this.title;
  }
  
}