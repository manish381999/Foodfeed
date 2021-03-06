package com.tie.foodfeed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.tie.foodfeed.databinding.ActivityForgotPasswordBinding;

public class ForgotPasswordActivity extends AppCompatActivity {
    ActivityForgotPasswordBinding binding;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        auth=FirebaseAuth.getInstance();

        binding.gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class));
                finish();
            }
        });

        binding.passwordRecoverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.forgotPassword.getText().toString().isEmpty()) {
                    binding.forgotPassword.setError("Enter your registered email");
                    binding.forgotPassword.requestFocus();
                } else {
                    auth.sendPasswordResetEmail(binding.forgotPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Please check your mail to reset your password", Toast.LENGTH_LONG).show();
                             startActivity(new Intent(ForgotPasswordActivity.this,LoginActivity.class));
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), "Email is wrong or Account Not Exist", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }
}