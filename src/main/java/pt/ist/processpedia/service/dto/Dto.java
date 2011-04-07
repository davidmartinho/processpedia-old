/**
 * Copyright (c) 2011. Instituto Superior Técnico.
 *
 * Author: David Martinho (davidmartinho@ist.utl.pt)
 **/

package pt.ist.processpedia.service.dto;

public abstract class Dto {

  private Integer id;

  public Dto(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return this.id;
  }
  
}