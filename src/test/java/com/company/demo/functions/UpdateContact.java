package com.company.demo.functions;

import com.company.demo.base.BaseTest;
import com.company.demo.entity.User;
import com.company.demo.pages.method.ContactDetailsPage;
import com.company.demo.pages.method.CreateContactPage;
import com.company.demo.ultis.AssertUtility;
import com.company.demo.ultis.DataTest;
import com.company.demo.ultis.StringUtility;
import org.testng.annotations.Test;

public class UpdateContact extends BaseTest {
    private User user;
    private ContactDetailsPage contactDetailsPage;
    private CreateContactPage createContactPage;
    private String newName, newPhoneNumber;

    @Override
    public void preCondition() {
        user = DataTest.getUserTest();
        newName = StringUtility.getRandomName();
        newPhoneNumber = StringUtility.getRandomNumber(10);
    }

    @Test
    public void updateContact() {
        contactDetailsPage = getContactListPage().goToContactDetails(user.getName());
        createContactPage = contactDetailsPage.editContact();
        createContactPage.fillName(newName);
        createContactPage.fillPhoneNumber(newPhoneNumber);
        contactDetailsPage = createContactPage.saveContact();
        AssertUtility.assertTrue(contactDetailsPage.checkContactName(newName), "Name is updated");
        AssertUtility.assertTrue(contactDetailsPage.checkContactPhoneNumber(newPhoneNumber), "Phone number is updated");
    }

    @Test(dependsOnMethods = "updateContact")
    public void revertUpdate(){
        createContactPage = contactDetailsPage.editContact();
        createContactPage.fillName(user.getName());
        createContactPage.fillPhoneNumber(user.getPhoneNumber());
        contactDetailsPage = createContactPage.saveContact();
    }
}
