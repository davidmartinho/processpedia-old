/*
 * Copyright 2011 ESW Software Engineering Group
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
 */

package pt.ist.processpedia.domain;

public class Id {

  private final Long id;

  /**
   * Creates a new identifier.
   * @param id the string representation of the identifier
   */
  public Id(String id) {
    this.id = new Long(id);
  }

  /**
   * Creates a new identifier.
   * @param id the long representation of the identifier
   */
  public Id(Long id) {
    this.id = id;
  }

  /**
   * Obtains the next id.
   * @return the next identifier
   */
  public Id getNextId() {
    return new Id(id+1);
  }
  
  public String toString() {
    return id.toString();
  }

}