package ui.Tests;

import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import report.ExtentTestManager;
import ui.pages.LandingPage;

import java.lang.reflect.Method;

public class TestFacebookRegistration extends TestBase {

    LandingPage landingPage;


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

    @Test(description = "Register to facebook with valid information")
    public void newRegistration(Method method) {
        ExtentTestManager.startTest(method.getName(), "RegisterToFacebookValidInformation");
      landingPage.Register(testData.get("FirstName"),testData.get("LastName"),testData.get("Email"),
              testData.get("NewPassword"),testData.get("BirthDay"),testData.get("BirthMonth"),
              testData.get("BirthYear"),testData.get("Gender"));
        Assert.assertEquals(landingPage.isConfirmRegisterationdisplayed(),true);
    }

    @Test(description = "Click on Signup Without entering FirstName")
    public void RegisterWithoutName(Method method) {
        ExtentTestManager.startTest(method.getName(), "RegisterToFacebookWithoutFirstName");
        landingPage.Register("",testData.get("LastName"),testData.get("Email"),
                testData.get("NewPassword"),testData.get("BirthDay"),testData.get("BirthMonth"),
                testData.get("BirthYear"),testData.get("Gender"));
        Assert.assertEquals(landingPage.isFieldMissingOrInvalidDisplayed(testData.get("MissingName")),true);
    }

    @Test(description = "Click on Signup Without entering LastName")
    public void RegisterWithoutLastName(Method method) {
        ExtentTestManager.startTest(method.getName(), "RegisterToFacebookWithoutLastName");
        landingPage.Register(testData.get("FirstName"),"",testData.get("Email"),
                testData.get("NewPassword"),testData.get("BirthDay"),testData.get("BirthMonth"),
                testData.get("BirthYear"),testData.get("Gender"));
        Assert.assertEquals(landingPage.isFieldMissingOrInvalidDisplayed(testData.get("MissingName")),true);
    }

    @Test(description = "Click on Signup Without entering Email")
    public void RegisterWithoutEmail(Method method) {
        ExtentTestManager.startTest(method.getName(), "RegisterToFacebookWithoutEmail");
        landingPage.Register(testData.get("FirstName"),testData.get("LastName"),"",
                testData.get("NewPassword"),testData.get("BirthDay"),testData.get("BirthMonth"),
                testData.get("BirthYear"),testData.get("Gender"));
        Assert.assertEquals(landingPage.isFieldMissingOrInvalidDisplayed(testData.get("MissingEmail")),true);
    }

    @Test(description = "Click on Signup Without entering Password")
    public void RegisterWithoutPassword(Method method) {
        ExtentTestManager.startTest(method.getName(), "RegisterToFacebookWithoutPassword");
        landingPage.Register(testData.get("FirstName"),testData.get("LastName"),testData.get("Email"),
                "",testData.get("BirthDay"),testData.get("BirthMonth"),
                testData.get("BirthYear"),testData.get("Gender"));
        Assert.assertEquals(landingPage.isFieldMissingOrInvalidDisplayed(testData.get("MissingPassword")),true);
    }

    @Test(description = "Click on Signup Without entering Age")
    public void RegisterWithoutAge(Method method) {
        ExtentTestManager.startTest(method.getName(), "RegisterToFacebookWithoutAge");
        landingPage.Register(testData.get("FirstName"),testData.get("LastName"),testData.get("Email"),
                testData.get("NewPassword"),"","",
                "",testData.get("Gender"));
        Assert.assertEquals(landingPage.isFieldMissingOrInvalidDisplayed(testData.get("WrongAge")),true);
    }

    @Test(description = "Click on Signup Without entering Gender")
    public void RegisterWithoutGender(Method method) {
        ExtentTestManager.startTest(method.getName(), "RegisterToFacebookWithoutGender");
        landingPage.Register(testData.get("FirstName"),testData.get("LastName"),testData.get("Email"),
                testData.get("NewPassword"),testData.get("BirthDay"),testData.get("BirthMonth"),
                testData.get("BirthYear"),"");
        Assert.assertEquals(landingPage.isFieldMissingOrInvalidDisplayed(testData.get("MissingGender")),true);
    }

    @Test(description = "Click on Signup With entering weak Password")
    public void RegisterWithWrongPassword(Method method) {
        ExtentTestManager.startTest(method.getName(), "RegisterToFacebookWithWeakPassword");
        landingPage.Register(testData.get("FirstName"),testData.get("LastName"),testData.get("Email"),
                testData.get("WeakPassword"),testData.get("BirthDay"),testData.get("BirthMonth"),
                testData.get("BirthYear"),testData.get("Gender"));
        Assert.assertEquals(landingPage.isFieldMissingOrInvalidDisplayed(testData.get("WrongPassword")),true);
    }

    @Test(description = "Click on Signup With entering invalid Email Fomrat")
    public void RegisterWithInvalidFormatEmail(Method method) {
        ExtentTestManager.startTest(method.getName(), "RegisterToFacebookWithInvalidFormatEmail");
        landingPage.Register(testData.get("FirstName"),testData.get("LastName"),testData.get("InvalidFormatEmail"),
                testData.get("NewPassword"),testData.get("BirthDay"),testData.get("BirthMonth"),
                testData.get("BirthYear"),testData.get("Gender"));
        Assert.assertEquals(landingPage.isFieldMissingOrInvalidDisplayed(testData.get("WrongEmail")),true);
    }

    @Test(description = "Click on Signup With entering data")
    public void RegisterWithNoData(Method method) {
        ExtentTestManager.startTest(method.getName(), "RegisterToEmptyData");
        landingPage.Register("","","",
                "","","",
                "","");
        Assert.assertEquals(landingPage.isAllFieldRequiredDisplayed(),true);
    }
}