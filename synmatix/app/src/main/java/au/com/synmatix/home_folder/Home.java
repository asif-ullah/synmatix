package au.com.synmatix.home_folder;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import au.com.synmatix.R;
import au.com.synmatix.helper.DbHelper;
import au.com.synmatix.utils.CustomTextSliderView;

/**
 * Created by user on 3/27/18.
 */

public class Home extends Fragment {
    DbHelper db;
    Cursor cur;
    List<String_aboutus> about_usList = null;
    RecyclerView recyclerView_about;
    SliderLayout sliderLayout;

    public Home() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView_about = (RecyclerView) view.findViewById(R.id.rv_home);
        sliderLayout = (SliderLayout) view.findViewById(R.id.slider);

        aboutus();
        return view;
    }

    private void aboutus() {
        about_usList = new ArrayList<>();
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
        cur = db.about_us();

        try {
            while (cur.moveToNext()) {
                String_aboutus string_aboutus = new String_aboutus();
                String id = cur.getString(0);
                String s_name = cur.getString(1);
                String detail = cur.getString(2);

                string_aboutus.setId(id);
                string_aboutus.setName(s_name);
                string_aboutus.setDetail(detail);

                about_usList.add(string_aboutus);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView_about.setLayoutManager(linearLayoutManager);
                Home_adapter adapter = new Home_adapter(about_usList, getContext());
                recyclerView_about.setAdapter(adapter);
                recyclerView_about.setNestedScrollingEnabled(false);

                HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
                file_maps.put("Mobile Applications Development", R.drawable.mobileapp);
                file_maps.put("Websites Design and Development", R.drawable.webdevelopment);
                file_maps.put("Digital Markrting", R.drawable.digitalmarketing);
                file_maps.put("Search Engine Optimization", R.drawable.banners);
                file_maps.put("Logo Design", R.drawable.logodesign);
                file_maps.put("Content Marketing", R.drawable.contentmarketing);
                file_maps.put("Google Adword", R.drawable.googleadwords);
                file_maps.put("Email Marketing", R.drawable.emailmarketing);

                for (String name : file_maps.keySet()) {
                    //Typeface fonttype = Typeface.createFromAsset(getContext().getAssets(), "fonts/fff.ttf");

                    CustomTextSliderView textSliderView = new CustomTextSliderView(getActivity());
                    textSliderView
                            .description(name)
                            .image(file_maps.get(name))
                            .setScaleType(BaseSliderView.ScaleType.Fit);

                    sliderLayout.addSlider(textSliderView);
                }

                sliderLayout.setPresetTransformer(SliderLayout.Transformer.DepthPage);
                sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);
                sliderLayout.setCustomAnimation(new DescriptionAnimation());
                sliderLayout.setDuration(5000);

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
