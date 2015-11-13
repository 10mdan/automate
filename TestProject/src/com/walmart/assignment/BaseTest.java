package com.walmart.assignment;


import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
  private Map<String, Object> _customCapabilities;

  @BeforeMethod(alwaysRun = true)
  public final void mandatoryBeforeTestMethod(Method testMethod) throws Exception {
    try {
      SessionManager.startWebSession(testMethod, _customCapabilities);
    } catch (Exception e) {
      throw new Exception("Unable to create Session due to " + e.getMessage());
    }
  }

  @AfterMethod(alwaysRun = true)
  public final void mandatoryAfterTestMethod() throws Exception
  {
     SessionManager.killSession();
  }
}
