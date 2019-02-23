package com.jss.abhi.zealicon.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.jss.abhi.zealicon.R;
import com.jss.abhi.zealicon.activities.ZealIDActivity;
import com.jss.abhi.zealicon.di.Injector;
import com.jss.abhi.zealicon.model.BackofficeResponse;
import com.jss.abhi.zealicon.network.ApiService;

import java.util.HashMap;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.util.Patterns.EMAIL_ADDRESS;
import static android.util.Patterns.PHONE;

public class RegisterFragment extends Fragment {

    private EditText nameView, emailView, contactView;
    private AutoCompleteTextView collegeView;
    private Spinner branchView, yearView, courseView;
    private Button register;

    @Inject
    ApiService apiService;

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
        Injector.appComponent.inject(this);

        nameView = (EditText) view.findViewById(R.id.name);
        emailView = (EditText) view.findViewById(R.id.email);
        collegeView = (AutoCompleteTextView) view.findViewById(R.id.college_name);
        contactView = (EditText) view.findViewById(R.id.contact_no);
        yearView = (Spinner) view.findViewById(R.id.year);
        courseView = (Spinner) view.findViewById(R.id.course);
        branchView = (Spinner) view.findViewById(R.id.branch);

        register = (Button) view.findViewById(R.id.register_button);

        final String[] colleges = getResources().getStringArray(R.array.list_of_colleges);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, colleges);
        collegeView.setAdapter(adapter);

        ArrayAdapter<CharSequence> branchAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.branch, R.layout.spinner_item);
        branchView.setAdapter(branchAdapter);

        ArrayAdapter<CharSequence> courseAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.course, R.layout.spinner_item);
        courseView.setAdapter(courseAdapter);

        ArrayAdapter<CharSequence> yearAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.year, R.layout.spinner_item);
        yearView.setAdapter(yearAdapter);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("App", "started");
                registertask(name, email, college, contact, course, branch, year);
                name = nameView.getText().toString();
                email = emailView.getText().toString();
                college = collegeView.getText().toString();
                contact = contactView.getText().toString();
                try {
                    course = courseView.getSelectedItem().toString();
                    branch = branchView.getSelectedItem().toString();
                    year = yearView.getSelectedItem().toString();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }

               /* Log.v("Register Fragment", name + email + contact + year + branch);
                if (name.equals("") || email.equals("") || college.equals("") || contact.equals("") || course.equals("") || branch.equals("") || year.equals(""))
                    Toast.makeText(getActivity(), "Sorry..Please Enter All Fields", Toast.LENGTH_SHORT).show();
                if (name.length() == 0 || !isValidMail(email) || contact.length() != 10 || college.length() == 0
                        || branch.equals("") || year.equals(" ") || course.equals("") || !isValidMobile(contact)) {
                    Toast.makeText(getActivity(), "Sorry..Invalid Fields", Toast.LENGTH_SHORT).show();
                } else {
                    registertask(name, email, college, contact, course, branch, year);
                }*/

                /*if (name.equals("") || email.equals("") || college.equals("") || contact.equals("") || course.equals("") || branch.equals("") || year.equals(""))
                    Toast.makeText(getActivity(), "Please Enter All the Fields", Toast.LENGTH_SHORT).show();
                else if(name.length()==0 || !isValidMail(email) || contact.length()!= 10 || college.length()==0
                        || branch.equals("") || year.equals(" ") || course.equals("") || !isValidMobile(contact)) {
                    Toast.makeText(getActivity(), "Invalid Fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    registertask();
                }*/
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

    void registertask(String name, String email, String college, String contact, String course, String branch, String year) {
        HashMap<String, String> params = new HashMap<>();
        params.put("name", "a2a");
        params.put("email", "a22b3@gm.com");
        params.put("course", "B-Tech");
        params.put("branch", "CSE");
        params.put("contact", "9914994343");
        params.put("college", "jss");
        params.put("year", "4");
        params.put("token", "XAHbduUGiXNiuauaytebQTbduygcYi");
        apiService.doRegister(params).enqueue(new Callback<BackofficeResponse>() {
            @Override
            public void onResponse(Call<BackofficeResponse> call, Response<BackofficeResponse> response) {
                Log.v("RESP", response.toString());
                if(response.isSuccessful()) {
                    if(response.body() != null && response.body().getResponse().equals("200")) {
                        // zeal id given no errors
                        Intent i = new Intent(getActivity(), ZealIDActivity.class);
                        i.putExtra("zealId", response.body().getId());
                        startActivity(i);

                    } else if(response.body() != null && response.body().getResponse().equals("500")) {
                        // error in params
                        BackofficeResponse res = response.body();
                        if(res != null ) {

                        }
                        Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                    }
                }
                Toast.makeText(getActivity(), "OnRespnse", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<BackofficeResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Failure", Toast.LENGTH_SHORT).show();
            }
        });
        Intent i = new Intent(this.getActivity(), ZealIDActivity.class);
        startActivity(i);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //setEmpty();
    }
}
