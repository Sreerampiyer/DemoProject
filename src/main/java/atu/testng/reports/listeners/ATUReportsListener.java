/* Decompiler 129ms, total 490ms, lines 368 */
package atu.testng.reports.listeners;

import atu.testng.reports.ATUReports;
import atu.testng.reports.excel.ExcelReports;
import atu.testng.reports.exceptions.ATUReporterException;
import atu.testng.reports.exceptions.ATUReporterStepFailedException;
import atu.testng.reports.utils.Attributes;
import atu.testng.reports.utils.Directory;
import atu.testng.reports.utils.Platform;
import atu.testng.reports.utils.SettingsFile;
import atu.testng.reports.writers.ConsolidatedReportsPageWriter;
import atu.testng.reports.writers.CurrentRunPageWriter;
import atu.testng.reports.writers.HTMLDesignFilesJSWriter;
import atu.testng.reports.writers.IndexPageWriter;
import atu.testng.reports.writers.TestCaseReportsPageWriter;
import atu.testrecorder.ATUTestRecorder;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.testng.IExecutionListener;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class ATUReportsListener implements ITestListener, IExecutionListener, IReporter, ISuiteListener {
   int runCount = 0;
   List<ITestResult> passedTests = new ArrayList();
   List<ITestResult> failedTests = new ArrayList();
   List<ITestResult> skippedTests = new ArrayList();
   private ATUTestRecorder recorder;
   public static boolean suiteStarted = false;

   public void onStart(ITestContext var1) {
   }

   public void onFinish(ITestContext var1) {
   }

   public void onTestFailedButWithinSuccessPercentage(ITestResult var1) {
   }

   public void onTestFailure(ITestResult var1) {
      this.failedTests.add(var1);
   }

   public void onTestSkipped(ITestResult var1) {
      if (var1.getThrowable() instanceof SkipException) {
         this.skippedTests.add(var1);
      } else {
         createReportDir(var1);
         this.skippedTests.add(var1);
      }
   }

   public void onTestStart(ITestResult var1) {
   }

   public void onTestSuccess(ITestResult var1) {
      try {
         if (var1.getAttribute("passedButFailed").equals("passedButFailed")) {
            var1.setStatus(2);
            var1.setThrowable(new ATUReporterStepFailedException());
            this.failedTests.add(var1);
            return;
         }
      } catch (NullPointerException var3) {
      }

      this.passedTests.add(var1);
   }

   public static void setPlatfromBrowserDetails(ITestResult var0) {
      //Platform.prepareDetails(ATUReports.getWebDriver());
      var0.setAttribute("Chrome", "Chrome");
      var0.setAttribute("latest", "latest");
   }

   public static void createReportDir(ITestResult var0) {
      String var1 = getReportDir(var0);
      Directory.mkDirs(var1);
      Directory.mkDirs(var1 + Directory.SEP + Directory.SCREENSHOT_DIRName);
   }

   public static String getRelativePathFromSuiteLevel(ITestResult var0) {
      String var1 = var0.getTestContext().getSuite().getName();
      String var2 = var0.getTestContext().getCurrentXmlTest().getName();
      String var3 = var0.getTestClass().getName().replace(".", Directory.SEP);
      String var4 = var0.getMethod().getMethodName();
      var4 = var4 + "_Iteration" + (var0.getMethod().getCurrentInvocationCount() + 1);
      return var1 + Directory.SEP + var2 + Directory.SEP + var3 + Directory.SEP + var4;
   }

   public static String getReportDir(ITestResult var0) {
      String var1 = getRelativePathFromSuiteLevel(var0);
      var0.setAttribute("relativeReportDir", var1);
      String var2 = Directory.RUNDir + Directory.SEP + var1;
      var0.setAttribute("iteration", var0.getMethod().getCurrentInvocationCount() + 1);
      var0.setAttribute("reportDir", var2);
      return var2;
   }

   public void setTickInterval(List<ITestResult> var1, List<ITestResult> var2, List<ITestResult> var3) throws ATUReporterException {
      int var4 = SettingsFile.getHighestTestCaseNumber();
      int var5 = SettingsFile.getBiggestNumber(new int[]{var4, var1.size(), var2.size(), var3.size()});
      int var6 = var5 / 10;
      if (var6 > 1) {
         HTMLDesignFilesJSWriter.TICK_INTERVAL = var6;
      }

   }

   public void onFinish() {
      try {
         String var1 = SettingsFile.get("passedList") + this.passedTests.size() + ';';
         String var2 = SettingsFile.get("failedList") + this.failedTests.size() + ';';
         String var3 = SettingsFile.get("skippedList") + this.skippedTests.size() + ';';
         SettingsFile.set("passedList", var1);
         SettingsFile.set("failedList", var2);
         SettingsFile.set("skippedList", var3);
         this.setTickInterval(this.passedTests, this.failedTests, this.skippedTests);
         HTMLDesignFilesJSWriter.lineChartJS(var1, var2, var3, this.runCount);
         HTMLDesignFilesJSWriter.barChartJS(var1, var2, var3, this.runCount);
         HTMLDesignFilesJSWriter.pieChartJS(this.passedTests.size(), this.failedTests.size(), this.skippedTests.size(), this.runCount);
         this.generateIndexPage();
         long var4 = (Long)Attributes.getAttribute("startExecution");
         this.generateConsolidatedPage();
         this.generateCurrentRunPage(this.passedTests, this.failedTests, this.skippedTests, var4, System.currentTimeMillis());
         this.startReportingForPassed(this.passedTests);
         this.startReportingForFailed(this.failedTests);
         this.startReportingForSkipped(this.skippedTests);
         if (Directory.generateExcelReports) {
            ExcelReports.generateExcelReport(Directory.RUNDir + Directory.SEP + "(" + Directory.REPORTSDIRName + ") " + Directory.RUNName + this.runCount + ".xlsx", this.passedTests, this.failedTests, this.skippedTests);
         }

         if (Directory.generateConfigReports) {
            ConfigurationListener.startConfigurationMethodsReporting(this.runCount);
         }

      } catch (Exception var6) {
         throw new IllegalStateException(var6);
      }
   }

   public void startCreatingDirs(ISuite var1) {
      Directory.mkDirs(Directory.RUNDir + Directory.SEP + var1.getName());
      Iterator var2 = var1.getXmlSuite().getTests().iterator();

      while(var2.hasNext()) {
         XmlTest var3 = (XmlTest)var2.next();
         Directory.mkDirs(Directory.RUNDir + Directory.SEP + var1.getName() + Directory.SEP + var3.getName());
      }

   }

   public void generateIndexPage() {
      PrintWriter var1 = null;

      try {
         var1 = new PrintWriter(Directory.REPORTSDir + Directory.SEP + "index.html");
         IndexPageWriter.header(var1);
         IndexPageWriter.content(var1, ATUReports.indexPageDescription);
         IndexPageWriter.footer(var1);
      } catch (FileNotFoundException var11) {
         var11.printStackTrace();
      } finally {
         try {
            var1.close();
         } catch (Exception var10) {
            var1 = null;
         }

      }

   }

   public void generateCurrentRunPage(List<ITestResult> var1, List<ITestResult> var2, List<ITestResult> var3, long var4, long var6) {
      PrintWriter var8 = null;

      try {
         var8 = new PrintWriter(Directory.RUNDir + Directory.SEP + "CurrentRun.html");
         CurrentRunPageWriter.header(var8);
         CurrentRunPageWriter.menuLink(var8, 0);
         CurrentRunPageWriter.content(var8, var1, var2, var3, ConfigurationListener.passedConfigurations, ConfigurationListener.failedConfigurations, ConfigurationListener.skippedConfigurations, this.runCount, var4, var6);
         CurrentRunPageWriter.footer(var8);
      } catch (FileNotFoundException var18) {
         var18.printStackTrace();
      } finally {
         try {
            var8.close();
         } catch (Exception var17) {
            var8 = null;
         }

      }

   }

   public void generateConsolidatedPage() {
      PrintWriter var1 = null;

      try {
         var1 = new PrintWriter(Directory.RESULTSDir + Directory.SEP + "ConsolidatedPage.html");
         ConsolidatedReportsPageWriter.header(var1);
         ConsolidatedReportsPageWriter.menuLink(var1, this.runCount);
         ConsolidatedReportsPageWriter.content(var1);
         ConsolidatedReportsPageWriter.footer(var1);
      } catch (FileNotFoundException var11) {
         var11.printStackTrace();
      } finally {
         try {
            var1.close();
         } catch (Exception var10) {
            var1 = null;
         }

      }

   }

   public void startReportingForPassed(List<ITestResult> var1) {
      PrintWriter var2 = null;
      Iterator var3 = var1.iterator();

      while(var3.hasNext()) {
         ITestResult var4 = (ITestResult)var3.next();
         String var5 = var4.getAttribute("reportDir").toString();

         try {
            var2 = new PrintWriter(var5 + Directory.SEP + var4.getName() + ".html");
            TestCaseReportsPageWriter.header(var2, var4);
            TestCaseReportsPageWriter.menuLink(var2, var4, 0);
            TestCaseReportsPageWriter.content(var2, var4, this.runCount);
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

   public void startReportingForFailed(List<ITestResult> var1) {
      PrintWriter var2 = null;
      Iterator var3 = var1.iterator();

      while(var3.hasNext()) {
         ITestResult var4 = (ITestResult)var3.next();
         String var5 = var4.getAttribute("reportDir").toString();

         try {
            var2 = new PrintWriter(var5 + Directory.SEP + var4.getName() + ".html");
            TestCaseReportsPageWriter.header(var2, var4);
            TestCaseReportsPageWriter.menuLink(var2, var4, 0);
            TestCaseReportsPageWriter.content(var2, var4, this.runCount);
            TestCaseReportsPageWriter.footer(var2);
         } catch (FileNotFoundException var15) {
         } finally {
            try {
               var2.close();
            } catch (Exception var14) {
               var2 = null;
            }

         }
      }

   }

   public void startReportingForSkipped(List<ITestResult> var1) {
      PrintWriter var2 = null;
      Iterator var3 = var1.iterator();

      while(var3.hasNext()) {
         ITestResult var4 = (ITestResult)var3.next();
         String var5 = var4.getAttribute("reportDir").toString();

         try {
            var2 = new PrintWriter(var5 + Directory.SEP + var4.getName() + ".html");
            TestCaseReportsPageWriter.header(var2, var4);
            TestCaseReportsPageWriter.menuLink(var2, var4, 0);
            TestCaseReportsPageWriter.content(var2, var4, this.runCount);
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

   public void onExecutionFinish() {
      Attributes.setAttribute("endExecution", System.currentTimeMillis());
      if (Directory.recordSuiteExecution) {
         try {
            this.recorder.stop();
         } catch (Throwable var2) {
            throw new IllegalStateException(var2);
         }
      }

   }

   private void initChecking() {
      try {
         Directory.verifyRequiredFiles();
         SettingsFile.correctErrors();
         this.runCount = Integer.parseInt(SettingsFile.get("run").trim()) + 1;
         SettingsFile.set("run", "" + this.runCount);
         Directory.RUNDir = Directory.RUNDir + this.runCount;
         Directory.mkDirs(Directory.RUNDir);
         if (Directory.recordSuiteExecution) {
            try {
               this.recorder = new ATUTestRecorder(Directory.RUNDir, "ATU_CompleteSuiteRecording", false);
               this.recorder.start();
            } catch (Throwable var2) {
               throw new IllegalStateException(var2);
            }
         }

      } catch (Exception var3) {
         throw new IllegalStateException(var3);
      }
   }

   public void onExecutionStart() {
      Attributes.setAttribute("startExecution", System.currentTimeMillis());
      this.initChecking();
   }

   public void generateReport(List<XmlSuite> var1, List<ISuite> var2, String var3) {
      Iterator var4 = var2.iterator();

      while(var4.hasNext()) {
         ISuite var5 = (ISuite)var4.next();
         Attributes.setSuiteNameMapper(var5.getName());
         this.startCreatingDirs(var5);
         this.onFinish();
      }

   }

   public void onFinish(ISuite var1) {
   }

   public void onStart(ISuite var1) {
   }
}
