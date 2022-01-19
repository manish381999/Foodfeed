package com.tie.foodfeed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationBarView;
import com.tie.foodfeed.Fragment.DonateFragment;
import com.tie.foodfeed.Fragment.HomeFragment;
import com.tie.foodfeed.Fragment.ProfileFragment;

import com.tie.foodfeed.databinding.ActivityMainBinding;
import com.tie.foodfeed.databinding.FragmentDonateBinding;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setTitle("Home");

        FragmentTransaction  transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container,new HomeFragment());
        transaction.commit();


        binding.bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                    switch (item.getItemId()){
                        case R.id.home:
                            getSupportActionBar().setTitle(item.getTitle());
                            transaction.replace(R.id.container,new HomeFragment());
                            break;
                        case R.id.donate:
                            getSupportActionBar().setTitle(item.getTitle());
                            transaction.replace(R.id.container,new DonateFragment());
                            break;
                        case R.id.profile:
                            getSupportActionBar().setTitle(item.getTitle());
                            transaction.replace(R.id.container,new ProfileFragment());
                            break;
                    }
                    transaction.commit();
                return true;

            }
        });
    }
    int counter=0;
    public void onBackPressed() {
        counter++;
        Toast.makeText(getApplicationContext(), "Press Back Again to Exit", Toast.LENGTH_SHORT).show();
        if (counter==2){

            super.onBackPressed();
        }

    }
}