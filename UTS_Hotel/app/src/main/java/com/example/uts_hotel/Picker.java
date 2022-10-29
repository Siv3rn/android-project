package com.example.uts_hotel;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;

import java.util.Calendar;

public class Picker extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private Button dateButton1;
    private Button dateButton2;
    private NumberPicker kamar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker);
        dateButton1 = (Button) findViewById(R.id.DatePicker1);
        dateButton1.setText(getTodayDate());
        dateButton2 = (Button) findViewById(R.id.DatePicker2);
        dateButton2.setText(getTodayDate());
        kamar = (NumberPicker) findViewById(R.id.kamar);
        kamar.setMinValue(1);
        kamar.setMaxValue(30);

        Button search = (Button) findViewById(R.id.Search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Picker.this, DaftarHotel.class);
                String t2 = dateButton2.getText().toString();
                String t1 = dateButton1.getText().toString();
                String jumlah = String.valueOf(kamar.getValue());
                intent.putExtra("tanggal2", t2);
                intent.putExtra("tanggal1", t1);
                intent.putExtra("kamar", jumlah);

                startActivity(intent);
            }
        });


    }
    private String getTodayDate(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day,month,year);
    }


    private String makeDateString(int day, int month, int year) {
        return day + " " + month + " " + year;
    }

    public void OpenDatePicker1(View view) {
        DatePickerDialog.OnDateSetListener datasetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton1.setText(date);

            }

        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;

        datePickerDialog = new DatePickerDialog(this,style,datasetListener,year,month,day);
        datePickerDialog.show();
    }
    public void OpenDatePicker2(View view) {
        DatePickerDialog.OnDateSetListener datasetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton2.setText(date);

            }

        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;

        datePickerDialog = new DatePickerDialog(this,style,datasetListener,year,month,day);
        datePickerDialog.show();
    }

}