import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.io.File;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Misc {
    public static void main(String[] args) throws InterruptedException {
        Misc misc = new Misc();
        WebDriver driver = driver();
//        misc.regulateAttendance(driver);
        misc.file();
        driver.close();
        driver.quit();
    }


    public static String passwordEncoder(String str) {
//      Encode data on your side using BASE64
        byte[] bytesEncoded = Base64.getEncoder().encode(str.getBytes());
        System.out.println("encoded value is " + new String(bytesEncoded));
        return new String(bytesEncoded);
    }

    public static String passwordDecoder(String str) {
        byte[] bytesDecoded = Base64.getDecoder().decode(str);
        System.out.println("Decoded value is " + new String(bytesDecoded));
        return new String(bytesDecoded);
    }

    public static WebDriver driver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\asjad.p\\GitHub\\Testing\\Testing_Automation\\Driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    public void file() {
        File file = new File("C:\\Users\\asjad.p\\Documents\\test.txt");
        if (file.renameTo(new File("C:\\Users\\asjad.p\\Downloads\\test.txt"))) {
            System.out.println("success");
        }
    }

    public void regulateAttendance(WebDriver driver) throws InterruptedException {

        driver.navigate().to("https://eag.synechron.com/syne.ui/attendance/common/home/index#/");
        JFrame frame = new JFrame();
        String strUsername = JOptionPane.showInputDialog(frame, "Enter Username:");
        String strPassword = JOptionPane.showInputDialog(frame, "Enter Password:");
        driver.findElement(By.xpath("//input[@id='UserName']")).sendKeys(strUsername);
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(strPassword);
        driver.findElement(By.xpath("//input[@class='signInBtn']")).click();

        Actions actions = new Actions(driver);
        List<WebElement> listDays = driver.findElements(By.xpath("//span[@class='event' and text() = 'A']"));
        WebDriverWait wait = new WebDriverWait(driver, 30);
        if (!((listDays.size()) == 0)) {
            for (WebElement day : listDays) {
                Thread.sleep(2000);
                List<WebElement> listRTDays = driver.findElements(By.xpath("//span[@class='event' and text() = 'A']"));
                actions.moveToElement(listRTDays.get(0)).perform();
                driver.findElement(By.xpath("//a[text()='Regularize']")).click();

                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value = 'Cancel']")));

                Select reason = new Select(driver.findElement(By.id("Remarks")));
                reason.selectByVisibleText("Permit to Work Out of Office due to Covid-19");

                driver.findElement(By.xpath("//input[@id='fromPicker']")).click();
                Select inTime = new Select(driver.findElement(By.className("ui-timepicker-select")));
                inTime.selectByIndex(9);
                driver.findElement(By.xpath("//button[text() = 'Done']")).click();

                driver.findElement(By.xpath("//input[@id='Text1']")).click();
                driver.findElement(By.className("ui-timepicker-select")).click();
                driver.findElement(By.xpath("//option[text() = '19']")).click();
                driver.findElement(By.xpath("//button[text() = 'Done']")).click();

                driver.findElement(By.xpath("//input[@value = 'Apply']")).click();
//                driver.findElement(By.xpath("//div[@class='ui-widget-overlay']")).click();
            }
        } else {
            System.out.println("No regularization required");

            "str".getClass().getSimpleName();
        }
    }
}
