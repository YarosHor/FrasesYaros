package com.germangascon.navigationdrawersample;

import android.os.Bundle;
import com.google.android.material.navigation.NavigationView;
import androidx.fragment.app.Fragment;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment f;
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            f = new FragmentCamara();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, f).commit();
            setTitle("Cámara");
        } else if (id == R.id.nav_gallery) {
            f = new FragmentGaleria();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, f).commit();
            setTitle("Galería");
        } else if (id == R.id.nav_slideshow) {
            f = new FragmentPresentacion();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, f).commit();
            setTitle("Presentación");
        } else if (id == R.id.nav_manage) {
            f = new FragmentHerramientas();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, f).commit();
            setTitle("Herramientas");
        } else if (id == R.id.nav_share) {
            Bundle b = new Bundle();
            f = new FragmentPresentacion();
            b.putString("SHARE", "Mi texto");
            f.setArguments(b);
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, f).addToBackStack(null).commit();
            setTitle("Compartir");
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
