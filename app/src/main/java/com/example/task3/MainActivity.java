package com.example.task3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    Toolbar toolbar;

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        NavigationView navigationView = findViewById(R.id.nav_bar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        if (savedInstanceState == null) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = preferences.edit();
            editor.putString("opt", "pokemon");
            editor.apply();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new Poke_Display()).commit();
            navigationView.setCheckedItem(R.id.poke_mon);
            toolbar.setTitle(R.string.Pokemon);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = preferences.edit();
        switch (item.getItemId()) {
            case R.id.poke_mon:
                editor.putString("opt", "pokemon");
                editor.apply();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new Poke_Display()).commit();
                toolbar.setTitle(R.string.Pokemon);
                break;
            case R.id.item:
                editor.putString("opt", "item");
                editor.apply();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new Poke_Display()).commit();
                toolbar.setTitle(R.string.item);
                break;
            case R.id.location:
                editor.putString("opt", "location");
                editor.apply();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new Item_Display()).commit();
                toolbar.setTitle(R.string.location);
                break;
            case R.id.region:
                editor.putString("opt", "region");
                editor.apply();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new Item_Display()).commit();
                toolbar.setTitle(R.string.region);
                break;
            case R.id.type:
                editor.putString("opt", "type");
                editor.apply();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new Item_Display()).commit();
                toolbar.setTitle(R.string.type);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.hamburger_menu, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }*/
}