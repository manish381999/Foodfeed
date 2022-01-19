package com.tie.foodfeed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tie.foodfeed.databinding.ActivityLoginBinding;


public class LoginActivity extends AppCompatActivity {
ActivityLoginBinding binding;
FirebaseAuth auth;
FirebaseDatabase database;
FirebaseUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();


        if(auth.getCurrentUser()!=null){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
        }


        binding.gotoForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,ForgotPasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });

      binding.gotoSignup.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(new Intent(LoginActivity.this,SignupActivity.class));
              finish();
          }
      });


      binding.loginBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              if (binding.loginEmail.getText().toString().isEmpty() | binding.loginPassword.getText().toString().isEmpty()) {

                  binding.loginEmail.setError("Please enter your Email");
                  binding.loginEmail.requestFocus();

                  binding.loginPassword.setError("Please enter Password");
                  binding.loginPassword.requestFocus();
              }else if(binding.loginPassword.length()<6){

                  binding.loginPassword.setError("Invalid Password");
                  binding.loginPassword.requestFocus();
              }else {
                  auth.signInWithEmailAndPassword(binding.loginEmail.getText().toString(),binding.loginPassword.getText().toString())
                          .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                              @Override
                              public void onComplete(@NonNull Task<AuthResult> task) {
                                  if (task.isSuccessful()){
                                      currentUser= auth.getCurrentUser();

                                      String password=binding.loginPassword.getText().toString();
                                      String id=task.getResult().getUser().getUid();
                                      DatabaseReference  reference=database.getReference().child("Users");
                                      reference.child(id).child("password").setValue(password);

                                      checkemailverifiction();

                            }else {
                                Toast.makeText(getApplicationContext(), "Account Doesn't exist First Create Account", Toast.LENGTH_SHORT).show();
                            }
                      }
                  });

              }
          }
      });
    }

    public void checkemailverifiction(){
        if (currentUser.isEmailVerified()==true){
            Toast.makeText(getApplicationContext(), "Logged In", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
        }else {
            Toast.makeText(getApplicationContext(), "verify your email first", Toast.LENGTH_SHORT).show();
        }
    }


    }
