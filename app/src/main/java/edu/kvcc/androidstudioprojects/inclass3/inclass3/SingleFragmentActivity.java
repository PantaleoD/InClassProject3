
// this is created as an Abstract class to be reused by all fragments vs having 1 per fragmetn
//

package edu.kvcc.androidstudioprojects.inclass3.inclass3;

        import android.support.v4.app.FragmentManager;    // needed to remember to change this line to support.v4.app.FragmentManager
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentActivity;   // make sure they are from support

/**
 * Created by dpantaleo on 10/27/2015.
 */
public abstract class SingleFragmentActivity extends FragmentActivity {  // makes the class Abstract

    protected abstract Fragment createFragment();      // abstract method also....  called 1st ... so that every activity that we create
    //                          must implement this method. Instead of having every activity that we create inherit from FragmentActivity it will
    // inherit from THIS class, which now provides some base functionality for us...we will no longer duplicate
    //                          this code in any child activities that we create.


    @Override                           // must be overriden...each time use...must inherit (same as what was in CrimeActivity.java
    public  void  onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);                             // uses parent ..
        setContentView(R.layout.activity_fragment);         // activity's view inflatesd w/ Fragment &

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);         // id's in layout... if 2 names..how know which one to use?

        if (fragment == null){                              // this looks for fragmt in fragment mgr &, if not there - add's it!
            fragment = createFragment();                   // this is only difference between CrimeActivity & this..now Abstract since
            // NOT calling specific fragment - calls the method to createFragment()... not 'new' but existing..just call method
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}
