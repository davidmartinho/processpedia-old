/**
 * Copyright (c) 2011. Instituto Superior Técnico.
 *
 * Author: David Martinho (davidmartinho@ist.utl.pt)
 **/

package pt.ist.processpedia.service.dto;

/**
 * This class provides the structure of a DTO containing data of a Process domain object.
 */
public class ProcessDto extends Dto {
  
  String title;
  Boolean isOpen;

  /**
   * Creates a new <code>ProcessDto</code> containing the process's id, title and open-status.
   * @param id The id of the process.
   * @param title The title of the process.
   * @param isOpen The boolean representative value of the process open state.
   */
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