package com.tie.foodfeed.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tie.foodfeed.R;
import com.tie.foodfeed.databinding.FragmentThankyouBinding;


public class ThankyouFragment extends Fragment {
FragmentThankyouBinding binding;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding=FragmentThankyouBinding.inflate(inflater, container, false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                HomeFragment Name=new HomeFragment();
                fragmentTransaction.replace(R.id.container,Name);
                fragmentTransaction.commit();

            }
        },6000);
        return binding.getRoot();
    }
}