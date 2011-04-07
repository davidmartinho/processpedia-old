/**
 * Copyright (c) 2011. Instituto Superior Técnico.
 *
 * Author: David Martinho (davidmartinho@ist.utl.pt)
 **/

package pt.ist.processpedia.service.dto;

public class CommentDto extends Dto {

  private String commentText;
  private UserDto authorDto;

  public CommentDto(Integer id, String commentText, UserDto authorDto) {
    super(id);
    this.commentText = commentText;
    this.authorDto = authorDto;
  }

  public String getCommentText() {
    return this.commentText;
  }

  public UserDto getAuthorDto() {
    return this.authorDto;
  }

}
