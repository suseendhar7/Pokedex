package com.example.task3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class fetch_data extends AppCompatActivity {

    RecyclerView recyclerView;
    Retrofit retrofit;
    sub_adapter sub_adapter;
    TextView textView;
    List<Type_Modal_Class.p_mon.extra> ex;
    List<String> urls;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_fetch);
        final Intent i = getIntent();
        int number = i.getIntExtra("id", 1);
        String name = i.getStringExtra("name");
        textView = findViewById(R.id.item_name);
        textView.setText(name);

        retrofit = new Retrofit.Builder().
                baseUrl(pokeApi.BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build();
        recyclerView = findViewById(R.id.rl2);
        sub_adapter = new sub_adapter(this);
        recyclerView.setAdapter(sub_adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        pokeApi pokeApi = retrofit.create(com.example.task3.pokeApi.class);
        Call<Type_Modal_Class> call = pokeApi.getTypeData(number);
        call.enqueue(new Callback<Type_Modal_Class>() {
            @Override
            public void onResponse(Call<Type_Modal_Class> call, Response<Type_Modal_Class> response) {
                Type_Modal_Class typeModalClass = response.body();
                List<Type_Modal_Class.p_mon> pMon = typeModalClass.getP_mon();
                ex = new ArrayList<>();
                urls = new ArrayList<>();
                for (Type_Modal_Class.p_mon pMon1 : pMon) {
                    ex.add(pMon1.getExtra());
                    String[] x = pMon1.getExtra().getUrl().split("/");
                    String s = "https://pokeres.bastionbot.org/images/pokemon/" + x[x.length - 1] + ".png";
                    urls.add(s);
                }
                sub_adapter.addData(ex, urls);
            }

            @Override
            public void onFailure(Call<Type_Modal_Class> call, Throwable t) {
                Toast.makeText(fetch_data.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
