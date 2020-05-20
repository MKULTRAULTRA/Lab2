package com.example.Lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {

    //    public RecyclerView recyclerView;
    public ArrayList<ContentClass> contentClassArrayList = new ArrayList<>();
    private RequestQueue requestQueue;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        requestQueue = Volley.newRequestQueue(this);

        jsonParse();
    }

    public void jsonParse() {

        String url = "https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/data/techs.ruleset.json";


        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject obj = response.getJSONObject(i);

                                if (i == 0) {
                                    String description = obj.optString("description");
                                    String format_version = obj.optString("format_version");
                                    String options = obj.optString("options");
                                    contentClassArrayList.add(new ContentClass(description, format_version, options));
                                    continue;
                                }

                                String flags = obj.optString("flags");
                                String graphic = obj.optString("graphic");
                                String graphic_alt = obj.optString("graphic_alt");
                                String helptext = obj.optString("helptext");
                                String name = obj.optString("name");
                                String req1 = obj.optString("req1");
                                String req2 = obj.optString("req2");



                                contentClassArrayList.add(new ContentClass(flags, graphic, graphic_alt, helptext, name, req1, req2));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        intent = new Intent(SplashActivity.this, MainActivity.class);
                        intent.putExtra("content", contentClassArrayList);
                        startActivity(intent);
                        finish();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(request);
    }
}
