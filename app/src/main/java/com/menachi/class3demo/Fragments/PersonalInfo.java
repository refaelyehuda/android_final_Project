package com.menachi.class3demo.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.menachi.class3demo.Alerts.BasicAlertDialog;
import com.menachi.class3demo.DateAndPickers.Date.DateEditText;
import com.menachi.class3demo.Model.Model;
import com.menachi.class3demo.Model.User;
import com.menachi.class3demo.R;

public class PersonalInfo extends Fragment {

    public interface Delegate extends NewProduct.Delegate{
    }
    EditText fName;
    EditText lName;
    EditText address;
    DateEditText birthDate;
    User user;
    Delegate delegate;

    public PersonalInfo() {
        // Required empty public constructor
    }

    public void setCurrentUser(User user){
        this.user = user;
    }
    public void setDelegate(Delegate delegate){
        this.delegate = delegate;
    }
    // TODO: Rename and change types and number of parameters
    public static PersonalInfo newInstance(String param1, String param2) {
        PersonalInfo fragment = new PersonalInfo();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_personal_info, container, false);
        if (user != null){
            fName = (EditText) view.findViewById(R.id.userFname);
            lName = (EditText) view.findViewById(R.id.userLname);
            address = (EditText) view.findViewById(R.id.usertAddress);
            birthDate = (DateEditText) view.findViewById(R.id.userBirthDate);

            Button saveBtn = (Button) view.findViewById(R.id.saveUserPersonalInfo);
            Button cancelBtn = (Button) view.findViewById(R.id.cancelUserPersonalInfo);

            fName.setText(user.getfName());
            lName.setText(user.getlName());
            address.setText(user.getAddress());
            birthDate.setText(user.getBirthDate());

            cancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("TAG", "Canceling Edit of user");
                    if (delegate != null) {
                        delegate.onReturnToList();
                    }
                }
            });

            saveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    user.setfName(fName.getText().toString());
                    user.setlName(lName.getText().toString());
                    user.setAddress(address.getText().toString());
                    user.setBirthDate(birthDate.getText().toString());
                    Log.d("TAG", "student created");
                    BasicAlertDialog alert;
                    Model.instance().setUser(user);
                    alert = new BasicAlertDialog("OK"," The user updated successfully", delegate);
                    alert.show(getFragmentManager(), "TAG");
                }
            });
        }
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
