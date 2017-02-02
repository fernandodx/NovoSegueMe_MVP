package com.segueme.seguemepsul.view.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.segueme.seguemepsul.R;
import com.segueme.seguemepsul.adapter.MainTabAdapter;

/**
 * Created by FernandoDias on 16/01/17.
 */

public class MainTabFragment extends BaseFragment implements TabLayout.OnTabSelectedListener {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private MainTabAdapter mainTabAdapter;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        setRetainInstance(true);

        View view = layoutInflater.inflate(R.layout.main_tab_fragment, container, false);

        mainTabAdapter = new MainTabAdapter(getFragmentManager(), getContext());
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);

        int corNormal = ContextCompat.getColor(getContext(), R.color.colorlight);
        int corSelecionado = ContextCompat.getColor(getContext(), R.color.colorBranco);

        tabLayout.setTabTextColors(corNormal, corSelecionado);
        tabLayout.setSelectedTabIndicatorColor(corSelecionado);

        tabLayout.addTab(tabLayout.newTab().setText("EVENTOS"));
        tabLayout.addTab(tabLayout.newTab().setText("SEGUE-ME BSB"));
        tabLayout.addTab(tabLayout.newTab().setText("OBJETIVOS"));

        viewPager.setAdapter(mainTabAdapter);
        tabLayout.setOnTabSelectedListener(this);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        return view;
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public String getTagFragment() {
        return getClass().getSimpleName();
    }
}
