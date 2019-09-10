package com.example.sqllite;

import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private EditText mName,mPhone,mAdd,mGender;
    DBManger dbManger = new DBManger(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        mName = findViewById(R.id.text_name);
        mPhone = findViewById(R.id.text_phone);
        mAdd = findViewById(R.id.text_add);
        mGender = findViewById(R.id.text_gender);
    }

    Personal personal;
    private Personal createPersonal(){
        String name = mName.getText().toString();
        String phone = mPhone.getText().toString();
        String address = mAdd.getText().toString();
        String gender = mGender.getText().toString();

        personal = new Personal(name,phone,address,gender);
        return personal;
    }

    public void onClick(View view) {
        Personal personal = createPersonal();
        if (view.getId()==R.id.button_save){
            if (personal!=null) dbManger.addPersonal(personal);
        }
    }
}
