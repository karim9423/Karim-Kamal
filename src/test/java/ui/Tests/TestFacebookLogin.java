package ui.Tests;

import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.*;
import report.ExtentTestManager;
import ui.pages.HomePage;
import ui.pages.LandingPage;

import java.lang.reflect.Method;

public class TestFacebookLogin extends TestBase {

    LandingPage landingPage;
    HomePage homePage;

    @BeforeClass
    public void openFacebook()
    {
        ExtentTestManager.initializeReport(TestFacebookLogin.class.getName());
        setTestName(TestFacebookLogin.class.getName());
        landingPage = new LandingPage(driver);
    }

    @AfterMethod
    public void resetAndClear()
    {
        landingPage.reload();
    }
    @Test(description = "Login To facebook with valid username and password")
    public void LoginWithValidCeredentials(Method method) {
        ExtentTestManager.startTest(method.getName(), "LoginWithValidUserNameAndPassword");
        landingPage.login(testData.get("username"), testData.get("Password"));
        homePage = new HomePage(driver);
        Assert.assertEquals(homePage.isHomePageLoaded(), true);
    }

    @Test(description = "Login To facebook with invalid username and valid password")
    public void LoginWithInvalidUserNameAndPassword(Method method) {
        ExtentTestManager.startTest(method.getName(), "LoginWithInValidUserNameAndPassword");
        landingPage.login(testData.get("InvalidUsername"), testData.get("Password"));
        Assert.assertEquals(landingPage.isInvalidEmailDisplayed(), true);
    }

    @Test(description = "Login To facebook with valid username and invalid password")
    public void LoginWithUserNameAndinvalidPassword(Method method) {
        ExtentTestManager.startTest(method.getName(), "LoginWithUserNameAndInvalidPassword");
        landingPage.login(testData.get("username"), testData.get("InvalidPassword"));
        Assert.assertEquals(landingPage.isInvalidPasswordDisplayed(), true);
    }
    @Test(description = "Login To facebook with Invalid username and password")
    public void LoginWithInvalidCeredentials(Method method) {
        ExtentTestManager.startTest(method.getName(), "LoginWithInvalidUserNameAndInvalidPassword");
        landingPage.login(testData.get("InvalidUsername"), testData.get("InvalidPassword"));
        Assert.assertEquals(landingPage.isInvalidEmailDisplayed(), true);
    }

    @Test(description = "Login To facebook without typing a username")
    public void LoginWithoutTypingUsername(Method method) {
        ExtentTestManager.startTest(method.getName(), "LoginWithoutUserName");
        landingPage.login("", testData.get("Password"));
        Assert.assertEquals(landingPage.isInvalidEmailDisplayed(), true);
    }

    @Test(description = "Login To facebook without typing a password")
    public void LoginWithoutTypingPassword(Method method) {
        ExtentTestManager.startTest(method.getName(), "LoginWithoutPassword");
        landingPage.login(testData.get("username"),"");
        Assert.assertEquals(landingPage.isInvalidPasswordDisplayed(), true);
    }

    @Test(description = "Click Login button without providing username or password")
    public void LoginWithoutCeredentials(Method method) {
        ExtentTestManager.startTest(method.getName(), "LoginWithoutUsernameAndPassword");
        landingPage.login("","");
        Assert.assertEquals(landingPage.isInvalidEmailDisplayed(), true);
    }

}