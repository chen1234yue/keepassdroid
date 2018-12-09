package test;

import android.os.Handler;

import com.keepassdroid.FillInfoActivity;
import com.keepassdroid.app.App;
import com.keepassdroid.database.edit.SetSelfinfo;

import junit.framework.Assert;

public class SetselfInfoTest {
    public static String email = "chen1234yue@163.com";
    public static String selfname = "chenyue";
    public static String handphone = "11111";
    @org.junit.Test
    public void testSetSelfInfo()
    {
        SetSelfinfo si = new SetSelfinfo(null, App.getDB(),selfname,email,handphone,null);
        si.setPwDataBase();
        Assert.assertEquals(email,App.getDB().pm.email);
        Assert.assertEquals(selfname,App.getDB().pm.infoname);
        Assert.assertEquals(handphone,App.getDB().pm.handphone);
    }
}
