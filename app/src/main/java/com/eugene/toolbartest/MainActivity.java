package com.eugene.toolbartest;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks,
    FragmentOne.FragmentCallbacks,
    FragmentTwo.FragmentCallbacks {
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        InitiateToolbar(1);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    /**
     * Control Toolbar
     */
    private void InitiateToolbar(int position) {
        toolbar.setNavigationIcon(R.mipmap.ic_menu_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerCloseOpen();
            }
        });
        switch (position) {
            case 1:
                toolbar.setTitle("");
                break;
            case 2:
                toolbar.setTitle("Hello");
                toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
                break;
            default:
                break;
        }
    }

    /**
     * Interface from Navigation Drawer Fragment
     */
    @Override
    public void onNavigationDrawerItemSelected(int position) {
        DrawerCloseOpen();
        if (toolbar != null) {
            InitiateToolbar(position);
        }
        switch (position) {
            case 1:
                fragment = new FragmentOne();
                break;
            case 2:
                fragment = new FragmentTwo();
                break;
            default:
                break;
        }
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
        }
    }

    /**
     * Open Close Toolbar
     */
    private void DrawerCloseOpen() {
        if (drawer != null) {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                drawer.openDrawer(GravityCompat.START);
            }
        }
    }

    /**
     * Toolbar Menu controls
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_done:
                Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_headline:
                Toast.makeText(MainActivity.this, "Headline", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_shuffle:
                Toast.makeText(MainActivity.this, "Shuffle", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void ButtonClicked(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }
}
