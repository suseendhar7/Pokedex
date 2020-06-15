package com.example.task3;


import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class pokemon_desc extends AppCompatActivity {

    Retrofit retrofit;
    private static final String TAG = "Poke Class";
    ChipGroup chipGroup_ability, chipGroup_type, chipGroup_stats;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pokemondesc);
        Fade fade = new Fade();
        View decor = getWindow().getDecorView();
        fade.excludeTarget(decor.findViewById(R.id.tool_bar), true);
        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);
        Intent i = getIntent();
        String name = i.getStringExtra("name");
        String image_url = i.getStringExtra("image_url");
        String url = i.getStringExtra("url");
        String[] x = url.split("/");
        TextView pname = findViewById(R.id.pname);
        CircleImageView image = findViewById(R.id.pimage);
        pname.setText(name);
        Glide.with(this)
                .asBitmap().load(image_url).into(image);

        Log.d(TAG, "reached");
        chipGroup_ability = findViewById(R.id.chipGroup_ability);
        chipGroup_type = findViewById(R.id.chipGroup_type);
        chipGroup_stats = findViewById(R.id.chipGroup_stat);

        //getting data from api...
        retrofit = new Retrofit.Builder().baseUrl(pokeApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        pokeApi pokeApi = retrofit.create(com.example.task3.pokeApi.class);

        //calling...
        Call<poke_info_modal_class> call = pokeApi.getPokeData(x[x.length - 1]);
        call.enqueue(new Callback<poke_info_modal_class>() {
            @Override
            public void onResponse(Call<poke_info_modal_class> call, Response<poke_info_modal_class> response) {
                poke_info_modal_class modal_class = response.body();
                int colour;
                List<poke_info_modal_class.types> types = modal_class.getTypes();
                LayoutInflater inflater = LayoutInflater.from(pokemon_desc.this);
                colour = colours.getColorByType(change(types.get(0).getType().getName()));
                for (poke_info_modal_class.types type : types) {
                    Chip chip = (Chip) inflater.inflate(R.layout.chip, null, false);
                    chip.setChipBackgroundColorResource(colours.getColorByType(change(type.getType().getName())));
                    chip.setText(change(type.getType().getName()));
                    chipGroup_type.addView(chip);
                }

                List<poke_info_modal_class.ability> abilityList = modal_class.getAbilities();
                for (poke_info_modal_class.ability ability : abilityList) {
                    Chip chip = (Chip) inflater.inflate(R.layout.chip, null, false);
                    chip.setChipBackgroundColorResource(colour);
                    chip.setText(change(ability.getAb().getName()));
                    chipGroup_ability.addView(chip);
                }

                List<poke_info_modal_class.stats> stats = modal_class.getStats();
                for (poke_info_modal_class.stats stat : stats) {
                    Chip chip = (Chip) inflater.inflate(R.layout.chip, null, false);
                    chip.setChipBackgroundColorResource(colours.getColourByStat(change(stat.getStat().getName())));
                    String s = change(stat.getStat().getName()) + ": " + stat.getBase_stat();
                    chip.setText(s);
                    chipGroup_stats.addView(chip);
                }
            }

            @Override
            public void onFailure(Call<poke_info_modal_class> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    public String change(String string) {
        String sub = string.substring(1);
        char x = string.charAt(0);
        return Character.toUpperCase(x) + sub;
    }
}
