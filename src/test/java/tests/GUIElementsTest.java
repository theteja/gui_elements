package tests;

import java.time.Duration;
import java.util.List;
import org.testng.annotations.Listeners;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import listeners.TestListener;
import base.BaseTest;
import pages.GUIElementsPage;


@Listeners(TestListener.class)
public class GUIElementsTest extends BaseTest {

    @Test
    public void automateGUIElements() throws Exception {

        GUIElementsPage page = new GUIElementsPage(driver);

        page.enterName("Sai Teja");
        page.enterEmail("saiteja@gmail.com");
        page.enterPhone("9876543210");
        page.enterAddress("Andhra Pradesh");

        page.selectGender();
        page.selectDays();
        Thread.sleep(3000);


        // Country
        Select country = new Select(driver.findElement(By.id("country")));
        country.selectByVisibleText("India");
        //Thread.sleep(3000);


        // Colors
        Select colors = new Select(driver.findElement(By.id("colors")));
        colors.selectByVisibleText("Red");
        //Thread.sleep(3000);

  
        // Animals
        Select animals = new Select(driver.findElement(By.id("animals")));
        animals.selectByVisibleText("Lion");
        //Thread.sleep(3000);


        // Date Picker
        driver.findElement(By.id("datepicker")).sendKeys("06/06/2026");  
        Thread.sleep(2000);
        driver.findElement(By.id("txtDate")).sendKeys("13/06/2026");
        Thread.sleep(2000);
        driver.findElement(By.id("start-date")).sendKeys("06/06/2026");
        Thread.sleep(2000);
        driver.findElement(By.id("end-date")).sendKeys("06/13/2026");
        Thread.sleep(2000);
        WebElement submitButton =driver.findElement(By.xpath("//button[text()='Submit']"));

        submitButton.click();
       // Thread.sleep(5000);
        
        
     // Single File Upload
     String file1 = "C:\\Users\\wprjavanguser\\Desktop\\New Text Document.txt";

     WebElement singleFile = driver.findElement(By.id("singleFileInput"));
     singleFile.sendKeys(file1);
     driver.findElement(By.xpath("//button[text()='Upload Single File']")).click();
     Thread.sleep(3000);
     System.out.println(driver.findElement(By.id("singleFileStatus")).getText());



     // Multiple File Upload
     String file2 ="C:\\Users\\wprjavanguser\\Desktop\\sample.txt";
     WebElement multipleFiles = driver.findElement(By.id("multipleFilesInput"));
     multipleFiles.sendKeys(file1 + "\n" + file2);
     driver.findElement(By.xpath("//button[text()='Upload Multiple Files']")).click();
     Thread.sleep(3000);
     System.out.println(driver.findElement(By.id("multipleFilesStatus")).getText());
        
        //tabs
        GUIElementsPage page2 =new GUIElementsPage(driver);
    	page2.searchWikipediaAndOpenFirstResult("Selenium");
    	System.out.println("Wikipedia first result opened and closed successfully");
        
    	//Dyanamic Button
    	   page.handleDynamicButton();
           System.out.println("Dynamic Button handled successfully");
    	
    	
        // Alert
        List<WebElement> alertButtons =driver.findElements(By.xpath("//button[contains(text(),'Alert')]"));

        if(!alertButtons.isEmpty()) {
            alertButtons.get(0).click();
            Alert alert =driver.switchTo().alert();
            alert.accept();
        }
        
        //Confirmation Alert
        page.acceptConfirmationAlert();
        System.out.println("Confirmation Alert OK button clicked");
        

    	//prompt alert
        page.handlePromptAlert("Harry Potter");
        String result = driver.findElement(By.id("demo")).getText();
        Assert.assertTrue(result.contains("Harry Potter"));
        System.out.println("Prompt Alert handled successfully");                                           
        
     	//new tab
        page.handleNewTab();
        System.out.println("New Tab handled successfully");

        // Double Click
        List<WebElement> buttons =driver.findElements(By.tagName("button"));
        Actions actions =new Actions(driver);

        for(WebElement button : buttons) {
            String text = button.getText();
            if(text.toLowerCase().contains("copy")) {
                actions.doubleClick(button).perform();
                break;
            }
        }
  

        // Mouse Hover
        for(WebElement button : buttons) {
            String text = button.getText();
            if(text.toLowerCase().contains("point")) {
                actions.moveToElement(button).perform();
                break;
            }
        }
    
        // Drag And Drop
        List<WebElement> draggable =driver.findElements(By.id("draggable"));

        List<WebElement> droppable =driver.findElements(By.id("droppable"));

        if(!draggable.isEmpty() && !droppable.isEmpty()) {

            actions.dragAndDrop(
                    draggable.get(0),
                    droppable.get(0))
                    .perform();

            Assert.assertTrue(droppable.get(0).getText().contains("Dropped"));
        }
       
        //Slider
        Actions actions1 = new Actions(driver);
        WebElement slider =driver.findElement(By.xpath("//*[@id=\"slider-range\"]/div"));
        actions1.dragAndDropBy(slider, 80, 0).perform();
       
        //Scrolling Dropdown
        page.selectScrollingDropDown("Item 5");
        System.out.println("Scrolling Dropdown Item Selected");
     
        //Checkboxes 4 pages
         GUIElementsPage page1 =new GUIElementsPage(driver);
         page1.selectAllPaginationCheckboxes();
         System.out.println("All checkboxes selected from 4 pages");
               
         //laptop links
          page.handleLaptopLinks();
          System.out.println("Laptop Links handled successfully");

                          
           //Broken Links
           page.handleBrokenLinks();
           System.out.println("Broken Links handled successfully");
                          
            //Form Sction
              page.handleFormSections();
              System.out.println( "Form Sections handled successfully");
                             
                            
            //Bolg and Youtube
            page.handleBlogAndYoutube();
            Thread.sleep(3000);
                            
             //Footer links AJAX
              driver.get( "https://testautomationpractice.blogspot.com/p/gui-elements-ajax-hidden.html");
              page.handleHiddenElementsAndAjax();
              System.out.println( "Footer link Ajax handled successfully");
                            
              //Footer links donwloadfiles
              driver.findElement(By.linkText("Download Files")).click();
               Thread.sleep(3000);
               page.handleDownloadFilesPage(); 
               System.out.println("Download Files handled successfully");
               
               //HeaderLinks
               page.handleHeaderLinks();
               System.out.println("Online Trainings and Blog handled successfully");
               Thread.sleep(3000);
               
               //PlaywrightPractice
               page.handlePlaywrightPractice();
               System.out.println("PlaywrightPractice executed successfully");
               Thread.sleep(3000);
        //--------------------------------
        // Verification
        //--------------------------------

        Assert.assertEquals(
                driver.findElement(By.id("name"))
                        .getAttribute("value"),
                "Sai Teja");

        System.out.println("All GUI Elements executed successfully");
    }
}