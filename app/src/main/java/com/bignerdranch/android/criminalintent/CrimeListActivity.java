package com.bignerdranch.android.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by SAN on 11/01/2016.
 */
public class CrimeListActivity extends SingleFragmentActivity implements CrimeFragment.Callbacks {




    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }

    @Override
    public void onCrimeUpdated(Crime crime) {
        //CrimeListFragment listFragment = (CrimeListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        //listFragment.updateUI();
    }
}
