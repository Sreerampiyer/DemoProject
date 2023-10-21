/* Decompiler 40ms, total 1497ms, lines 65 */
package atu.testng.selenium.reports;

import org.openqa.selenium.WebElement;

public class CaptureScreen {
   private boolean captureBrowserPage = false;
   private boolean captureDesktop = false;
   private boolean captureWebElement = false;
   private WebElement element = null;

   public CaptureScreen(WebElement var1) {
      if (var1 != null) {
         this.setCaptureWebElement(true);
         this.setElement(var1);
      }

   }

   public CaptureScreen(CaptureScreen.ScreenshotOf var1) {
      if (var1 == CaptureScreen.ScreenshotOf.BROWSER_PAGE) {
         this.setCaptureBrowserPage(true);
      } else if (var1 == CaptureScreen.ScreenshotOf.DESKTOP) {
         this.setCaptureDesktop(true);
      }

   }

   public boolean isCaptureBrowserPage() {
      return this.captureBrowserPage;
   }

   public void setCaptureBrowserPage(boolean var1) {
      this.captureBrowserPage = var1;
   }

   public boolean isCaptureDesktop() {
      return this.captureDesktop;
   }

   public void setCaptureDesktop(boolean var1) {
      this.captureDesktop = var1;
   }

   public boolean isCaptureWebElement() {
      return this.captureWebElement;
   }

   public void setCaptureWebElement(boolean var1) {
      this.captureWebElement = var1;
   }

   public WebElement getElement() {
      return this.element;
   }

   public void setElement(WebElement var1) {
      this.element = var1;
   }

   public static enum ScreenshotOf {
      BROWSER_PAGE,
      DESKTOP;
   }
}