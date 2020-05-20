package com.example.Lab2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private TextView test_text_view;
    private RequestQueue test_request_queue;
    public ArrayList<ContentClass> contentClassArrayList = new ArrayList<>();
    ArrayList<String> stringArrayList = new ArrayList<>();
    TextView mtw;
    public RecyclerView recyclerView;
    private static final String URL_IMAGE = "https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/images/tech/advanced_flight.jpg";

    private ImageView ivResult;

    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        test_request_queue = Volley.newRequestQueue(this);

        recyclerView = findViewById(R.id.mainRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        Intent intent = getIntent();
        contentClassArrayList = (ArrayList<ContentClass>)intent.getSerializableExtra("content"); // вариант приема 2

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this, contentClassArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);


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
                        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, contentClassArrayList);
                        recyclerView.setAdapter(recyclerViewAdapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        test_request_queue.add(request);
    }

    private class LoadImage extends AsyncTask<String, Void, Bitmap> {

        ImageView imageView;

        public LoadImage(ImageView iv) {
            imageView = iv;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String url = strings[0];
            Bitmap bitmap = null;
            try {
                InputStream inputStream = new URL(url).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            ivResult.setImageBitmap(bitmap);
        }
    }
}
