package com.example.Lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class ViewPager extends AppCompatActivity {

    private androidx.viewpager.widget.ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    ArrayList<ContentClass> contentClasses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        Intent intent = getIntent();
        int position = intent.getIntExtra("pos", 0);
        contentClasses = (ArrayList<ContentClass>) intent.getSerializableExtra("content");

        viewPager = findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(this, getSupportFragmentManager(), contentClasses);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(position);


    }
}
