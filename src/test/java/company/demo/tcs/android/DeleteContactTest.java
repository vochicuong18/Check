package company.demo.tcs.android;

import company.demo.base.BaseTest;
import company.demo.pages.method.android.ContactDetailsScreen;
import company.demo.pages.method.android.ContactListScreen;
import company.demo.ultis.AssertUtility;
import org.testng.annotations.Test;

import java.util.Random;

public class DeleteContactTest extends BaseTest {
    private ContactDetailsScreen contactDetailsScreen;
    private ContactListScreen contactListScreen;
    private String name;

    @Test
    public void deleteContact(){
        getRandomContact();
        contactDetailsScreen = getAndroidContactListScreen().goToContactDetails(name);
        contactListScreen = contactDetailsScreen.deleteContact();
        AssertUtility.assertFalse(contactListScreen.isContactExist(name), "Check contact is deleted");
    }

    private void getRandomContact(){
        Random random = new Random();
        name = getAndroidContactListScreen().getContactList().get(random.nextInt(getAndroidContactListScreen().getContactList().size()));
    }

}
