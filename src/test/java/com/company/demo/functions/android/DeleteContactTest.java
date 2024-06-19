package com.company.demo.functions.android;

import com.company.demo.base.BaseTest;
import com.company.demo.pages.method.android.ContactDetailsScreen;
import com.company.demo.pages.method.android.ContactListScreen;
import com.company.demo.ultis.AssertUtility;
import org.testng.annotations.Test;

import java.util.Random;

public class DeleteContactTest extends BaseTest {
    private ContactDetailsScreen contactDetailsScreen;
    private ContactListScreen contactListScreen;
    private String name;

    @Test
    public void deleteContact(){
        getRandomContact();
        contactDetailsScreen = getContactListScreen().goToContactDetails(name);
        contactListScreen = contactDetailsScreen.deleteContact();
        AssertUtility.assertFalse(contactListScreen.isContactExist(name), "Check contact is deleted");
    }

    private void getRandomContact(){
        Random random = new Random();
        name = getContactListScreen().getContactList().get(random.nextInt(getContactListScreen().getContactList().size()));
    }

}
