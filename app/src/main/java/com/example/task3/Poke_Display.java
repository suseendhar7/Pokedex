package com.example.task3;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Poke_Display extends Fragment {

    private static final String TAG = "MainActivity";
    boolean getData;
    int offset = 0;
    List<String> images = new ArrayList<>();
    List<poke.Pokemon> pokemons = new ArrayList<>();
    Retrofit retrofit;
    Adapter adapter;
    RecyclerView recyclerView;
    String opt;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        opt = preferences.getString("opt", "default");
        Log.d(TAG, opt);
        View view = inflater.inflate(R.layout.poke_dsiplay, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new Adapter(getContext());
        adapter.setActivity(getActivity());
        recyclerView.setAdapter(adapter);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int current = layoutManager.getChildCount();
                int total = layoutManager.getItemCount();
                int first = layoutManager.findFirstVisibleItemPosition();
                if (getData && current + first >= total) {
                    getData = false;
                    offset += 20;
                    get_Data(offset, opt);
                }
            }
        });

        retrofit = new Retrofit.Builder()
                .baseUrl(pokeApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        getData = true;
        get_Data(offset, opt);
        return view;
    }

    public void get_Data(int offset, String s) {
        Call<poke> call;
        final pokeApi pokeApi = retrofit.create(com.example.task3.pokeApi.class);
        if (s.equals("item"))
            call = pokeApi.getItem(20, offset);
        else
            call = pokeApi.getPoke(20, offset);
        fun(call, s);
    }

    public void fun(Call<poke> call, final String l) {
        call.enqueue(new Callback<poke>() {
            @Override
            public void onResponse(Call<poke> call, Response<poke> response) {
                getData = true;
                poke p = response.body();
                pokemons = p.getResults();
                images.clear();
                images = getImg(pokemons, l);
                /*for (poke.Pokemon pokemon : pokemons) {
                    //Log.d(TAG, pokemon.getName());
                    String[] x = pokemon.getUrl().split("/");
                    String s = "https://pokeres.bastionbot.org/images/pokemon/" + x[x.length - 1] + ".png";
                    //Log.d(TAG, s);
                    images.add(s);
                }*/
                adapter.addPokemon(pokemons, images);
            }

            @Override
            public void onFailure(Call<poke> call, Throwable t) {
                getData = true;
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public List<String> getImg(List<poke.Pokemon> mon, String opt) {
        List<String> img_urls = new ArrayList<>();
        if (opt.equals("item")) {
            for (poke.Pokemon pokemon : mon) {
                String s = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/items/" + pokemon.getName() + ".png";
                img_urls.add(s);
            }
        } else {
            for (poke.Pokemon pokemon : mon) {
                String[] x = pokemon.getUrl().split("/");
                String s = "https://pokeres.bastionbot.org/images/pokemon/" + x[x.length - 1] + ".png";
                img_urls.add(s);
            }
        }
        return img_urls;
    }
}
