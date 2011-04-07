/**
 * Copyright (c) 2011. Instituto Superior Técnico.
 *
 * Author: David Martinho (davidmartinho@ist.utl.pt)
 **/

package pt.ist.processpedia.domain;

import org.joda.time.DateTime;

public class Comment extends Comment_Base {

  /**
   * Creates a new comment.
   * @param author The user authoring the comment.
   * @param commentText The text of the comment..
   */
  public Comment(User author, String commentText) {
    setAuthor(author);
    setCommentText(commentText);
    setCreationTimestamp(new DateTime());
  }

}
