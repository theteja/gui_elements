package pages;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
 
public class GUIElementsPage {

    WebDriver driver;

    public GUIElementsPage(WebDriver driver) {
        this.driver = driver;
    }

    By name = By.id("name");
    By email = By.id("email");
    By phone = By.id("phone");
    By address = By.id("textarea");

    By male = By.id("male");

    By monday = By.id("monday");
    By tuesday = By.id("tuesday");
    By friday = By.id("friday");

    By country = By.id("country");

    public void enterName(String value) {
        driver.findElement(name).sendKeys(value);
    }

    public void enterEmail(String value) {
        driver.findElement(email).sendKeys(value);
    }

    public void enterPhone(String value) {
        driver.findElement(phone).sendKeys(value);
    }

    public void enterAddress(String value) {
        driver.findElement(address).sendKeys(value);
    }

    public void selectGender() {
        driver.findElement(male).click();
    }

    public void selectDays() {
        driver.findElement(monday).click();
        driver.findElement(tuesday).click();
        driver.findElement(friday).click();
    }
    
    //Pagination
    public void selectAllPaginationCheckboxes() {

        for (int page = 1; page <= 4; page++) {
            // Select all checkboxes on current page
            List<WebElement> checkBoxes =
                    driver.findElements(By.xpath("//table[@id='productTable']//input[@type='checkbox']"));

            for (WebElement checkbox : checkBoxes) {

                if (!checkbox.isSelected()) {
                    checkbox.click();
                }
            }

            // Go to next page
            if (page < 4) {
                driver.findElement(
                        By.xpath("//ul[@id='pagination']//li/a[text()='" + (page + 1) + "']")).click();
            }
        }
    }
    
    //Search Tab
    public void searchWikipediaAndOpenFirstResult(String searchText) {
        driver.findElement(By.id("Wikipedia1_wikipedia-search-input")).sendKeys(searchText);
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        String parentWindow = driver.getWindowHandle();
        List<WebElement> results =
                driver.findElements(By.xpath("//div[@id='Wikipedia1_wikipedia-search-results']//a"));

        if (!results.isEmpty()) {
            results.get(0).click();
            Set<String> windows = driver.getWindowHandles();
            for (String window : windows) {
                if (!window.equals(parentWindow)) {
                    driver.switchTo().window(window);
                    System.out.println("Child Window Title : " + driver.getTitle());
                    driver.close();
                    break;
                }
            }
            driver.switchTo().window(parentWindow);
        }
    }
    
    //Confirmation Alert
    public void acceptConfirmationAlert() {
        driver.findElement(By.id("confirmBtn")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
    
    //Prompt Button
    public void handlePromptAlert(String text) {
        driver.findElement(By.id("promptBtn")).click();
        Alert alert =driver.switchTo().alert();
        alert.sendKeys(text);
        alert.accept();
    }
    
   //New tab
    public void handleNewTab() {
        String parentWindow =driver.getWindowHandle();
        driver.findElement(By.xpath("//button[contains(text(),'New Tab')]")).click();

        Set<String> windows = driver.getWindowHandles();
        for (String window : windows) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                System.out.println(driver.getTitle());
                driver.close();
                break;
            }
        }

        driver.switchTo().window(parentWindow);
    }
    
  //Popup window
    public void handlePopupWindow() throws InterruptedException {

        String parentWindow =driver.getWindowHandle();
        driver.findElement(By.id("PopUp")).click();
        System.out.println("Total Windows = "+ driver.getWindowHandles().size());
        Set<String> windows =driver.getWindowHandles();
        for (String window : windows) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                System.out.println("Popup Title : "+ driver.getTitle());

                System.out.println("Popup URL : "+ driver.getCurrentUrl());
                Thread.sleep(3000);
                driver.close();
                break;
            }
        }

        driver.switchTo().window(parentWindow);
        System.out.println("Returned to Parent Window");
    }
    
    public void handleDynamicButton() {
        driver.findElement(By.xpath("//button[@name='start']")).click(); 
    }
    
    //Scrolling 
    public void selectScrollingDropDown(String value)
            throws InterruptedException {
        WebElement dropDown =driver.findElement(By.xpath("//input[@placeholder='Select an item']"));
        dropDown.click();
        Thread.sleep(1000);
        dropDown.sendKeys(value);
        driver.findElement(By.xpath("//div[text()='" + value + "']")).click();
    }
    
    //Laptop Section
    public void handleLaptopLinks() throws InterruptedException {

        for (int i = 0; i < 3; i++) {
            List<WebElement> links =driver.findElements(By.xpath("//a[text()='Apple' or text()='Lenovo' or text()='Dell']"));
            String linkName =links.get(i).getText();
            links.get(i).click();
            Thread.sleep(3000);

            System.out.println("Clicked : " + linkName);

            System.out.println("Title : " + driver.getTitle());
            driver.navigate().back();
            Thread.sleep(3000);
        }
    }
    
    //BrokenLinks section
    public void handleBrokenLinks() throws InterruptedException {

        String[] brokenLinks = {
                "Errorcode 400",
                "Errorcode 401",
                "Errorcode 403",
                "Errorcode 404",
                "Errorcode 408",
                "Errorcode 500",
                "Errorcode 502",
                "Errorcode 503"
        };

        for (String link : brokenLinks) {
            driver.findElement(By.linkText(link)).click();
            Thread.sleep(2000);

            System.out.println("Link : " + link);
            System.out.println("Title : " + driver.getTitle());
            System.out.println("URL : " + driver.getCurrentUrl());

            // Return back to main page
            driver.navigate().back();
            Thread.sleep(2000);
        }
        System.out.println("All Broken Links handled successfully");
    }
    
    
    public void handleFormSections() throws InterruptedException {
        // Section 1
        driver.findElement(By.id("input1")).sendKeys("Section 1 Data");
        driver.findElement(By.xpath("(//button[text()='Submit'])[1]")).click();
        Thread.sleep(1000);

        // Section 2
        driver.findElement(By.id("input2")).sendKeys("Section 2 Data");
        driver.findElement(By.xpath("(//button[text()='Submit'])[2]")).click();
        Thread.sleep(1000);

        // Section 3
        driver.findElement(By.id("input3")).sendKeys("Section 3 Data");
        driver.findElement(By.xpath("(//button[text()='Submit'])[3]")).click();
        Thread.sleep(1000);
        System.out.println(
                "All Form Sections Submitted Successfully");
    }
    
    
    public void handleBlogAndYoutube() throws InterruptedException {
        // Blog Link  
        driver.findElement(By.linkText("Blog")).click();
        Thread.sleep(3000);
        driver.navigate().back();
        Thread.sleep(3000);


     // Shadow DOM Elements
     WebElement shadowHost = driver.findElement(By.id("shadow_host"));
     SearchContext shadowRoot = shadowHost.getShadowRoot();

     // Textbox
     WebElement textBox =shadowRoot.findElement(By.cssSelector("input[type='text']"));

     textBox.sendKeys("SDET Automation");
     Thread.sleep(2000);

     // Checkbox
     WebElement checkBox = shadowRoot.findElement(By.cssSelector("input[type='checkbox']"));

     if (!checkBox.isSelected()) {
         checkBox.click();
     }
     Thread.sleep(2000);

     // File Upload
     WebElement uploadFile = shadowRoot.findElement(By.cssSelector("input[type='file']"));

     uploadFile.sendKeys("C:\\Users\\wprjavanguser\\Desktop\\New Text Document.txt");

     Thread.sleep(3000);
     System.out.println("Textbox, Checkbox and File Upload Completed");

        // Youtube Link
        driver.findElement(By.linkText("Youtube")).click();
        Thread.sleep(3000);
        driver.navigate().back();
        Thread.sleep(3000);
        System.out.println("Blog and Youtube section completed successfully");
    }
    
    public void handleHiddenElementsAndAjax()
            throws InterruptedException {
        // Input Box 1
        driver.findElement(By.id("input1")).sendKeys("SDET Automation");
        Thread.sleep(2000);

        // Toggle Input Box 2
        driver.findElement(By.id("toggleInput")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("input2")).sendKeys("Hidden Input");
        Thread.sleep(2000);

        // Checkbox 1
        WebElement checkbox1 = driver.findElement(By.xpath("//input[@type='checkbox'][1]"));

        if (!checkbox1.isSelected()) {

            checkbox1.click();
        }
        Thread.sleep(2000);

 
        // Toggle Checkbox 2
        driver.findElement(By.id("toggleCheckbox")).click();
        Thread.sleep(2000);

        List<WebElement> checkBoxes = driver.findElements(By.xpath("//input[@type='checkbox']"));

        if (checkBoxes.size() > 1) {
            WebElement checkbox2 = checkBoxes.get(1);
            if (!checkbox2.isSelected()) {
                checkbox2.click();
            }
        }
        Thread.sleep(2000);

        // Load AJAX Content
        driver.findElement(By.id("loadContent")).click();
        Thread.sleep(5000);

        WebElement ajaxContent =driver.findElement(By.id("ajaxContent"));
        System.out.println("AJAX Content : "+ ajaxContent.getText());

        // Back To Main Page
        driver.navigate().back();
        Thread.sleep(3000);

        System.out.println("Returned to Main Page Successfully");
    }
    
    
    public void handleDownloadFilesPage()
            throws InterruptedException {
        // Enter Text
        driver.findElement(By.id("inputText")).sendKeys("SDET Automation Testing");
        Thread.sleep(2000);

        // Generate Text File
        driver.findElement(By.id("generateTxt")).click();
        Thread.sleep(2000);

        // Download Text File
        driver.findElement(By.id("txtDownloadLink")).click();
        Thread.sleep(3000);

        // Generate PDF File
        driver.findElement(By.id("generatePdf")).click();
        Thread.sleep(2000);

        // Download PDF File
        driver.findElement(By.id("pdfDownloadLink")).click();
        Thread.sleep(3000);

        // PDF With Browser
        driver.findElement(By.xpath("//button[contains(text(),'Download PDF File')]")).click();
        Thread.sleep(5000);

        // Return To Main Page
        driver.navigate().back();
        Thread.sleep(3000);
        System.out.println("Download Files Page Completed Successfully");
    }
    
    
    public void handleHeaderLinks()
            throws InterruptedException {

        String[] links = {
                "Online Trainings",
                "Blog"
        };

        for (String link : links) {

            driver.findElement(By.linkText(link)).click();
            Thread.sleep(3000);

            System.out.println("Link : " + link);

            System.out.println("Title : " + driver.getTitle());

            System.out.println("URL : " + driver.getCurrentUrl());

            // Return to Automation Practice page
            driver.navigate().back();

            Thread.sleep(3000);
        }

        System.out.println("All Header Links handled successfully");
    }
    
    
    public void handlePlaywrightPractice()
            throws InterruptedException {
        // Open PlaywrightPractice
        driver.findElement(By.linkText("PlaywrightPractice")).click();
        Thread.sleep(3000);

        // Section 1 - getByRole()
        driver.findElement(By.xpath("//button[contains(text(),'Primary Action')]")).click();

        driver.findElement(By.xpath("//button[contains(text(),'Toggle Button')]")).click();

        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("Admin");

        driver.findElement(By.xpath("//input[@type='checkbox']")).click();

        driver.findElement(By.linkText("Home")).click();
        driver.navigate().back();

        // Section 2 - getByText()
        WebElement submitBtn = driver.findElement(By.xpath("//button[contains(text(),'Submit Form')]"));
        submitBtn.click();
        Thread.sleep(2000);

        // Section 3 - getByLabel()
        List<WebElement> inputs = driver.findElements(By.xpath("//input"));
        inputs.get(1).sendKeys("sai@gmail.com");
        inputs.get(2).sendKeys("Password123");
        inputs.get(3).sendKeys("22");
        Thread.sleep(2000);
        List<WebElement> radios =driver.findElements(By.xpath("//input[@type='radio']"));
        if (!radios.isEmpty()) {
            radios.get(0).click();
        }
        Thread.sleep(2000);

        // Section 4 - getByPlaceholder()
        driver.findElement(By.xpath("//input[@placeholder='Enter your full name']")).sendKeys("John Smith");
        driver.findElement(By.xpath("//input[contains(@placeholder,'Phone')]")).sendKeys("9876543210");
        driver.findElement(By.xpath("//textarea")).sendKeys("Automation Testing Message");
        driver.findElement(By.xpath("//input[contains(@placeholder,'Search')]")).sendKeys("Laptop");
        driver.findElement(By.xpath("//button[contains(text(),'Search')]")).click();
        Thread.sleep(2000);


        // Section 5 - getByAltText()
        WebElement image = driver.findElement(By.xpath("//img"));
        System.out.println(image.getAttribute("alt"));

        // Section 6 - getByTitle()
        Actions actions = new Actions(driver);
        WebElement homeLink = driver.findElement(By.linkText("Home"));
        actions.moveToElement(homeLink).perform();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();

    
        // Section 7 - getByTestId()
        driver.findElement(By.xpath("//button[contains(text(),'Edit Profile')]")).click();

        Thread.sleep(2000);

        List<WebElement> footerLinks =
                driver.findElements(By.xpath("//a[text()='Home' or text()='Products' or text()='Contact']"));

        for (WebElement link : footerLinks) {
            System.out.println("Footer Link : "+ link.getText());
        }

        // Back To Main Page    
        driver.navigate().back();
        Thread.sleep(3000);
        System.out.println("PlaywrightPractice completed successfully");
    }
    
    
}