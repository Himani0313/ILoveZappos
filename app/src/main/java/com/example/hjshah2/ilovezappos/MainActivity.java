package com.example.hjshah2.ilovezappos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    SearchView searchView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView=(SearchView) findViewById(R.id.searchView);
        searchView.setQueryHint("Enter the search term");
        //textView = (TextView)findViewById(R.id.textView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                //textView.setText(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
               // textView.setText(newText);
                return false;
            }
        });
    }
}
