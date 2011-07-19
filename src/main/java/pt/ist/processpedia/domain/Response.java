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

public class Response extends Response_Base {

  /**
   * Creates a new response to a commitment.
   * @param creator the author of the response
   * @param commitment the commitment to which the user is responding
   */
  public Response(User creator, Commitment commitment) {
    setCreator(creator);
    setCommitment(commitment);
    setState(ResponseState.CONSTRUCTION);
  }

  /**
   * Creeates an improved response relatively to a previously existing one.
   * @param creator the author of the response
   * @param commitment the commitment to which the user is responding
   * @param previousResponse the response that is being improved
   */
  public Response(User creator, Commitment commitment, Response previousResponse) {
    this(creator, commitment);
    setPreviousResponse(this);
  }

}