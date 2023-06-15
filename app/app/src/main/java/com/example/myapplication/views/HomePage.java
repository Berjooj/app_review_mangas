package com.example.myapplication.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.interfaces.InitContext;
import com.example.myapplication.repositories.RepositorioFavoritos;
import com.example.myapplication.repositories.RepositorioObras;
import com.example.myapplication.services.ApplicationService;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomePage extends AppCompatActivity implements InitContext {

    public RepositorioObras repoObras;
    public RepositorioFavoritos repoFavoritos;

    TextView nomeUsuario;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setInstance();

        this.repoObras = RepositorioObras.getInstance();
        this.repoFavoritos = RepositorioFavoritos.getInstance();
        HomeFragment homeFragment = new HomeFragment();
        BuscaFragment buscaFragment = new BuscaFragment();
        FavoritosFragment favoritosFragment = new FavoritosFragment();
        PerfilFragment perfilFragment = new PerfilFragment();

        setContentView(R.layout.home_page);


        bottomNavigationView = findViewById(R.id.bottomNavigationViewId);

        getSupportFragmentManager().beginTransaction().replace(R.id.layoutId, homeFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().setCustomAnimations(
                                R.anim.slide_in,  // enter
                                R.anim.fade_out,  // exit
                                R.anim.fade_in,   // popEnter
                                R.anim.slide_out  // popExit
                        ).replace(R.id.layoutId, homeFragment).commit();
                        return true;
                    case R.id.busca:
                        getSupportFragmentManager().beginTransaction().setCustomAnimations(
                                R.anim.slide_in,  // enter
                                R.anim.fade_out,  // exit
                                R.anim.fade_in,   // popEnter
                                R.anim.slide_out  // popExit
                        ).replace(R.id.layoutId, buscaFragment).commit();
                        return true;
                    case R.id.favoritos:
                        getSupportFragmentManager().beginTransaction().setCustomAnimations(
                                R.anim.slide_in,  // enter
                                R.anim.fade_out,  // exit
                                R.anim.fade_in,   // popEnter
                                R.anim.slide_out  // popExit
                        ).replace(R.id.layoutId, favoritosFragment).commit();

                        return true;
                    case R.id.perfil:
                        getSupportFragmentManager().beginTransaction().setCustomAnimations(
                                R.anim.slide_in,  // enter
                                R.anim.fade_out,  // exit
                                R.anim.fade_in,   // popEnter
                                R.anim.slide_out  // popExit
                        ).replace(R.id.layoutId, perfilFragment).commit();
                        return true;
                }
                return false;
            }
        });

    }

    @Override
    public void setInstance() {
        ApplicationService service = ApplicationService.getInstance();
        service.setContext(this);
    }
}

