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

import pt.ist.processpedia.domain.exception.OldPasswordIsIncorrectDomainException;
import pt.ist.processpedia.domain.exception.UserIsNotExecutingParentRequestDomainException;
import pt.ist.processpedia.domain.exception.UserIsNotRequestInitiatorDomainException;

import java.util.Set;

public class User extends User_Base {

  /**
   * Creates a new user.
   * @param userId the identifier of the new user
   * @param name the name of the new user
   * @param email the email address of the new user
   * @param passwordHash the hash of the new user's password
   */
  public User(String userId, String name, String email, String passwordHash) {
    init(userId);
    setName(name);
    setEmail(email);
    setPasswordHash(passwordHash);
  }

  /**
   * Changes the user's password.
   * @param oldPasswordHash the old password's hash value
   * @param newPasswordHash the new password's hash value
   * @throws OldPasswordIsIncorrectDomainException when the old password provided does not match the current one
   */
  public void changePassword(String oldPasswordHash, String newPasswordHash) throws OldPasswordIsIncorrectDomainException {
    if(getPasswordHash().equals(oldPasswordHash)) {
      setPasswordHash(newPasswordHash);
    } else {
      throw new OldPasswordIsIncorrectDomainException(this);
    }
  }

  /**
   * Change the user's avatar url.
   * @param newAvatarUrl the url where the new avatar is located
   */
  public void changeAvatarUrl(String newAvatarUrl) {
    setAvatarUrl(newAvatarUrl);
  }

  /**
   * Checks if the user is equal to the provided user.
   * @param user the comparing user
   * @return true if users have the same id, false otherwise
   */
  public Boolean equals(User user) {
    return this.getId().equals(user.getId());
  }

  /**
   * Cancels an initiated request.
   * @param request the request to be cancelled
   * @throws UserIsNotRequestInitiatorDomainException when the user trying to cancel the request is not its initiator
   */
  public void unpublishRequest(Request request) throws UserIsNotRequestInitiatorDomainException {
    if(!request.isInitiator(this)) {
      throw new UserIsNotRequestInitiatorDomainException(this, request);
    }
    for(Queue queue : request.getPublishedQueue()) {
      queue.removePublishedRequest(request);
    }
  }

}