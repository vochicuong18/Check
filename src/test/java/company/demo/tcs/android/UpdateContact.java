package company.demo.tcs.android;

import company.demo.base.BaseTest;
import company.demo.entity.User;
import company.demo.pages.method.android.ContactDetailsScreen;
import company.demo.pages.method.android.CreateContactScreen;
import company.demo.ultis.AssertUtility;
import company.demo.ultis.DataTest;
import company.demo.ultis.StringUtility;
import org.testng.annotations.Test;

public class UpdateContact extends BaseTest {
    private User user;
    private ContactDetailsScreen contactDetailsScreen;
    private CreateContactScreen createContactPage;
    private String newName, newPhoneNumber;

    @Override
    public void preCondition() {
        user = DataTest.getUserTest();
        newName = StringUtility.getRandomName();
        newPhoneNumber = StringUtility.getRandomNumber(10);
    }

    @Test
    public void updateContact() {
        contactDetailsScreen = getAndroidContactListScreen().goToContactDetails(user.getName());
        createContactPage = contactDetailsScreen.editContact();
        createContactPage.fillName(newName);
        createContactPage.fillPhoneNumber(newPhoneNumber);
        contactDetailsScreen = createContactPage.saveContact();
        AssertUtility.assertTrue(contactDetailsScreen.checkContactName(newName), "Name is updated");
        AssertUtility.assertTrue(contactDetailsScreen.checkContactPhoneNumber(newPhoneNumber), "Phone number is updated");
    }

    @Test(dependsOnMethods = "updateContact")
    public void revertUpdate(){
        createContactPage = contactDetailsScreen.editContact();
        createContactPage.fillName(user.getName());
        createContactPage.fillPhoneNumber(user.getPhoneNumber());
        contactDetailsScreen = createContactPage.saveContact();
        contactDetailsScreen.backToContactList();
    }
}
