package com.tie.foodfeed.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tie.foodfeed.R;
import com.tie.foodfeed.databinding.FragmentDonationDetailBinding;


public class DonationDetailFragment extends Fragment {
FragmentDonationDetailBinding binding;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding=FragmentDonationDetailBinding.inflate(inflater, container, false);

        binding.acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                HomeFragment NAME = new HomeFragment();
                fragmentTransaction.replace(R.id.container, NAME);
                fragmentTransaction.commit();

            }
        });

        Bundle bundle=this.getArguments();

        assert bundle != null;
        String donorName=bundle.getString("donorName");
        String typeofFood=bundle.getString("typeofFood");
        String quantityofFood=bundle.getString("quantityofFood");
        String donorMobileNo=bundle.getString("donorMobileNo");
        String address=bundle.getString("address");

        binding.donorName.setText(donorName);
        binding.typeofFood.setText(typeofFood);
        binding.quantityofFood.setText(quantityofFood);
        binding.donorMobileNo.setText(donorMobileNo);
        binding.address.setText(address);

//Bundle data=getArguments();
//
//
//     binding.donorName.setText(data.getString("donorName"));
//     binding.typeofFood.setText(data.getString("typeofFood"));
//     binding.quantityofFood.setText(data.getString("quantityofFood"));
//     binding.donorMobileNo.setText(data.getString("donorMobileNo"));
//     binding.address.setText(data.getString("address"));

        return binding.getRoot();
    }
}