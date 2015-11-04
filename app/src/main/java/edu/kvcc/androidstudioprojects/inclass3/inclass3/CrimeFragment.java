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


public class CrimeFragment extends Fragment {

//  private  EditText mEditText;

    private Crime mCrime;                           // create a class level variable

    private Button mDateButton;             // CHPT 8 added
    private CheckBox mSolvedCheckbox;


    private EditText mTitleField;
    // This methods does not do the inflating of the view, like the onCreate for an activity does

    @Override                       // used auto generator:   rt click - generate - override methods -
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCrime = new Crime();           // make a new instance of a crime (the crime model)


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

        mSolvedCheckbox = (CheckBox)v.findViewById(R.id.crime_solved);
        mSolvedCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {   // checkbox is child of the compound button class.. hence new CompoundButton
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                mCrime.setSolved(isChecked);                // sets the crime's solved property - checks and unchecks the button when changed.
            }
        });



        return v;
    }
}
