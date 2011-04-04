package pt.ist.processpedia.service.dto;

import pt.ist.processpedia.domain.Request;
import pt.ist.processpedia.domain.User;
import pt.ist.processpedia.domain.Process;
import pt.ist.processpedia.domain.Queue;

import java.util.Set;
import java.util.HashSet;

public class DtoMapper {

  public static UserDto createUserDtoFromUser(User user) {
    return new UserDto(user.getId(), user.getName());
  }

  public static ProcessDto createProcessDtoFromProcess(Process process) {
    return new ProcessDto(process.getId(), process.getTitle(), process.isOpen());
  }

  public static QueueDto createQueueDtoFromQueue(Queue queue) {
    return new QueueDto(queue.getId(), queue.getName());
  }

  public static RequestDto createRequestDtoFromRequest(Request request) {
    return new RequestDto(request.getId(), request.getTitle(), request.getDescription(), createUserDtoFromUser(request.getInitiator()));
  }

  public static QueueDetailedDto createQueueDetailedDtoFromQueue(Queue queue) {
    Set<QueueDetailedDto> queueDetailedDtoSet = new HashSet<QueueDetailedDto>();
    for(Queue childQueue : queue.getChildQueueSet()) {
      queueDetailedDtoSet.add(createQueueDetailedDtoFromQueue(childQueue));
    }
    return new QueueDetailedDto(queue.getId(), queue.getName(), queueDetailedDtoSet);
  }
}
