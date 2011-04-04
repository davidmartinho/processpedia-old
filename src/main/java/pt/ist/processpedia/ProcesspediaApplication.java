package pt.ist.processpedia;

import pt.ist.fenixframework.Config;
import pt.ist.fenixframework.FenixFramework;

import pt.ist.processpedia.service.CreateProcessService;
import pt.ist.processpedia.util.ConfigLoader;

import pt.ist.processpedia.domain.Processpedia;
import pt.ist.processpedia.domain.Process;
import pt.ist.processpedia.domain.User;
import pt.ist.processpedia.domain.Request;

import pt.ist.processpedia.service.CreateUserService;
import pt.ist.processpedia.service.GetUserByIdService;

import pt.ist.processpedia.service.dto.UserDto;

public class ProcesspediaApplication {
  
  public static void main(String[] args) {
    Config config = ConfigLoader.loadFromProperties("src/main/config/persistence.properties");
    FenixFramework.initialize(config);
    doStuff();
  }
  
  public static void doStuff() {
    try {
    CreateUserService service = new CreateUserService("David Martinho", "davidmartinho@gmai.com", "adfdad");
    service.execute();

    CreateProcessService createProcessService = new CreateProcessService(1, "Master Thesis Management");
    service.execute();
    
    GetUserByIdService s = new GetUserByIdService(1);
    UserDto dto = s.execute();

    GetProcessByIdService s2 = new GetProcess

    System.out.println(dto.toJson());


    } catch(Exception e) {
      
    }

    
  }
  
  
}