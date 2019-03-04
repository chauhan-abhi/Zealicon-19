package com.jss.abhijeet.zealicon.fragments;

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

import com.jss.abhijeet.zealicon.BuildConfig;
import com.jss.abhijeet.zealicon.R;
import com.jss.abhijeet.zealicon.activities.ZealIDActivity;
import com.jss.abhijeet.zealicon.di.Injector;
import com.jss.abhijeet.zealicon.model.BackofficeResponse;
import com.jss.abhijeet.zealicon.network.ApiService;

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

    private String name = "", email = "", college = "", contact = "", year = "", branch = "", course = "";


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

        nameView = view.findViewById(R.id.name);
        emailView = view.findViewById(R.id.email);
        collegeView = view.findViewById(R.id.college_name);
        contactView = view.findViewById(R.id.contact_no);
        yearView = view.findViewById(R.id.year);
        courseView = view.findViewById(R.id.course);
        branchView = view.findViewById(R.id.branch);

        register = view.findViewById(R.id.register_button);

        final String[] colleges = getResources().getStringArray(R.array.list_of_colleges);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, colleges);
        collegeView.setAdapter(adapter);

        ArrayAdapter<CharSequence> branchAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.branch, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        branchView.setAdapter(branchAdapter);


        ArrayAdapter<CharSequence> courseAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.course, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        courseView.setAdapter(courseAdapter);

        ArrayAdapter<CharSequence> yearAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.year, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        yearView.setAdapter(yearAdapter);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("App", "started");
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

                Log.v("Register Fragment", name + email + college + course + contact + year + branch);
                if (name.equals("") || email.equals("") || college.equals("") || contact.equals("") || course.equals("") || branch.equals("") || year.equals(""))
                    Toast.makeText(getActivity(), "Please Enter All the Fields", Toast.LENGTH_SHORT).show();
                else if (name.length() == 0 || !isValidMail(email) || contact.length() != 10 || college.length() == 0
                        || branch.equals("") || year.equals(" ") || course.equals("") || !isValidMobile(contact)) {
                    Toast.makeText(getActivity(), "Invalid Fields", Toast.LENGTH_SHORT).show();
                } else {
                    registertask(name, email, college, contact, course, branch, year);
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

    void registertask(String name, final String email, String college, final String contact, String course, String branch, String year) {
        HashMap<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("email", email);
        params.put("course", course);
        params.put("branch", branch);
        params.put("contact", contact);
        params.put("college", college);
        params.put("year", year);
        params.put("token", BuildConfig.TOKEN);
        apiService.doRegister(params).enqueue(new Callback<BackofficeResponse>() {
            @Override
            public void onResponse(Call<BackofficeResponse> call, Response<BackofficeResponse> response) {
                Log.v("RESP", response.toString());
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().getResponse().equals("200")) {
                        // zeal id given no errors
                        Intent i = new Intent(getActivity(), ZealIDActivity.class);
                        i.putExtra("zealId", response.body().getId() + "");
                        startActivity(i);

                    } else if (response.body() != null && response.body().getResponse().equals("500") && response.body().getErrors() != null) {
                        // error in params
                        String contact_error = response.body().getContactErrors();
                        String email_error = response.body().getEmailErrors();
                        if (!email_error.equals("")) {
                            Toast.makeText(getActivity(), email_error, Toast.LENGTH_SHORT).show();
                        } else if (!contact_error.equals("")) {
                            Toast.makeText(getActivity(), contact_error, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Email or contact already registered", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(getActivity(), "Registration failed. Please try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BackofficeResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Registration failed. Please try again", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

    }

    @Override
    public void onDetach() {
        super.onDetach();
        //setEmpty();
    }
}
