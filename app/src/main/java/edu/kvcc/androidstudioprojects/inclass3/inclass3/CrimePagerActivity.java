package edu.kvcc.androidstudioprojects.inclass3.inclass3;

/**
 * Created by dpantaleo on 11/9/2015.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;
import java.util.UUID;


public class CrimePagerActivity extends FragmentActivity {
   // what is needed to replace CrimeActivity with this class... CrimePagerActivity - this next line & Intent code:
    private static final String EXTRA_CRIME_ID =
           "edu.kvcc.androidstudioprojects.inclass3.inclass3.crime_id";

    private ViewPager mViewPager;
    private List<Crime> mCrimes;

    public static Intent newIntent(Context packageContext, UUID crimeId){        // crimeId
        Intent intent = new Intent(packageContext, CrimePagerActivity.class);   // looks same as in CrimeActivity.java - MAKES NEW INTENT
        intent.putExtra(EXTRA_CRIME_ID, crimeId);                       // now can put the extra...        PUT PASSED IN crimeId     AS AN EXTRA USING THE KEY DECLARED ABOVE
        return intent;                                                  // and returnS the intent
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        UUID crimeId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_CRIME_ID);                  // ch 11 after new Intent

        mViewPager = (ViewPager) findViewById(R.id.activity_crime_pager_view_pager);

        mCrimes = CrimeLab.get(this).getCrimes();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getmID());
                           }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });
                                    //   THIS CODE ALLOWS THE CRIME SELECTED TO APPEAR...BEFORE THIS..ALWAYS SHOWED 1ST ONE ONLY
        for (int i = 0; i < mCrimes.size(); i++) {
            if (mCrimes.get(i).getmID().equals(crimeId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}