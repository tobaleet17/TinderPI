package com.l.marc.tinderpi;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.l.marc.tinderpi.Cartas.CartasFragmentTab;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int numeroTabs;

    public PagerAdapter(FragmentManager fm, int numeroTabs) {
        super(fm);
        this.numeroTabs = numeroTabs;
    }

    @Override
    public Fragment getItem(int posicion) {
        switch (posicion) {
            case 0:
                PerfilFragmentTab tab1 = new PerfilFragmentTab();
                return tab1;
            case 1:
                CartasFragmentTab cart1 = new CartasFragmentTab();
                return cart1;
            case 2:
                ChatFragmentTab chatTab1 = new ChatFragmentTab();
                return chatTab1;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numeroTabs;
    }
}
