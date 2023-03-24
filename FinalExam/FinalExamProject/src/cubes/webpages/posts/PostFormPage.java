package cubes.webpages.posts;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PostFormPage {

	private WebDriver driver;
	private WebDriverWait driverWait;
	private JavascriptExecutor js;
	private Select select;
	
	
	private static final String PAGE_URL = "https://testblog.kurs-qa.cubes.edu.rs/admin/posts/add";
	@FindBy(xpath = "//button[@class='btn btn-primary']")
	private WebElement weButtonSave;
	@FindBy(xpath = "//a[@class='btn btn-outline-secondary']")
	private WebElement weButtonCancel;
	@FindBy(xpath = "//span[@class='error invalid-feedback']")
	private WebElement weErrorMessages;
	@FindBy(xpath = "//select[@name='post_category_id']")
	private WebElement weCategory;
	@FindBy(xpath = "//span[@id='title-error']")
	private WebElement weErrorMessageTitle;
	@FindBy(xpath = "//textarea[@name='description']")
	private WebElement weDescription;
	@FindBy(xpath = "//span[@id='description-error']")
	private WebElement weErrorMessageDescription;
	@FindBy(xpath = "//div[@class='col-md-6']/div[@class='form-group']/div[contains(.,'No')]")
	private WebElement weImportantNo;
	@FindBy(xpath = "//div[@class='col-md-6']/div[@class='form-group']/div[contains(.,'Yes')]")
	private WebElement weImportantYes;
	@FindBy(xpath = "//input[@name='photo']")
	private WebElement weInputFoto;
	@FindBy(xpath = "//div[.='The content field is required.']")
	private WebElement weContentErrorMessage;
	@FindBy(xpath = "//button[@class='btn btn-sm btn-outline-danger']")
	private WebElement weDeletePhoto;
	@FindBy(xpath = "//div[@class='toast-message']")
	private WebElement weDivToastMessage;



	public PostFormPage(WebDriver driver, WebDriverWait driverWait, JavascriptExecutor js, Select select) {
		super();
		this.driver = driver;
		this.driverWait = driverWait;
		this.js = js;
		this.select = select;
		this.driver.get(PAGE_URL);
		this.driver.manage().window().maximize();
		PageFactory.initElements(driver, this);
	}

	public void openPage() {
		this.driver.get(PAGE_URL);
	}
	
	public void clickOnNavigationLink(String title) {
		WebElement weLink = driver.findElement(By.xpath("//a[text()='"+title+"']"));
		weLink.click();
	}
	
	
	public  void clickOnSaveButton() {
		js.executeScript("arguments[0].scrollIntoView(true);", weButtonSave);
		driverWait.until(ExpectedConditions.visibilityOf(weButtonSave));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		weButtonSave.click();

	}
	
	public void clickOnCancelButton() {
		js.executeScript("arguments[0].scrollIntoView(true);", weButtonCancel);
		driverWait.until(ExpectedConditions.visibilityOf(weButtonCancel));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		weButtonCancel.click();
	}
	
	public String getRequiredMassage() {
		return weErrorMessages.getText();
	}
	
	public void chooseCategory() {
		weCategory.click();
	}
	
	public String getErrorTitle() {
		return weErrorMessageTitle.getText();
	}
	
	public void enterDescription(String text) {
		weDescription.sendKeys(text);
	}
	
	public String getErrorMassageDescription() {
		return weErrorMessageDescription.getText();
	}
	
	public void importantNo() {
		weImportantNo.click();
	}
	
	public void importantYes() {
		
		weImportantYes.click();
	}
	
	public void tags(String name) {
		WebElement weTagName = driver.findElement(By.xpath("//div[@class='col-md-6']//div[1]/div[*='"+name+"']"));
		js.executeScript("arguments[0].scrollIntoView(true);", weTagName);
	
		weTagName.click();
	  
	}
	
	public void chooseFotoClick() {
		js.executeScript("arguments[0].scrollIntoView(true);", weInputFoto);
		String foto = "C:\\Users\\Marija\\Documents\\Desktop\\Image\\planeta-zemlja_357651707.jpg";
		weInputFoto.sendKeys(foto);
		
	}
	
	public void clickOnContentArea() {		
		WebElement weContent = driver.findElement(By.xpath("//div[@id='cke_content']"));
		js.executeScript("arguments[0].scrollIntoView(true);", weContent);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!weContent.getAttribute("class").toString().equalsIgnoreCase("cke_1 cke cke_reset cke_chrome cke_editor_content cke_ltr cke_browser_webkit cke_focus")) {
			weContent.click();
	   }

    }
	public void enterContent(String text) {
		WebElement descriptionElement = driver.findElement(By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']"));
		driver.switchTo().frame(descriptionElement);

		WebElement editable = driver.switchTo().activeElement();
		editable.sendKeys(text);
		driver.switchTo().defaultContent();

	}
	
	
	public String getErrorContent() {
		return weContentErrorMessage.getText();
	}
	
	public void deletePhoto() {
		weDeletePhoto.click();
	}
	
	public String getDivToastMessage() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return weDivToastMessage.getText();
	}
}
