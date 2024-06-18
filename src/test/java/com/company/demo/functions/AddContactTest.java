package com.company.demo.functions;

import com.company.demo.base.BaseTest;
import com.company.demo.pages.method.ContactDetailsPage;
import com.company.demo.pages.method.CreateContactPage;
import com.company.demo.ultis.AssertUtility;
import com.company.demo.ultis.StringUtility;
import org.testng.annotations.Test;

public class AddContactTest extends BaseTest {
    private CreateContactPage createContactPage;
    private ContactDetailsPage contactDetailsPage;
    String name, phoneNumber;

    @Override
    public void preCondition() {
        name = StringUtility.getRandomName();
        phoneNumber = StringUtility.getRandomNumber(10);
    }

    @Test
    public void addEmptyContact(){
        createContactPage = getContactListPage().addContact();
        createContactPage.saveContact();
        AssertUtility.assertTrue(createContactPage.isWarningPopupDisplayed(), "Error popup is displayed");
        createContactPage.closeWarningPopup();
    }

    @Test(dependsOnMethods = "addEmptyContact")
    public void addContact(){
        createContactPage.fillName(name);
        createContactPage.fillPhoneNumber(phoneNumber);
        contactDetailsPage = createContactPage.saveContact();
        AssertUtility.assertTrue(contactDetailsPage.checkContactName(name), "Check contact name");
        AssertUtility.assertTrue(contactDetailsPage.checkContactPhoneNumber(phoneNumber), "Check contact phone number");
    }
}
