package com.germangascon.navigationdrawersample;

import android.os.Bundle;

import com.germangascon.navigationdrawersample.fragments.CameraFragment;
import com.germangascon.navigationdrawersample.fragments.GalleryFragment;
import com.germangascon.navigationdrawersample.fragments.ShareFragment;
import com.germangascon.navigationdrawersample.fragments.ToolsFragment;
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
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        /**
         * Si el usuario pulsa el botón atrás mientras está mostrándose el menú del NavigationView,
         * hacemos que se cierre dicho menú, ya que el comportamiento por defecto es cerrar la
         * Activity.
         */
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflamos el menú de la ActionBar
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Se ha hecho click en algún item del menú de la ActionBar
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment f;
        // Se ha hecho click en algún item del NavigationView
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            f = new CameraFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_frame, f)
                    .commit();
            setTitle(R.string.camera);
        } else if (id == R.id.nav_gallery) {
            f = new GalleryFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_frame, f)
                    .commit();
            setTitle(R.string.gallery);
        } else if (id == R.id.nav_manage) {
            f = new ToolsFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_frame, f)
                    .commit();
            setTitle(R.string.tools);
        } else if (id == R.id.nav_share){
            f = new ShareFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_frame, f)
                    .addToBackStack(null)
                    .commit();
            //Podemos darle un nombre a addToBackStack para que luego poder acceder directamente a ese estado de la pila
            //addToBackStack("MiEstado")
            //getSupportFragmentManager().popBackStack("MiEstado",flag);
            //Donde flag puede ser 0 o POP_BACK_STACK_INCLUSIVE
            //- 0 desapila hasta llegar al estado indicado (pero sin incluirlo)
            //- POP_BACK_STACK_INCLUSIVE desapila hasta llegar al estado indicado (incluyéndolo también)
            setTitle(R.string.share);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
