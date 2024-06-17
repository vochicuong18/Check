package com.company.demo.functions;

import com.company.demo.base.BaseTest;
import com.company.demo.pages.method.ContactListPage;
import com.company.demo.pages.method.CreateContactPage;
import com.company.demo.pages.method.MenuPage;
import org.testng.annotations.Test;

public class AddContactTest extends BaseTest {
    private MenuPage menuPage;
    private ContactListPage contactListPage;
    private CreateContactPage createContactPage;
    String name;

    @Override
    public void preCondition() {
        name = System.currentTimeMillis() + "Name";
    }

    @Test
    public void addContact(){
        menuPage = getHomePage().goToMenuPage();
        contactListPage = menuPage.goToContactApp();
        createContactPage = contactListPage.addContact();
        createContactPage.fillName(name);
        createContactPage.fillPhoneNumber("0123456789");
        createContactPage.saveContact();
    }
}
