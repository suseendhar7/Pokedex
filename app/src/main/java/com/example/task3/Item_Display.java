package com.example.task3;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

public class Item_Display extends Fragment {

    String opt;
    boolean getData;
    int offset = 0;
    List<poke.Pokemon> pokemons = new ArrayList<>();
    Retrofit retrofit;
    ItemAdapter itemAdapter;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.poke_dsiplay, container, false);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getContext());
        opt = pref.getString("opt", "default");
        recyclerView = view.findViewById(R.id.recyclerView);
        itemAdapter = new ItemAdapter(getContext());
        recyclerView.setAdapter(itemAdapter);
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
                    func(offset, opt);
                }
            }
        });

        retrofit = new Retrofit.Builder()
                .baseUrl(pokeApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        getData = true;
        func(offset, opt);
        return view;
    }

    public void func(int offset, String s) {
        Call<poke> call;
        final pokeApi pokeApi = retrofit.create(com.example.task3.pokeApi.class);
        if (s.equals("location"))
            call = pokeApi.getLocation(20, offset);
        else if (s.equals("region"))
            call = pokeApi.getRegion(20, offset);
        else
            call = pokeApi.getType(20, offset);
        data(call);
    }

    public void data(Call<poke> call) {
        call.enqueue(new Callback<poke>() {
            @Override
            public void onResponse(Call<poke> call, Response<poke> response) {
                getData = true;
                poke p = response.body();
                pokemons = p.getResults();
                itemAdapter.addItems(pokemons);
            }
            @Override
            public void onFailure(Call<poke> call, Throwable t) {
                getData = true;
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
