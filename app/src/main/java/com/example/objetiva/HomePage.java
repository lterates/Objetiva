    package com.example.objetiva;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

    public class HomePage extends AppCompatActivity {
    boolean logged = false;
    Fragment selectedFragment = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        Button seePricingBtn = findViewById(R.id.seePricingBtn);
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