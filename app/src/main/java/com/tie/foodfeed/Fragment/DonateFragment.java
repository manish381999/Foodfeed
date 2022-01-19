package com.tie.foodfeed.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.tie.foodfeed.Model.DonateModel;
import com.tie.foodfeed.R;
import com.tie.foodfeed.databinding.FragmentDonateBinding;


public class DonateFragment extends Fragment {
FragmentDonateBinding binding;

FirebaseAuth auth;
FirebaseDatabase database;



    public DonateFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding=FragmentDonateBinding.inflate(inflater, container, false);

        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String donorName=binding.donorName.getText().toString();
                String typeofFood=binding.typeofFood.getText().toString();
                String quantityofFood=binding.quantityofFood.getText().toString();
                String donorMobileNo=binding.donorMobileNo.getText().toString();
                String address=binding.address.getText().toString();

                if (donorName.isEmpty() | typeofFood.isEmpty() | quantityofFood.isEmpty() | donorMobileNo.isEmpty()
                | address.isEmpty()){
                    binding.donorName.setError("Please enter donorName");
                    binding.donorName.requestFocus();

                    binding.typeofFood.setError("Enter food types");
                    binding.typeofFood.requestFocus();

                    binding.quantityofFood.setError("Enter quantity of food");
                    binding.quantityofFood.requestFocus();

                    binding.donorMobileNo.setError("Please enter Mobile no");
                    binding.donorMobileNo.requestFocus();

                    binding.address.setError("Enter your Address");
                    binding.address.requestFocus();
                }else if(donorMobileNo.length()!=10){

                    binding.donorMobileNo.setError("Invalid Number");
                    binding.donorMobileNo.requestFocus();
                }else {
                    DonateModel donateModel=new DonateModel();
                    donateModel.setDonorName(donorName);
                    donateModel.setDonatedBy(auth.getUid());
                    donateModel.setTypeofFood(typeofFood);
                    donateModel.setQuantityofFood(quantityofFood);
                    donateModel.setDonorMobileNo(donorMobileNo);
                    donateModel.setAddress(address);

                    database.getReference().child("Donation").push().setValue(donateModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            FragmentManager fragmentManager = getFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            ThankyouFragment NAME = new ThankyouFragment();
                            fragmentTransaction.replace(R.id.container, NAME);
                            fragmentTransaction.commit();


                        }
                    });
                }
            }
        });

        return binding.getRoot();
    }
}