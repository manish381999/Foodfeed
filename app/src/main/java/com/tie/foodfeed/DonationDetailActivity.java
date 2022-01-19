package com.tie.foodfeed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tie.foodfeed.Fragment.HomeFragment;
import com.tie.foodfeed.databinding.ActivityDonationDetailBinding;


public class DonationDetailActivity extends AppCompatActivity {
ActivityDonationDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDonationDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        Intent data=getIntent();

        binding.donorName.setText(data.getStringExtra("donorName"));
        binding.typeofFood.setText(data.getStringExtra("typeofFood"));
        binding.quantityofFood.setText(data.getStringExtra("quantityofFood"));
        binding.donorMobileNo.setText(data.getStringExtra("donorMobileNo"));
        binding.address.setText(data.getStringExtra("address"));


        binding.acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });


    }
}