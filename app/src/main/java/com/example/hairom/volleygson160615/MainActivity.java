package com.example.hairom.volleygson160615;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.example.hairom.volleygson160615.apis.Api;
import com.example.hairom.volleygson160615.apis.ApiError;
import com.example.hairom.volleygson160615.apis.ApiErrorListener;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;


public class MainActivity extends ActionBarActivity {
    public static final String TAG = "MainActivity";
    final ArrayList<Contacts> mDatas = new ArrayList<Contacts>();
//    final ArrayList<Phone> mPhones = new ArrayList<Phone>();
    Context context = MainActivity.this;
    private  ListView listView;
    private TextView textView1,textView2;
    MyBaseAdapter adapter;
    Typeface typeface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("hai","cho");

        listView = (ListView) MainActivity.this.findViewById(R.id.listview);
            textView1 = (TextView) findViewById(R.id.textview1);
//        typeface = Typeface.createFromAsset(context.getAssets(), "font/KL019.ttf");
//        textView1.setTypeface(typeface);
        adapter = new MyBaseAdapter(context, mDatas);
        listView.setAdapter(adapter);

        Api.init(getApplicationContext());

       // Log.d(TAG, "before - Data ArrayList: " + mDatas.size());
        getContact();
      //  Log.d(TAG, "after - Data ArrayList: "+mDatas.size());
    }

    private void getContact() {
        HashMap<String, String> param = new HashMap<>();

        Api.getInstance().getUserInfo(param, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                final Gson gson = new Gson();
                try {
                    String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                    JsonElement je = gson.fromJson(json, JsonElement.class);
                    // String jeString = je.toString();
                    Log.d(TAG, "Response: "+je.toString());







                    // khai báo object đầu tiên, object này có 1 phần tử là contact
                  JsonObject jsonObjectRoot = je.getAsJsonObject();
                    // contact là 1 array, khai bao kieu danh cho contact
                    JsonArray jsonArrayContacts = jsonObjectRoot.get("contacts").getAsJsonArray();
                     // test id
                    //String id="";
                    for (int i=0;i<jsonArrayContacts.size();i++){

                        JsonObject jsonObjectOneContact = jsonArrayContacts.get(i).getAsJsonObject();

                        // tesst ID
                        // id = jsonObjectOneContact.get("id").getAsString();

                        Contacts a = new Contacts();
                        a.id = jsonObjectOneContact.get("id").getAsString();
                        a.name = jsonObjectOneContact.get("name").getAsString();
                        a.email= jsonObjectOneContact.get("email").getAsString();
                        a.address= jsonObjectOneContact.get("address").getAsString();
                        a.gender = jsonObjectOneContact.get("gender").getAsString();

                        JsonObject jsonObjectPhone = jsonObjectOneContact.get("phone").getAsJsonObject();



                         Phone phone = new Phone();
                        phone.home =  jsonObjectPhone.get("home").getAsString();
                        phone.mobile = jsonObjectPhone.get("mobile").getAsString();
                        phone.office = jsonObjectPhone.get("office").getAsString();




                         a.setPhoneList(phone);
                        mDatas.add(a);

                    }
                    // test mdatas
                    String testmDatas ="";
                    for (int i=0;i<mDatas.size();i++){

                        testmDatas = "gia tri mang thu 1 : " + mDatas.get(1).name;
//                        for(int j = 0; j < mDatas.get(i).getPhoneList().size(); j++) {
//                            Log.d(TAG, "FX" + mDatas.get(i).getPhoneList().get(j).home);
//                        }

                    }
                    Log.d(TAG, "mDatda  "+ testmDatas);

                  // test id
                 //  Log.d(TAG, "Response ID" +id) ;
                    adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new ApiErrorListener() {
            @Override
            public void onErrorResponse(ApiError error) {
                Log.d(TAG, "Error: "+error.getMessage());
            }
        });

    }

}
