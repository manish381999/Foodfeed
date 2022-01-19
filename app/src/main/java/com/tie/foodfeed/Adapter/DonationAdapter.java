package com.tie.foodfeed.Adapter;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.tie.foodfeed.DonationDetailActivity;
import com.tie.foodfeed.Fragment.DonationDetailFragment;
import com.tie.foodfeed.Model.DonateModel;
import com.tie.foodfeed.R;
import com.tie.foodfeed.UserDetails;
import com.tie.foodfeed.databinding.DonationRecivedSampleBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DonationAdapter extends RecyclerView.Adapter<DonationAdapter.ViewHolder>{
Context context;
ArrayList<DonateModel> list;

    public DonationAdapter(Context context, ArrayList<DonateModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.donation_recived_sample,parent,false);

        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        DonateModel donateModel=list.get(position);

        int colorcode=getRandomColor();
        holder.binding.linerLayout.setBackgroundColor(holder.itemView.getResources().getColor(colorcode,null));

        holder.binding.address.setText(donateModel.getAddress());

        FirebaseDatabase.getInstance().getReference()
                .child("Users")
                .child(donateModel.getDonatedBy()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserDetails userDetails=snapshot.getValue(UserDetails.class);

                Picasso.get()
                        .load(userDetails.getProfile_photo())
                        .placeholder(R.drawable.place_holder)
                        .into(holder.binding.profileImage);

                holder.binding.name.setText(userDetails.getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





      holder.binding.donation.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              Intent intent=new Intent(v.getContext(), DonationDetailActivity.class);
              intent.putExtra("donorName",donateModel.getDonorName());
              intent.putExtra("typeofFood",donateModel.getTypeofFood());
              intent.putExtra("quantityofFood",donateModel.getQuantityofFood());
              intent.putExtra("donorMobileNo",donateModel.getDonorMobileNo());
              intent.putExtra("address",donateModel.getAddress());
              intent.putExtra("donationId",donateModel.getDonationId());
              v.getContext().startActivity(intent);




//              Bundle bundle=new Bundle();
//              bundle.putString("donorName",donateModel.getDonorName());
//              bundle.putString("typeofFood",donateModel.getTypeofFood());
//              bundle.putString("quantityofFood",donateModel.getQuantityofFood());
//              bundle.putString("donorMobileNo",donateModel.getDonorMobileNo());
//              bundle.putString("address",donateModel.getAddress());
//              bundle.putString("donationId",donateModel.getDonationId());
//
//              DonationDetailFragment donationDetailFragment=new DonationDetailFragment();
//              donationDetailFragment.setArguments(bundle);
//
//             FragmentTransaction transaction=((AppCompatActivity)context).getSupportFragmentManager().beginTransaction();
//             transaction.replace(R.id.container,new DonationDetailFragment());
//             transaction.addToBackStack(null);
//             transaction.commit();


          }
      });

    }

    private int getRandomColor() {
        List<Integer> list=new ArrayList<>();
        list.add(R.color.gray);
        list.add(R.color.green);
        list.add(R.color.lightgreen);
        list.add(R.color.skyblue);
        list.add(R.color.pink);
        list.add(R.color.color1);
        list.add(R.color.color2);
        list.add(R.color.color3);
        list.add(R.color.color4);
        list.add(R.color.color5);
        list.add(R.color.color6);
        list.add(R.color.color7);

        Random random=new Random();
        int number=random.nextInt(list.size());
        return list.get(number);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
    DonationRecivedSampleBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=DonationRecivedSampleBinding.bind(itemView);


        }
    }
}
