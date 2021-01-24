package com.example.meenu_androidapi;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String url = "https://www.simplifiedcoding.net/demos/view-flipper/heroes.php";
    ListView listView;
    ArrayList<Hero> heroArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list_view);
        heroArrayList = new ArrayList<>();
        loadheroList();
    }

     public void loadheroList(){
         ProgressBar progressBar = findViewById(R.id.progressbar);
         progressBar.setVisibility(View.VISIBLE);
         StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
             @Override
             public void onResponse(String response) {
             progressBar.setVisibility(View.INVISIBLE);
                 try {
                     JSONObject jsonObject = new JSONObject(response);
                     JSONArray jsonArray = jsonObject.getJSONArray("heroes");
                     for (int i=0;i<jsonArray.length();i++){
                         JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                         Hero hero = new Hero(jsonObject1.getString("name"), jsonObject1.getString("imageurl"));
                         heroArrayList.add(hero);
                         ListViewAdapter adapter = new ListViewAdapter(heroArrayList, getApplicationContext());
                         listView.setAdapter(adapter);
                     }
                 } catch (JSONException e) {
                     e.printStackTrace();
                 }
             }
         }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {
                 Toast.makeText(getApplicationContext(), "There is an error", Toast.LENGTH_LONG).show();
             }
         });
         RequestQueue requestQueue = Volley.newRequestQueue(this);
         requestQueue.add(stringRequest);
     }
}