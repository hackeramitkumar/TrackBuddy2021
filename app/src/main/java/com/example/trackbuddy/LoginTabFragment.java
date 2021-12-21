package com.example.trackbuddy;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginTabFragment extends Fragment {
    EditText email,password;
    TextView forgetPassword;
    Button signin;
    float v = 0;
    private FirebaseAuth mAuth;
    View objectSignInFragment;

//    public void resetPass(){
//
//                EditText resetMail = new EditText(v.);
//                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
//                passwordResetDialog.setTitle("Reset Password ?");
//                passwordResetDialog.setMessage("Enter your Email to Received Reset Link. ");
//                passwordResetDialog.setView(resetMail);
//                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        //extract the email and send reset link
//                        String mail = resetMail.getText().toString();
//                        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void unused) {
//                                Toast.makeText(Login.this, "Reset Link sent to Your Email", Toast.LENGTH_SHORT).show();
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(Login.this, "Error ! Reset Link is not Sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
//
//                            }
//                        });
//                    }
//                });
//                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // close the dialog
//                    }
//                });
//                passwordResetDialog.create().show();
//
//            }

    public void signinUser(){
        try {
            if(!email.getText().toString().isEmpty() && !password.getText().toString().isEmpty())
            {
                mAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Logged in successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getActivity(),MainActivity.class));
                        } else
                        {
                            Toast.makeText(getActivity(), "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }else
            {
                Toast.makeText(getContext(), "Please fill the fields first", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private void attachToXML()
    {
        try {
            email = objectSignInFragment.findViewById(R.id.email);
            password = objectSignInFragment.findViewById(R.id.password);
            mAuth = FirebaseAuth.getInstance();
            signin = objectSignInFragment.findViewById(R.id.login);
            forgetPassword = objectSignInFragment.findViewById(R.id.forget_pass);

            forgetPassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText resetMail = new EditText(v.getContext());
                    AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                    passwordResetDialog.setTitle("Reset Password ?");
                    passwordResetDialog.setMessage("Enter your Email to Received Reset Link. ");
                    passwordResetDialog.setView(resetMail);
                    passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //extract the email and send reset link
                            String mail = resetMail.getText().toString();
                            mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(getActivity(), "Reset Link sent to Your Email", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getActivity(), "Error ! Reset Link is not Sent" + e.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            });
                        }
                    });
                    passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // close the dialog
                        }
                    });
                    passwordResetDialog.create().show();


                }
            });


            signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    signinUser();
                }
            });
        }catch (Exception e)
        {
            Toast.makeText(getContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        objectSignInFragment = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment,container,false);

        email = objectSignInFragment.findViewById(R.id.email);
        password = objectSignInFragment.findViewById(R.id.password);
        forgetPassword = objectSignInFragment.findViewById(R.id.forget_pass);
        signin = objectSignInFragment.findViewById(R.id.login);

        email.setTranslationY(800);
        password.setTranslationY(800);
        forgetPassword.setTranslationY(800);
        signin.setTranslationY(800);

       email.setAlpha(v);
        password.setAlpha(v);
        forgetPassword.setAlpha(v);
        signin.setAlpha(v);

        email.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
        password.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgetPassword.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(700).start();
        signin.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(900).start();

        attachToXML();
        return objectSignInFragment;

    }

}
