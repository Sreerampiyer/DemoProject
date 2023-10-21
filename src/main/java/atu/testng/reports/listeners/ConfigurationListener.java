/* Decompiler 60ms, total 485ms, lines 135 */
package atu.testng.reports.listeners;

import atu.testng.reports.utils.Directory;
import atu.testng.reports.writers.TestCaseReportsPageWriter;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.testng.IConfigurationListener2;
import org.testng.ITestResult;

public class ConfigurationListener implements IConfigurationListener2 {
   static List<ITestResult> passedConfigurations = new ArrayList();
   static List<ITestResult> failedConfigurations = new ArrayList();
   static List<ITestResult> skippedConfigurations = new ArrayList();

   public void onConfigurationFailure(ITestResult var1) {
      if (Directory.generateConfigReports) {
         failedConfigurations.add(var1);
      }

   }

   public void onConfigurationSkip(ITestResult var1) {
      if (Directory.generateConfigReports) {
         ATUReportsListener.createReportDir(var1);
         skippedConfigurations.add(var1);
      }

   }

   public void onConfigurationSuccess(ITestResult var1) {
      if (Directory.generateConfigReports) {
         passedConfigurations.add(var1);
      }

   }

   public static void startConfigurationMethodsReporting(int var0) {
      startReportingForPassedConfigurations(passedConfigurations, var0);
      startReportingForFailedConfigurations(failedConfigurations, var0);
      startReportingForSkippedConfigurations(skippedConfigurations, var0);
   }

   private static void startReportingForPassedConfigurations(List<ITestResult> var0, int var1) {
      PrintWriter var2 = null;
      Iterator var3 = var0.iterator();

      while(var3.hasNext()) {
         ITestResult var4 = (ITestResult)var3.next();
         String var5 = null;
         var5 = var4.getAttribute("reportDir").toString();

         try {
            var2 = new PrintWriter(var5 + Directory.SEP + var4.getName() + ".html");
            TestCaseReportsPageWriter.header(var2, var4);
            TestCaseReportsPageWriter.menuLink(var2, var4, 0);
            TestCaseReportsPageWriter.content(var2, var4, var1);
            TestCaseReportsPageWriter.footer(var2);
         } catch (FileNotFoundException var15) {
            var15.printStackTrace();
         } finally {
            try {
               var2.close();
            } catch (Exception var14) {
               var2 = null;
            }

         }
      }

   }

   private static void startReportingForFailedConfigurations(List<ITestResult> var0, int var1) {
      PrintWriter var2 = null;
      Iterator var3 = var0.iterator();

      while(var3.hasNext()) {
         ITestResult var4 = (ITestResult)var3.next();
         String var5 = var4.getAttribute("reportDir").toString();

         try {
            var2 = new PrintWriter(var5 + Directory.SEP + var4.getName() + ".html");
            TestCaseReportsPageWriter.header(var2, var4);
            TestCaseReportsPageWriter.menuLink(var2, var4, 0);
            TestCaseReportsPageWriter.content(var2, var4, var1);
            TestCaseReportsPageWriter.footer(var2);
         } catch (FileNotFoundException var15) {
            var15.printStackTrace();
         } finally {
            try {
               var2.close();
            } catch (Exception var14) {
               var2 = null;
            }

         }
      }

   }

   private static void startReportingForSkippedConfigurations(List<ITestResult> var0, int var1) {
      PrintWriter var2 = null;
      Iterator var3 = var0.iterator();

      while(var3.hasNext()) {
         ITestResult var4 = (ITestResult)var3.next();
         String var5 = var4.getAttribute("reportDir").toString();

         try {
            var2 = new PrintWriter(var5 + Directory.SEP + var4.getName() + ".html");
            TestCaseReportsPageWriter.header(var2, var4);
            TestCaseReportsPageWriter.menuLink(var2, var4, 0);
            TestCaseReportsPageWriter.content(var2, var4, var1);
            TestCaseReportsPageWriter.footer(var2);
         } catch (FileNotFoundException var15) {
            var15.printStackTrace();
         } finally {
            try {
               var2.close();
            } catch (Exception var14) {
               var2 = null;
            }

         }
      }

   }

   public void beforeConfiguration(ITestResult var1) {
      ATUReportsListener.createReportDir(var1);
   }
}