package com.walmart.assignment;


import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class FileUtils extends org.apache.commons.io.FileUtils{


  public static String getResourcePath(String resourceName, boolean extractFromJar, boolean setExecutable) throws Exception {
    URL location = FileUtils.class.getResource(resourceName);
    if (location == null) {
      throw new Exception("Resource not found: " + resourceName);
    }
    try {
      File file;
      if (extractFromJar && location.getProtocol().equals("jar")) {
        int index = resourceName.lastIndexOf("/");
        String suffix = index != -1 ? resourceName.substring(index + 1) : resourceName;
        file = copyUrlToTempFile(location, "resource", suffix);
        if (setExecutable) {
          file.setExecutable(true);
        }
      } else {
        file = new File(location.toURI());
      }
      return file.getAbsolutePath();
    } catch (URISyntaxException e) {
      throw new Exception("Error getting resource path: " + e.getMessage());
    }
  }

  private static File copyUrlToTempFile(URL url, String prefix, String suffix) throws Exception {
    try {
      File file = File.createTempFile(prefix, suffix);
      file.deleteOnExit();
      copyURLToFile(url, file);
      return file;
    } catch (IOException e) {
      throw new Exception("Error copying URL to temporary file: " + e.getMessage());
    }
  }
}

