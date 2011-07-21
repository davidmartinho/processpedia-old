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

public class Comment extends Comment_Base {

  /**
   * Creates a new comment.
   * @param author the user authoring the comment
   * @param commentText the text of the comment
   */
  public Comment(User author, String commentText) {
    this(author, commentText, new DateTime());
  }

  /**
   * Creates a new comment at a particular timestamp
   * @param author the user authoring the comment
   * @param commentText the text of the comment
   * @param creationTimestamp the timestamp at which the comment is created
   */
  public Comment(User author, String commentText, DateTime creationTimestamp) {
    setAuthor(author);
    setCommentText(commentText);
    setCreationTimestamp(creationTimestamp);
  }

  /**
   * Associates a new reply to the comment.
   * @param author the user authoring the reply
   * @param commentText the text of the reply
   */
  public void reply(User author, String commentText) {
    this.addReply(new Comment(author, commentText));
  }

  /**
   * Associates a new reply to the comment at a particular timestamp
   * @param author the user authoring the reply
   * @param commentText the text of the reply
   * @param creationTimestamp the timestamp at which the comment is created
   */
  public void reply(User author, String commentText, DateTime creationTimestamp) {
    this.addReply(new Comment(author, commentText, creationTimestamp));
  }

}