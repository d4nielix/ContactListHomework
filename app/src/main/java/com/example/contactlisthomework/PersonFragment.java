package com.example.contactlisthomework;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.contactlisthomework.persons.PersonListContent;


public class PersonFragment extends Fragment {

    private MyPersonRecyclerViewAdapter mRecyclerViewAdapter;
    private static final String ARG_COLUMN_COUNT = "column-count";
    private OnListFragmentInteractionListener mListener;

    public PersonFragment() {
    }

    public static PersonFragment newInstance(int columnCount) {
        PersonFragment fragment = new PersonFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person_list, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            mRecyclerViewAdapter = new MyPersonRecyclerViewAdapter(PersonListContent.ITEMS, mListener);
            recyclerView.setAdapter(mRecyclerViewAdapter);
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentClickInteraction(PersonListContent.Person person, int position);
        void onListFragmentLongClickInteraction(PersonListContent.Person person, int position);
        void onListBinClickInteraction(int position);
    }

    public void notifyDataChange(){
        mRecyclerViewAdapter.notifyDataSetChanged();
    }
}
