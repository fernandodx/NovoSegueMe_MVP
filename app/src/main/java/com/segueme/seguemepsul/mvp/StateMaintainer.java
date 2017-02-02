package com.segueme.seguemepsul.mvp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.HashMap;

/**
 * Created by FernandoDias on 26/01/17.
 */

public class StateMaintainer {

    protected  final String TAG = getClass().getSimpleName();

    private final String stateMaintainerTag;
    private final WeakReference<FragmentManager> fragmentManager;
    private StateMngFragment stateMngFragment;


    public StateMaintainer(FragmentManager fragmentManager, String stateMaintainerTag) {
        this.stateMaintainerTag = stateMaintainerTag;
        this.fragmentManager = new WeakReference<>(fragmentManager);
    }

    public boolean firstTimeIn() {

        stateMngFragment = (StateMngFragment) fragmentManager.get().findFragmentByTag(stateMaintainerTag);

        if(stateMngFragment == null) {
            Log.d(TAG, "Criando  StateMngFragment - " + stateMngFragment);

            stateMngFragment = new StateMngFragment();

            fragmentManager.get().beginTransaction().add(stateMngFragment, stateMaintainerTag).commit();

            return true;

        } else {

            Log.d(TAG, "Retornando StateMngFragment já existente - " + stateMngFragment);

            return false;
        }
    }

    public void put(String key, Object obj) {
        stateMngFragment.put(key, obj);
    }

    public void put(Object obj) {
      put(obj.getClass().getName(), obj);
    }

    public <T> T get(String key) {
        return stateMngFragment.get(key);
    }

    public boolean hasKey(String key) {
        return stateMngFragment.get(key) != null;
    }


    public static class StateMngFragment extends Fragment {

        private HashMap<String, Object> dados = new HashMap<>();

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setRetainInstance(true);

        }

        public void put(Object obj) {
            dados.put(obj.getClass().getName(), obj);
        }

        public void  put(String key, Object obj) {
            dados.put(key, obj);
        }

        public <T> T get(String key) {
            return (T) dados.get(key);
        }





    }
}
