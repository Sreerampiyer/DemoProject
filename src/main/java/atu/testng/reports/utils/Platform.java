/* Decompiler 25ms, total 671ms, lines 97 */
package atu.testng.reports.utils;

import java.net.InetAddress;

import java.net.UnknownHostException;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.internal.BuildInfo;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Platform {
  // private static BuildInfo driverInfo = new BuildInfo();
   public static final String DRIVER_VERSION;
   public static final String DRIVER_REVISION;
   public static final String USER;
   public static final String OS;
   public static final String OS_ARCH;
   public static final String OS_VERSION;
   public static final String JAVA_VERSION;
   public static String BROWSER_NAME;
   public static String BROWSER_VERSION;
   public static String BROWSER_NAME_PROP;
   public static String BROWSER_VERSION_PROP;

   public static String getHostName() {
      try {
         return InetAddress.getLocalHost().getHostName();
      } catch (UnknownHostException var1) {
         return "Unknown";
      }
   }

   public static void prepareDetails(WebDriver var0) {
      BROWSER_VERSION = "";
      BROWSER_NAME = "UnKnown";
      if (var0 == null) {
         BROWSER_VERSION = "";
         BROWSER_NAME = "UnKnown";
      } else {
         try {
            String var1 = (String)((JavascriptExecutor)var0).executeScript("return navigator.userAgent;", new Object[0]);
            if (var1.contains("MSIE")) {
               BROWSER_VERSION = var1.substring(var1.indexOf("MSIE") + 5, var1.indexOf("Windows NT") - 2);
               BROWSER_NAME = "Internet Explorer";
            } else if (var1.contains("Firefox/")) {
               BROWSER_VERSION = var1.substring(var1.indexOf("Firefox/") + 8);
               BROWSER_NAME = "Mozilla Firefox";
            } else if (var1.contains("Chrome/")) {
               BROWSER_VERSION = var1.substring(var1.indexOf("Chrome/") + 7, var1.lastIndexOf("Safari/"));
               BROWSER_NAME = "Google Chrome";
            } else if (var1.contains("AppleWebKit") && var1.contains("Version/")) {
               BROWSER_VERSION = var1.substring(var1.indexOf("Version/") + 8, var1.lastIndexOf("Safari/"));
               BROWSER_NAME = "Apple Safari";
            } else {
               if (!var1.startsWith("Opera/")) {
                  return;
               }

               BROWSER_VERSION = var1.substring(var1.indexOf("Version/") + 8);
               BROWSER_NAME = "Opera";
            }

            getCapabilitiesDetails(var0);
         } catch (Exception var4) {
            try {
               getCapabilitiesDetails(var0);
               return;
            } catch (Exception var3) {
               return;
            }
         }

         BROWSER_VERSION = "v" + BROWSER_VERSION;
      }
   }

   private static void getCapabilitiesDetails(WebDriver var0) {
      Capabilities var1 = ((RemoteWebDriver)var0).getCapabilities();
      BROWSER_NAME = var1.getBrowserName();
      BROWSER_VERSION = "version";
   }

   static {
      DRIVER_VERSION = "Latest";
      DRIVER_REVISION = "Build Revision";
      USER = System.getProperty("user.name");
      OS = System.getProperty("os.name");
      OS_ARCH = System.getProperty("os.arch");
      OS_VERSION = System.getProperty("os.version");
      JAVA_VERSION = System.getProperty("java.version");
      BROWSER_NAME = "Unknown";
      BROWSER_VERSION = "";
      BROWSER_NAME_PROP = "BrowserName";
      BROWSER_VERSION_PROP = "BrowserVersion";
   }
}