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

public class CommentDto extends Dto {

  private String commentText;
  private UserDto authorDto;

  public CommentDto(String id, String commentText, UserDto authorDto) {
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
