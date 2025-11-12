package seleniumautomation;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.time.Duration;
import java.util.List;

public class selenium1 {
    public static void main(String[] args) throws IOException, InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

        try {
            // opens flipkart page
            driver.get("https://www.flipkart.com/account/login");
            System.out.println("✅ Flipkart login page opened successfully.");

            // Mobile Number 
            WebElement mobileInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("input[type='text'][autocomplete='off']")));
            mobileInput.sendKeys("8466865860");
            System.out.println("📱 Mobile number entered.");

            // It requests otp
            WebElement otpButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Request OTP')]")));
            otpButton.click();
            System.out.println("📩 OTP requested. Please enter it manually...");

            // waits for otp entry
            System.out.println("⏳ Waiting 30 seconds for OTP entry...");
            Thread.sleep(30000);
            System.out.println("✅ OTP assumed entered successfully.");

            // searches for the product 
            WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
            searchBox.sendKeys("mobiles under 10000");
            searchBox.sendKeys(Keys.ENTER);
            System.out.println("🔍 Searching for 'mobiles under 10000'...");

            // waits fot the product to load
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[contains(@class,'CGtC98')]")));
            System.out.println("📱 Search results loaded successfully.");

            // Step 7: Fetch product names and prices
            List<WebElement> names = driver.findElements(By.xpath("//a[contains(@class,'CGtC98')]"));
            List<WebElement> prices = driver.findElements(By.xpath("//div[contains(@class,'Nx9bqj')]"));

            System.out.println("📦 Found " + names.size() + " products.");

            // Save results  into text file.
            File folder = new File("C:\\selenium");
            if (!folder.exists()) folder.mkdirs();
            File reportFile = new File(folder, "Flipkart_Mobiles.txt");

            try (FileWriter writer = new FileWriter(reportFile)) {
                writer.write("Flipkart Mobile Search Results (Under ₹10,000)\n");
                writer.write("=====================================================\n\n");

                if (names.isEmpty()) {
                    writer.write("⚠️ No products found. Please check locators.\n");
                } else {
                    for (int i = 0; i < names.size(); i++) {
                        String name = names.get(i).getText().trim();
                        String price = (i < prices.size()) ? prices.get(i).getText().trim() : "Price N/A";
                        writer.write(String.format("%2d. %-70s %s%n", i + 1, name, price));
                    }
                }
            }

            System.out.println("📝 Product list saved to: " + reportFile.getAbsolutePath());

       
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(screenshot.toPath(),
                    new File(folder, "Flipkart_Search_Screenshot.png").toPath(),
                    StandardCopyOption.REPLACE_EXISTING);
            System.out.println("📸 Screenshot saved successfully.");

            System.out.println("\n🚀 Test completed successfully — products extracted & screenshot captured.");
        } catch (Exception e) {
            System.out.println("❌ Test failed: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
