package com.example.objetiva;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
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
        EditText searchBar = findViewById(R.id.search_bar);

        mQueue = Volley.newRequestQueue(this);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
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
                            selectedFragment = new LoginFragment();
                            break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };

    public void seePricing(View view) {
        Intent intent = new Intent(this, activity_subscription_pricing.class);
        startActivity(intent);
    }

        public void openSearch(View view) {
            Intent intent = new Intent(this, SearchPage.class);
            startActivity(intent);
        }

    public void openProduct(View view) {
        Intent intent = new Intent(this, ProductPage.class);
        startActivity(intent);
    }

    public void cameraSearch(View view) {
        Intent intent = new Intent (this, SearchPage.class);
        intent.putExtra("categoryCode", "18");
        startActivity(intent);
    }

    public void lensSearch(View view) {
        Intent intent = new Intent (this, SearchPage.class);
        intent.putExtra("categoryCode", "19");
        startActivity(intent);
    }

    public void filmSearch(View view) {
        Intent intent = new Intent (this, SearchPage.class);
        intent.putExtra("categoryCode", "21");
        startActivity(intent);
    }

    public void acessoriesSearch(View view) {
        Intent intent = new Intent (this, SearchPage.class);
        intent.putExtra("categoryCode", "20");
        startActivity(intent);
    }
}