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

package pt.ist.processpedia.domain;

import pt.ist.processpedia.domain.exception.OldPasswordIsIncorrectDomainException;
import pt.ist.processpedia.domain.exception.UserIsNotExecutingParentRequestDomainException;
import pt.ist.processpedia.domain.exception.UserIsNotRequestInitiatorDomainException;

import java.util.Set;

public class User extends User_Base {

  /**
   * Creates a new user.
   * @param name The name of the user.
   * @param email The email address of the user.
   * @param passwordHash The hash of the user's password.
   */
  public User(String name, String email, String passwordHash) {
    setName(name);
    setEmail(email);
    setPasswordHash(passwordHash);
  }
  
  /**
   * Changes the user's password.
   * @throws OldPasswordIsIncorrectDomainException If the provided old password does not match the current one.
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
   * @param newAvatarUrl The url where the new avatar is located.
   */
  public void changeAvatarUrl(String newAvatarUrl) {
    setAvatarUrl(newAvatarUrl);
  }
  
  /**
   * Checks if the user is equal to the provided user.
   * @param user The comparing user.
   * @return True if users have the same id, false otherwise.
   */
  public Boolean equals(User user) {
    return this.getId().equals(user.getId());
  }


  public void unpublishRequest(Request request) throws UserIsNotRequestInitiatorDomainException {
    if(!request.isInitiator(this)) {
      throw new UserIsNotRequestInitiatorDomainException(this, request);
    }
    for(Queue queue : request.getPublishedQueue()) {
      queue.removePublishedRequest(request);
    }
  }
}
