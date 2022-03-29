package ui.pages;

import base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPage extends PageBase {

    By txtBox_Username = By.name("email");
    By txtBox_Password = By.id("pass");
    By btn_Login = By.name("login");
    By btn_CreateAccount = By.xpath("//a[@data-testid=\"open-registration-form-button\"]");
    By txtBox_FirstName = By.name("firstname");
    By txtBox_LastName = By.name("lastname");
    By txtBox_EmailPhone = By.name("reg_email__");
    By txtBox_confirmEmail = By.name("reg_email_confirmation__");
    By txtBox_NewPassword = By.id("password_step_input");
    By cmbBox_BirthDay=By.id("day");
    By cmbBox_BirthMonth=By.id("month");
    By cmbBox_BirthYear=By.id("year");
    By btn_Signup=By.name("websubmit");
    By txtBox_confirmCode = By.id("recovery_code_entry");
    By txt_InvalidEmail = By.xpath("//div[contains(text(),\"isn't connected to an account.\")]");
    By txt_InvalidPassword = By.xpath("//div[text()=\"The password that you've entered is incorrect. \"]");
    By icn_Mandatory = By.xpath("//i[contains(@class,\"img sp_98fCI7IVTTz sx_54513f\")]");

    public LandingPage(WebDriver driver) {
	super(driver);
    }

    public void login(String username, String password) {
	setElement(txtBox_Username, username);
    setElementPassword(txtBox_Password, password.getBytes(),"Password");
    clickElement(btn_Login);
    }

    public void Register(String firstName,String lastName,String email,String newPassword,String birthDay,String birthMonth,String birthYear,String gender)
    {
        clickElement(btn_CreateAccount);
        setElement(txtBox_FirstName,firstName);
        setElement(txtBox_LastName,lastName);
        setElement(txtBox_EmailPhone,email);
        if (isElementDisplayed(txtBox_confirmEmail)) {
            setElement(txtBox_confirmEmail, email);
        }
        setElementPassword(txtBox_NewPassword,newPassword.getBytes(),"NewPassword");
        selectItemFromList(cmbBox_BirthDay,birthDay);
        selectItemFromList(cmbBox_BirthMonth,birthMonth);
        selectItemFromList(cmbBox_BirthYear,birthYear);
        if (!gender.isBlank()) {
            clickElement(By.xpath(String.format("//label[text()=\"%s\"]//parent::span//input", gender)));
        }
        clickElement(btn_Signup);
    }

    public boolean isConfirmRegisterationdisplayed()
    {
        return WaitForElementToBeVisible(txtBox_confirmCode);
    }

    public boolean isInvalidEmailDisplayed()
    {
        return WaitForElementToBeVisible(txt_InvalidEmail);
    }
    public boolean isInvalidPasswordDisplayed()
    {
        return WaitForElementToBeVisible(txt_InvalidPassword);
    }

    public void reload() {
        driver.manage().deleteAllCookies();
        driver.get("https://www.facebook.com");
    }
    public boolean isAllFieldRequiredDisplayed()
    {
       return getElements(icn_Mandatory).size()==9;
    }

    public boolean isFieldMissingOrInvalidDisplayed(String missingField)
    {
        return WaitForElementToBeVisible(By.xpath(String.format("//div[text()=\"%s\"]", missingField)));
    }
}
