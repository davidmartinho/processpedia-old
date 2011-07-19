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

public class Request extends Request_Base {

  /**
   * Creates a new request.
   * @param initiator The user initiating the request.
   * @param title The title of the request.
   * @param description A more detailed description of the request rationale.
   */
  public Request(User initiator, String title, String description) {
    setInitiator(initiator);
    setTitle(title);
    setDescription(description);
    setState(RequestState.UNPUBLISHED);
    setCreationTimestamp(new DateTime());
  }

  /**
   * Creates a new sub-request in the context of a particular request.
   * @param initiator The user initiating the request.
   * @param title The title of the request.
   * @param description A more detailed description of the request rationale.
   * @param parentRequest The request in which this request is being created.
   */
  public Request(User initiator, String title, String description, Request parentRequest) {
    this(initiator, title, description);
    setParentRequest(parentRequest);
  }

  /**
   * Checks if a given user is either the initiator or the executor of the request or recursively in any nested request.
   * @param user The user to check direct or indirect involvement in the request.
   * @return True if the user is either the initiator or executor of the request or sub-request, false otherwise.
   */
  public boolean hasUserInvolved(User user) {
    if(isInitiator(user) || isExecutor(user)) {
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
   * Checks if a given user is the executor of the request.
   * @param user The user to be checked
   * @return True if the given user is the executor of the request, false otherwise.
   */
  public Boolean isExecutor(User user) {
    return hasExecutor() && getExecutor().equals(user);
  }

  /**
   * Creates a new comment and associates it to the request.
   * @param author The user authoring the comment.
   * @param commentText The text of the comment.
   * @return The created comment.
   */
  public Comment createComment(User author, String commentText) {
    Comment comment = new Comment(author, commentText);
    addComment(comment);
    return comment;
  }
  
}