package com.keepassdroid.database.edit;

import android.content.Context;
import android.net.Uri;

import com.keepassdroid.Database;
import com.keepassdroid.database.PwDatabase;

public class SetSelfinfo extends RunnableOnFinish {
    private String mname;
    private String memail;
    private String mhandphone;
    private Database mDb;
    private boolean mDontSave;
    private Context ctx;
    public SetSelfinfo(Context ctx, Database db, String name,String email,String handphone,  OnFinish finish) {
        this(ctx, db, name,email,handphone,  finish, false);

    }
    public SetSelfinfo(Context ctx, Database db, String name,String email,String handphone, OnFinish finish,boolean dontSave) {
        super(finish);
        mDb = db;
        mname = name;
        memail = email;
        mhandphone = handphone;
        mDontSave = dontSave;
        this.ctx = ctx;
     /*   PwDatabase pm = mDb.pm;
        String backupName = pm.getInfoname();
        String backupEmail = pm.getEmail();
        String backupHandphone = pm.getHandphone();
        mFinish = new AfterSave(backupName,backupEmail,backupHandphone,mFinish);*/
      //  this(ctx, db, name,email,handphone, keyfile, finish, false);

    }
    public void setPwDataBase()
    {
        PwDatabase pm = mDb.pm;
        pm.setEmail(memail);
        pm.setHandphone(mhandphone);
        pm.setInfoname(mname);
    }
    @Override
    public void run() {
        setPwDataBase();
        PwDatabase pm = mDb.pm;
        String backupName = pm.getInfoname();
        String backupEmail = pm.getEmail();
        String backupHandphone = pm.getHandphone();
        mFinish = new AfterSave(backupName,backupEmail,backupHandphone, mFinish);
        SaveDB save = new SaveDB(ctx, mDb, mFinish, mDontSave);
        save.run();
        /*byte[] backupName = new byte[pm.infoname.length];
        System.arraycopy(pm.infoname, 0, backupName, 0, backupName.length);
        byte[] backupEmail = new byte[pm.email.length];
        System.arraycopy(pm.email, 0, backupEmail, 0, backupEmail.length);
        byte[] backupHandphone = new byte[pm.handphone.length];
        System.arraycopy(pm.handphone, 0, backupHandphone, 0, backupHandphone.length);*/
    }
    private class AfterSave extends OnFinish {
        private String backupName;
        private String backupEmail;
        private String backupHandphone;
        public AfterSave(String name,String email,String handphone,OnFinish finish)
        {
            super(finish);
            backupName = name;
            backupEmail = email;
            backupHandphone = handphone;

        }
        @Override
        public void run() {
            if ( ! mSuccess ) {
                // Erase the current master key
                mDb.pm.setInfoname(backupName);
                mDb.pm.setEmail(backupEmail);
                mDb.pm.setHandphone(backupHandphone);
            }

            super.run();

        }
    }
   /* private void erase(String array) {
        if ( array == null ) return;

        for ( int i = 0; i < array.length(); i++ ) {
            array[i]= 0;
        }
    }*/
}
