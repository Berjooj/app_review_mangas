package com.example.myapplication.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.BannerAdapter;
import com.example.myapplication.adapters.CardAdapter;
import com.example.myapplication.models.Obra;
import com.example.myapplication.repositories.RepositorioObras;
import com.example.myapplication.services.ApplicationService;
import com.example.myapplication.services.FeedService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class BuscaFragment extends Fragment {
    List<Obra> obras = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.busca_fragment, container, false);
        TextView nomeUsuario = view.findViewById(R.id.nomeUsuarioId);
        EditText editTextBusca = view.findViewById(R.id.editTextBusca);

        editTextBusca.setOnEditorActionListener((v, actionId, event) -> {
            boolean handled = false;

            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                ApplicationService applicationService = ApplicationService.getInstance();

                String searchText = editTextBusca.getText().toString();

                if (searchText.length() > 3) {
                    applicationService.loader.showDialog();

                    RepositorioObras repositorioObras = RepositorioObras.getInstance();
                    RecyclerView recyclerView = view.findViewById(R.id.recyclerViewBusca);

                    FeedService.carregarPesquisa(searchText, onServiceDone -> {
                        applicationService.loader.dismiss();

                        if (onServiceDone.codigo != 500) {
                            // Aqui vou atualizar o array da View
                            obras = repositorioObras.listaFiltro;

                            Log.wtf("MEH", "onFocusChange: puts");

                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false) {
                                @Override
                                public boolean canScrollVertically() {
                                    return false;
                                }
                            });
                            recyclerView.setAdapter(new BannerAdapter(getActivity(), obras));
                        }
                    });
                }
            }

            return handled;
        });

        return view;
    }
}