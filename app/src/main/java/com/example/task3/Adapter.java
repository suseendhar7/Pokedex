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

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>  {

    List<poke.Pokemon> pokemon;
    List<String> urls;
    List<String> id;
    List<poke.Pokemon> full_pokemon;
    List<String> full_urls;
    Context context;
    Activity activity;

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Adapter(Context context) {
        pokemon = new ArrayList<>();
        urls = new ArrayList<>();
        this.context = context;
        id = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Glide.with(context)
                .asBitmap().load(urls.get(position))
                .into(holder.imageView);
        final String corr = change(pokemon.get(position).getName());
        holder.textView.setText(corr);
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

                String[] x = urls.get(position).split("/");
                if (x[x.length - 2].equals("pokemon")) {
                    Intent i = new Intent(context, pokemon_desc.class);
                    i.putExtra("name", corr);
                    i.putExtra("url", pokemon.get(position).getUrl());
                    i.putExtra("image_url", urls.get(position));
                    context.startActivity(i, compat.toBundle());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return pokemon.size();
    }

    public void addPokemon(List<poke.Pokemon> p, List<String> u) {
        pokemon.addAll(p);
        urls.addAll(u);
        full_pokemon = new ArrayList<>(pokemon);
        full_urls = new ArrayList<>(urls);
        for (poke.Pokemon n : p) {
            String[] x = n.getUrl().split("/");
            id.add(x[x.length - 1]);
        }
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
