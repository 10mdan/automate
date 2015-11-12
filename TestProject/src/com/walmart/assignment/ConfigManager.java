package com.walmart.assignment;


import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class ConfigManager {
  protected static ConcurrentHashMap<String, String> _config = null;
  private static ConfigManager _instance = null;

  protected ConfigManager() {
    // Exists only to defeat instantiation.
 }

  public static ConfigManager getInstance() {
    if (_instance == null) {
       System.out.println("Creating first instace of Config class");
       _instance = new ConfigManager();
       _instance.getDefaultConf();
    }

    return _instance;
 }

  public ConcurrentHashMap<String, String> getDefaultConf() {

    String filename = "config.properties";
    _config = loadPropertiesFile(filename, true);

    return _config;
  }

  private ConcurrentHashMap<String, String> loadPropertiesFile(String filename, boolean b) {
    Properties prop = new Properties();
    InputStream input = null;
    ConcurrentHashMap<String, String> propertiesHash = new ConcurrentHashMap<String, String>();

    try {

      input = getClass().getClassLoader().getResourceAsStream(filename);
      if (input == null) {
        System.out.println("rerouce not found");
        return null;

      }

      //Loads the config.properties file
      prop.load(input);

      //Parsing the properties file and creating a hash of property and its value.
      Enumeration<?> e = prop.propertyNames();
      while (e.hasMoreElements()) {
        String key =  (String) e.nextElement();
        String value = System.getProperty(key) != null ? System.getProperty(key) : prop.getProperty(key);
        propertiesHash.put(key, value);
      }

    } catch (IOException ex) {
      ex.printStackTrace();
    } finally {
      //This will close the stream after reading/loading the properties file
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return propertiesHash;
  }

  public String getProperty(String key) {

    return _config.get(key);

  }

}
