package cubes.webpages.posts;



import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.bytebuddy.description.type.TypeDescription.Generic.AnnotationReader.Delegator.ForLoadedMethodReturnType;

public class PostsListPage {

	private WebDriver driver;
	private WebDriverWait driverWait;
	private JavascriptExecutor js;
	private Select select;
	
	private static final String PAGE_URL = "https://testblog.kurs-qa.cubes.edu.rs/admin/posts";
	@FindBy(xpath = "//input[@name='title']")
	private WebElement weInputTitle;
	@FindBy(xpath = "//tbody//tr[@class='odd']//td[@class='dataTables_empty']")
	private WebElement weNoPost;
	@FindBy(xpath = "//span[@class='select2-search select2-search--dropdown']/input[@class='select2-search__field']")
	private WebElement weInputAuthor;
	@FindBy(xpath = "//li[text() = 'No results found']")
	WebElement weNoResultMessage;
	@FindBy(xpath = "//span[@class='select2-search select2-search--dropdown']/input[@class='select2-search__field']")
	private WebElement weInputCategory;
	@FindBy(xpath = "//select [@name='user_id']")
	private WebElement dropdownAuthor;
	@FindBy(xpath = "//select [@name='post_category_id']")
	private WebElement dropdownCategory;
	@FindBy(xpath = "//select [@name='important']")
	private WebElement dropdownImportant;
	@FindBy(xpath = "//select [@name='status']")
	private WebElement dropdownStatus;
	@FindBy(xpath = "//select [@name='tag_ids']")
	private WebElement dropdownTag;
	@FindBy(xpath = "//input[@class='select2-search__field']")
	private WebElement inputTag;
	@FindBy(xpath = "//input[@class='form-control form-control-sm']")
	private WebElement weSearchField;
	@FindBy(xpath = "//a[@class='btn btn-success']")
	private WebElement weAddnewPost;
	@FindBy(xpath = "//div[@class='toast-message']")
	private WebElement weToastMessageEdited;
	@FindBy(xpath = "//button[text()='Cancel']")
	private WebElement weDialogCancel;
	@FindBy(xpath = "//button[text()='Delete']")
	private WebElement weDialogDelete;
	@FindBy(xpath = "//form[@id='disable-modal']//button[@class='btn btn-default']")
	private WebElement weDisableCancel;
	@FindBy(xpath = "//button[contains(.,'Disable')]")
	private WebElement weDialogDisable;
	@FindBy(xpath = "//form[@id='enable-modal']//button[@class='btn btn-default']")
	private WebElement weEnableCancel;
	@FindBy(xpath = "//button[contains(.,'Enable')]")
	private WebElement weDialogEnable;
	@FindBy(xpath = "//div[@class='toast toast-success']//div[@class='toast-message']")
	private WebElement weToastEnable;
	@FindBy(xpath = "//form[@id='important-modal']//button[@class='btn btn-default']")
	private WebElement weBookmarkCancel;
	@FindBy(xpath = "//button[contains(.,'Set as Important')]")
	private WebElement weBookmarkImportant;
	@FindBy(xpath = "//form[@id='unimportant-modal']//button[@class='btn btn-default']")
	private WebElement weUnimportantCancel;
	@FindBy(xpath = "//button[contains(.,'Set as Unimportant')]")
	private WebElement weBookmarkUnimportant;
	@FindBy(xpath = "//a[contains(.,'Polaznik Kursa')]")
	private WebElement weProfile;
	@FindBy(xpath = "//a[contains(.,'Your Profile')]")
	private WebElement weYourProfile;
	@FindBy(xpath = "//a[@href='https://testblog.kurs-qa.cubes.edu.rs/logout']")
	private WebElement weLogOut;

	
	

	
	public PostsListPage(WebDriver driver, WebDriverWait driverWait,JavascriptExecutor js,Select select) {
	
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
	
	public void openLinkParentInMenu(String title) {
		WebElement weMenu = driver.findElement(By.xpath("//p[text()='"+title+"']//ancestor::li[2]"));
		
		if(!weMenu.getAttribute("class").toString().equalsIgnoreCase("nav-item has-treeview menu-open")) {
			weMenu.click();
		}
	}
	
	public void clickOnLinkInMenu(String title) {
		WebElement weLink = driver.findElement(By.xpath("//p[text()='"+title+"']"));
		driverWait.until(ExpectedConditions.visibilityOf(weLink));
		weLink.click();
	}
	
	public void clickOnNavigationLink(String title) {
		WebElement weLink = driver.findElement(By.xpath("//a[text()='"+title+"']"));
		weLink.click();
	}
	
	public void inputTitle(String title) {
		weInputTitle.clear();
		weInputTitle.sendKeys(title);
	}
	
	public void scroll() {
		WebElement weElement = driver.findElement(By.xpath("//div[@class='dataTables_wrapper dt-bootstrap4 no-footer']"));
	    js.executeScript("arguments[0].scrollIntoView(true);", weElement);
		driverWait.until(ExpectedConditions.visibilityOf(weElement));
	
		try {
		Thread.sleep(1000);
	
		} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	
		}
	}
	public void scrollTillTheEndOfThePage() {
		WebElement weEle = driver.findElement(By.xpath("//footer[@class='main-footer']"));
		js.executeScript("arguments[0].scrollIntoView(true);", weEle);
	}
	public void scrollToTheTopOfThePage(){
		WebElement weEle = driver.findElement(By.xpath("//div[@class='card-body']"));
		js.executeScript("arguments[0].scrollIntoView(true);", weEle);
	}
	
	public String getNoMatchingMessage() {
		try {
			Thread.sleep(1000);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return weNoPost.getText();
	}
	
	public void openAuthor() {
		WebElement weAuthor = driver.findElement(By.xpath("//span [@title='--Choose Author --']//ancestor::span[4]"));
		
		if(!weAuthor.getAttribute("class").toString().equalsIgnoreCase("select2 select2-container select2-container--bootstrap4 select2-container--below select2-container--focus")) {
			weAuthor.click();
		}	
	}
	
	public void inputAuthor(String author) {
		weInputAuthor.sendKeys(author);
	}
	
	public String checkPost(String author) {
		try {
			WebElement webElementPost = driver.findElement(By.xpath("//tbody//td[text() ='"+author+"']"));
			return webElementPost.getText();
		} catch (Exception e) {
			return "";
		}
		
	}
	
	public String checkPostTitle(String title) {
		try {
			WebElement webElementPost = driver.findElement(By.xpath("//td[text()='"+title+"']//ancestor::tr//td[5]"));
			return webElementPost.getText();
		} catch (Exception e) {
			return "";
		}
		
	}
	
	public boolean isPostInList(String title) {
		return !driver.findElements(By.xpath("//td[text()='\"+title+\"']//ancestor::tr//td[5]")).isEmpty();
	}
	
	public String toastMessage() {
		WebElement weToastMessage= driver.findElement(By.xpath("//*[@class='toast-message']"));
	    return weToastMessage.getText();
	}
	
	
	public String getNoResultMessage() {
		return weNoResultMessage.getText();
	}
	
	public void openCategory() {
		WebElement weCategory = driver.findElement(By.xpath("//span [@title='--Choose Category --']//ancestor::span[4]"));
		
		if(!weCategory.getAttribute("class").toString().equalsIgnoreCase("select2 select2-container select2-container--bootstrap4 select2-container--above select2-container--focus")) {
			weCategory.click();
		}	
	}
	public void inputCategory(String category) {
		weInputCategory.sendKeys(category);
	}
	
	public void selectAuthor(String value) {
		Select select = new Select(dropdownAuthor);
		select.selectByValue(value);
	}
	public void Author(String name) {
		Select select = new Select(dropdownAuthor);
		select.selectByValue(name);
	}
	public void selectCategory(String value) {
		Select select = new Select(dropdownCategory);
		select.selectByValue(value);
	}
	public void Category(String name) {
		Select select = new Select(dropdownCategory);
		select.selectByValue(name);
	}


	public void impotrantYes() {
		Select select = new Select(dropdownImportant);
		select.selectByValue("1");
	}
	public void impotrantNo() {
		Select select = new Select(dropdownImportant);
		select.selectByValue("0");
	}
	public void statusEnabled() {
		Select select = new Select(dropdownStatus);
		select.selectByValue("1");
	}
	public void statusDisabled() {
		Select select = new Select(dropdownStatus);
		select.selectByValue("0");
	}
	public void tagField() {
		WebElement wetagField = driver.findElement(By.xpath("//ul[@class='select2-selection__rendered']"));
	}
	public void inputTag(String name) {
		inputTag.sendKeys(name);
		
	}
	public void withTag(String value) {
		Select multipleChoice = new Select(dropdownTag);
		if(multipleChoice.isMultiple()) {
			
	    multipleChoice.selectByValue(value);
		
		}
	}

		public void withTagName(String name) {
			Select multipleChoice = new Select(dropdownTag);
			if(multipleChoice.isMultiple()) {
				
		    multipleChoice.selectByVisibleText(name);
			
			}
	}
	public void findAndClickOnButtonNext() {
		WebElement weDiv =driver.findElement(By.xpath("//div[@id='entities-list-table_paginate']"));
		js.executeScript("arguments[0].scrollIntoView(true);", weDiv);
		driverWait.until(ExpectedConditions.visibilityOf(weDiv));
			
		WebElement weButtonNext=driver.findElement(By.xpath("//a[@class='page-link']//ancestor::ul//li[5]"));
		js.executeScript("arguments[0].scrollIntoView(true);", weButtonNext);
		driverWait.until(ExpectedConditions.visibilityOf(weButtonNext));
			
		try {
				Thread.sleep(1000);
				} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				}
			weButtonNext.click();

			try {
				Thread.sleep(1000);
				} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				}
	
	}
	public void clickOnButtonNext() {
		WebElement weDiv =driver.findElement(By.xpath("//div[@id='entities-list-table_paginate']"));
		driverWait.until(ExpectedConditions.visibilityOf(weDiv));

		WebElement weButtonNext=driver.findElement(By.xpath("//a[@class='page-link']//ancestor::ul//li[5]"));
		driverWait.until(ExpectedConditions.visibilityOf(weButtonNext));
		weButtonNext.click();
	}	
		
	public void searchPostField(String name) {
		weSearchField.sendKeys(name);
	}
		
	public void clickOnAddnewPost() {
		weAddnewPost.click();
		}
	
	public void clickOnField() {
		WebElement weField = driver.findElement(By.xpath("//div[@id='entities-list-table_wrapper']"));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		weField.click();
		}
	
	public void clickOnView() {
		WebElement weEditButton = driver.findElement(By.xpath("//td[.='Simply dummy text of the printing and typesetting industry']//ancestor::tr//td[12]//div[1]//a[@class='btn btn-info']/i[@class='fas fa-eye']"));
		weEditButton.click();

	}
		
	public void clickOnEditPost(){
		WebElement weEditButton = driver.findElement(By.xpath("//td[.='Simply dummy text of the printing and typesetting industry']//ancestor::tr//td[12]//div[1]//a[@class=='btn btn-info']/i[@class='fas fa-edit']"));
		weEditButton.click();

	}
		
	public String getToastMessageEdited() {
		return weToastMessageEdited.getText();
	}
		
	public void returnToPage() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.switchTo().window(PAGE_URL);

	}
	
	public void clicklOnDeletePost() {
		WebElement weDeleteButton = driver.findElement(By.xpath("//td[.='Simply dummy text of the printing and typesetting industry']//ancestor::tr//td[12]//div[1]//button"));		
		weDeleteButton.click();
	}
		
	public void clickOnDialogCancel() {
		driverWait.until(ExpectedConditions.visibilityOf(weDialogCancel));
		weDialogCancel.click();
	}
		
	public void clickOnDialogDelete() {
		driverWait.until(ExpectedConditions.visibilityOf(weDialogDelete));
		weDialogDelete.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public void clickOnMinusButton() {
		WebElement weMinus = driver.findElement(By.xpath("//td[.='Simply dummy text of the printing and typesetting industry']//ancestor::tr//td[12]//div[2]//button[1]"));
		
		weMinus.click();	
	}
	
	public void clickDialogCancelDisableButton() {
		weDisableCancel.click();
		}
		
	public void clickDialogDisableButton() {
		weDialogDisable.click();
		}
		
	public void clickOnCheckButton() {
		WebElement weCheck = driver.findElement(By.xpath("//td[.='Simply dummy text of the printing and typesetting industry']//ancestor::tr//td[12]//div[2]//button[1]"));		
		weCheck.click();	
	}
		
	public void clickOnCancelEnableButton() {
		weEnableCancel.click();
	}
		
	public void clickOnEnableButton() {
		weDialogEnable.click();
	}
		
	public String getToastMessageEnabled() {
		return weToastEnable.getText();
	}
	
	public void clickOnBookmark() {
		WebElement weBookmark = driver.findElement(By.xpath("//td[.='Simply dummy text of the printing and typesetting industry']//ancestor::tr//td[12]//div[2]//button[2]"));		
		weBookmark.click();
	}
	
	public void clickOnBookmarkCancel() {
		weBookmarkCancel.click();
	}
	
	public void clickOnSetAsImportant() {
		weBookmarkImportant.click();
	}
	
	public void clickOnX() {
		WebElement weBookmark = driver.findElement(By.xpath("//td[.='Simply dummy text of the printing and typesetting industry']//ancestor::tr//td[12]//div[2]//button[2]"));		
		weBookmark.click();
	}
	
	public void clickOnUnimportantCancel() {
		weUnimportantCancel.click();
	}
	
	public void clickOnUnimpotrant() {
		weBookmarkUnimportant.click();
	}
	
	public void clickOpenProfile() {
		WebElement weProfileDropdown = driver.findElement(By.xpath("//li[@class='nav-item dropdown']"));
		
		if(!weProfileDropdown.getAttribute("class").toString().equalsIgnoreCase("nav-item dropdown show")) {
			weProfileDropdown.click();
		}	
	}
	
	public void clickProfile() {
		weProfile.click();
	}
	
	public void clickYourProfile() {
		weYourProfile.click();
	}
	
	public void clickLogOut() {
		weLogOut.click();
	}


		

		
	

}
