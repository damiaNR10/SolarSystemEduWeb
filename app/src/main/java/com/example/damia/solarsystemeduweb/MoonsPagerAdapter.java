package com.example.damia.solarsystemeduweb;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class MoonsPagerAdapter extends FragmentStatePagerAdapter{

    private final SolarObject[] objectsWithMoons;

    public MoonsPagerAdapter(FragmentManager fm, SolarObject[] objectsWithMoons) {
        super(fm);
        this.objectsWithMoons = objectsWithMoons;
    }

    @Override
    public Fragment getItem(int i) {
        return SolarObjectsFragment.newInstance(objectsWithMoons[i].getMoons());
    }


    @Override
    public int getCount() {
        return objectsWithMoons.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return objectsWithMoons[position].getName();
    }
}