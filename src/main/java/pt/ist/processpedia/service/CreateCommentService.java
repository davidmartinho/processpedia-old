/**
 * Copyright (c) 2011. Instituto Superior Técnico.
 *
 * Author: David Martinho (davidmartinho@ist.utl.pt)
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
