package com.example.myapplication.views;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;


public class PerfilFragment extends Fragment {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.perfil_fragment, container, false);
        pref = getContext().getSharedPreferences("MyPreferencias", MODE_PRIVATE);
        editor = pref.edit();
        ImageButton voltarActivity = view.findViewById(R.id.sairId);
        voltarActivity.setOnClickListener(view1 -> {
            editor.clear();
            editor.commit();
            Intent intent = new Intent(requireActivity(), LandingPage.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        return view;
    }
}