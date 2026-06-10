package utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    public static String captureScreenshot(WebDriver driver, String testName) {

        String timeStamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss")
                        .format(new Date());

        String screenshotPath =
                System.getProperty("user.dir")
                        + "/screenshots/"
                        + testName
                        + "_"
                        + timeStamp
                        + ".png";

        try {

            File sourceFile =
                    ((TakesScreenshot) driver)
                            .getScreenshotAs(OutputType.FILE);

            File destinationFile =
                    new File(screenshotPath);

            destinationFile.getParentFile().mkdirs();

            Files.copy(
                    sourceFile.toPath(),
                    destinationFile.toPath(),
                    StandardCopyOption.REPLACE_EXISTING);

            System.out.println(
                    "Screenshot saved at : "
                            + screenshotPath);

            return screenshotPath;

        } catch (IOException e) {

            e.printStackTrace();

            return null;
        }
    }
}