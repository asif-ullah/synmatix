package au.com.synmatix.packages_avaible;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import au.com.synmatix.R;
import au.com.synmatix.helper.DbHelper;
import au.com.synmatix.packages_avaible.adapters.PackageDetail_adapter;
import au.com.synmatix.services_folder.Services_detail.Services_detail;
import au.com.synmatix.services_folder.Services_detail.String_sdetail;

/**
 * Created by user on 3/29/18.
 */

public class Packages_detail extends AppCompatActivity {
    DbHelper db;
    Cursor cur;
    List<String_pkgDetail> sdetails = null;
    RecyclerView rec_detail;
    String id, name;
    CollapsingToolbarLayout collapsingToolbarLayout;
    ImageView iv_pkg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.package_detail_activity);
        id = getIntent().getExtras().getString("id");
        name = getIntent().getExtras().getString("name");
        rec_detail = (RecyclerView) findViewById(R.id.sub_pkgdetail);
        iv_pkg = (ImageView) findViewById(R.id.iv_pkg);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//        collapsingToolbarLayout.setCollapsedTitleTypeface(fonttype);
//        collapsingToolbarLayout.setExpandedTitleTypeface(fonttype);
        collapsingToolbarLayout.setTitle(name);
        setImage(name);
        getDetail();


    }

    private void getDetail() {
        sdetails = new ArrayList<>();
        try {
            db = new DbHelper(Packages_detail.this);
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
        cur = db.sub_pakgDetail(id);

        try {
            while (cur.moveToNext()) {
                String_pkgDetail string_pdetail = new String_pkgDetail();
                String id = cur.getString(0);
                String name = cur.getString(1);
                String detail = cur.getString(2);

                string_pdetail.setId(id);
                string_pdetail.setTopicname(name);
                string_pdetail.setDetail(detail);

                sdetails.add(string_pdetail);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Packages_detail.this);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                rec_detail.setLayoutManager(linearLayoutManager);
                PackageDetail_adapter adapter = new PackageDetail_adapter(sdetails, Packages_detail.this);
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
        if (name.trim().equals("Mobile app startup")){
            Picasso.with(Packages_detail.this)
                    .load(R.drawable.mobileapp)
                    .error(R.drawable.banners)
                    .fit()
                    .into(iv_pkg);
        }else if (name.trim().equals("Standard SEO Package")){
            Picasso.with(Packages_detail.this)
                    .load(R.drawable.seo)
                    .error(R.drawable.banners)
                    .fit()
                    .into(iv_pkg);
        }else {
            Picasso.with(Packages_detail.this)
                    .load(R.drawable.logodesign)
                    .error(R.drawable.banners)
                    .fit()
                    .into(iv_pkg);
        }
    }

}
