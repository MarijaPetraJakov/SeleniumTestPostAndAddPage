package cubes.test;

import static org.junit.Assert.*;
import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cubes.webpages.LoginPage;
import cubes.webpages.posts.PostFormPage;
import cubes.webpages.posts.PostsListPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestPosts {
	
	private static ChromeDriver driver;
	private static WebDriverWait driverWait;
	private static JavascriptExecutor js;
	private static Select select;

	private static LoginPage loginPage;
	private static PostsListPage postsListPage;
	private static PostFormPage postFormPage;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Marija\\Documents\\Desktop\\Web Driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driverWait = new WebDriverWait(driver, Duration.ofMillis(10000));
		js= (JavascriptExecutor) driver;
	

		
		
		loginPage = new LoginPage(driver);
		postsListPage = new PostsListPage(driver, driverWait,js,select);
		postFormPage = new PostFormPage(driver, driverWait, js, select);
		
		loginPage.loginSuccess();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.close();
	}

	@Before
	public void setUp() throws Exception {
		postsListPage.openPage();
	}

	@After
	public void tearDown() throws Exception {
		
	}

	//@Test
	public void tc01TestMenuLinks() {
		checkMenuLink("Sliders list", "https://testblog.kurs-qa.cubes.edu.rs/admin/sliders");
		checkMenuLink("Add Slider", "https://testblog.kurs-qa.cubes.edu.rs/admin/sliders/add");
		checkMenuLink("Post Categories list", "https://testblog.kurs-qa.cubes.edu.rs/admin/post-categories");
		checkMenuLink("Add Post Category", "https://testblog.kurs-qa.cubes.edu.rs/admin/post-categories/add");
		checkMenuLink("Tags list", "https://testblog.kurs-qa.cubes.edu.rs/admin/tags");
		checkMenuLink("Add Tag", "https://testblog.kurs-qa.cubes.edu.rs/admin/tags/add");
		checkMenuLink("Posts list", "https://testblog.kurs-qa.cubes.edu.rs/admin/posts");
		checkMenuLink("Add Post", "https://testblog.kurs-qa.cubes.edu.rs/admin/posts/add");
		checkMenuLink("Comments List", "https://testblog.kurs-qa.cubes.edu.rs/admin/comments");
		checkMenuLink("Users List", "https://testblog.kurs-qa.cubes.edu.rs/admin/users");
		checkMenuLink("Add User", "https://testblog.kurs-qa.cubes.edu.rs/admin/users/add");
	}

	
	@Test
	public void tc06TestSeeAllPosts() {

		postsListPage.findAndClickOnButtonNext();
		postsListPage.clickOnButtonNext();
	}
	
	@Test
	public void tc07TestSearchEnterTitle() {
		postsListPage.openPage();
		postsListPage.inputTitle("abcabc");
		
		postsListPage.scroll();
		
	}
	
	@Test
	public void tc08TestAuthorField() {
		postsListPage.inputTitle("abcabc");
		postsListPage.openAuthor();
		postsListPage.inputAuthor("abc");
		
		assertEquals(postsListPage.getNoResultMessage(), "No results found");
		
		postsListPage.scroll();
		assertEquals(postsListPage.getNoMatchingMessage(), "No matching records found");
		
	}
	
	@Test
	public void tc09TestCategory() {
		postsListPage.inputTitle("abcabc");
		postsListPage.openCategory();
		postsListPage.inputCategory("abc");
		

		assertEquals(postsListPage.getNoResultMessage(), "No results found");
		
		postsListPage.scroll();
		assertEquals(postsListPage.getNoMatchingMessage(), "No matching records found");

	}
	
	@Test
	public void tc10TestAuthorList() {
		postsListPage.selectAuthor("1");
		postsListPage.scrollTillTheEndOfThePage();
		
		String expectedPost = postsListPage.checkPost("Polaznik Kursa");
		assertEquals("Polaznik kursa", expectedPost);
	}
	
	@Test
	public void tc11TestAuthorAndCategory(){
		postsListPage.scrollToTheTopOfThePage();
		postsListPage.selectAuthor("1");
		postsListPage.selectCategory("1");
		
		postsListPage.scroll();
		
		String expectedPost = postsListPage.checkPost("Polaznik Kursa");
		assertEquals("Polaznik Kursa",expectedPost);
	}
	
	@Test
	public void tc12TestAuthorAndCategory(){
		postsListPage.scrollToTheTopOfThePage();
		postsListPage.selectAuthor("1");
		postsListPage.selectCategory("2");
		
		postsListPage.scroll();
		
		String expectedPost = postsListPage.checkPost("Polaznik Kursa");
		assertEquals( expectedPost,"Polaznik Kursa");
		
	}
	
	@Test
	public void tc13TestAuthorAndCategory(){
		postsListPage.scrollToTheTopOfThePage();
		postsListPage.selectAuthor("1");
		postsListPage.selectCategory("3");
		
		postsListPage.scroll();
		
		String expectedPost = postsListPage.checkPost("Polaznik Kursa");
		assertEquals("Polaznik Kursa", expectedPost);
	}
	
	@Test
	public void tc14TestAuthorAndCategory(){
		postsListPage.scrollToTheTopOfThePage();
		postsListPage.selectAuthor("1");
		postsListPage.selectCategory("4");
		
		postsListPage.scroll();
		assertEquals(postsListPage.getNoMatchingMessage(), "No matching records found");
		
	}
	
	@Test
	public void tc15TestAuthorAndCategory(){
		postsListPage.scrollToTheTopOfThePage();
		postsListPage.selectAuthor("1");
		postsListPage.selectCategory("5");
		
        postsListPage.scroll();
		
		String expectedPost = postsListPage.checkPost("Polaznik Kursa");
		assertEquals("Polaznik Kursa", expectedPost);
		
	}
	
	@Test
	public void tc16TestAuthorAndCategory(){
		postsListPage.scrollToTheTopOfThePage();
		postsListPage.selectAuthor("1");
		postsListPage.selectCategory("6");
		
		postsListPage.scroll();
		
		assertEquals(postsListPage.getNoMatchingMessage(), "No matching records found");

	}

	@Test
	public void tc17TestAuthorAndCategory(){
		postsListPage.scrollToTheTopOfThePage();
		postsListPage.selectAuthor("1");
		postsListPage.selectCategory("1");
		postsListPage.impotrantYes();
		
		postsListPage.scroll();
		
		String expectedPost = postsListPage.checkPost("Polaznik Kursa");
		assertEquals( expectedPost,"Polaznik Kursa");
	}
	
	@Test
	public void tc18TestAuthorAndCategory(){
		postsListPage.selectAuthor("1");
		postsListPage.selectCategory("1");
		postsListPage.impotrantNo();
		
		postsListPage.scroll();
		
		String expectedPost = postsListPage.checkPost("Polaznik Kursa");
		assertEquals("Polaznik Kursa", expectedPost);
		
	}
	
	@Test
	public void tc19TestAuthorAndCategory(){
		postsListPage.scrollToTheTopOfThePage();
		postsListPage.selectAuthor("1");
		postsListPage.selectCategory("1");
		postsListPage.impotrantYes();
		postsListPage.statusEnabled();
	
		postsListPage.scroll();
		
		String expectedPost = postsListPage.checkPost("Polaznik Kursa");
		assertEquals("Polaznik Kursa", expectedPost);
	}
   
	@Test
	public void tc20TestAuthorAndCategory(){
		postsListPage.selectAuthor("1");
		postsListPage.selectCategory("1");
		postsListPage.impotrantYes();
		postsListPage.statusDisabled();
	
		postsListPage.scroll();
		assertEquals(postsListPage.getNoMatchingMessage(), "No matching records found");
				
	}
	
	@Test
	public void tc21TestAllTag() {
	    postsListPage.withTag("7");
	    postsListPage.withTag("8");
	    postsListPage.withTag("9");
		
		postsListPage.scroll();
				
	}

	@Test
	public void tc22TestEnterTag() {
		postsListPage.tagField();
		postsListPage.inputTag("abc");
		
		assertEquals(postsListPage.getNoResultMessage(), "No results found");

	}
   
	@Test
	public void tc23Test()  {
		postsListPage.selectAuthor("2");
		
		postsListPage.scrollTillTheEndOfThePage();
		
		String expectedPost = postsListPage.checkPost("Vladan Dzulovic");
		assertEquals(expectedPost,"Vladan Dzulovic");

	}

	@Test
	public void tc24Test()  {
		postsListPage.selectAuthor("2");
		postsListPage.selectCategory("1");
		
		postsListPage.scroll();
		
		String expectedPost = postsListPage.checkPost("Vladan Dzulovic");
		assertEquals(expectedPost,"Vladan Dzulovic");

	}

	@Test
	public void tc25Test()  {
		postsListPage.selectAuthor("2");
		postsListPage.selectCategory("2");
		
		postsListPage.scroll();
		
		String expectedPost = postsListPage.checkPost("Vladan Dzulovic");
		assertEquals("Vladan Dzulovic",expectedPost);

	}
	
	@Test
	public void tc26Test()  {
		postsListPage.selectAuthor("2");
		postsListPage.selectCategory("3");
		
		postsListPage.scroll();
		
		String expectedPost = postsListPage.checkPost("Vladan Dzulovic");
		assertEquals(expectedPost,"Vladan Dzulovic");

	}
	
	@Test
	public void tc27Test()  {
		postsListPage.selectAuthor("2");
		postsListPage.selectCategory("4");
		
		postsListPage.scroll();
		
		String expectedPost = postsListPage.checkPost("Vladan Dzulovic");
		assertEquals("Vladan Dzulovic",expectedPost);

	}
	
	@Test
	public void tc28Test()  {
		postsListPage.selectAuthor("2");
		postsListPage.selectCategory("5");
		
		postsListPage.scroll();
		
		String expectedPost = postsListPage.checkPost("Vladan Dzulovic");
		assertEquals("Vladan Dzulovic",expectedPost);

	}
	
	@Test
	public void tc29Test()  {
		postsListPage.selectAuthor("2");
		postsListPage.selectCategory("6");
		
		postsListPage.scroll();
		
		String expectedPost = postsListPage.checkPost("Vladan Dzulovic");
		assertEquals("Vladan Dzulovic",expectedPost);
	}

	@Test
	public void tc30Test()  {
		postsListPage.selectAuthor("3");
		
		postsListPage.scrollTillTheEndOfThePage();
		
		String expectedPost = postsListPage.checkPost("Marko Dragonjic");
		assertEquals(expectedPost,"Marko Dragonjic");
	}
	
	@Test
	public void tc31Test()  {
		postsListPage.selectAuthor("3");
		postsListPage.selectCategory("1");
		
		postsListPage.scroll();
		
		assertEquals(postsListPage.getNoMatchingMessage(), "No matching records found");

	}
	
	@Test
	public void tc32Test()  {
		postsListPage.selectAuthor("3");
		postsListPage.selectCategory("2");
		
		postsListPage.scroll();
		
		assertEquals(postsListPage.getNoMatchingMessage(), "No matching records found");

	}
	
	@Test
	public void tc33Test()  {
		postsListPage.selectAuthor("3");
		postsListPage.selectCategory("3");
		
		postsListPage.scroll();
		
		assertEquals(postsListPage.getNoMatchingMessage(), "No matching records found");

	}
	
	@Test
	public void tc34Test()  {
		postsListPage.selectAuthor("3");
		postsListPage.selectCategory("4");
		
		postsListPage.scroll();
		
		assertEquals(postsListPage.getNoMatchingMessage(), "No matching records found");

	}

	@Test
	public void tc35Test()  {
		postsListPage.selectAuthor("3");
		postsListPage.selectCategory("5");
		
		postsListPage.scroll();
		
		String expectedPost = postsListPage.checkPost("Marko Dragonjic");
		assertEquals("Marko Dragonjic",expectedPost);
	}
	
	@Test
	public void tc36Test()  {
		postsListPage.selectAuthor("3");
		postsListPage.selectCategory("6");
		
		postsListPage.scroll();
		
		String expectedPost = postsListPage.checkPost("Marko Dragonjic");
		assertEquals("Marko Dragonjic",expectedPost);
	}
	
	@Test
	public void tc37Test()  {
		postsListPage.selectAuthor("4");
		
		postsListPage.scroll();
		
		assertEquals(postsListPage.getNoMatchingMessage(), "No matching records found");

	}
	
	@Test
	public void tc38TestSearchField()  {
		postsListPage.searchPostField("Perspiciatis perspiciatis quo ut quia.");
		postsListPage.scroll();

		assertEquals(postsListPage.checkPostTitle("Perspiciatis perspiciatis quo ut quia."),"Perspiciatis perspiciatis quo ut quia.");

	}
	
	@Test
	public void tc39TestaddnewPost() {
		postsListPage.clickOnAddnewPost();
	}
	
	@Test
	public void tc40TestNavigationLink() {
		checkNavigationLink("Home", "https://testblog.kurs-qa.cubes.edu.rs/admin");
	}
	
	@Test
	public void tc41TestNavigationLink() {
		postFormPage.openPage();
		checkNavigationLink("Post", "https://testblog.kurs-qa.cubes.edu.rs/admin/posts");
	}
	
	@Test
	public void tc42TestClickOnCancelButton() {
		postsListPage.clickOnAddnewPost();
		postFormPage.clickOnCancelButton();
	}

	@Test
	public void tc43TestSaveWhitEmptyField() {
		postsListPage.clickOnAddnewPost();
		postFormPage.clickOnSaveButton();
		
		assertEquals(postFormPage.getRequiredMassage(), "This field is required.");
	}
	
	@Test
	public void tc44TestChooesCategory() {
		postsListPage.clickOnAddnewPost();
		postFormPage.chooseCategory();
		postsListPage.selectCategory("4");
		postFormPage.clickOnSaveButton();
		
		assertEquals(postFormPage.getRequiredMassage(), "This field is required.");

	}
	
	@Test
	public void tc45TestEnterInvalidTitleAndSave() {
		postsListPage.clickOnAddnewPost();
		postsListPage.inputTitle("abc");
		postFormPage.clickOnSaveButton();
		postsListPage.scrollToTheTopOfThePage();
		
		assertEquals( postFormPage.getErrorTitle(), "Please enter at least 20 characters.");
	}

    @Test
	public void tc46TestEnterValidTitleAndSave() {
		postsListPage.clickOnAddnewPost();
		postsListPage.inputTitle("Simply dummy text of the printing and typesetting industry");
		postFormPage.clickOnSaveButton();
		postsListPage.scrollToTheTopOfThePage();
		
		assertEquals(postFormPage.getRequiredMassage(), "This field is required.");
	}
	
	@Test
	public void tc47TestEnterInvalidDescriptionAndSaveValid() {
		postsListPage.clickOnAddnewPost();
		postFormPage.enterDescription("abc");
		postFormPage.clickOnSaveButton();
		postsListPage.scrollToTheTopOfThePage();
			
		assertEquals(postFormPage.getErrorMassageDescription(), "Please enter at least 50 characters.");

	}
	
	@Test
	public void tc48TestEnterDescriptionAndSave() {
		postsListPage.clickOnAddnewPost();
		postFormPage.enterDescription("when an unknown printer took a galley of type and scrambled it to make a type specimen book");
		postFormPage.clickOnSaveButton();
		postsListPage.scrollToTheTopOfThePage();
		
		assertEquals(postFormPage.getRequiredMassage(), "This field is required.");

	}
	
	@Test
	public void tc49TestSaveWhitInvalidTitleAndDescription() {
		postsListPage.clickOnAddnewPost();
		postFormPage.chooseCategory();
		postsListPage.selectCategory("2");
		postsListPage.inputTitle("abc");
		postFormPage.enterDescription("abc");
		postFormPage.clickOnSaveButton();
		postsListPage.scrollToTheTopOfThePage();
		
		assertEquals( postFormPage.getErrorTitle(), "Please enter at least 20 characters.");
		assertEquals(postFormPage.getErrorMassageDescription(), "Please enter at least 50 characters.");

	}
	
	@Test
	public void tc50TestSaveWhitValidTitleAndDescription() {
		postsListPage.clickOnAddnewPost();
		postFormPage.chooseCategory();
		postsListPage.selectCategory("2");
		postsListPage.inputTitle(" Simply dummy text of the printing and typesetting industry");
		postFormPage.enterDescription("when an unknown printer took a galley of type and scrambled it to make a type specimen book");
		postFormPage.clickOnSaveButton();
		postsListPage.scrollToTheTopOfThePage();
		
		assertEquals(postFormPage.getRequiredMassage(), "This field is required.");

	}
	
	@Test
	public void tc51TestImportantRadioButton() {
		postsListPage.clickOnAddnewPost();
		postFormPage.importantYes();
		postFormPage.clickOnSaveButton();
		postsListPage.scrollToTheTopOfThePage();
		
		assertEquals(postFormPage.getRequiredMassage(), "This field is required.");

	}
	
	@Test
	public void tc52TestChooseTagsAndSave() {
		postsListPage.clickOnAddnewPost();
		postFormPage.tags("minus");
		postFormPage.tags("eos");
		postFormPage.clickOnSaveButton();
		postsListPage.scrollToTheTopOfThePage();
		
		assertEquals(postFormPage.getRequiredMassage(), "This field is required.");

	}
	
	@Test
	public void tc53TestChooseFotoAndSave() {
		postsListPage.clickOnAddnewPost();
		postFormPage.chooseFotoClick();
		postFormPage.clickOnSaveButton();
		postsListPage.scrollToTheTopOfThePage();
		
		assertEquals(postFormPage.getRequiredMassage(), "This field is required.");

	}
	
	@Test
	public void tc54TestInputContent() {
		postsListPage.clickOnAddnewPost();
		postFormPage.clickOnContentArea();
		postFormPage.enterContent("anupea");
		postsListPage.scrollTillTheEndOfThePage();
		postFormPage.clickOnSaveButton();
		postsListPage.scrollToTheTopOfThePage();
		
		assertEquals(postFormPage.getRequiredMassage(), "This field is required.");
		
	}

	@Test
	public void tc55TestSaveWhitInvalidTitleAndDescriptionAndTags() {
		postsListPage.clickOnAddnewPost();
		postFormPage.chooseCategory();
		postsListPage.selectCategory("2");
		postsListPage.inputTitle("abc");
		postFormPage.enterDescription("abc");
		postFormPage.importantNo();
		postFormPage.tags("minus");
		postFormPage.tags("eos");

		assertEquals( postFormPage.getErrorTitle(), "Please enter at least 20 characters.");
		assertEquals(postFormPage.getErrorMassageDescription(), "Please enter at least 50 characters.");
	}
	
	@Test
	public void tc56TestSaveWhitValidTitleAndDescriptionTagsPhoto() {
		postsListPage.clickOnAddnewPost();
		postFormPage.chooseCategory();
		postsListPage.selectCategory("2");
		postsListPage.inputTitle("Simply dummy text of the printing and typesetting industry");
		postFormPage.enterDescription("when an unknown printer took a galley of type and scrambled it to make a type specimen book");
		postFormPage.importantNo();
		postFormPage.tags("minus");
		postFormPage.tags("eos");
		postFormPage.chooseFotoClick();
		postFormPage.clickOnSaveButton();

		assertEquals(postFormPage.getErrorContent(), "The content field is required.");
		
	}
	
	@Test
	public void tc57TestSaveWhitAllvalidData() {
		postsListPage.clickOnAddnewPost();
		postFormPage.chooseCategory();
		postsListPage.selectCategory("2");
		postsListPage.inputTitle("Simply dummy text of the printing and typesetting industry");
		postFormPage.enterDescription("when an unknown printer took a galley of type and scrambled it to make a type specimen book");
		postFormPage.importantNo();
		postFormPage.tags("minus");
		postFormPage.tags("eos");
		postFormPage.chooseFotoClick();
		postFormPage.clickOnContentArea();
		postFormPage.enterContent("anupea");
		postFormPage.clickOnSaveButton();
		
		assertEquals(postsListPage.toastMessage(), "New post has been added");
	}
	
	@Test
	public void tc58TestIsPostInList() {
		postsListPage.findAndClickOnButtonNext();
		postsListPage.clickOnButtonNext();

		assertEquals("Simply dummy text of the printing and typesetting industry", postsListPage.checkPostTitle("Simply dummy text of the printing and typesetting industry"));
	}
		
	@Test
	public void tc59TestViewPost() {
		postsListPage.findAndClickOnButtonNext();
		postsListPage.clickOnButtonNext();
		postsListPage.scrollToTheTopOfThePage();
		postsListPage.scroll();
		postsListPage.clickOnField();
		postsListPage.clickOnView();
		
		postsListPage.returnToPage();
	}
		
	@Test
	public void tc60TestEditPostDeletePhoto() {
		postsListPage.findAndClickOnButtonNext();
		postsListPage.clickOnButtonNext();
		postsListPage.scrollToTheTopOfThePage();
		postsListPage.scroll();
		postsListPage.clickOnField();
		postsListPage.clickOnEditPost();
		postFormPage.deletePhoto();
		
		assertEquals("Error while deleteing photo", postFormPage.getDivToastMessage());
		
	}
	
	@Test
	public void tc61TestEditPost() {
		postsListPage.findAndClickOnButtonNext();
		postsListPage.clickOnButtonNext();
		postsListPage.scrollToTheTopOfThePage();
		postsListPage.scroll();
		postsListPage.clickOnField();
		postsListPage.clickOnEditPost();
		postFormPage.chooseCategory();
		postsListPage.selectCategory("4");
		postFormPage.tags("Tag 711");
		postFormPage.tags("Tag 17");
		postFormPage.clickOnSaveButton();
		
		assertEquals(postsListPage.getToastMessageEdited(), "Post has been edited");
	}
	
	@Test
	public void tc62TestDisablePost() {
		postsListPage.findAndClickOnButtonNext();
		postsListPage.clickOnButtonNext();
		postsListPage.scrollToTheTopOfThePage();
		postsListPage.scroll();
		postsListPage.clickOnField();
		postsListPage.clickOnMinusButton();
	
	}
	
	@Test
	public void tc63TestDisablePostClickOnCancel() {
		postsListPage.findAndClickOnButtonNext();
		postsListPage.clickOnButtonNext();
		postsListPage.scrollToTheTopOfThePage();
		postsListPage.scroll();
		postsListPage.clickOnField();
		postsListPage.clickOnMinusButton();
		postsListPage.clickDialogCancelDisableButton();
		
		assertEquals("Simply dummy text of the printing and typesetting industry",postsListPage.checkPostTitle("Simply dummy text of the printing and typesetting industry"));

	}
	
	@Test
	public void tc64TestDisablePostClickOnDisable() {
		postsListPage.findAndClickOnButtonNext();
		postsListPage.clickOnButtonNext();
		postsListPage.scrollToTheTopOfThePage();
		postsListPage.scroll();
		postsListPage.clickOnField();
		postsListPage.clickOnMinusButton();
		postsListPage.clickDialogDisableButton();
		
		assertEquals(postsListPage.toastMessage(), "Post has been disabled");

	}
	
	@Test
	public void tc65TestEnablePost() {
		postsListPage.findAndClickOnButtonNext();
		postsListPage.clickOnButtonNext();
		postsListPage.scrollToTheTopOfThePage();
		postsListPage.scroll();
		postsListPage.clickOnField();
		postsListPage.clickOnCheckButton();
		
	}
	
	@Test
	public void tc66TestEnableCancel() {
		postsListPage.findAndClickOnButtonNext();
		postsListPage.clickOnButtonNext();
		postsListPage.scrollToTheTopOfThePage();
		postsListPage.scroll();
		postsListPage.clickOnField();
		postsListPage.clickOnCheckButton();
		postsListPage.clickOnCancelEnableButton();
	
		assertEquals("Simply dummy text of the printing and typesetting industry",postsListPage.checkPostTitle("Simply dummy text of the printing and typesetting industry"));

	}
	
	@Test
	public void tc67TestEnablePost() {
		postsListPage.findAndClickOnButtonNext();
		postsListPage.clickOnButtonNext();
		postsListPage.scrollToTheTopOfThePage();
		postsListPage.scroll();
		postsListPage.clickOnField();
		postsListPage.clickOnCheckButton();
		postsListPage.clickOnEnableButton();
		
		assertEquals(postsListPage.getToastMessageEnabled(), "Post has been enabled");
	}
		
	@Test
	public void tc68TestClickOnBookmark() {
		postsListPage.findAndClickOnButtonNext();
		postsListPage.clickOnButtonNext();
		postsListPage.scrollToTheTopOfThePage();
		postsListPage.scroll();
		postsListPage.clickOnField();
		postsListPage.clickOnBookmark();
	}
	
	@Test
	public void tc69TestBookmarkCancel() {
		postsListPage.findAndClickOnButtonNext();
		postsListPage.clickOnButtonNext();
		postsListPage.scrollToTheTopOfThePage();
		postsListPage.scroll();
		postsListPage.clickOnField();
		postsListPage.clickOnBookmark();
		postsListPage.clickOnBookmarkCancel();
		
		assertEquals("Simply dummy text of the printing and typesetting industry",postsListPage.checkPostTitle("Simply dummy text of the printing and typesetting industry"));

	}
	
	@Test
	public void tc70TestbookmarkImportant() {
		postsListPage.findAndClickOnButtonNext();
		postsListPage.clickOnButtonNext();
		postsListPage.scrollToTheTopOfThePage();
		postsListPage.scroll();
		postsListPage.clickOnField();
		postsListPage.clickOnBookmark();
		postsListPage.clickOnSetAsImportant();
		
		assertEquals(postsListPage.getToastMessageEnabled(), "Post has been set as important");

	}
	
	@Test
	public void tc71TestUnimportantDialogBox() {
		postsListPage.findAndClickOnButtonNext();
		postsListPage.clickOnButtonNext();
		postsListPage.scrollToTheTopOfThePage();
		postsListPage.scroll();
		postsListPage.clickOnField();
		postsListPage.clickOnX();
	}
	
	@Test
	public void tc72TestUnimportantDialogCancel() {
		postsListPage.findAndClickOnButtonNext();
		postsListPage.clickOnButtonNext();
		postsListPage.scrollToTheTopOfThePage();
		postsListPage.scroll();
		postsListPage.clickOnField();
		postsListPage.clickOnX();
		postsListPage.clickOnUnimportantCancel();
		
		assertEquals("Simply dummy text of the printing and typesetting industry",postsListPage.checkPostTitle("Simply dummy text of the printing and typesetting industry"));

	}
	
	@Test
	public void tc73TestnUnimportantDialogUnimportant() {
		postsListPage.findAndClickOnButtonNext();
		postsListPage.clickOnButtonNext();
		postsListPage.scrollToTheTopOfThePage();
		postsListPage.scroll();
		postsListPage.clickOnField();
		postsListPage.clickOnX();
		postsListPage.clickOnUnimpotrant();
		
		assertEquals(postsListPage.getToastMessageEnabled(), "Post has been set as unimportand");

	}
	
	@Test
	public void tc74TestClicOnDeletePost() {
		postsListPage.findAndClickOnButtonNext();
		postsListPage.clickOnButtonNext();
		postsListPage.scrollToTheTopOfThePage();
		postsListPage.scroll();
		postsListPage.clickOnField();
		postsListPage.clicklOnDeletePost();
		
	}
	
	@Test
	public void tc75TestDeletePostDialogCancel() {
		postsListPage.findAndClickOnButtonNext();
		postsListPage.clickOnButtonNext();
		postsListPage.scrollToTheTopOfThePage();
		postsListPage.scroll();
		postsListPage.clickOnField();
		postsListPage.clicklOnDeletePost();
		postsListPage.clickOnDialogCancel();
		
		assertEquals("Simply dummy text of the printing and typesetting industry",postsListPage.checkPostTitle("Simply dummy text of the printing and typesetting industry"));

		
	}
	
	@Test
	public void tc76TestDeletePostDialogDelete() {
		postsListPage.findAndClickOnButtonNext();
		postsListPage.clickOnButtonNext();
		postsListPage.scrollToTheTopOfThePage();
		postsListPage.scroll();
		postsListPage.clickOnField();
		postsListPage.clicklOnDeletePost();
		postsListPage.clickOnDialogDelete();
		
		
		assertEquals("Post has been deleted", postsListPage.getToastMessageEnabled());
		assertEquals(false, postsListPage.isPostInList("Simply dummy text of the printing and typesetting industry"));
		
	}

	


	@Test
	public void tc77TestOpenProfile() {
		postsListPage.clickOpenProfile();
	}
	
	@Test
	public void tc78TestClickProfile() {
		postsListPage.clickOpenProfile();
		postsListPage.clickProfile();
	}
	
	@Test
	public void tc79TestClickYourProfile() {
		postsListPage.clickOpenProfile();
		postsListPage.clickYourProfile();
		
		assertEquals(driver.getCurrentUrl(),"https://testblog.kurs-qa.cubes.edu.rs/admin/users");

	}
	
	@Test
	public void tc80TestClickLogout() {
		postsListPage.clickOpenProfile();
		postsListPage.clickLogOut();
		
		assertEquals(driver.getCurrentUrl(),"https://testblog.kurs-qa.cubes.edu.rs/login");
	}

	

	
	
	
	
	









	
	
	
	
	
	
	
	
	
	
public void checkMenuLink(String title,String url) {
		
		postsListPage.openLinkParentInMenu(title);
		
		postsListPage.clickOnLinkInMenu(title);
		
		assertEquals(driver.getCurrentUrl(), url,"Bad url for "+title);
		
		postsListPage.openPage();
	}
	
	public void checkNavigationLink(String title, String url) {
		
		postsListPage.clickOnNavigationLink(title);
		
		assertEquals(driver.getCurrentUrl(), url,"Bad url for "+title);

		postsListPage.openPage();
	}
	


}
