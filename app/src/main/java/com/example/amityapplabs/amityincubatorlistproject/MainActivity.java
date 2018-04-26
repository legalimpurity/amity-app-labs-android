package com.example.amityapplabs.amityincubatorlistproject;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.amityapplabs.amityincubatorlistproject.database.AppLabDatabase;
import com.example.amityapplabs.amityincubatorlistproject.database.AppLabUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView myRecyclerView;
    RecyclerView.LayoutManager myLayoutManager;
    MyAdapter myAdapter;
    Button appLabUserAddButton;

    private String[] mTitleDataset;
    private String[] mSubTitleDataset;

    public static AppLabDatabase appLabDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myRecyclerView = findViewById(R.id.my_recycler_view);
        appLabUserAddButton = findViewById(R.id.add_app_lab_user_button);

        mTitleDataset = new String[35];
        mSubTitleDataset = new String[35];

        appLabDatabase = Room.databaseBuilder(getApplicationContext(),AppLabDatabase.class,"AppLabStudentsDatabase").build();

//        getAPIData();
//        createFakeData();

        setMyRecyclerView();
        addOnClickListenerToButton();
    }

    @Override
    protected void onResume() {
        getDataFromDatabase();
        super.onResume();
    }

    void getAPIData()
    {
        String url = "http://194.168.1.208:8888/MyFirstAPI.php";

        JsonObjectRequest myRequest = new JsonObjectRequest(
                Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray listOfNames = response.getJSONArray("studentapiresponse");
                            for(int i=0; i< listOfNames.length(); i++)
                            {
                                JSONObject studentObject = listOfNames.getJSONObject(i);
                                mTitleDataset[i]=studentObject.getString("first_name");
                                mSubTitleDataset[i]=studentObject.getString("last_name");
                            }
                            myAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );


        RequestQueue queue = Volley.newRequestQueue(this);

        queue.add(myRequest);
    }

    void createFakeData()
    {
        for(int i =0; i< 35; i++)
        {
            mTitleDataset[i] = "Amity"+i;
            mSubTitleDataset[i] = "Incubator"+i;
        }
    }

    void setMyRecyclerView()
    {
        myLayoutManager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(myLayoutManager);

        myAdapter = new MyAdapter(mTitleDataset,mSubTitleDataset);
        myRecyclerView.setAdapter(myAdapter);

    }

    void addOnClickListenerToButton()
    {
        appLabUserAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AddAppLabUserDetail.class));
            }
        });
    }

    void getDataFromDatabase()
    {
        new AsyncTask<Void,Void,Void>()
        {
            List<AppLabUser> listalu;
            @Override
            protected Void doInBackground(Void... params)
            {
                listalu = appLabDatabase.appLabDAO().getAllUsers();
                return null;
            }
            protected void onPostExecute(Void nothing)
            {
                for(int i =0; i < listalu.size(); i++)
                {
                    AppLabUser applabuser = listalu.get(i);
                    mTitleDataset[i]=applabuser.getFirstName();
                    mSubTitleDataset[i]=applabuser.getLastName();
                }
                myAdapter.notifyDataSetChanged();
            }
        }.execute();
    }
}
