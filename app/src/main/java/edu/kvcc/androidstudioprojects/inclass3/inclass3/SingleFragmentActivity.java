
// this is created as an Abstract class to be reused by all fragments vs having 1 per fragmetn
//


package edu.kvcc.androidstudioprojects.inclass3.inclass3;

import android.support.v4.app.FragmentManager;    // needed to remember to change this line to support.v4.app.FragmentManager
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * Created by dpantaleo on 10/27/2015.
 */
public abstract class SingleFragmentActivity extends FragmentActivity {

    protected abstract Fragment createFragment();

    @Override
    public  void  onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);         // activity's view inflatesd w/ Fragment &

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null){                              // this looks for fragmt in fragment mgr &, if not there - add's it!
            fragment = createFragment();               // this is only difference between CrimeActivity & this..now Abstract since
                                                        // NOT calling specific fragment
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}
