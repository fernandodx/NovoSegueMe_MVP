package com.segueme.seguemepsul.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.segueme.seguemepsul.R;
import com.segueme.seguemepsul.view.fragment.BaseFragment;
import com.segueme.seguemepsul.view.fragment.MainTabFragment;

public class MainMobileActivity extends BaseActivity implements FragmentManager.OnBackStackChangedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mobile);

        getSupportFragmentManager().addOnBackStackChangedListener(this);

        MainTabFragment mainTabFragment = new MainTabFragment();
        trocarFragment(mainTabFragment);

    }


    public void trocarFragment(BaseFragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container_main, fragment, fragment.getTagFragment());
        fragmentTransaction.addToBackStack(fragment.getTagFragment());
        fragmentTransaction.commit();
    }

    public void addFragment(BaseFragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container_main, fragment, fragment.getTagFragment());
        fragmentTransaction.addToBackStack(fragment.getTagFragment());
        fragmentTransaction.commit();
    }

    @Override
    public void onBackStackChanged() {

        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
          finish();
        }
    }
}
