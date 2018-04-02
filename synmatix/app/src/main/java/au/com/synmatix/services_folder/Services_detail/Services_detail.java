package au.com.synmatix.services_folder.Services_detail;

import android.database.Cursor;
import android.graphics.Typeface;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import au.com.synmatix.R;
import au.com.synmatix.helper.DbHelper;
import au.com.synmatix.home_folder.String_aboutus;
import au.com.synmatix.services_folder.Services;
import au.com.synmatix.services_folder.Services_adapter;
import au.com.synmatix.services_folder.String_services;

public class Services_detail extends AppCompatActivity {
    DbHelper db;
    Cursor cur;
    List<String_sdetail> sdetails = null;
    RecyclerView rec_detail;
    String id,name;
    CollapsingToolbarLayout collapsingToolbarLayout;
    ImageView service_image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_detail);
        id=getIntent().getExtras().getString("id");
        name=getIntent().getExtras().getString("name");
        rec_detail=(RecyclerView)findViewById(R.id.rv_services);
        collapsingToolbarLayout= (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        service_image= (ImageView) findViewById(R.id.iv_services);

        Typeface fonttype = Typeface.createFromAsset(getApplication().getAssets(), "fonts/fff.ttf");
        collapsingToolbarLayout.setCollapsedTitleTypeface(fonttype);
        collapsingToolbarLayout.setExpandedTitleTypeface(fonttype);
        collapsingToolbarLayout.setTitle(name);
        setImage(name);
        getDetail();


    }

    private void getDetail() {
        sdetails = new ArrayList<>();
        try {
            db = new DbHelper(Services_detail.this);
        } catch (IOException e2) {
            e2.printStackTrace();
        }
// create database
        try {
            db.createdatabase();
        } catch (IOException e) {

            e.printStackTrace();
        }
// open database
        db.opendatabase();
        cur = db.services_detail(id);

        try {
            while (cur.moveToNext()) {
                String_sdetail string_sdetail = new String_sdetail();
                String id = cur.getString(0);
                String name = cur.getString(1);
                String detail = cur.getString(2);

                string_sdetail.setId(id);
                string_sdetail.setName(name);
                string_sdetail.setDetail(detail);

                sdetails.add(string_sdetail);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Services_detail.this);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                rec_detail.setLayoutManager(linearLayoutManager);
                ServiceDetail_adapter adapter = new ServiceDetail_adapter(sdetails, Services_detail.this);
                rec_detail.setAdapter(adapter);
                rec_detail.setNestedScrollingEnabled(false);


                adapter.notifyDataSetChanged();


            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cur.close();
            db.close();
        }

    }

    private void setImage(String name) {
        if (name.trim().equals("Social Media Marketing")){
            Picasso.with(Services_detail.this)
                    .load(R.drawable.digitalmarketing)
                    .error(R.drawable.banners)
                    .fit()
                    .into(service_image);
        }else if (name.trim().equals("Google Adwords")){
            Picasso.with(Services_detail.this)
                    .load(R.drawable.googleadwords)
                    .error(R.drawable.banners)
                    .fit()
                    .into(service_image);
        }else if (name.trim().equals("Web Design & Development")){
            Picasso.with(Services_detail.this)
                    .load(R.drawable.webdevelopment)
                    .error(R.drawable.banners)
                    .fit()
                    .into(service_image);
        }else if (name.trim().equals("Content Marketing")){
            Picasso.with(Services_detail.this)
                    .load(R.drawable.contentmarketing)
                    .error(R.drawable.banners)
                    .fit()
                    .into(service_image);
        }else if (name.trim().equals("Search Engine Optimisation")){
            Picasso.with(Services_detail.this)
                    .load(R.drawable.seo)
                    .error(R.drawable.banners)
                    .fit()
                    .into(service_image);
        }else if (name.trim().equals("Logo Design")){
            Picasso.with(Services_detail.this)
                    .load(R.drawable.logodesign)
                    .error(R.drawable.banners)
                    .fit()
                    .into(service_image);
        }else if (name.trim().equals("Email Marketing Experts in Melbourne")){
            Picasso.with(Services_detail.this)
                    .load(R.drawable.emailmarketing)
                    .error(R.drawable.banners)
                    .fit()
                    .into(service_image);
        }else {
            Picasso.with(Services_detail.this)
                    .load(R.drawable.digitalmarketing)
                    .error(R.drawable.banners)
                    .fit()
                    .into(service_image);
        }
    }

}
