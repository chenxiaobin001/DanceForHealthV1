package com.example.danceforhealth;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class FragmentTimeAndSubmit extends Fragment{
	
	private TimePicker timePicker;
	private Button setTimePickerButton;
	private Button setDatePickerButton;
	private TextView submitTimeView;
	private TextView submitDateView;

	int month, day;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
		View view = inflater.inflate(R.layout.fragment_time_and_submit, container, false);
		Calendar calendar = Calendar.getInstance();
		timePicker = (TimePicker) view.findViewById(R.id.timePicker1);
		setTimePickerButton = (Button) view.findViewById(R.id.setTimePicker);
		setDatePickerButton = (Button) view.findViewById(R.id.setDatePicker);
		submitTimeView = (TextView) view.findViewById(R.id.submitTimeView);
		submitDateView = (TextView) view.findViewById(R.id.submitDateView);
		submitTimeView.setText(timePicker.getCurrentHour() + " : " + timePicker.getCurrentMinute());
		submitDateView.setText(getFormatDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar  
                .get(Calendar.DAY_OF_MONTH)));
		
		setTimePickerButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				submitTimeView.setText(timePicker.getCurrentHour() + " : " + timePicker.getCurrentMinute());
				Toast.makeText(v.getContext(), "Set Time:"+timePicker.getCurrentHour() + " : " + timePicker.getCurrentMinute(), Toast.LENGTH_SHORT).show();
			}
		});
		
		
		setDatePickerButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Calendar calendar = Calendar.getInstance();  
				new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {  
					  
     				@Override
					public void onDateSet(DatePicker view, int year, int month,
							int day) {
						// TODO Auto-generated method stub
     					submitDateView.setText(getFormatDate(year, month, day));
					}  
                }  , calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar  
                        .get(Calendar.DAY_OF_MONTH)).show();
			}
		});
		
		return view;
	} 
	
	private String getFormatDate(int year, int month,
			int day){
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
		sdf.applyPattern("MMM dd, yyyy");
		Date date = new Date(year - 1900, month, day);
		return sdf.format(date);
	}
}
