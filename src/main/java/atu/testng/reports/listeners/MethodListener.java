/* Decompiler 7ms, total 333ms, lines 22 */
package atu.testng.reports.listeners;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class MethodListener implements IInvokedMethodListener {
   public void afterInvocation(IInvokedMethod var1, ITestResult var2) {
   }

   public void beforeInvocation(IInvokedMethod var1, ITestResult var2) {
      if (var1.isConfigurationMethod()) {
      }

      if (var1.isTestMethod()) {
         ATUReportsListener.createReportDir(var2);
         ATUReportsListener.setPlatfromBrowserDetails(var2);
      }

   }
}