package com.keepassdroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.keepass.R;
import com.keepassdroid.app.App;
import com.keepassdroid.database.PwDatabase;
import com.keepassdroid.database.edit.OnFinish;
import com.keepassdroid.database.edit.RunnableOnFinish;
import com.keepassdroid.database.edit.SetSelfinfo;

import static java.security.AccessController.getContext;

public class FillInfoActivity extends Activity{//LockCloseHideActivity

    FillInfoActivity superthis = this;
    public static final String KEY_SELFNAME = "selfname";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_HANDPHONE = "handphone";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fill_info);
        Database db = App.getDB();
        PwDatabase pm = db.pm;
        TextView selfnameField = (TextView) findViewById(R.id.info_name);
        if(pm.infoname!= null && pm.infoname.length()>0)
            selfnameField.setText(pm.infoname);
        TextView emailField = (TextView) findViewById(R.id.info_email);
        if(pm.email!=null && pm.email.length()>0)
            emailField.setText(pm.email);
        TextView handphoneField = (TextView) findViewById(R.id.info_handphone);
        if(pm.handphone!=null&&pm.handphone.length()>0)
            handphoneField.setText(pm.handphone);
        TextView passwordField = (TextView) findViewById(R.id.info_password);
        passwordField.setText(pm.masterKey.toString());
        Button changepw = findViewById(R.id.info_change_password_button);
        changepw.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                SetPasswordDialog dialog = new SetPasswordDialog(superthis);
                dialog.show();
            }
        });
        Button saveButton = findViewById(R.id.info_save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                TextView nameField = (TextView) findViewById(R.id.info_name);
                String selfname = nameField.getText().toString();
                nameField = (TextView) findViewById(R.id.info_email);
                String email = nameField.getText().toString();
                nameField = (TextView) findViewById(R.id.info_handphone);
                String handphone = nameField.getText().toString();
              SetSelfinfo si = new SetSelfinfo(FillInfoActivity.this, App.getDB(),selfname,email,handphone,new AfterSave(new Handler()));
                ProgressTask pt = new ProgressTask(FillInfoActivity.this, si, R.string.saving_database);
                pt.run();

            }
        });
        Button cancelButton = findViewById(R.id.info_cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                finish();
            }
        });
    }
    public static void Launch(Context ctx) {
        Intent i = new Intent(ctx, FillInfoActivity.class);

        ctx.startActivity(i);
    }
    private final class AfterSave extends OnFinish {

        public AfterSave(Handler handler) {
            super(handler);
        }

        @Override
        public void run() {
            if ( mSuccess ) {
                finish();
            } else {
                displayMessage(FillInfoActivity.this);
            }
        }

    }
}
