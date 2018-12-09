package test;

import com.keepassdroid.PasswordActivity;

import junit.framework.Assert;

public class formatCodeTest {
    @org.junit.Test
    public void testFormatCode1()
    {
        byte[] bytes=PasswordActivity.formatCode(999);
        Assert.assertEquals(bytes.length, 4);
        Assert.assertEquals(bytes[0], '0');
        Assert.assertEquals(bytes[1], '9');
        Assert.assertEquals(bytes[2], '9');
        Assert.assertEquals(bytes[3], '9');
    }
    @org.junit.Test
    public void testFormatCode2()
    {
        byte[] bytes=PasswordActivity.formatCode(9990);
        Assert.assertEquals(bytes.length, 4);
        Assert.assertEquals(bytes[0], '9');
        Assert.assertEquals(bytes[1], '9');
        Assert.assertEquals(bytes[2], '9');
        Assert.assertEquals(bytes[3], '0');
    }
    @org.junit.Test
    public void testFormatCode3()
    {
        byte[] bytes=PasswordActivity.formatCode(1234);
        Assert.assertEquals(bytes.length, 4);
        Assert.assertEquals(bytes[0], '1');
        Assert.assertEquals(bytes[1], '2');
        Assert.assertEquals(bytes[2], '3');
        Assert.assertEquals(bytes[3], '4');
    }
}
