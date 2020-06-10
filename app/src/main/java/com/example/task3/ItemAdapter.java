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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    List<poke.Pokemon> pokemon;
    Context context;
    Activity activity;

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public ItemAdapter(Context c) {
        pokemon = new ArrayList<>();
        this.context = c;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.textView.setText(change(pokemon.get(position).getName()));
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] parts = pokemon.get(position).getUrl().split("/");
                if (parts[(parts.length) - 2].equals("type")) {
                    Intent i = new Intent(context, fetch_data.class);
                    Fade fade = new Fade();
                    i.putExtra("id", Integer.parseInt(parts[parts.length - 1]));
                    i.putExtra("name", change(pokemon.get(position).getName()));
                    context.startActivity(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return pokemon.size();
    }

    public void addItems(List<poke.Pokemon> p) {
        pokemon.addAll(p);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        RelativeLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.it);
            layout = itemView.findViewById(R.id.rLayout2);
        }
    }

    public String change(String string) {
        String sub = string.substring(1);
        char x = string.charAt(0);
        return Character.toUpperCase(x) + sub;
    }

}
