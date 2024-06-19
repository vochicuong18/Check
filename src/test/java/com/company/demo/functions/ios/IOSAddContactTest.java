package com.company.demo.functions.ios;

import com.company.demo.base.BaseTest;
import com.company.demo.pages.method.ios.IOSContactDetailsScreen;
import com.company.demo.pages.method.ios.IOSNewContactScreen;
import com.company.demo.ultis.AssertUtility;
import com.company.demo.ultis.StringUtility;
import org.testng.annotations.Test;

public class IOSAddContactTest extends BaseTest {
    private IOSNewContactScreen iosNewContactScreen;
    private IOSContactDetailsScreen iosContactDetailsScreen;

    String name, phoneNumber;

    @Override
    public void preCondition() {
        name = StringUtility.getRandomName();
        phoneNumber = StringUtility.getRandomNumber(10);
    }

    @Test
    public void addContact(){
        iosNewContactScreen = getIOSContactListPage().addContact();
        iosNewContactScreen.fillFirstName(name);
        iosNewContactScreen.addPhoneNumber();
        iosNewContactScreen.fillPhoneNumber(phoneNumber);
        iosContactDetailsScreen = iosNewContactScreen.saveContact();
//        AssertUtility.assertTrue(iosContactDetailsScreen.checkContactName(name), "Check contact name");
//        AssertUtility.assertTrue(iosContactDetailsScreen.checkContactPhoneNumber(phoneNumber), "Check contact phone number");
    }
}
