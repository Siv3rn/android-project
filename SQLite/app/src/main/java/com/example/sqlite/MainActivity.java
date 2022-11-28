package com.example.sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private DBManager dbManager;

    private ListView listView;

    private SimpleCursorAdapter adapter;

    final String[] from = new String[] { DatabaseHelper._ID,
            DatabaseHelper.NAME, DatabaseHelper.GENDER,DatabaseHelper.PRODI,DatabaseHelper.ADDRESS };

    final int[] to = new int[] { R.id.id, R.id.name,R.id.jk,R.id.prodi,R.id.adress};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_emp_list);

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();
        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.activity_view_record, cursor, from, to, 0);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView idTextView = (TextView) view.findViewById(R.id.id);
                TextView nameTextView = (TextView) view.findViewById(R.id.name);
                TextView genderTextView = (TextView) view.findViewById(R.id.gender);
                TextView prodiTextView = (TextView) view.findViewById(R.id.prod);
                TextView adressTextView = (TextView) view.findViewById(R.id.addr);

                String id = idTextView.getText().toString();
                String name = nameTextView.getText().toString();
                String gender = genderTextView.getText().toString();
                String prodi = prodiTextView.getText().toString();
                String adress = adressTextView.getText().toString();

                Intent intent = new Intent(getApplicationContext(), MainDetail.class);
                intent.putExtra("tname", name);
                intent.putExtra("tgen", gender);
                intent.putExtra("tprod", prodi);
                intent.putExtra("tadd", adress);
                intent.putExtra("id", id);

                startActivity(intent);

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.add_record) {

            Intent add_mem = new Intent(this, AddCountryActivity.class);
            startActivity(add_mem);

        }
        return super.onOptionsItemSelected(item);
    }
    }

