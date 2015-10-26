package edu.kvcc.androidstudioprojects.inclass3.inclass3;  // the MODEL view

import java.util.UUID;

/**
 * Created by dpantaleo on 10/19/2015.  Rt Click on java class - new java class
 */
public class Crime {

    //   this will be unique even if names are same;
    private UUID mID;         // to distinguish 1 crime from another   (type is UUID (unique identifier) (string value))
                                // if from a db - replaces with db id #
    private  String mTitle;    //

    public Crime(){      // default constructor

           mID = UUID.randomUUID();    // makes anew unique id for this particular crime
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
}
