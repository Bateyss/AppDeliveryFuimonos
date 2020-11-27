package com.fm.modules.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    int numofTabs;

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior, int numofTabs) {
        super(fm, behavior);
        this.numofTabs = numofTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Pedidos();
            case 1:
                return new PedidoActual();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numofTabs;
    }
}
