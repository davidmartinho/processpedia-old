package pt.ist.processpedia.service.dto;

public abstract class Dto {

  private Integer id;

  public Dto(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return this.id;
  }

  public abstract String toJson();
  
}