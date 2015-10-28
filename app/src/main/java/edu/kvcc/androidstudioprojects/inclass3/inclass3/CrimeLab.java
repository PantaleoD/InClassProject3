// THIS WILL HOLD A COLLECTION OF CRIMES.
// THIS IS A "SINGLETON" - CAN ONLY INSTANTIATE 1 TIME PER RUN AND ONLY FROM WITHIN B/C CONSTRUCTOR IS PRIVATE


// BIG PICTURE:   HOLDS ALL CRIMES AND WHEN ADDED...USES THE GETTER TO MAKE A NEW ONE...SO YOU CAN ADD TO IT AS YOU GO ALONG.
package edu.kvcc.androidstudioprojects.inclass3.inclass3;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by dpantaleo on 10/27/2015.
 */


public class CrimeLab {
    private  static CrimeLab sCrimeLab;         // s = static / HOLDS THE INSTANCE OF CrimeLab
                                                // rather than returning a new instance of CrimeLab,
                                                // we will return this variable that holds our instance

    // variable of the 'type' list, which is an interface, to hold a list ('type') of crimes
    private List<Crime> mCrimes;             // interface that supports a ordered list of objects of a given type it defines
                                             // methods for add, del, retreive elements. List typically implemts Array Lists
                                            // mCrimes holds an Array List (which is also a List (polymorphism) -
                                            // both valid types for mCrime
                                            //using List makes chg's later easier (if chg to, for ie: linklist)

                            // Context = object (will use in  chpt 14)
    // This is the method that will be used to get an instance of CrimeLab.  It will check to see if the current instance in the
    //      variable is null and, if it is, it will create a new one using the private constructor.
    // If it is NOT null, it will just return the instance that exists
    public static CrimeLab get(Context context) {                          // GETTER
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;                           // will always return what's created or what exists....
    }

    //                  diamond notation = shorthand notation - tells compiler to infer the type of item
    //                      the list will contain based on generic argument passed in the var. declaration.
    //                  for this....compiler will infer that the ArrayLists contains Crimes (because of declaration above)
    //  (eventually: list will contain user-created Crimes that can be saved and reloaded)

                     // + note 'private' constructor
                    // this constructor is private b/x we don't ant people to be able to create a new instance from outside classes
                    // If they want to make a new instance, we watn them to use the get method
    private  CrimeLab(Context context){

        // Instantiates a new ArrayList, which is a child class that Implements the Interface List. Because Array List is a child of List
        //      we can store it in the mCrimes variable which is of the 'type' List, and not ArrayList (ie of Polymorphism)

// currently can only add crimes in this call..will change later....

        // why store in array list?   thing we call an adapter that can acquire all this data (design pattern = adapter pattern)
        //              to make it easier...
        mCrimes = new ArrayList<>();      // makes a new array list

        for (int i = 0; i < 100; i++)       // loads 100 random crimes into the list - generates crimes
        {
            Crime crime = new Crime();
            crime.setmTitle("Crime #" + i);
            crime.setSolved(i % 2 == 0);    // set solved to every other crime created.
            mCrimes.add(crime);
        }
    }

    public  List<Crime> getCrimes() {                     // GETTER
        return mCrimes;
    }
//this method 'gets' a selected crime...if available
     // uuid is unique identifier for each Crime in the list/array
    // this method  is used to get a specific crime based on the UUID that is passed in
        public Crime getCrime(UUID id) {
            // this is a FOREACH loop to go through all of the crimes.
            // at each iteration the current crime will be called 'crime'
        for (Crime crime : mCrimes){
            if (crime.getmID().equals(id)){
                return  crime;
            }
        }
        return null;                        // no match, returns null
    }


}
