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

import org.joda.time.DateTime;

import pt.ist.processpedia.domain.exception.RequestDeadlineMetDomainException;

public class RequestDeadlineClaimingPolicy extends RequestDeadlineClaimingPolicy_Base {

  public RequestDeadlineClaimingPolicy(DateTime deadlineTimestamp) {
    setDeadlineTimestamp(deadlineTimestamp);
  }

  @Override
  public void validate(User claimer) {
    DateTime now = new DateTime();
    if(now.isAfter(getDeadlineTimestamp())) {
      throw new RequestDeadlineMetDomainException(getRequest(), getDeadlineTimestamp());
    }
  }

}
