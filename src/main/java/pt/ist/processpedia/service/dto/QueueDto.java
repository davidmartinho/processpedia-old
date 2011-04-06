/**
 * Copyright (c) 2011. Instituto Superior Técnico.
 *
 * Author: David Martinho (davidmartinho@ist.utl.pt)
 **/

package pt.ist.processpedia.service.dto;

/**
 *
 */
public class QueueDto extends Dto {

  private String name;
  
  public QueueDto(Integer id, String name) {
    super(id);
    this.name = name;
  }
  
  public String getName() {
    return this.name;
  }
  
}
