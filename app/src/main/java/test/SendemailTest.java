package test;

import com.keepassdroid.email.Sendemail;

import junit.framework.Assert;

public class SendemailTest {
    @org.junit.Test
    public void testisEmail()
    {
        boolean result1 = Sendemail.isEmail("chen1234yue@163.com");
        boolean result2 = Sendemail.isEmail("chen1234yue");
        Assert.assertEquals(result1, true);
        Assert.assertEquals(result2,false);
    }
    @org.junit.Test
    public void testisEmailfail()
    {
        boolean result1 = Sendemail.isEmail("chen1234yue@111.com");
        Assert.assertEquals(result1,false);
    }
}
