package edu.kvcc.androidstudioprojects.inclass3.inclass3;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by dpantaleo on 10/27/2015.
 */
public class CrimeListFragment extends Fragment {    // this is a fragment to be used with CrimeListActivity..eventually


    private RecyclerView mCrimeRecyclerView;     // creates class level variable to hold the Recycler view

    //              varilabe to hold instance of the adapter.
    private CrimeAdapter mAdapter;          // declared for adapter, at end, and used for binding in updateUI()

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {       // pg 181
        // get view from laoyut that will be displayed.
// use the inflater to inflate the layout to Java code

        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);      // NOTE WRONG NAME..REFACTORED BUT NEED BAD NAME HERE!!  ???

        //                                      onCreateView SO THE CRIMELISTFRAGMENT USES THIS LAYOUT TO FIND RECYCLER VIEW IN
        //                                          IN THE LAYOUT FILE...  (GOT AN R REFERENCE ERROR GOING!)

                                        // get a reference to the recycler view int he layout file.Rmember taht we have to call findViewbyId
                                        // on the view that we created above.
                                        //   it is not an automatic method..like it was for activity:
        mCrimeRecyclerView = (RecyclerView) view             // to hook view to the fragment...
                .findViewById(R.id.crime_recycler_view);

                                    //         A NEW LAYOUT MANAGER FOR RecyclerView - else breaks...
                                    // the recycler view requires that it is given a layout manager. the recycler view is a fairly new control,
                                    // and is not capable of displaying the list items on the screen.
                                    //   a Linear Layout Manager is required to do that work. Therefore... we create a new Linear Layout manager,
                                    // and pass it the context
                                    //   which it needs to operate it.  The context is passed by  using  the getActivity method.
                                    // Which gets the activity that is hosting this fragment.

        // GET ACTIVITY()METHOD IN A FRAGMENT ... CONNECTED IWTH THE FRAMENT/ACTIVITY THAT IS HOSTING THE FRAGMENT


        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));  // must set layout manager..because it doesn't know how to
                                    // display and the provided layout manager is provided for you by android
                                    // get activity () gets the activity thta's hosting the fragment (CrimeListActivity in our case)

        updateUI();           // called (pg 184) to do the work of updateUI - displays the crimes
        //                      calls method to do the work fo getting the data from CrimaeLab,
        //                      setting it up w/ the adapter and then adding the adapter to the recycler view.

        return view;   // returns the created view
    }

    //                  Chpt 10 pg 201  to save values upon resume  triggers updateUI to reload the list added onResume method here + code in updateUI
    @Override
    public  void onResume() {
        super.onResume();
        updateUI();
    }

    // done at end but written before all the private classes

    //                  pg 184:  Once have adapter (below!)  will implement this updateUI method to set up the CrimeListFragment's user interface:.. it will
    //                          a) create a CrimeAdapter and b) set it on the Recycler View:

    // 2 steps:    Call updateUI in onCreate and then this....

    // this updateUI.... when created...(each time needed)  the singleton will always return the EXACT SAME DATA from CrimeLab..
    //              that is why the singleton is used.

     private  void  updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());      // gets the data for.   call static method on singleton(crimelab) - instantiates it!
                  //  (dont use context in crimelab - yet. this gets the collection of data from
                                                                // crimelab singleton. the get method constructors requires that a context
                                                            // is passed in  so we send it the holding activity of this fragment

         List<Crime> crimes = crimeLab.getCrimes();     // the list of crimes... using 'getter' from crime labs class

         if (mAdapter == null) {

             //                                 creates a new crimeAdapter and send it over the list of crimes so that
             //                                             it can work w/ the recycler to display them
             mAdapter = new CrimeAdapter(crimes);

             //                     takes the Adapter we just created, and set it as the adapter that the
             //                     recycler view is goint to use.
             mCrimeRecyclerView.setAdapter(mAdapter);
         }
         else
         {
             mAdapter.notifyDataSetChanged();
         }

     }



                    /*          RecyclerView RECYCLES textViews SO CAN SHOW X # OF VIEWS AT A TIME....
                    //      Layout Manager does the job of positioning the items on the screen. & also defines the scrolling behavior.
                    //          there are multiple layout managers to choose from... we will use LinearLayoutManager - lists items vertically (later use GridLayoutManager)
                    //
                    // *********************************8
                    //                  ViewHolder maintains  reference to a Single View - the title TextView
                    //                  this expects the itemView to be a TextView, else will crash
                            /*

                            //                      pg 182...initially to hold the title textview... viewHolder holds a single reference to a single view. will do more later!!
                            //                          this is an 'inner' class within CrimeListFragment  */
       // these are 'implemented'...together... View Holder and Adapter:
       // A) View Holder



    // the crime holder will implement the View.OnClickListener... you can only implement 1 thing at a time??
    //

        private class CrimeHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{  // p191
       //     public TextView mTitleTextView;    // Public because of override methods inthe adpater. needs access to this view
                        //      and can't 'get' it without the variable being declared as public. The only other option much be to
                        //          make it privte, and then declare a public 'get' method to return it.

                        //               the built in view (single_line_item....) must need public variables... (we think?)

            private  TextView mTitleTextView;     // added after list_item_crime.xml created  p 188  PRIVATE & specific
            private  TextView mDateTextView;      // now to/for our list_item_crime.xml
            private CheckBox mSolvedCheckBox;      // Class level var. to hold the view for this holder
                                                    //          was public but then private later....as above



            private Crime mCrime;                   // mCrime is instance variable for Crime



//          Constructor that takes in a View. The parent constructor is called,
                        // and then the passed in View is Assigned to the class level version
            public CrimeHolder(View itemView) {
                super(itemView);              // itemView is the parent class of CrimeHolder... so send itemView to parent class
                itemView.setOnClickListener(this);       // for this view - NOT HERE..NO TOAST DISPLAYS         // pg 191
     //           mTitleTextView = (TextView) itemView;       // holder of the title textview/ assigned to class level var


                // assigns to class lavel vars. Uses the findviewbyid method to get access to the various controls want to work w/
                mTitleTextView = (TextView)                                          // ADDED AFTER list_item_crime.xml CREATED P188
                        itemView.findViewById(R.id.list_item_crime_title_text_view);  // now can use id's based on our layout
                mDateTextView = (TextView)
                        itemView.findViewById(R.id.list_item_crime_date_text_view);
                mSolvedCheckBox = (CheckBox)
                        itemView.findViewById(R.id.list_item_crime_solved_check_box);
                // COULD DISABLE CHECKBOXES HERE TOO W/ mSolvedCheckBox.setEnabled(false); - did it in list_item_crime.xml
            }

// Method to take in an instance of a crime, assign it to the class level version. Then use the classs level version to take
   //                     properties form the crime and assign them to the various view controls

            public void bindCrime(Crime crime) {
                 mCrime = crime;                                 // variable bc has to outlive this method...
                //                          made line above because w/intent and view holders...it's available!!! mCrime declared at top! class level!
                 mTitleTextView.setText(mCrime.getmTitle());     // set all the values for display
                 mDateTextView.setText(mCrime.getDate().toString());
                 mSolvedCheckBox.setChecked(mCrime.isSolved());
           }

                        //      called method to take in a crime instance and bind it to the controls
            //  again, could use auto generator:
            // getActivity will will context of where this is running in.   then toast w/ values
            //   this can occur because we EXTENDE the CrimeHolder class with:   (above)
            // CrimeHolder extends  RecyclerView.ViewHolder implements View.OnClickListener

            // as click a ViewHolder...it will toast the message shown below.

            //  the method bmust be implemented because we have this class implementing the onClickListener interface.
            //This mehtod will do the work of toasting the titel of the crime that was clicked upon.
        @Override
          public void onClick(View v){
  //          Toast.makeText(getActivity(), mCrime.getmTitle() + " clicked!", Toast.LENGTH_SHORT)
  //                  .show();
      //      Intent intent = new Intent(getActivity(), CrimeActivity.class);       // dch 10 explicit intent replace
            //                  once create new intent method in CrimeAcdtivity.java with:

            // HOW SEND DATA FROM INTENT?  EXTRAS...WHERE DOES THAT OCCUR -
            // asks crime activity intent to make the intent...returns the intent and then starts the activity
            //      ASKS CRIME ACTIVITY FOR AN INTENT THAT WILL GET THE CrimeActivity STARTED.  THE METHOD REQUIES US TO PASS THE
            //      CONTEXT, WHICH WE CAN GET FROM CALLING THE getActivity(), and the id of the crime we want
            //      to start the activity with.   once we have the intent, we call the StartActivity method to start it.

            // mCrime avail b/c declared at top and set in Bind method above

   // replc'd in ch 11 w/nx line         Intent intent = CrimeActivity.newIntent(getActivity(), mCrime.getmID());   // sending activity and crime # from the crime
            Intent intent = CrimePagerActivity.newIntent(getActivity(), mCrime.getmID());
            startActivity(intent);                   //        START A CRIME ACTIVITY FROM THIS - NEXT NEED TO GET THE VALUES FOR OUTPUT
        }

        }
//                                      pg 182... recycler needs adapter...adapter
//     B) ADAPTER needed because it holds the instructions for the Crime to be shown...
    //              recycler view communicates with this adapter when a ViewHolder needs to be created or connected with a Crime object.
    //              the recycler view does not know anything about the Crime object although the Adapter does

    // inherits from the RecyclerView.Adapter

        private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {    // needs 3 overrided classes until error gone

        //                  Class level varialbe to hold the 'data' of our app...this will be the list of crimes
            private List<Crime> mCrimes;                        //

            // Constructor that takes in a list of rimes, and then assigns them to the class level variable:
            public CrimeAdapter(List<Crime> crimes) {       //
                mCrimes = crimes;
            }
//                              These are the 3 methods implemented in CrimeAdapter Class

    // A):               p183 called by Recycler View when it needs a new View to display an item. So this creates a View and wraps it in ViewHolder.
    //                          but holds nothing. We will inflate the View from the Android Standard Library called simple_list_item_1
    //                          This layout contains a single TextView.  Later in chpt will for mult items
            @Override                                                                   // can generate!!!!  all together since next to 1 another
            public CrimeHolder onCreateViewHolder(ViewGroup parent, int ViewType) {   // this occurs when create viewholder first time
                LayoutInflater layoutInflater = LayoutInflater.from(getActivity());    // get inflater first...use from method and will use/get the inflater the activity has

                View view = layoutInflater                                              // declares a view and use a 'built in' one (simple_list_item_1 - initially!
                            .inflate(R.layout.list_item_crime, parent, false);              // false = don't want to use the 'root'

                return new CrimeHolder(view);                             // Return a new CrimeHolder withth eview passed in as a parameter;
                                        // each time called ...check out thi sclass... which accepts a view and then assigns to variable
            }
    //                  .inflate(android.R.layout.simple_list_item_1, parent, false);       // a built in android layout
    //  B):                pg 183  binds ViewHolders View to your model object.  This recieves the ViewHolder and a position in the dataset.  The position is used to
    //                              find the correct model data.  then View is updated to  reflect the model data. (the binding!)

            //      happens when a view holder wants to show data??

            @Override                               // this happens with each scroll
            public void onBindViewHolder(CrimeHolder holder, int position) {          // receives the view holder and position (index)
                Crime crime = mCrimes.get(position);                     // assigns the particular crime to crime variable (local version of crime)
                            //                                                  using the position (index)

             // Given the hold that is passed into this method, we need to set the text on the view. In order to do that, the variable that represents
                //      the view must be declared in the holder class as pubic.   If it is not... we would not be able to call .setText on it.
                // Another option might be to make the variable private, and then provide a public 'get' method that would return it's value.
                // holder.mTitleTextView.setText(crime.getmTitle());        // displays the crime;  Binds the crime to the view)
                /// on the CrimeHNolder...havea  public variable(could make  proparty w/public getter and then
                //   use the getter

         // can remove doc & code in group above
                //  send teh crime over to the bindCrime methods that we wrote on the crimeHolder class
                //    The method does the work of setting the properties of the crime to the layout control in the custom
                //   layout we made.
                holder.bindCrime(crime);
            }

            @Override                               // returns # so know how many times to do this - to property do work ahead of time?
            public int getItemCount() {

                return mCrimes.size();
            }
        }
}

