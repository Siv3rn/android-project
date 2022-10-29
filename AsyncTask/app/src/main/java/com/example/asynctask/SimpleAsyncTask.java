package com.example.asynctask;

import android.os.AsyncTask;
import android.widget.TextView;

import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void,Void,String> {

    public TextView mTextView;

    public SimpleAsyncTask(TextView tv){
        mTextView = tv;

    }

    @Override
    protected String doInBackground(Void... voids) {
        Random r = new Random();
        int n = r.nextInt(11);
        int s = n * 200;
        try{
            Thread.sleep(s);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        return "awake at last after sleeping for "+ s +"milliesecond!";
    }
    protected void onPostExecute(String result){
        mTextView.setText(result);
    }
}
