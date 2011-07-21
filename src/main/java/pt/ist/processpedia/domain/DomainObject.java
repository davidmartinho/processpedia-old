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

import pt.ist.fenixframework.FenixFramework;

public abstract class DomainObject extends DomainObject_Base {

  /**
   * Initializes a new domain object with a given id.
   * @param id the identifier of the new domain object
   */
  public void init(String id) {
    setId(id);
  }

  public void initialize() {
    String id = Processpedia.getIdFactory().reserveId(this.getClass());
    System.out.println("I've just created a "+getClass().getCanonicalName()+" with ID: "+id);
    setId(id);
  }

  public interface IdFactory {
    public String reserveId(Class domainClass);
  }

  public DomainObject() {
    initialize();
  }

}