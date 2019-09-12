package com.example.sqllite;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText mName, mPhone, mAdd, mGender;
    DBManger dbManger = new DBManger(this);
    private List<Personal> mPersonalList;
    private ListView mListView;
    private AdapterList mAdapterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mPersonalList = dbManger.getAllListPersonal();
        setAdapter();
    }

    private void initView() {
        mName = findViewById(R.id.text_name);
        mPhone = findViewById(R.id.text_phone);
        mAdd = findViewById(R.id.text_add);
        mGender = findViewById(R.id.text_gender);
        mListView = findViewById(R.id.list_view);
    }

    Personal personal;

    private Personal createPersonal() {
        String name = mName.getText().toString();
        String phone = mPhone.getText().toString();
        String address = mAdd.getText().toString();
        String gender = mGender.getText().toString();

        personal = new Personal(name, phone, address, gender);
        return personal;
    }

    private void cleanText() {
        mName.setText("");
        mPhone.setText("");
        mAdd.setText("");
        mGender.setText("");
    }

    public void onClick(View view) {
        personal = createPersonal();
        if (view.getId() == R.id.button_save) {
           insertData();
        } else {
            reloadData();
        }
    }

    public void setAdapter() {
        if (mAdapterList == null) {
            mAdapterList = new AdapterList(this, R.layout.items_personal, mPersonalList);
        }
        mListView.setAdapter(mAdapterList);
    }

    private void insertData(){
        if (personal != null) {
            dbManger.addPersonal(personal);
            cleanText();
        }
        Toast.makeText(this, "Save Success", Toast.LENGTH_SHORT).show();
    }
    private void reloadData(){
        mPersonalList.clear();
        List<Personal> personalList = dbManger.getAllListPersonal();
        mPersonalList.addAll(personalList);
        mAdapterList.notifyDataSetChanged();
        Toast.makeText(this, "DONE", Toast.LENGTH_SHORT).show();
    }
}
