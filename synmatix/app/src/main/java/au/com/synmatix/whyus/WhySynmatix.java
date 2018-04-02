package au.com.synmatix.whyus;

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

public class WhySynmatix extends Fragment {
    DbHelper db;
    Cursor cur;
    List<String_yus> yusList = null;
    RecyclerView rec_yus;
    public  WhySynmatix(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_why_synmatix, container, false);
        rec_yus = (RecyclerView) view.findViewById(R.id.rv_yus);
        Synmatixdata();
        return view;
    }

    private void Synmatixdata() {
        yusList = new ArrayList<>();
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
        cur = db.yUs();

        try {
            while (cur.moveToNext()) {
                String_yus yus = new String_yus();
                String id = cur.getString(0);
                String name = cur.getString(1);
                String detail = cur.getString(2);

                yus.setId(id);
                yus.setName(name);
                yus.setDetail(detail);

                yusList.add(yus);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                rec_yus.setLayoutManager(linearLayoutManager);
                WhyUs_adapter adapter = new WhyUs_adapter(yusList, getContext());
                rec_yus.setAdapter(adapter);
                rec_yus.setNestedScrollingEnabled(false);


                adapter.notifyDataSetChanged();


            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cur.close();
            db.close();
        }


    }}
