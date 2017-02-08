package com.example.hjshah2.ilovezappos;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hjshah2.ilovezappos.Model.ProductDescription;
import com.example.hjshah2.ilovezappos.Model.productList;
import com.example.hjshah2.ilovezappos.Services.ZapposAPI;
import com.example.hjshah2.ilovezappos.Utils.APIUtils;
import com.example.hjshah2.ilovezappos.databinding.ActivityMainBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements Animation.AnimationListener {

    SearchView searchView;
    //TextView textView;
    private ProductDescriptionModel pdm;
    private ActivityMainBinding bind;
    private ZapposAPI zService;
    private FloatingActionButton cart,fav;
    Animation animationbounce;
    Animation animationcustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this,R.layout.activity_main);
        bind.setProduct(pdm = new ProductDescriptionModel());
        searchView =(SearchView)findViewById(R.id.searchView);
        searchView.setQueryHint("Enter the search term");

        animationbounce = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_bounce);
        animationbounce.setAnimationListener(this);
        animationcustom = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.custom);
        animationcustom.setAnimationListener(this);
        cart = (FloatingActionButton)findViewById(R.id.floatingActionButton2);
        fav = (FloatingActionButton)findViewById(R.id.floatingActionButton3);
        //textView = (TextView)findViewById(R.id.textView);
        zService = APIUtils.getService();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextChange(String query) {
                //textView.setText(query);
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(final String newText) {
               // textView.setText(newText);
                zService.getProducts(newText).enqueue(new Callback<productList>() {
                    @Override
                    public void onResponse(Call<productList> call, Response<productList> response) {
                        if(response.isSuccessful()){
                            //name.setText(response.body().getCurrentResultCount());


                            productList pl =response.body();
                            if (pl == null){
                                pdm.setName("Could not find the product" + newText);
                                pdm.setPrice("");
                                pdm.setOriginalPrice("");
                                pdm.setBrandname("");
                                pdm.setExists(false);
                                pdm.setThumbnailImageUrl("");
                                pdm.setOff("");
                            }
                            else {
                                TextView textView = (TextView)findViewById(R.id.originalprice);
                                textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                                ProductDescription product = pl.getResults().get(0);
                                pdm.setName(product.getProductName());
                                pdm.setBrandname(product.getBrandName());
                                pdm.setPrice(product.getPrice());
                                pdm.setThumbnailImageUrl(product.getThumbnailImageUrl());
                                pdm.setOriginalPrice(product.getOriginalPrice());
                                pdm.setExists(true);
                                pdm.setOff(product.getPercentOff() + " Discount");

                                bind.setProduct(pdm);
                            }

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

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cart.startAnimation(animationbounce);
            }
        });
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fav.startAnimation(animationcustom);
            }
        });
    }

    @Override
    public void onAnimationStart(Animation animation) {
        if(animation == animationbounce){
            Toast.makeText(MainActivity.this,"Item added to cart",Toast.LENGTH_SHORT).show();
        }
        if(animation == animationcustom){
            Toast.makeText(MainActivity.this,"Item added to favorites",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if(animation == animationbounce){
            cart.setImageResource(R.mipmap.ic_cartloaded);
        }
        if(animation == animationcustom){
            fav.setImageResource(R.mipmap.ic_done);
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        if(animation == animationbounce){
            Toast.makeText(MainActivity.this,"Already added to cart",Toast.LENGTH_SHORT).show();
        }
        if(animation == animationcustom){
            Toast.makeText(MainActivity.this,"Already added to favorites",Toast.LENGTH_SHORT).show();
        }
    }
}
