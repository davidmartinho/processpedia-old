package pt.ist.processpedia.domain.exception;

import pt.ist.processpedia.domain.Request;

public class RequestAlreadyClaimedDomainException extends ProcesspediaDomainException {

  private static final long serialVersionUID = 1L;

  private Request request;

  public RequestAlreadyClaimedDomainException(Request request) {
    this.request = request;
  }
  
  public Request getRequest() {
    return this.request;
  }

}