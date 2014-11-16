package com.example.danceforhealth;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginDialog extends DialogFragment{
	
	private EditText username;
	private EditText password;
	private Button loginBtn;
	private Button cancelBtn;
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View view = inflater.inflate(R.layout.popup_window_login, null);
		username = (EditText) view.findViewById(R.id.usernameEditText);
		password = (EditText) view.findViewById(R.id.passwordEditText);
		loginBtn = (Button) view.findViewById(R.id.loginButton);
		cancelBtn = (Button) view.findViewById(R.id.cancelButton);
		cancelBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		
		builder.setView(view);
		Dialog dialog = builder.create();
		return dialog;
	}	
}
