package com.tie.foodfeed.Fragment;

import static android.app.Activity.RESULT_OK;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.tie.foodfeed.LoginActivity;
import com.tie.foodfeed.R;
import com.tie.foodfeed.UserDetails;
import com.tie.foodfeed.databinding.FragmentProfileBinding;


import java.io.ByteArrayOutputStream;


public class ProfileFragment extends Fragment {
FragmentProfileBinding binding;
FirebaseAuth auth;
FirebaseDatabase database;
FirebaseStorage storage;
    private Bitmap bitmap;



    public ProfileFragment() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        storage=FirebaseStorage.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      binding=FragmentProfileBinding.inflate(inflater, container, false);


      binding.logoutBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              auth.signOut();
              Intent intent=new Intent(getContext(), LoginActivity.class);
              startActivity(intent);
              getActivity().finish();
          }
      });

      database.getReference().child("Users").child(auth.getUid()).addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot snapshot) {
              UserDetails userDetails=snapshot.getValue(UserDetails.class);

              Picasso.get()
                      .load(userDetails.getCover_photo())
                      .placeholder(R.drawable.place_holder)
                      .into(binding.coverImage);

              Picasso.get()
                      .load(userDetails.getProfile_photo())
                      .placeholder(R.drawable.place_holder)
                      .into(binding.profileImage);

              binding.name.setText(userDetails.getName());
              binding.profession.setText(userDetails.getProfession());
          }

          @Override
          public void onCancelled(@NonNull DatabaseError error) {

          }
      });

      binding.changeCoverPhoto.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent=new Intent();
              intent.setAction(Intent.ACTION_GET_CONTENT);
              intent.setType("image/*");
              startActivityForResult(intent,33);
          }
      });

      binding.profileImage.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent=new Intent();
              intent.setAction(Intent.ACTION_GET_CONTENT);
              intent.setType("image/*");
              startActivityForResult(intent,22);
          }
      });
      return binding.getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==22 && resultCode==RESULT_OK && data.getData()!=null ){
            Uri uri=data.getData();

            try {
                bitmap= MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),uri);
            }catch (Exception e){
                e.printStackTrace();
            }
        binding.profileImage.setImageBitmap(bitmap);

            ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,50,byteArrayOutputStream);
            byte[]FinalImg=byteArrayOutputStream.toByteArray();

            StorageReference reference=storage.getReference().child("profile_photo")
                    .child(auth.getUid());

            reference.putBytes(FinalImg).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                  reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                      @Override
                      public void onSuccess(Uri uri) {
                        database.getReference().child("Users").child(auth.getUid()).child("profile_photo").setValue(uri.toString());
                      }
                  });
                    Toast.makeText(getContext(), "Profile photo uploaded", Toast.LENGTH_SHORT).show();
                }
            });
        }if(requestCode==33&& resultCode==RESULT_OK && data.getData()!=null){
            Uri uri=data.getData();

            try {
                bitmap= MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),uri);
            }catch (Exception e){
                e.printStackTrace();
            }
            binding.coverImage.setImageBitmap(bitmap);
             ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
             bitmap.compress(Bitmap.CompressFormat.JPEG,50,byteArrayOutputStream);
             byte[]finalImg=byteArrayOutputStream.toByteArray();

             StorageReference reference=storage.getReference().child("cover_photo")
                     .child(auth.getUid());

             reference.putBytes(finalImg).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                 @Override
                 public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                   reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                       @Override
                       public void onSuccess(Uri uri) {
                         database.getReference().child("Users").child(auth.getUid()).child("cover_photo").setValue(uri.toString());
                       }
                   });
                     Toast.makeText(getContext(), "Cover photo uploaded", Toast.LENGTH_SHORT).show();
                 }
             });
        }
    }




}