package com.example.Lab2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private static final String URL_IMAGE = "https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/images/tech/advanced_flight.jpg";
    private ArrayList<ContentClass> contentClassArrayList;
    private Context context;

    public RecyclerViewAdapter(Context context, ArrayList<ContentClass> arrayList) {

        contentClassArrayList = arrayList;
        contentClassArrayList.remove(0);
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        if (position == -1) {
            String s = contentClassArrayList.get(position).getDescription()+
                    contentClassArrayList.get(position).getFormat_version()+
                    contentClassArrayList.get(position).getOptions();

            holder.textView1.setText(s);

            return;
        }



        holder.textView1.setText(contentClassArrayList.get(position).getName());
        holder.textView2.setText(contentClassArrayList.get(position).getHelptext());

        Picasso.with(context).load(URL_IMAGE).into(holder.imageView);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewPager.class);
                intent.putExtra("content", contentClassArrayList);
                intent.putExtra("pos", position);
                context.startActivity(intent);
            }
        };

        holder.cardView.setOnClickListener(onClickListener);
        holder.relativeLayout.setOnClickListener(onClickListener);
        holder.textView1.setOnClickListener(onClickListener);
        holder.textView2.setOnClickListener(onClickListener);
    }


    @Override
    public int getItemCount() {

        return contentClassArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView1;
        TextView textView2;
        ImageView imageView;
        CardView cardView;
        RelativeLayout relativeLayout;


        public MyViewHolder(View view) {

            super(view);
            textView1 = view.findViewById(R.id.listItemTextView1);
            textView2 = view.findViewById(R.id.listItemTextView2);
            imageView = view.findViewById(R.id.listItemImageView);
            cardView = view.findViewById(R.id.listItemCardView);
            relativeLayout = view.findViewById(R.id.listItemRelativeLayout);

        }
    }
}
