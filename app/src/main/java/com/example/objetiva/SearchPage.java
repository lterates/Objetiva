package com.example.objetiva;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class SearchPage extends AppCompatActivity {
    private EditText searchBox;
    private RequestQueue mQueue;
    private String categoryCode = null;
    private String url;
    private RecyclerView recyclerView;
    private TextView numItemsTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_page);

        initViews();
        mQueue = Volley.newRequestQueue(this);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            categoryCode = extras.getString("categoryCode");
            jsonParse();
        }

        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                jsonParse();
            }
        });
    }

    private void initViews() {
        searchBox = findViewById(R.id.searchBox);
        recyclerView = findViewById(R.id.recyclerView);
        numItemsTxt = findViewById(R.id.numItemsTxt);
    }

    public void jsonParse(){
        String searchFor = searchBox.getText().toString();
        if (categoryCode != null) {
            url = "https://trabalhos.esmad.ipp.pt/tsiw/20-21/nes/wp_group06/wp-json/wc/v3/products?category="+ categoryCode +"&consumer_key=ck_c9a44480dab7f33199745d06604ae5f8049b3729&consumer_secret=cs_4c1a6a42e1422af263dfd4177e8f8cb464b8eb9f";
        } else {
            url = "https://trabalhos.esmad.ipp.pt/tsiw/20-21/nes/wp_group06/wp-json/wc/v3/products?search="+ searchFor +"&consumer_key=ck_c9a44480dab7f33199745d06604ae5f8049b3729&consumer_secret=cs_4c1a6a42e1422af263dfd4177e8f8cb464b8eb9f";
        }
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("Request: ", url);
                numItemsTxt.setText(response.length() + " items encontrados");
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject titleResponse = response.getJSONObject(i);
                        Log.d("Items: ", titleResponse.getString("name"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error", error.getMessage());
            }
        });
        mQueue.add(request);
    }
}