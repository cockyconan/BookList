package com.casper.testdrivendevelopment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class BookFragmentPagerAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> datas;
    ArrayList<String> titles;

    @Override
    public Fragment getItem(int position) {
        return datas == null ? null : datas.get(position);
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles == null ? null : titles.get(position);
    }

    public BookFragmentPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    public ArrayList<Fragment> getDatas() {
        return datas;
    }

    public void setDatas(ArrayList<Fragment> datas) {
        this.datas = datas;
    }

    public ArrayList<String> getTitles() {
        return titles;
    }

    public void setTitles(ArrayList<String> titles) {
        this.titles = titles;
    }
}
