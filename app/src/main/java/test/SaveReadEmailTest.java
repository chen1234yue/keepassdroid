package test;


import android.net.Uri;

import com.keepassdroid.Database;
import com.keepassdroid.PasswordActivity;
import com.keepassdroid.utils.UriUtil;

import junit.framework.Assert;

import java.io.File;

public class SaveReadEmailTest {
    static String emailaddr = "chen1234yue@163.com";
    static String pass = "111111";
    static String filename = new String("E:\\github\\keepassdroid\\app\\src\\main\\java\\test\\keepass.txt");
    @org.junit.Test
    public void testSaveAndReadEmail()
    {
        try {
            Database.SaveEmail(filename,emailaddr,pass);

        }catch (Exception e)
        {
            String result = PasswordActivity.getEmailAddress(filename);
            Assert.assertEquals(result,emailaddr);
        }


    }
    @org.junit.Test
    public void testSaveAndReadPass()
    {
        try {
            Database.SaveEmail(filename,emailaddr,pass);

        }catch (Exception e)
        {
            String result = PasswordActivity.getpass(filename);
            Assert.assertEquals(result,pass);
        }


    }
}
