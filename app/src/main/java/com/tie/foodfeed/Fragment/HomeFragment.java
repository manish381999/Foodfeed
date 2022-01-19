package com.tie.foodfeed.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tie.foodfeed.Adapter.DonationAdapter;
import com.tie.foodfeed.Model.DonateModel;
import com.tie.foodfeed.R;
import com.tie.foodfeed.UserDetails;
import com.tie.foodfeed.databinding.FragmentHomeBinding;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
FragmentHomeBinding binding;
FirebaseAuth auth;
FirebaseDatabase database;
ArrayList<DonateModel> list;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        list=new ArrayList<>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding=FragmentHomeBinding.inflate(inflater, container, false);


       DonationAdapter donationAdapter=new DonationAdapter(getContext(),list);
       LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
       binding.donationRV.setAdapter(donationAdapter);
       binding.donationRV.setLayoutManager(layoutManager);

       FirebaseDatabase.getInstance().getReference()
               .child("Donation").addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               list.clear();
               for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                   DonateModel donateModel=dataSnapshot.getValue(DonateModel.class);
                   list.add(donateModel);
               }
               donationAdapter.notifyDataSetChanged();
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });

        return binding.getRoot();
    }
    

}