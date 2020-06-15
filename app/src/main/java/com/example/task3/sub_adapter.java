package com.example.task3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class sub_adapter extends RecyclerView.Adapter<sub_adapter.ViewHolder> {

    List<Type_Modal_Class.p_mon.extra> extras;
    List<String> imgs;
    Activity activity;
    Context context;

    public sub_adapter(Context context) {
        extras = new ArrayList<>();
        imgs = new ArrayList<>();
        this.context = context;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Glide.with(context).asBitmap().load(imgs.get(position)).into(holder.imageView);
        holder.textView.setText(change(extras.get(position).getName()));
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fade fade = new Fade();
                View decor = activity.getWindow().getDecorView();
                fade.excludeTarget(decor.findViewById(R.id.tool_bar), true);
                activity.getWindow().setEnterTransition(fade);
                activity.getWindow().setExitTransition(fade);
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation
                        ((Activity) context, holder.imageView, Objects.requireNonNull(ViewCompat.getTransitionName(holder.imageView)));
                Intent i = new Intent(context, pokemon_desc.class);
                i.putExtra("name", change(extras.get(position).getName()));
                i.putExtra("url", "https://pokeapi.co/api/v2/pokemon/" + extras.get(position).getName());
                i.putExtra("image_url", imgs.get(position));
                context.startActivity(i, compat.toBundle());
            }
        });
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
