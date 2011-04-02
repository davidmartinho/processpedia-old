package pt.ist.processpedia.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import pt.ist.fenixframework.Config;
import pt.ist.fenixframework.FenixFramework;

public class ConfigLoader {
  
  public static Config loadFromProperties(String relativePathFileName) {
     final Properties props = new Properties();
      try {
        props.load(new FileInputStream(relativePathFileName));
      } catch(IOException ioe) {
        System.out.println("Problems while trying to read property file at "+relativePathFileName);
      }
      try {
        return new Config() {{
          domainModelPath = props.getProperty("persistence.domain.model.path");
          dbAlias = props.getProperty("persistence.db.alias");
          dbUsername= props.getProperty("persistence.db.username");
          dbPassword = props.getProperty("persistence.db.password");
          repositoryType = RepositoryType.BERKELEYDB;
          rootClass = Class.forName(props.getProperty("persistence.root.class"));
          updateRepositoryStructureIfNeeded = true;
        }};
      } catch (ClassNotFoundException cnfe) {
        if(props.getProperty("persistence.root.class").equals("")) {
          System.out.println("The persistence.root.class property was not defined");
        }
        System.out.println("The class "+props.getProperty("persistence.root.class")+" was not found.");
      }
      return null;
  }

}
