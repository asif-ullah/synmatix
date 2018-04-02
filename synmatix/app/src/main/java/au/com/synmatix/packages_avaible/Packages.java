package au.com.synmatix.packages_avaible;


import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import au.com.synmatix.R;
import au.com.synmatix.packages_avaible.packages_tabs.DesignPackage;
import au.com.synmatix.packages_avaible.packages_tabs.Mobile;
import au.com.synmatix.packages_avaible.packages_tabs.Seo;
import au.com.synmatix.packages_avaible.packages_tabs.WebsitePackage;

/**
 * Created by user on 3/28/18.
 */

public class Packages extends AppCompatActivity {
    private TabLayout tabLayout;
    static ViewPager viewPager;

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.packages_maintab);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabTextColors(ColorStateList.valueOf(Color.WHITE));
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new Packages.ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Seo(), "SEO Packages");
        adapter.addFragment(new DesignPackage(), "Design Packages");
        adapter.addFragment(new Mobile(), "Mobile App Development");
//        adapter.addFragment(new WebsitePackage(), "Website Packages");
        viewPager.setAdapter(adapter);
    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }


    }

}
