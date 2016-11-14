package com.aryaan.ankitha.loginwithsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class LoginFragment extends Fragment implements View.OnClickListener {

    DbHelperAdapter dbHelperAdapter;
    EditText userName,password;
    Button login,reset;
    private static final String CLEAR = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_fragment,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        userName = (EditText)getActivity().findViewById(R.id.userEditText);
        password = (EditText)getActivity().findViewById(R.id.passEditText);
        login = (Button)getActivity().findViewById(R.id.loginButton);
        reset = (Button)getActivity().findViewById(R.id.resButton);

        dbHelperAdapter = new DbHelperAdapter(getActivity());

        login.setOnClickListener(this);
        reset.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.loginButton){
            loginUser();
        }
        if (view.getId() == R.id.resButton){
            userName.setText(CLEAR);
            password.setText(CLEAR);
        }

    }

    private void loginUser() {

        String logName = userName.getText().toString();
        String logPass = password.getText().toString();
        String name = dbHelperAdapter.getData(logName,logPass);

        if (logName.equals(name)){
            Intent intent = new Intent(getActivity(),SecondActivity.class);
            intent.putExtra("name",name);
            startActivity(intent);
        }else {
            Message.message(getActivity(),"Login details does'nt match, Please Try again");
        }
    }
}
