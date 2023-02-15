package com.yaros.frasesYaros;

import android.os.Bundle;

import com.yaros.frasesYaros.fragments.FraseDiaFragment;
import com.yaros.frasesYaros.fragments.AutorFraseFragment;
import com.yaros.frasesYaros.fragments.CategoriaFraseFragment;
import com.google.android.material.navigation.NavigationView;
import androidx.fragment.app.Fragment;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
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
        // Obtenemos una referencia al header del Navigation para poder modificarlo en tiempo de ejecución
        View headerView = navigationView.getHeaderView(0);
        ImageView ivUser = headerView.findViewById(R.id.ivProfile);
        TextView tvUser = headerView.findViewById(R.id.tvUser);
        tvUser.setText(R.string.nav_header_title);
        TextView tvEmail = headerView.findViewById(R.id.tvEmail);
        tvEmail.setText(R.string.nav_header_subtitle);
    }

    @Override
    public void onBackPressed() {
        /*
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
        // Cargamos el menú de la ActionBar
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

        if (id == R.id.nav_today) {
            f = new FraseDiaFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_frame, f)
                    .commit();
            setTitle("Frase de hoy");
        } else if (id == R.id.nav_author) {
            f = new AutorFraseFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_frame, f)
                    .commit();
            setTitle("Frases por autor");
        } else if (id == R.id.nav_category) {
            f = new CategoriaFraseFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_frame, f)
                    .commit();
            setTitle("Frases por categoria");
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
