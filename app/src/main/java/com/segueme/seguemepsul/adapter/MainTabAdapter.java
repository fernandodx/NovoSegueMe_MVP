package com.segueme.seguemepsul.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.segueme.seguemepsul.util.Constantes;
import com.segueme.seguemepsul.view.fragment.ListaEventosFragment;
import com.segueme.seguemepsul.view.fragment.SiteSegueMeFragment;

/**
 * Created by FernandoDias on 18/01/17.
 */

public class MainTabAdapter extends FragmentStatePagerAdapter {

    private Context context;
    private FragmentManager fm;



    public MainTabAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        this.fm = fm;
    }

    @Override
    public Fragment getItem(int position) {

        if(position == 0) {
            //Lista eventos
            return new ListaEventosFragment();
        }

        if(position == 1) {

            //Site Segue-me BSB
            SiteSegueMeFragment siteSegueMe = new SiteSegueMeFragment();

            Bundle parametros = new Bundle();
            parametros.putString(SiteSegueMeFragment.URL_SITE, Constantes.URL_SITE_SEGUE_BSB);

            siteSegueMe.setArguments(parametros);

            return siteSegueMe;
        }

        if(position == 2) {

            //Site estrutura
            SiteSegueMeFragment siteSegueMe = new SiteSegueMeFragment();

            Bundle parametros = new Bundle();
            parametros.putString(SiteSegueMeFragment.URL_SITE, Constantes.URL_SITE_OBJETIVO);

            siteSegueMe.setArguments(parametros);

            return siteSegueMe;
        }



        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
