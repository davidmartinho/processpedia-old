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

package pt.ist.processpedia.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import pt.ist.fenixframework.Config;

public class ConfigLoader {

  /**
   * Generates a Config object containing the properties defined in a relative path-based properties file, required to initialize the FenixFramework.
   * @param relativePathFileName The relative path name where the property file defining the config properties exists.
   * @return The respective Config object to initialize the FenixFramework.
   */
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
