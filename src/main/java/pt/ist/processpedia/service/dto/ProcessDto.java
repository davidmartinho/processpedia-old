package pt.ist.processpedia.service.dto;

public class ProcessDto extends Dto {
  
  String title;
  
  public ProcessDto(Integer id, String title) {
    super(id);
    this.title = title;
  }
  
  public String getTitle() {
    return this.title;
  }

  @Override
  public String toJson() {
    return "{ id: "+this.getId()+", title: \""+this.getTitle()+"\"}";
  }
}