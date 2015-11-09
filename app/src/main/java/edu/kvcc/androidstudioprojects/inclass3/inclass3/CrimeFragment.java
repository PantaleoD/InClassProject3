package edu.kvcc.androidstudioprojects.inclass3.inclass3;    // TO MUCH IN HERE!!!  got this w/2 checkboxes..DONT USE THEM!!

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.UUID;


public class CrimeFragment extends Fragment {
//                                                      static variable to be used as the KEY for the bundle &
    //                                                  retrieve from the bundle
    private static final String ARG_CRIME_ID = "crime_id";          // end of chpt 10 to loosely couple the act and fragmnt


//  private  EditText mEditText;

    private Crime mCrime;                           // create a class level variable

    private Button mDateButton;             // CHPT 8 added
    private CheckBox mSolvedCheckbox;

    private EditText mTitleField;

    //                      this will return a properly formed crime fragment... to get it going..need a UUID  and then can create a new instance of Crime Fragment..
    //                          next use this in CrimeActivity

    /*  This is a static method that is used to create a new instance of a CrimeFragment w/ the correctd information
     * of a Crime based on a UUID that is passed in. Any Activity inclduing the one we are using (CrimeActivity) can call this
      * method and get a properly created CrimeFragment.   the method takes the UUID that is passed in, and then it sets it in an argument
       * bundle that can be passed along with the Fragment.   Once the fragment is started, the data in the bundle can be retrieved and used.
       * */
  public static CrimeFragment newInstance(UUID crimeId) {           // chpt 10  pg 198 ... best way to attach arguments to a fragment/ make a newe bundle
      Bundle args = new Bundle();                                   // CREATES A NEW ARGUMNETS BUNDLE
      args.putSerializable(ARG_CRIME_ID, crimeId);                  // can only send a uuid an serializable;  PUTS THE uuid IN AS A VALUE TO THE BUNDLE

    CrimeFragment fragment = new CrimeFragment();                   // make a new CrimeFragment instance
    fragment.setArguments(args);                                    // calls setsArgument method from the fragment to get the fields; SETS TEH ARGS ON THE FRAMENT WITH THE BUNDLE
    return fragment;                                                //   FINALLY RETURN THE FRAGMENT THAT WAS CREATED.           and return the fragment  (self contained so long name is NA)
  }

    // This methods does not do the inflating of the view, like the onCreate for an activity does
    @Override                       // used auto generator:   rt click - generate - override methods -
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        //                           getIntent returns value from the Intent... and from that gotten intent...get teh extra out
        //                  getSerializableExtra (can be converted to a string) and then put back into a string... unique for UUID so there is no put/get for UUID
        //                  best we have is  getSerializableExtra
 //          UUID crimeId = (UUID) getActivity().getIntent()
 //              .getSerializableExtra(CrimeActivity.EXTRA_CRIME_ID);         //   this line casts the special UUID as serializable           // take out chpt 10 pg 199 and add next line:
        //                      almost all work for a crime activiyt is done in CrimeFragment...hence the work here!... to assign values to fragment mmmmust assign here...best spot to get data is in
        //                      this onCreate method...
          //                must get out the properites and assign to widget controls , which belong to the fragment control
        //               mCrime = new Crime();           // make a new instance of a crime (the crime model) - w/chpt 10 want a SPECIFIC crime... hence the UUID

        //              gets the UUID from teh Extras for the activity. Since UUID is not a simple type, sucha s int, double..it must be of a type that implements the
        //              serializable interface. UUID does  implement serializable, so it can be sent thru as an Extra using he serializable type
        //.                     We didnt have to do anything w/ serialaizable. We just needed to kjnow that UUID is serializable, and so that is the method we used to store
        //               and retrieve it. Since it arrives here as serializable, it must be casted to a UUID. That is the (UUID) part of the next line.


        // want Fragment to pull data out of the bundle..... so line of code above commented out - this time use getArguments... which retrieves data out of the bundle
        //                  again using the ARG_CRIME_ID (serializable b/c still a UUID
        /*  now that the fragment is being started with an UUID passed by the bundle      called savedInstanceState, we need to fetch out the CrimeID
        * from the bundle.   Get the UUID as a serializable, and then cast it to the type UUID*/

        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);            // CrimeLab IS OUR SINGLETON... PASSING IN A CONTEXT (getAcdtiviy()) and a single instnace of a crime is returned

       // can divide next line into 2....
        // WE WANT TO GET A SPECIFIC CRIME FROM OUR CRIME COLLECTION BASED ON TEH UUID THAT WE HAVE. IN ORDER TO DO THAT, WE GET EH SINGLETON INSTANCE OF OUR CRIME LAB BY CALLING THE
        //   STATIC METHOD 'GET' ON CRIMELAB

     /*   next part:
     now that have crime lab.. we can call the getCrime method on it passing the UUID to get back a single crime */

        //                                                                      using the UUID... searches w/ a For and retrieves a single crime....
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);           // now get ID out of the EXTRA to get a specific instance of a crime   // CHPT 10 Pg 196 ERROR WHY??? w/ chg in p199 no error hmmmm
    }

// This method IS responsible for inflating the view
    //  & getting the content of the screen.

    @Nullable     // ok to return nothing   - part of auto generate  // used auto generator:   rt click - generate - override methods -
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
  //      return super.onCreateView(inflater, container, savedInstanceState);  // take this out and do own:
     //      create own view var...then inflate using the layout, using view group's 'container' , and then pass in false)
        View v = inflater.inflate(R.layout.fragment_crime, container, false);  // finds layout view, inflates it - the view, turns it into java code of type 'View'// this is the xml file name!
                                                                   // when app needs to display this..will call this method to display the view
        // false is asking if to add to root...but actually adding into the inside of the frame layout of

        // working on text to be so added into fragment file:

       mTitleField = (EditText)v.findViewById(R.id.crime_title);  // here because do after a view is created/inflated before working with any data

       mTitleField.setText(mCrime.getmTitle());                  // CHPT 10 p 196 shortcut of retrieving an extra - TO SET THE TEXT ON THE CRIME IN HAND; do w/ checkbx too... date is auto

        mTitleField.addTextChangedListener(new TextWatcher() {     // next 3 methods are auto added when type T then hit tab
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // blank!

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mCrime.setmTitle(charSequence.toString());                           // added this....to set title
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // blank!!
            }
        });

        mDateButton = (Button)v.findViewById((R.id.crime_date));                // added w/ ch 8 (p153) t wire up the buttons widgets:    // puts date on the button
        mDateButton.setText(mCrime.getDate().toString());                    // uses date from Crime.Java   mDate = new Date();
        mDateButton.setEnabled(false);

        mSolvedCheckbox = (CheckBox)v.findViewById(R.id.crime_solved);          // also set in ch 10 to display using Intent

        mSolvedCheckbox.setChecked(mCrime.isSolved());                 // Chpt 10   p 196 shortcut of retrieving an extra
        mSolvedCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {   // checkbox is child of the compound button class.. hence new CompoundButton
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                mCrime.setSolved(isChecked);                // sets the crime's solved property - checks and unchecks the button when changed.
            }
        });



        return v;
    }
}
