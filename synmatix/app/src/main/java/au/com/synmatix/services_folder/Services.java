package au.com.synmatix.services_folder;

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
import au.com.synmatix.home_folder.String_aboutus;

/**
 * Created by user on 3/27/18.
 */

public class Services extends Fragment {
    DbHelper db;
    Cursor cur;
    List<String_services> servicesList = null;
    RecyclerView recyclerView_services;

    public Services() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_services, container, false);
        recyclerView_services = (RecyclerView) view.findViewById(R.id.rv_services);
        Services();
        return view;
    }

    private void Services() {
        servicesList = new ArrayList<>();
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
        cur = db.services();

        try {
            while (cur.moveToNext()) {
                String_services services = new String_services();
                String id = cur.getString(0);
                String name = cur.getString(1);
                String image = cur.getString(2);

                services.setId(id);
                services.setName(name);
                services.setImage(image);

                servicesList.add(services);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView_services.setLayoutManager(linearLayoutManager);
                Services_adapter adapter = new Services_adapter(servicesList, getContext());
                recyclerView_services.setAdapter(adapter);
                recyclerView_services.setNestedScrollingEnabled(false);


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
