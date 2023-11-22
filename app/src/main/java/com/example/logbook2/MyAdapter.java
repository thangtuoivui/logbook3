package com.example.logbook2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList image_id, user_name, user_dob, user_email;


    MyAdapter(Activity activity, Context context, ArrayList image_id,
              ArrayList user_name, ArrayList user_dob, ArrayList user_email) {
        this.activity = activity;
        this.context = context;
        this.image_id = image_id;
        this.user_name = user_name;
        this.user_dob = user_dob;
        this.user_email = user_email;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int[] myImageList = new int[]{R.drawable.image1, R.drawable.image2,R.drawable.image3,
                R.drawable.image4,R.drawable.image5};
        holder.imageView.setImageResource(myImageList[(Integer) image_id.get(position)]);
        holder.nameView.setText(String.valueOf(user_name.get(position)));
        holder.dobView.setText(String.valueOf(user_dob.get(position)));
        holder.emailView.setText(String.valueOf(user_email.get(position)));
    }

    @Override
    public int getItemCount() {
        return image_id.size();
    }
}
