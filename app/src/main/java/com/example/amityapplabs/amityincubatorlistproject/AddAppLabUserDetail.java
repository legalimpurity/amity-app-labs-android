package com.example.amityapplabs.amityincubatorlistproject;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.amityapplabs.amityincubatorlistproject.database.AppLabUser;

public class AddAppLabUserDetail extends AppCompatActivity {

    EditText firstNameET, lastNameET;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_app_lab_user_detail);

        firstNameET = findViewById(R.id.first_name_edit_text);
        lastNameET = findViewById(R.id.last_name_edit_text);
        submitButton = findViewById(R.id.submit_button);

        addSubmitOnCLickListener();
    }

    private void addSubmitOnCLickListener()
    {
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                AppLabUser alu = new AppLabUser();
//                alu.setFirstName(firstNameET.getText().toString());
//                alu.setLastName(lastNameET.getText().toString());
//                MainActivity.ald.appLabDAO().insert(alu);
                addViaAsyncTask();
            }
        });
    }

    private void addViaAsyncTask()
    {
        new AsyncTask<Void,Void,Void>()
        {
            @Override
            protected Void doInBackground(Void... params)
            {
                AppLabUser alu = new AppLabUser();
                alu.setFirstName(firstNameET.getText().toString());
                alu.setLastName(lastNameET.getText().toString());
                MainActivity.appLabDatabase.appLabDAO().insert(alu);
                return null;
            }
            protected void onPostExecute(Void nothing)
            {

                AddAppLabUserDetail.this.finish();
            }
        }.execute();
    }
}