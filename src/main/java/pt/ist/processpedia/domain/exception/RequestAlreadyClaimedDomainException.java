package pt.ist.processpedia.domain.exception;

import pt.ist.processpedia.domain.Request;

public class RequestAlreadyClaimedDomainException extends ProcesspediaDomainException {

  private Request request;

  public RequestAlreadyClaimedDomainException(Request request) {
    this.request = request;
  }
  
  public Request getRequest() {
    return this.request;
  }

}