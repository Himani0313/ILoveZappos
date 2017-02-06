package com.example.hjshah2.ilovezappos;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
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
import com.example.hjshah2.ilovezappos.databinding.ActivityMainBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    SearchView searchView;
    //TextView textView;
    private ProductDescriptionModel pdm;
    private ActivityMainBinding bind;
    private ZapposAPI zService;
    private Bitmap bmp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this,R.layout.activity_main);
        bind.setModel(pdm = new ProductDescriptionModel());
        searchView =(SearchView)findViewById(R.id.searchView);
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
            public boolean onQueryTextChange(final String newText) {
               // textView.setText(newText);
                zService.getProducts(newText).enqueue(new Callback<productList>() {
                    @Override
                    public void onResponse(Call<productList> call, Response<productList> response) {
                        if(response.isSuccessful()){
                            //name.setText(response.body().getCurrentResultCount());
                            productList pl =response.body();
                            ProductDescription product = pl.getResults().get(0);
                            pdm.setName(product.getProductName());
                            pdm.setBrandname(product.getBrandName());
                            pdm.setPrice(product.getPrice());
                            pdm.setThumbnail(product.getThumbnailImageUrl());

                            bind.setModel(pdm);
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
