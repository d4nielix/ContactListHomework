package com.example.contactlisthomework;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.contactlisthomework.PersonFragment.OnListFragmentInteractionListener;
import com.example.contactlisthomework.persons.PersonListContent.Person;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Person} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyPersonRecyclerViewAdapter extends RecyclerView.Adapter<MyPersonRecyclerViewAdapter.ViewHolder> {

    private final List<Person> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyPersonRecyclerViewAdapter(List<Person> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_person, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Person person = mValues.get(position);
        holder.mItem =  person;
        holder.mContentView.setText(person.name);
        final String picPath = person.picPath;
        Context context = holder.mView.getContext();

        if(picPath != null && !picPath.isEmpty()){
            if(picPath.contains("drawable")){
                Drawable personDrawable;
                switch(picPath){
                    case "drawable 1":
                        personDrawable = context.getResources().getDrawable(R.drawable.avatar_1);
                        break;
                    case "drawable 2":
                        personDrawable = context.getResources().getDrawable(R.drawable.avatar_2);
                        break;
                    case "drawable 3":
                        personDrawable = context.getResources().getDrawable(R.drawable.avatar_3);
                        break;
                    case "drawable 4":
                        personDrawable = context.getResources().getDrawable(R.drawable.avatar_4);
                        break;
                    case "drawable 5":
                        personDrawable = context.getResources().getDrawable(R.drawable.avatar_5);
                        break;
                    case "drawable 6":
                        personDrawable = context.getResources().getDrawable(R.drawable.avatar_6);
                        break;
                    case "drawable 7":
                        personDrawable = context.getResources().getDrawable(R.drawable.avatar_7);
                        break;
                    case "drawable 8":
                        personDrawable = context.getResources().getDrawable(R.drawable.avatar_8);
                        break;
                    case "drawable 9":
                        personDrawable = context.getResources().getDrawable(R.drawable.avatar_9);
                        break;
                    case "drawable 10":
                        personDrawable = context.getResources().getDrawable(R.drawable.avatar_10);
                        break;
                    case "drawable 11":
                        personDrawable = context.getResources().getDrawable(R.drawable.avatar_11);
                        break;
                    case "drawable 12":
                        personDrawable = context.getResources().getDrawable(R.drawable.avatar_12);
                        break;
                    case "drawable 13":
                        personDrawable = context.getResources().getDrawable(R.drawable.avatar_13);
                        break;
                    case "drawable 14":
                        personDrawable = context.getResources().getDrawable(R.drawable.avatar_14);
                        break;
                    case "drawable 15":
                        personDrawable = context.getResources().getDrawable(R.drawable.avatar_15);
                        break;
                    default:
                        personDrawable = context.getResources().getDrawable(R.drawable.avatar_16);
                }
                holder.mItemImageView.setImageDrawable(personDrawable);
            }
        }else{
            holder.mItemImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.avatar_16));
        }
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != mListener){
                    mListener.onListFragmentClickInteraction(holder.mItem, position);
                }
            }
        });
        holder.mView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v){
                if(null != mListener){
                    mListener.onListFragmentLongClickInteraction(holder.mItem, position);
                }
                return false;
            }
        });
        holder.mDeleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(null != mListener){
                    mListener.onListBinClickInteraction(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public final ImageView mItemImageView;
        public final ImageButton mDeleteButton;
        public Person mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mItemImageView = view.findViewById(R.id.item_image);
            mContentView = (TextView) view.findViewById(R.id.content);
            mDeleteButton = view.findViewById(R.id.deleteBin);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
