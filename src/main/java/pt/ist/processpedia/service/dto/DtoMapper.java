/**
 * Copyright (c) 2011. Instituto Superior Técnico.
 *
 * Author: David Martinho (davidmartinho@ist.utl.pt)
 **/

package pt.ist.processpedia.service.dto;

import pt.ist.processpedia.domain.*;
import pt.ist.processpedia.domain.Process;

import java.util.Set;
import java.util.HashSet;

/**
 * This class provides a set of static methods to map domain objects to their correspondent different DTOs.
 */
public class DtoMapper {

  /**
   * Generates a <code>UserDto</code> from a given user domain object.
   * @param user The user domain object from which the dto will be created.
   * @return The dto containing data from the user provided as argument.
   */
  public static UserDto createUserDtoFromUser(User user) {
    return new UserDto(user.getId(), user.getName());
  }

  /**
   * Generates a <code>ProcessDto</code> containing the process id, title and open status.
   * @param process The process domain object from which the dto will be created.
   * @return The dto containing data from the process provided as argument.
   */
  public static ProcessDto createProcessDtoFromProcess(Process process) {
    return new ProcessDto(process.getId(), process.getTitle(), process.isOpen());
  }

  /**
   * Generates a <code>QueueDto</code> containing the queue's id and name.
   * @param queue The queue domain object from which the dto will be created.
   * @return The dto containing data from the queue provided as argument.
   */
  public static QueueDto createQueueDtoFromQueue(Queue queue) {
    return new QueueDto(queue.getId(), queue.getName());
  }

  /**
   * Generates a <code>RequestDto</code> containing the request's id, title, description and the dto of its initiator.
   * @param request The request domain object from which the dto will be created.
   * @return The dto containing data from the request provided as argument.
   */
  public static RequestDto createRequestDtoFromRequest(Request request) {
    return new RequestDto(request.getId(), request.getTitle(), request.getDescription(), createUserDtoFromUser(request.getInitiator()));
  }

  /**
   * Generates a <code>QueueDetailedDto</code> containing the queue's id, name and a list of its child queue DTOs.
   * @param queue The queue domain object from which the dto will be created.
   * @return The dto containing data from the queue provided as argument.
   */
  public static QueueDetailedDto createQueueDetailedDtoFromQueue(Queue queue) {
    Set<QueueDetailedDto> queueDetailedDtoSet = new HashSet<QueueDetailedDto>();
    for(Queue childQueue : queue.getChildQueueSet()) {
      queueDetailedDtoSet.add(createQueueDetailedDtoFromQueue(childQueue));
    }
    return new QueueDetailedDto(queue.getId(), queue.getName(), queueDetailedDtoSet);
  }

  /**
   * Generates a <code>CommentDto</code> containing the comment's id, text and the dto of its author.
   * @param comment The comment domain object from which the dto will be created.
   * @return The dto containing data from the comment provided as argument.
   */
  public static CommentDto createCommentDtoFromComment(Comment comment) {
    return new CommentDto(comment.getId(), comment.getCommentText(), createUserDtoFromUser(comment.getAuthor()));
  }

  /**
   * Creates a set of request DTOs from a set of requests domain objects.
   * @param requestSet The set of request domain objects.
   * @return The corresponding set of request DTOs.
   */
  public static Set<RequestDto> createRequestDtoSetFromRequestSet(Set<Request> requestSet) {
    Set<RequestDto> requestDtoSet = new HashSet<RequestDto>();
    for(Request request : requestSet) {
      requestDtoSet.add(createRequestDtoFromRequest(request));
    }
    return requestDtoSet;
  }
  
}
