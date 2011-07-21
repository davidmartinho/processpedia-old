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

public class Commitment extends Commitment_Base {

  /**
   * Creates a new commitment from a user to a particular response.
   * @param commitmentId the identifier of the new commitment
   * @param user the user committing to the request
   * @param request the request to which the user is committing into
   * @param commitmentTimestamp the timestamp for when the commitment was made
   */
  public Commitment(String commitmentId, User user, Request request, DateTime commitmentTimestamp) {
    setUser(user);
    setRequest(request);
    setCommitmentTimestamp(commitmentTimestamp);
  }

}