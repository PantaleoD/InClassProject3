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

   // THIS SETS UP A STING CONST FOR THE KEY PART OF THE 'EXTRA'
    //                                                            CHPT 10 p195   .
    private static final String EXTRA_CRIME_ID =    //              change from public to 'private' w/ pg 199 chpt and attaching arguments to a fragment - only used in this class
            "edu.kvcc.androidstudioprojects.inclass3.inclass3.crime_id";  //  TYPE W/O QUOTES AND IT WILL SUGGEST WHAT NEED..INTELLISENSE WILL HELP
//                                                                          crime_id is new and made up...use path so doesn't conflict w/ other things that may be floating about
    //                                                                      AND WILL BE THE 'KEY' TO BE ACCESSABLE FROM O/SD THE CLASS
    // THIS IS THE METHOD TO ACCEPT THE DATA FROM THE INTENT...VARIABLE ABOVE


    //        CONVENTION.... IF CLASS Context, uses lowercase for class name 'context'... this doesn't
    //       THIS IS A PUBLC AND STATIC SO THAT any OTHER ACTIVY OR FRAGMENT THAT MIGHT
    //          WANT TO START THIS aCTIVITY CAN GET A PROPERLY FORMATTED INTENT THAT WILL
    //          ALLOW THIS ACTIVITY TO START SUCCESFULLY.
    public static Intent newIntent(Context packageContext, UUID crimeId){        // crimeId
        Intent intent = new Intent(packageContext, CrimeActivity.class);   // looks same as in CrimeActivity.java - MAKES NEW INTENT
        intent.putExtra(EXTRA_CRIME_ID, crimeId);                       // now can put the extra...        PUT PASSED IN crimeId     AS AN EXTRA USING THE KEY DECLARED ABOVE
        return intent;                                                  // and returnS the intent
    }
// starts w/ the crime activity... .and gets the info that is needed


    @Override                      // added w Ch 9 - and Abstract Class called SingleFragmentActivity
//              bc done each time... easier to use ONE fragment activity!   so use an abstract class
//                                      singlefragmentactivity
    protected Fragment createFragment(){
     //   return  new CrimeFragment();            // take out w/ ch 10 pg 199 and add next lines to }



     /* return is to return a CrimeFragment that is created by calling the static method on CrimeFragment
        * called newInstance. the method take sin a UUID, and then returns a new instance of the Crime Fragment
         * By getting the UUID from teh Extras of the intent above, and then sending it over to the fragment through this method, we have
         * decoupled the fragment from the activity              */

        UUID crimeId = (UUID) getIntent()
                        .getSerializableExtra(EXTRA_CRIME_ID);      // pass  the UUID value form the intent (just call the Intent
                                                                    // and now calling the new method creatd in CrimeFragment...next chg a few things in CrimeFragment to see it
        return CrimeFragment.newInstance(crimeId);
             }
 }
 /*   @Override                                                     // taken out of here and moved to _____________________________
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

