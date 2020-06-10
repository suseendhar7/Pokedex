package com.example.task3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class sub_adapter extends RecyclerView.Adapter<sub_adapter.ViewHolder> {

    List<Type_Modal_Class.p_mon.extra> extras;
    List<String> imgs;
    Context context;

    public sub_adapter(Context context) {
        extras = new ArrayList<>();
        imgs = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).asBitmap().load(imgs.get(position)).into(holder.imageView);
        holder.textView.setText(change(extras.get(position).getName()));
    }

    @Override
    public int getItemCount() {
        return extras.size();
    }

    public void addData(List<Type_Modal_Class.p_mon.extra> e, List<String> urls) {
        extras.addAll(e);
        imgs.addAll(urls);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView imageView;
        TextView textView;
        RelativeLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.name);
            layout = itemView.findViewById(R.id.rLayout);
        }
    }

    public String change(String string) {
        String sub = string.substring(1);
        char x = string.charAt(0);
        return Character.toUpperCase(x) + sub;
    }
}
