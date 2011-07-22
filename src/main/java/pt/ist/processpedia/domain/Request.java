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

import org.joda.time.DateTime;

import java.util.HashSet;
import java.util.Set;

public class Request extends Request_Base {

  /**
   * Creates a new unpublished request without a description in the current time.
   * @param initiator the user initiating the request
   * @param subject the subject of the request
   */
  public Request(User initiator, String subject) {
    this(initiator, subject, null, RequestState.UNPUBLISHED, new DateTime());
  }

  /**
   * Creates a new unpublished request with a description in the current time.
   * @param initiator the user initiating the request
   * @param subject the subject of the request
   */
  public Request(User initiator, String subject, String description) {
    this(initiator, subject, description, RequestState.UNPUBLISHED, new DateTime());
  }

  /**
   * Creates a new unpublished request with a description in the current time and in the context of a particular
   * request.
   * @param initiator the user initiating the request
   * @param subject the subject of the request
   * @param description the description of the request
   * @param parentRequest the request in which this request is created
   */
  public Request(User initiator, String subject, String description, Request parentRequest) {
    this(initiator, subject, description, RequestState.UNPUBLISHED, new DateTime(), parentRequest);
  }

  /**
   * Creates a new request.
   * @param initiator the user initiating the request
   * @param subject the subject of the request
   * @param description a detailed description of the request
   */
  public Request(User initiator, String subject, String description, RequestState state, DateTime creationTimestamp) {
    setInitiator(initiator);
    setSubject(subject);
    setDescription(description);
    setState(state);
    setCreationTimestamp(creationTimestamp);
  }

  /**
   * Creates a new unpublished sub-request with a description in the current time and in the context of a particular 
   * request.
   * @param initiator the user initiating the request
   * @param subject the subject of the request
   * @param description A more detailed description of the request
   * @param parentRequest the request in which this request is being created
   */
  public Request(User initiator, String subject, String description, RequestState state,
                 DateTime creationTimestamp, Request parentRequest) {
    this(initiator, subject, description, state, creationTimestamp);
    setParentRequest(parentRequest);
  }

  public void setSubject(String subject) {
    setSubjectTag(new Tag(subject));
  }

  public String getSubject() {
    return getSubjectTag().getKeyword();
  }

  public void setDescription(String description) {
    if(description!=null) {
      setDescriptionComment(new Comment(getInitiator(), description));
    }
  }

  public String getDescription() {
    return getDescriptionComment().getCommentText();
  }

  /**
   * Checks if a given user is either the initiator or the executor of the request or recursively in any nested 
   * request.
   * @param user The user to check direct or indirect involvement in the request.
   * @return True if the user is either the initiator or executor of the request or sub-request, false otherwise.
   */
  public boolean hasUserInvolved(User user) {
    if(isInitiator(user) || hasCommitmentFrom(user)) {
      return true;
    } else {
      for(Request childRequest : getChildRequestSet()) {
        if(childRequest.hasUserInvolved(user)) {
          return true;
        }
      }
      return false;
    }
  }

  /**
   * Checks if a given user is the initiator of the request.
   * @param user The user to be checked
   * @return True if the given user is the initiator of the request, false otherwise.
   */
  public boolean isInitiator(User user) {
    return getInitiator().equals(user);
  }

  /**
   * Checks if a given user has made a commitment to execute the request.
   * @param user the user to be checked
   * @return true if the given user has committed to the request, false otherwise
   */
  public Boolean hasCommitmentFrom(User user) {
    for(Commitment commitment : getCommitmentSet()) {
      if(commitment.getUser().equals(user)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Creates a new comment and associates it to the request.
   * @param author The user authoring the comment.
   * @param commentText The text of the comment.
   * @return The created comment.
   */
  public Comment createComment(User author, String commentText) {
    Comment comment = new Comment(author, commentText);
    getDescriptionComment().addReply(comment);
    return comment;
  }

  /**
   * Computes the set of users participating in the request, considering the initiator and users committing to the
   * request, and recursively to the sub-requests.
   * @return the set of users participating in the request
   */
  public Set<User> getParticipantSet() {
    Set<User> participantSet = new HashSet<User>();
    participantSet.add(getInitiator());
    for(Commitment commitment : getCommitmentSet()) {
      participantSet.add(commitment.getUser());
    }
    for(Request subRequest : getChildRequestSet()) {
      participantSet.addAll(subRequest.getParticipantSet());
    }
    return participantSet;
  }

}