package com.android.keepass;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.keepassdroid.GroupEditActivity;
import com.keepassdroid.PasswordActivity;


public class FindpasswordActivity extends Activity {
    public static final String KEY_CODE = "code";
    public static void Launch(Activity act) {

        Intent i = new Intent(act, FindpasswordActivity.class);
        act.startActivityForResult(i, PasswordActivity.FIND_PASSWORD);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findpassword);
        setTitle(R.string.findpassword_title);
        Button okButton = (Button) findViewById(R.id.input_ok_button);
        okButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                TextView codeField = (TextView) findViewById(R.id.input_indentify_code);
                String code = codeField.getText().toString();
                if ( code.length() > 0 )
                {
                    final Intent intent = new Intent();
                    intent.putExtra(KEY_CODE, code);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
                else
                {
                    Toast.makeText(FindpasswordActivity.this, R.string.error_no_code, Toast.LENGTH_LONG).show();
                }
            }
        });

        Button cancel = (Button) findViewById(R.id.input_cancel_button);
        cancel.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                final Intent intent = new Intent();
                setResult(Activity.RESULT_CANCELED, intent);
                finish();
            }
        });
    }
}
