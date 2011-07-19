package pt.ist.processpedia.domain;

import org.joda.time.DateTime;

public class Commitment extends Commitment_Base {

  /**
   * Creates a new commitment from a user to a particular response.
   * @param user the user commiting to the request
   * @param request the request to which the user is commiting into
   * @param commitmentTimestamp the timestamp for when the commitment was made
   */
  public Commitment(User user, Request request, DateTime commitmentTimestamp) {
    setUser(user);
    setRequest(request);
    setCommitmentTimestamp(commitmentTimestamp);
  }

}