package pt.ist.processpedia.test;

import java.io.File;

import junit.framework.TestCase;

import pt.ist.fenixframework.Config;
import pt.ist.fenixframework.FenixFramework;
import jvstm.Transaction;

import pt.ist.processpedia.domain.Processpedia;
import pt.ist.processpedia.domain.Process;
import pt.ist.processpedia.domain.User;

import pt.ist.processpedia.service.exception.EmailAlreadyRegisteredException;

public class UserTest extends TestCase {
  
  private final static String testDbDirectory = "/tmp/db";
  
  public void setUp() {
    deleteDir(new File(testDbDirectory));
    if(FenixFramework.getConfig()==null) {
      FenixFramework.initialize(new Config() {{
        dbAlias=testDbDirectory;
        domainModelPath="src/main/dml/processpedia.dml";
        repositoryType=RepositoryType.BERKELEYDB;
        rootClass=Processpedia.class;
      }});
    }
  }
  
  protected void tearDown() {
    deleteDir(new File(testDbDirectory));
  }
  
  public void testUserNormalCreation() {
    Transaction.begin();
    Processpedia processpedia = FenixFramework.getRoot();
    User d1 = processpedia.createNewUser("David Martinho", "davidmartinho@gmail.com", "asdf43rf3223rd2323ef2");
    assertEquals(d1,processpedia.getUserByEmail("davidmartinho@gmail.com"));
    Transaction.commit();
  }
  
  public void testUserCreationWithAlreadyRegisteredEmail() {
    Transaction.begin();
    Processpedia processpedia = FenixFramework.getRoot();
    Boolean exceptionThrown = false;
    try {
      User d1 = processpedia.createNewUser("David Martinho", "davidmartinho@gmail.com", "asdf43rf3223rd2323ef2");
      User d2 = processpedia.createNewUser("David Martinho", "davidmartinho@gmail.com", "asdf43rf3223rd2323ef2");
    } catch(EmailAlreadyRegisteredException e) {
      exceptionThrown = true;
    }
    assertTrue(exceptionThrown);
    Transaction.commit();
  }
  
  public static boolean deleteDir(File dir) {
    if (dir.isDirectory()) {
      String[] children = dir.list();
      for (int i=0; i<children.length; i++) {
        boolean success = deleteDir(new File(dir, children[i]));
        if (!success) {
          return false;
        }
      }
    }
    return dir.delete();
  }

}