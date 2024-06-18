package com.company.demo.functions;

import com.company.demo.base.BaseTest;
import com.company.demo.pages.method.ContactDetailsPage;
import com.company.demo.pages.method.ContactListPage;
import com.company.demo.ultis.AssertUtility;
import org.testng.annotations.Test;

import java.util.Random;

public class DeleteContactTest extends BaseTest {
    private ContactDetailsPage contactDetailsPage;
    private ContactListPage contactListPage;
    private String name;

    @Test
    public void deleteContact(){
        getRandomContact();
        contactDetailsPage = getContactListPage().goToContactDetails(name);
        contactListPage = contactDetailsPage.deleteContact();
        AssertUtility.assertFalse(contactListPage.isContactExist(name), "Check contact is deleted");
    }

    private void getRandomContact(){
        Random random = new Random();
        name = getContactListPage().getContactList().get(random.nextInt(getContactListPage().getContactList().size()));
    }

}
