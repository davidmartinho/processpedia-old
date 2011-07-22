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

import pt.ist.processpedia.domain.exception.CannotCommitToRequestDomainException;
import pt.ist.processpedia.domain.exception.MaxNumberCommitmentsExceededDomainException;

public class RequestMaxCommitmentsClaimingPolicy extends RequestMaxCommitmentsClaimingPolicy_Base {

  public RequestMaxCommitmentsClaimingPolicy(Integer numMaxCommitments) {
    setNumMaxCommitments(numMaxCommitments);
  }

  @Override
  public void validate(User claimer) throws CannotCommitToRequestDomainException {
    Request request = getRequest();
    Integer numMaxCommitments = getNumMaxCommitments();
    if(request.getCommitmentCount() >= numMaxCommitments) {
      throw new MaxNumberCommitmentsExceededDomainException(request, numMaxCommitments);
    }
  }

}