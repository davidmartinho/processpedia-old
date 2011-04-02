package pt.ist.processpedia.domain;

import pt.ist.processpedia.domain.exception.OldPasswordIsIncorrectDomainException;

public class User extends User_Base {

  /**
   * Creates a new user.
   */
  public User(String name, String email, String passwordHash) {
    setName(name);
    setEmail(email);
    setPasswordHash(passwordHash);
  }
  
  /**
   * Changes the user's password.
   * @ throws OldPasswordIsIncorrectDomainException If the provided old password does not match the current one.
   */
  public void changePassword(String oldPasswordHash, String newPasswordHash) throws OldPasswordIsIncorrectDomainException {
    if(getPasswordHash().equals(oldPasswordHash)) {
      setPasswordHash(newPasswordHash);
    } else {
      throw new OldPasswordIsIncorrectDomainException();
    }
  }

}
