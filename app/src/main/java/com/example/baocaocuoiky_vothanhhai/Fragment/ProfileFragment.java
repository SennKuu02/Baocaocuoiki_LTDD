package com.example.baocaocuoiky_vothanhhai.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.baocaocuoiky_vothanhhai.LoginScreen.LoginActivity;
import com.example.baocaocuoiky_vothanhhai.R;


public class ProfileFragment extends Fragment {

    ImageView imgBack;
    ImageView imgOff;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        imgOff=view.findViewById(R.id.img_off);
        imgOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut();
            }
        });

        imgBack=view.findViewById(R.id.img_back);


        return view;
    }

    private void logOut() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);

}}


