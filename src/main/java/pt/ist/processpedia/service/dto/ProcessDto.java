/**
 * Processpedia
 * Copyright (C) 2011 ESW Software Engineering Group
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
  public ProcessDto(String id, String title, Boolean isOpen) {
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