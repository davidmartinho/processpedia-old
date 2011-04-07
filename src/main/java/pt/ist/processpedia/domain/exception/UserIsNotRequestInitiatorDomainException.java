/**
 * Copyright (c) 2011. Instituto Superior Técnico.
 *
 * Author: David Martinho (davidmartinho@ist.utl.pt)
 **/

package pt.ist.processpedia.domain.exception;

import pt.ist.processpedia.domain.Request;
import pt.ist.processpedia.domain.User;

public class UserIsNotRequestInitiatorDomainException extends ProcesspediaDomainException {

  private User user;

  private Request request;

  public UserIsNotRequestInitiatorDomainException(User user, Request request) {
    this.user = user;
    this.request = request;
  }

  public User getUser() {
    return user;
  }

  public Request getRequest() {
    return request;
  }
  
}
