package com.example.baocaocuoiky_vothanhhai;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.baocaocuoiky_vothanhhai.Fragment.HomeFragment;
import com.example.baocaocuoiky_vothanhhai.Fragment.ProfileFragment;
import com.example.baocaocuoiky_vothanhhai.Fragment.SettingFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return new ProfileFragment();
            case 2:
                return new SettingFragment();
            default:
                return new HomeFragment();
        }
    }
    @Override
    public int getCount() {
        return 3;
    }

}
