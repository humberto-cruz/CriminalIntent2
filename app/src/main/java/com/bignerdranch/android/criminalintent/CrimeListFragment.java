package com.bignerdranch.android.criminalintent;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
 * Created by SAN on 11/01/2016.
 */
public class CrimeListFragment extends Fragment {

    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;

    /*@Override
    public void onResume(){

        super.onResume();
        Toast.makeText(getContext(), "onResume",Toast.LENGTH_SHORT).show();
        updateUI();
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        Toast.makeText(getContext(), "onCreateView list",Toast.LENGTH_SHORT).show();
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

        mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    public void updateUI(){
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();

        if(mAdapter == null){
            Toast.makeText(getContext(), "setting adapter",Toast.LENGTH_SHORT).show();
            mAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdapter);
        }
        else{
            Toast.makeText(getContext(), "setting adapter",Toast.LENGTH_SHORT).show();
            mAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }

    }

    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnFocusChangeListener{

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mSolvedCheckBox;
        private Crime mCrime;

        public void bindCrime(Crime crime){
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
            mSolvedCheckBox.setChecked(mCrime.isSolved());
        }

        public CrimeHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnFocusChangeListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_crime_title_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_crime_date_text_view);
            mSolvedCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_crime_solved_check_box);
        }


        @Override
        public void onClick(View v) {
            //Toast.makeText(getActivity(), mCrime.getTitle() + "clicked", Toast.LENGTH_SHORT).show();
            //Intent intent = new Intent(getActivity(), CrimeActivity.class);
            //Intent intent = CrimeActivity.newIntent(getActivity(), mCrime.getId());

            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.fragment_container, CrimeFragment.newInstance(mCrime.getId()));
            transaction.addToBackStack(null);
// Commit the transaction
            transaction.commit();



            //Intent intent = CrimePagerActivity.newIntent(getActivity(), mCrime.getId());

            //startActivity(intent);
        }

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                v.setBackgroundColor(Color.rgb(255,165,0));
            } else {
                v.setBackgroundColor(Color.rgb(255,255,255));
            }
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{

        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes){
            mCrimes = crimes;
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_crime, parent, false);
            return new CrimeHolder(view);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Crime crime = mCrimes.get(position);
            //holder.mTitleTextView.setText(crime.getTitle());
            holder.bindCrime(crime);

        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }
}