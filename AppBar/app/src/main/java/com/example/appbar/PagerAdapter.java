package com.example.appbar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOftabs;
    public PagerAdapter(FragmentManager fm, int NumOftabs){
        super(fm);

        this.mNumOftabs = NumOftabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TabFragment1();
            case 1:
                return new TabFragment2();
            case 2:
                return new TabFragment3();
            default:
                return null;
        }    }


    @Override
    public int getCount() {
        return mNumOftabs;
    }
}
