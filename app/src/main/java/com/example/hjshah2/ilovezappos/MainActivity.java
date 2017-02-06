package com.example.hjshah2.ilovezappos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.hjshah2.ilovezappos.Model.ProductDescription;
import com.example.hjshah2.ilovezappos.Model.productList;
import com.example.hjshah2.ilovezappos.Services.ZapposAPI;
import com.example.hjshah2.ilovezappos.Utils.APIUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    SearchView searchView;
    //TextView textView;
    TextView name;
    TextView brandname;
    TextView price;
    private ZapposAPI zService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (TextView) findViewById(R.id.name);
        searchView=(SearchView) findViewById(R.id.searchView);
        searchView.setQueryHint("Enter the search term");
        //textView = (TextView)findViewById(R.id.textView);
        zService = APIUtils.getService();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                //textView.setText(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
               // textView.setText(newText);
                zService.getProducts(newText).enqueue(new Callback<productList>() {
                    @Override
                    public void onResponse(Call<productList> call, Response<productList> response) {
                        if(response.isSuccessful()){
                            name.setText(response.body().getCurrentResultCount());
                        }
                        else{

                        }
                    }

                    @Override
                    public void onFailure(Call<productList> call, Throwable t) {
                        Log.d("MainActivity", "error loading from API");
                    }
                });
                return false;
            }
        });
    }
}
