/**
 * Processpedia
 * Copyright (C) 2011 ESW Software Engineering Group
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
