package com.example.amityapplabs.amityincubatorlistproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    RecyclerView myRecyclerView;
    RecyclerView.LayoutManager myLayoutManager;
    MyAdapter myAdapter;
    private String[] mTitleDataset;
    private String[] mSubTitleDataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myRecyclerView = findViewById(R.id.my_recycler_view);


        mTitleDataset = new String[35];
        mSubTitleDataset = new String[35];

        getAPIData();

//        createFakeData();
        setMyRecyclerView();
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
}
