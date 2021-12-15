package com.example.trackbuddy;

import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class SignupTabFragment extends Fragment {
    EditText email,mobile,password,confirmpassword;
    TextView already_account;
    Button signup;
    float v = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment,container,false);


        email = root.findViewById(R.id.email);
        password = root.findViewById(R.id.password);
        confirmpassword= root.findViewById(R.id._confirm_password);
        mobile = root.findViewById(R.id.mobile_number);
        signup = root.findViewById(R.id.signup_btn);

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


        return root;
    }


}
