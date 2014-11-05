package com.example.danceforhealth;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class FragmentTypeAndFeel extends Fragment{
	
	private Spinner spinner;
	private String selection;
	private EditText editText;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
		View view = inflater.inflate(R.layout.fragment_type_and_feel, container, false);
		setSpinnerContent(view);
		selection = "Dance";
		return view;
	} 
	
	private void setSpinnerContent( View view )
	{	
		spinner = (Spinner) view.findViewById(R.id.workoutTypeSpinner_v1);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
		        R.array.workouts_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter( adapter );
	}
}
