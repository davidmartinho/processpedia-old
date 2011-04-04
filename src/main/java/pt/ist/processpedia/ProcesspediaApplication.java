package pt.ist.processpedia;

import pt.ist.fenixframework.Config;
import pt.ist.fenixframework.FenixFramework;

import pt.ist.processpedia.service.*;
import pt.ist.processpedia.service.dto.RequestDto;
import pt.ist.processpedia.service.util.JSONMapper;
import pt.ist.processpedia.service.util.Mapper;

import pt.ist.processpedia.util.ConfigLoader;

import pt.ist.processpedia.service.dto.UserDto;
import pt.ist.processpedia.service.dto.ProcessDto;

public class ProcesspediaApplication {
  
  public static void main(String[] args) {
    Config config = ConfigLoader.loadFromProperties("src/main/config/persistence.properties");
    FenixFramework.initialize(config);
    doStuff();
  }
  
  public static void doStuff() {
    try {
      CreateUserService createUserService = new CreateUserService("David Martinho", "davidmartinho@gmail.com", "adfdad");
      createUserService.execute();

      CreateUserService createAnotherUserService = new CreateUserService("John Doe", "john.doe@email.com", "23w4234234234");
      createAnotherUserService.execute();

      CreateProcessService createProcessService = new CreateProcessService(1, "Master Thesis Management");
      createProcessService.execute();

      GetUserByIdService getUserByIdService = new GetUserByIdService(1);
      UserDto userDto = getUserByIdService.execute();

      GetProcessByIdService getProcessByIdService = new GetProcessByIdService(1, 1);
      ProcessDto processDto = getProcessByIdService.execute();

      Mapper mapper = new JSONMapper();

      System.out.println(mapper.fromUserDto(userDto));

      System.out.println(mapper.fromProcessDto(processDto));


      CreateNewRequestService createNewRequestService = new CreateNewRequestService(1,1,"Review Paper", "Please review this paper.");
      createNewRequestService.execute();

      GetRequestByIdService getRequestByIdService = new GetRequestByIdService(1,1);
      RequestDto requestDto = getRequestByIdService.execute();


      System.out.println(mapper.fromRequestDto(requestDto));

      

      


    } catch(Exception e) {
      e.printStackTrace();
    }

    
  }
  
}