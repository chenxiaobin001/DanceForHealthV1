package com.example.danceforhealth;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnShowListener;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.widget.Button;
import android.widget.EditText;

public class SignupDialog extends DialogFragment{
	
	private EditText username;
	private EditText password;
	private EditText firstName;
	private EditText lastName;
	private EditText email;
	private View view;
	private Dialog mDialog;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View view = inflater.inflate(R.layout.popup_window_signup, null);
		this.view = view;
		username = (EditText) view.findViewById(R.id.usernameEditText);
		password = (EditText) view.findViewById(R.id.passwordEditText);
		builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
		builder.setPositiveButton(R.string.signup, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				
			}
		});
	
		builder.setView(view);
		Dialog dialog = builder.create();
		mDialog = dialog;
		
		((AlertDialog) dialog).setOnShowListener(new OnShowListener() {
			
			@Override
			public void onShow(DialogInterface dialog) {
				
				((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
				
			}
		});
		
		username.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void afterTextChanged(Editable s) {
				if (!usernameOrPasswordEmpty()){
					((AlertDialog) mDialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
				}else{
					((AlertDialog) mDialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
				}
				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				
			}
		});
		
		password.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void afterTextChanged(Editable s) {
				if (!usernameOrPasswordEmpty()){
					((AlertDialog) mDialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
				}else{
					((AlertDialog) mDialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
				}
				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				
			}
		});
		
		return dialog;
	}	
	
	private boolean usernameOrPasswordEmpty(){
		username = (EditText) view.findViewById(R.id.usernameEditText);
		password = (EditText) view.findViewById(R.id.passwordEditText);
		if (username.getText().toString().trim().equals("") || password.getText().toString().trim().equals("")){
			return true;
		}
		return false;
	}
}
