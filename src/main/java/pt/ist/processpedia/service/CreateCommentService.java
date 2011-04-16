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

package pt.ist.processpedia.service;

import pt.ist.processpedia.domain.Comment;
import pt.ist.processpedia.domain.Processpedia;
import pt.ist.processpedia.domain.Request;
import pt.ist.processpedia.domain.User;
import pt.ist.processpedia.service.dto.CommentDto;
import pt.ist.processpedia.service.dto.DtoMapper;
import pt.ist.processpedia.service.exception.ProcesspediaServiceException;
import pt.ist.processpedia.service.exception.RequestIdNotFoundServiceException;
import pt.ist.processpedia.service.exception.UserIdNotFoundServiceException;

public class CreateCommentService extends ProcesspediaService<CommentDto> {

  private Integer userId;
  private Integer requestId;
  private String commentText;

  public CreateCommentService(Integer userId, Integer requestId, String commentText) {
    this.userId = userId;
    this.requestId = requestId;
    this.commentText = commentText;
  }

  @Override
  public CommentDto dispatch() throws ProcesspediaServiceException {
    Processpedia processpedia = getProcesspedia();
    User author = processpedia.getUserById(this.userId);
    if(author == null) {
      throw new UserIdNotFoundServiceException(this.userId);
    }
    Request request = processpedia.getRequestById(this.requestId);
    if(request == null) {
      throw new RequestIdNotFoundServiceException(this.requestId);
    }
    Comment comment = request.createComment(author, this.commentText);
    return DtoMapper.createCommentDtoFromComment(comment);
  }
}
