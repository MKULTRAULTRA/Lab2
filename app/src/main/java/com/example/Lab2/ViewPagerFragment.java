package com.example.Lab2;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ViewPagerFragment extends Fragment {
    private int pageNumber;
    private static final String URL_IMAGE = "https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/images/tech/advanced_flight.jpg";
    private ArrayList<ContentClass> contentClasses;
    private Context context;

    public ViewPagerFragment newInstance(int page, ArrayList<ContentClass> contentClasses) {
        ViewPagerFragment fragment = new ViewPagerFragment();
        Bundle args = new Bundle();
        args.putInt("num", page);
        args.putSerializable("content", contentClasses);
        fragment.setArguments(args);

        return fragment;
    }

    public ViewPagerFragment() {
    }

    public ViewPagerFragment(ArrayList<ContentClass> contentClasses1) {
        contentClasses = contentClasses1;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments() != null ? getArguments().getInt("num") : 1;
        contentClasses = (ArrayList<ContentClass>) getArguments().getSerializable("content");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View result = inflater.inflate(R.layout.fragment_page, container, false);

        ImageView imageView = result.findViewById(R.id.fragmentImageView);
        TextView textView1 = result.findViewById(R.id.fragmentTextView1);
        TextView textView2 = result.findViewById(R.id.fragmentTextView2);

        Picasso.with(new ViewPager()).load(URL_IMAGE).into(imageView);

        textView1.setText(contentClasses.get(pageNumber).getName());
        textView2.setText(contentClasses.get(pageNumber).getHelptext());



        return result;
    }
}
