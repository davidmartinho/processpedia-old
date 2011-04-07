/**
 * Copyright (c) 2011. Instituto Superior Técnico.
 *
 * Author: David Martinho (davidmartinho@ist.utl.pt)
 **/

package pt.ist.processpedia.service.dto;

import java.util.Set;


public class RequestDetailedDto extends RequestDto {

  private Set<CommentDto> commentDtoSet;

  private Set<RequestDto> childRequestDtoSet;

  /**
   * Create a new Request detailed DTO containing data about the request's id, title, description, the dto of its initiator, and the set of associated comments.
   * @param id The id of the request.
   * @param title The title of the request.
   * @param description The description of the request.
   * @param initiator The User dto of the request initiator.
   * @param commentDtoSet The set of comment DTOs that are associated to the comment.
   * @param childRequestDtoSet The set of child request DTOs made in the context of the request.
   */
  public RequestDetailedDto(Integer id, String title, String description, UserDto initiator, Set<CommentDto> commentDtoSet, Set<RequestDto> childRequestDtoSet) {
    super(id,title,description,initiator);
    this.commentDtoSet = commentDtoSet;
    this.childRequestDtoSet = childRequestDtoSet;
  }

  public Set<CommentDto> getCommentDtoSet() {
    return this.commentDtoSet;
  }

  public Set<RequestDto> getChildRequestDtoSet() {
    return this.childRequestDtoSet;
  }

}
