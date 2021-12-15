package com.example.trackbuddy;
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
import androidx.fragment.app.Fragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupTabFragment extends Fragment {


    EditText email, mobile, password, confirmpassword;
    Button signup;
    float v = 0;


    private FirebaseAuth mAuth;

    View objectSignUpFragment;




    public SignupTabFragment(){ }


    public void createUSer(){
        try {
            if(!email.getText().toString().isEmpty() && !password.getText().toString().isEmpty())
            {
                mAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(getContext(), "User created", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getActivity(),MainActivity.class);
                                startActivity(i);
                                if(mAuth.getCurrentUser()!=null)
                                {

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
            email = objectSignUpFragment.findViewById(R.id.email);
            password = objectSignUpFragment.findViewById(R.id.password);
            mAuth = FirebaseAuth.getInstance();
            signup = objectSignUpFragment.findViewById(R.id.signup_btn);


            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createUSer();
                }
            });
        }catch (Exception e)
        {
            Toast.makeText(getContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        objectSignUpFragment = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment, container, false);


        email = objectSignUpFragment.findViewById(R.id.email);
        password = objectSignUpFragment.findViewById(R.id.password);
        confirmpassword = objectSignUpFragment.findViewById(R.id._confirm_password);
        mobile = objectSignUpFragment.findViewById(R.id.mobile_number);
        signup = objectSignUpFragment.findViewById(R.id.signup_btn);


        email.setTranslationY(800);
        password.setTranslationY(800);
        confirmpassword.setTranslationY(800);
        signup.setTranslationY(800);
        mobile.setTranslationY(800);

        email.setAlpha(v);
        password.setAlpha(v);
        confirmpassword.setAlpha(v);
        mobile.setAlpha(v);
        signup.setAlpha(v);

        email.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(100).start();
        password.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        confirmpassword.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(700).start();
        mobile.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
        signup.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(900).start();
attachToXML();
        return objectSignUpFragment;


    }

}


