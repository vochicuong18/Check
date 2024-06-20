package company.demo.tcs.android;

import company.demo.base.BaseTest;
import company.demo.pages.method.android.ContactDetailsScreen;
import company.demo.pages.method.android.CreateContactScreen;
import company.demo.ultis.AssertUtility;
import company.demo.ultis.StringUtility;
import org.testng.annotations.Test;

public class AddContactTest extends BaseTest {
    private CreateContactScreen createContactPage;
    private ContactDetailsScreen contactDetailsScreen;
    String name, phoneNumber;

    @Override
    public void preCondition() {
        name = StringUtility.getRandomName();
        phoneNumber = StringUtility.getRandomNumber(10);
    }

    @Test
    public void addEmptyContact(){
        createContactPage = getAndroidContactListScreen().addContact();
        createContactPage.saveContact();
        AssertUtility.assertTrue(createContactPage.isWarningPopupDisplayed(), "Error popup is displayed");
        createContactPage.closeWarningPopup();
    }

    @Test(dependsOnMethods = "addEmptyContact")
    public void addContact(){
        createContactPage.fillName(name);
        createContactPage.fillPhoneNumber(phoneNumber);
        contactDetailsScreen = createContactPage.saveContact();
        AssertUtility.assertTrue(contactDetailsScreen.checkContactName(name), "Check contact name");
        AssertUtility.assertTrue(contactDetailsScreen.checkContactPhoneNumber(phoneNumber), "Check contact phone number");
        contactDetailsScreen.backToContactList();
    }
}
