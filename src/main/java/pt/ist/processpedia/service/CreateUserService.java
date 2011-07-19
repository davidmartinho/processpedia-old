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

package pt.ist.processpedia.service;

import pt.ist.processpedia.domain.Processpedia;
import pt.ist.processpedia.domain.User;
import pt.ist.processpedia.domain.exception.EmailAlreadyRegisteredDomainException;
import pt.ist.processpedia.service.dto.UserDetailedDto;
import pt.ist.processpedia.service.dto.UserDto;
import pt.ist.processpedia.service.exception.EmailAlreadyRegisteredServiceException;

public class CreateUserService extends ProcesspediaService<UserDto> {

  private final String name;
  private final String email;
  private final String passwordHash;

  public CreateUserService(String name, String email, String passwordHash) {
    this.name = name;
    this.email = email;
    this.passwordHash = passwordHash;
  }

  @Override
  public UserDto dispatch() throws EmailAlreadyRegisteredServiceException {
    Processpedia processpedia = getProcesspedia();
    User user = null;
    try {
      user = processpedia.createNewUser(name, email, passwordHash);
    } catch(EmailAlreadyRegisteredDomainException e) {
      user = e.getUser();
      UserDetailedDto userDto = new UserDetailedDto(user.getId(), user.getName(), user.getEmail());
      throw new EmailAlreadyRegisteredServiceException(userDto);
    }
    return new UserDetailedDto(user.getId(), user.getName(), user.getEmail());
  }

}