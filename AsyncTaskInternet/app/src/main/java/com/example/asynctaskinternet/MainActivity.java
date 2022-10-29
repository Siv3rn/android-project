package com.example.asynctaskinternet;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText bookName;
    private TextView title;
    private TextView author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bookName = (EditText) findViewById(R.id.bookInput);
        title = (TextView) findViewById(R.id.titleText);
        author = (TextView) findViewById(R.id.authorText);
    }

    public void searchBooks(View view) {
        String queryString = bookName.getText().toString();
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected() && queryString.length()!=0) {
            new fetchBook(title, author,bookName).execute(queryString);
        }
        else {
            if (queryString.length() == 0) {
                author.setText("");
                title.setText("Please enter a search term");
            } else {
                author.setText("");
                title.setText("Please check your network connection and try again.");
            }
        }
    }
}