package com.aryaan.ankitha.loginwithsqlite;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class RegisterFragment extends Fragment implements View.OnClickListener {


    DbHelperAdapter dbHelperAdapter;
    EditText name,userName,password;
    Button save,reset;
    private static final String CLEAR = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.register_fragment,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        name = (EditText)getActivity().findViewById(R.id.nameEditText);
        userName = (EditText)getActivity().findViewById(R.id.userNameEditText);
        password = (EditText)getActivity().findViewById(R.id.passwordEditText);
        save = (Button)getActivity().findViewById(R.id.saveButton);
        reset = (Button)getActivity().findViewById(R.id.resetButton);

        dbHelperAdapter = new DbHelperAdapter(getActivity());

        save.setOnClickListener(this);
        reset.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.resetButton){
            name.setText(CLEAR);
            userName.setText(CLEAR);
            password.setText(CLEAR);
        }
        if (view.getId() == R.id.saveButton){
            saveData();
        }
    }

    private void saveData() {

        String regname = name.getText().toString();
        String user = userName.getText().toString();
        String pass = password.getText().toString();

        long id = dbHelperAdapter.insertData(regname,user,pass);
        if (id < 0){
            Message.message(getActivity(),"unsuccessful");
        }else {
            Message.message(getActivity(),"Data saved successfully");
        }

    }
}
