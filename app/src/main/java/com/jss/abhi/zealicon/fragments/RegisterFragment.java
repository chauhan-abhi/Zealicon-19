package com.jss.abhi.zealicon.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jss.abhi.zealicon.R;
import com.jss.abhi.zealicon.activities.ZealIDActivity;

import static android.util.Patterns.EMAIL_ADDRESS;
import static android.util.Patterns.PHONE;

public class RegisterFragment extends Fragment {

    private EditText nameView, emailView, collegeView, contactView, branchView, yearView, courseView;
    private Button register;

    private String name, email, college, contact, year, branch, course, token_id;


    public static Fragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_register, container, false);

        nameView = (EditText) view.findViewById(R.id.name);
        emailView = (EditText) view.findViewById(R.id.email);
        collegeView = (EditText) view.findViewById(R.id.college_name);
        contactView = (EditText) view.findViewById(R.id.contact_no);
        register = (Button) view.findViewById(R.id.register);
        courseView = (EditText) view.findViewById(R.id.course);
        branchView = (EditText) view.findViewById(R.id.branch);
        yearView = (EditText) view.findViewById(R.id.year);

        register = (Button) view.findViewById(R.id.register_button);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("App", "started");
                name = nameView.getText().toString();
                email = emailView.getText().toString();
                college = collegeView.getText().toString();
                contact = contactView.getText().toString();
                course = courseView.getText().toString();
                branch = branchView.getText().toString();
                year = yearView.getText().toString();

                Log.v("Register Fragment", name + email + contact + year + branch);
                if (name.equals("") || email.equals("") || college.equals("") || contact.equals("") || course.equals("") || branch.equals("") || year.equals(""))
                    Toast.makeText(getActivity(), "Sorry..Please Enter All Fields", Toast.LENGTH_SHORT).show();
                if(name.length()==0 || !isValidMail(email) || contact.length()!= 10 || college.length()==0
                    || branch.equals("") || year.equals(" ") || course.equals("") || !isValidMobile(contact)) {
                    Toast.makeText(getActivity(), "Sorry..Invalid Fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    registertask();
                }
            }
        });

        return view;

    }

    private boolean isValidMail(String email) {
        return EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidMobile(String phone) {
        return PHONE.matcher(phone).matches();
    }

    void registertask() {

        Intent i = new Intent(this.getActivity(),ZealIDActivity.class);
        startActivity(i);
    }

    @Override public void onDetach() {
        super.onDetach();
        //setEmpty();
    }
}
