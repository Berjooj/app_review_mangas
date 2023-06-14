package com.example.myapplication.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;


public class FavoritosFragment extends Fragment {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.favoritos_fragment, container, false);
        TextView nomeUsuario = view.findViewById(R.id.nomeUsuarioId);


        return view;
    }
}