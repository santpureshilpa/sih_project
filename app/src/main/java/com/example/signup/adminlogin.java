package com.example.signup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class adminlogin extends AppCompatActivity {

    TextView Info;
    EditText name;
    EditText passwd;
    Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);

        Info=(TextView)findViewById(R.id.textView);
        name=(EditText)findViewById(R.id.editText6);
        passwd=(EditText)findViewById(R.id.editText7);
        login=(Button)findViewById(R.id.button2);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(name.getText().toString(),passwd.getText().toString());
            }
        });



    }

    private void validate(String editText6, String editText7){
        if((editText6 == "Admin") && (editText7 == "1234")){
        Intent intent = new Intent(adminlogin.this, admindesk.class);
        startActivity(intent);
    }else {
            Toast.makeText(getApplicationContext(),"Invalid",Toast.LENGTH_LONG).show();
        }
    }

}
