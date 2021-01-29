    package com.example.objetiva;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;

    public class HomePage extends AppCompatActivity {
    boolean logged = false;
    private RequestQueue mQueue;
    Fragment selectedFragment = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        Button seePricingBtn = findViewById(R.id.seePricingBtn);

        Button parseBtn = findViewById(R.id.parseBtn);
        mQueue = Volley.newRequestQueue(this);
    }

    public void jsonParse(View view){
        TextView responseTxt = findViewById(R.id.responseTxt);
        String url = "https://trabalhos.esmad.ipp.pt/tsiw/20-21/nes/wp_group06/wp-json/wc/v3/products?consumer_key=ck_c9a44480dab7f33199745d06604ae5f8049b3729&consumer_secret=cs_4c1a6a42e1422af263dfd4177e8f8cb464b8eb9f";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Response:", response.toString());
                        responseTxt.setText(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error", error.getMessage());
            }
        });
        mQueue.add(request);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                    switch (item.getItemId()) {
                        case R.id.page_1:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.page_2:
                            selectedFragment = new CartFragment();
                            break;
                        case R.id.page_3:
                            selectedFragment = new ServiceFragment();
                            break;
                        case R.id.page_4:
                            if(logged = false) {
                                selectedFragment = new LoginFragment();
                                break;
                            } else {
                                selectedFragment = new ProfileFragment();
                                break;
                            }
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }
            };

    public void seePricing(View view) {
        Intent intent = new Intent(this, activity_subscription_pricing.class);
        startActivity(intent);
    }

    public void setLogged(View view){
        logged = true;
        selectedFragment = new ProfileFragment();
        Toast.makeText(this, "Logged:" + logged, Toast.LENGTH_SHORT).show();
    }

    public void logOut(View view) {
        logged = false;
        selectedFragment = new LoginFragment();
        Toast.makeText(this, "Logged:" + logged, Toast.LENGTH_SHORT).show();
    }

    public void openProduct(View view) {
        Intent intent = new Intent(this, ProductPage.class);
        startActivity(intent);
    }
}