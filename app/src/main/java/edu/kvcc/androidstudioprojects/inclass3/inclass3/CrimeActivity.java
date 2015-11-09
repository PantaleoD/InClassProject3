package edu.kvcc.androidstudioprojects.inclass3.inclass3;
// FOR A SINZGLE CRIE

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;   // this was handled in the gradle app
import android.support.v4.app.FragmentManager;    // support version of the fragement manager
import android.os.Bundle;

import java.util.UUID;

//public class CrimeActivity extends FragmentActivity {              take out in chpt 9 and replace w/

public class CrimeActivity extends SingleFragmentActivity{         // the single fragment activity.... will inherit from the abstract activity called
    //                                                            CHPT 10 p195  crash and burn....
    private static final String EXTRA_CRIME_ID =    //              change from public to 'private' w/ pg 199 chpt and attaching arguments to a fragment
            "edu.kvcc.androidstudioprojects.inclass3.inclass3.crime_id";

    public static Intent newIntent(Context packageContext, UUID crimeId){
        Intent intent = new Intent(packageContext, CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }



    @Override                      // added w Ch 9 - and Abstract Class called SingleFragmentActivity
//              bc done each time... easier to use ONE fragment activity!   so use an abstract class
//                                      singlefragmentactivity
    protected Fragment createFragment(){
     //   return  new CrimeFragment();            // take out w/ ch 10 pg 199 and add next lines to }
        UUID crimeId = (UUID) getIntent()
                        .getSerializableExtra(EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeId);
             }
 }
 /*   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);                // inflates activity (w/ fragment...
                                            //  get a variable that represents the support version of the fragment manager.
                                            // If the Android OS version of Fragmt mgr is imported this stmt will fail.  Must have the
                                            //          support version imported
        FragmentManager fm = getSupportFragmentManager();

                                                            // Use the fragment manager to get the fragment that is currently in
                                                            // the frame that we created in the crimeFragment.xml file
                                                                // On the start of the app - this will be null since there won't be
                                                                // anything there until we add it. once something is added - this method will return whatever is in it.

        Fragment fragment = fm.findFragmentById(R.id.fragment_container);      // mk instance of the fragment by quering the fragment want         // to get the fragment value - fetching the fragmt container to assign to fragment
                                                                                        // referncing the container in frame layout; always null at start of execution
                                            //    chk if frgmt us null . on Starting the app it will be.
                                            // Once we add a fragment to the frame it will Not be.
        if (fragment == null) {                 // 1st time used...there is nothing in there  .....put something there! - following is the work to add it to the frame:

            fragment = new CrimeFragment();          // returns / makes  a new fragment   first time used   - follows instructions in the CrimeFragment        // this is detroyed w/ @ new activity;  but with the fragment

                                                     //. to add it to the frame... // 1st: start a transaction, then
                                                     // add the fragment we want to attach & then commiting the changes.
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)       // can be on 1 line.   this adds the fragmt to the fragment we just created and then commit's it
                    .commit();                                      // now CrimeFragment is in here.... in fragment
        }
    }
}

                                        // dave created a 2nd frame layout w/in a linear layout in the xml file ..then duplicated
                                        // code above to show the 2 frames w/in the 1 view - fragment fragment2
*/








 /*   @Override                                       NA so removed
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_crime, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }  */

