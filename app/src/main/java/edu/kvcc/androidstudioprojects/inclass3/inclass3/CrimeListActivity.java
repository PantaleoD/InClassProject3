package edu.kvcc.androidstudioprojects.inclass3.inclass3;

import android.support.v4.app.Fragment;

/**
 * Created by dpantaleo on 10/27/2015.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment(){
        return new CrimeListFragment();

    }
}
