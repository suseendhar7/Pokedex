package com.example.task3;


import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class pokemon_desc extends AppCompatActivity {
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
        TextView pname = (TextView) findViewById(R.id.pname);
        CircleImageView image = (CircleImageView) findViewById(R.id.pimage);
        pname.setText(name);
        Glide.with(this)
                .asBitmap().load(image_url).into(image);
    }
}
