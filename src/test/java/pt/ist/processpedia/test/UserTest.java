package pt.ist.processpedia.test;

import junit.framework.TestCase;

import pt.ist.fenixframework.Config;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.fenixframework.pstm.Transaction;

import pt.ist.processpedia.domain.Processpedia;
import pt.ist.processpedia.domain.User;

import pt.ist.processpedia.domain.exception.EmailAlreadyRegisteredDomainException;

public class UserTest extends TestCase {

  public void setUp() {
    if(FenixFramework.getConfig()==null) {
      FenixFramework.initialize(new Config() {{
        dbAlias="test-db";
        domainModelPath="/processpedia.dml";
        repositoryType=RepositoryType.BERKELEYDB;
        rootClass=Processpedia.class;
      }});
    }
    Transaction.begin();
  }
  
  protected void tearDown() {
    Transaction.abort();
  }
  
  public void testUserNormalCreation() {
    Processpedia processpedia = FenixFramework.getRoot();
    User d1 = processpedia.createNewUser("David Martinho", "davidmartinho@gmail.com", "asdf43rf3223rd2323ef2");
    assertEquals(d1,processpedia.getUserByEmail("davidmartinho@gmail.com"));
  }
  
  public void testUserCreationWithAlreadyRegisteredEmail() {
    Processpedia processpedia = (Processpedia)FenixFramework.getRoot();
    Boolean exceptionThrown = false;
    try {
      User d1 = processpedia.createNewUser("David Martinho", "davidmartinho@gmail.com", "asdf43rf3223rd2323ef2");
      User d2 = processpedia.createNewUser("David Martinho", "davidmartinho@gmail.com", "asdf43rf3223rd2323ef2");
    } catch(EmailAlreadyRegisteredDomainException e) {
      exceptionThrown = true;
    }
    assertTrue(exceptionThrown);
  }

}