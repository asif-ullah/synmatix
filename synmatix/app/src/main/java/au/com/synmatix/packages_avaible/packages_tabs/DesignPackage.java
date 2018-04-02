package au.com.synmatix.packages_avaible.packages_tabs;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import au.com.synmatix.R;
import au.com.synmatix.helper.DbHelper;
import au.com.synmatix.packages_avaible.Strinh_subpkges;
import au.com.synmatix.packages_avaible.adapters.Design_adapter;

/**
 * Created by user on 3/28/18.
 */

public class DesignPackage extends Fragment {
    DbHelper db;
    Cursor cur;
    List<Strinh_subpkges> strinh_subpkges = null;
    RecyclerView recyclerView_pkgs;

    public DesignPackage() {

        //empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
// Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_design, container, false);
        recyclerView_pkgs = (RecyclerView) view.findViewById(R.id.rv_design);
        Seo_packages();
        return view;
    }

    private void Seo_packages() {
        strinh_subpkges = new ArrayList<>();
        try {
            db = new DbHelper(getContext());
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
        cur = db.designs();

        try {
            while (cur.moveToNext()) {
                Strinh_subpkges subpkges = new Strinh_subpkges();
                String id = cur.getString(0);
                String name = cur.getString(1);

                subpkges.setId(id);
                subpkges.setTopic(name);

                strinh_subpkges.add(subpkges);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView_pkgs.setLayoutManager(linearLayoutManager);
                Design_adapter adapter = new Design_adapter(strinh_subpkges, getContext());
                recyclerView_pkgs.setAdapter(adapter);
                recyclerView_pkgs.setNestedScrollingEnabled(false);


                adapter.notifyDataSetChanged();


            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cur.close();
            db.close();
        }


    }


}
