package edu.kvcc.androidstudioprojects.inclass3.inclass3;  // the MODEL view

import java.util.Date;
import java.util.UUID;

/**
 * Created by dpantaleo on 10/19/2015.  Rt Click on java class - new java class
 */
public class Crime {

    //   this will be unique even if names are same;
    private UUID mID;         // to distinguish 1 crime from another   (type is UUID (unique identifier) (string value))
                                // if from a db - replaces with db id #
    private  String mTitle;    //

    private Date mDate;             // CHPT 8 added
    private boolean mSolved;

    public Crime(){      // default constructor

           mID = UUID.randomUUID();    // makes anew unique id for this particular crime
           mDate = new Date();
       }

    public Crime(UUID uuid, String title, Date date, Boolean isSolved ){    // done to read file in Crime Lab
        mID = uuid;
        mTitle = title;
        mDate = date;
        mSolved = isSolved;
    }

    // getters and setters: - rt click - generate - getters & setters


    public UUID getmID() {    // kept getter deleted setter+
        return mID;
    }

        public String getmTitle() {     // need getter and setter for the title
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }
}
