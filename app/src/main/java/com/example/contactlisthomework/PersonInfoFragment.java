package com.example.contactlisthomework;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.contactlisthomework.persons.PersonListContent;


/**
 * A simple {@link Fragment} subclass.
 */
public class PersonInfoFragment extends Fragment {

    public PersonInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_person_info, container, false);
    }

    public void displayPerson(PersonListContent.Person person){
        FragmentActivity activity = getActivity();

        TextView personInfoName = activity.findViewById(R.id.personInfoName);
        TextView personInfoLastname = activity.findViewById(R.id.personInfoLastname);
        ImageView personInfoImage = activity.findViewById(R.id.personInfoImage);
        TextView personInfoBirthdate = activity.findViewById(R.id.personInfoBirthdate);
        TextView personInfoPhonenumber = activity.findViewById(R.id.personInfoPhonenumber);

        personInfoName.setText(person.name);
        personInfoLastname.setText(person.lastname);
        personInfoBirthdate.setText(person.birthdate);
        personInfoPhonenumber.setText(person.phonenumber);

        if(person.picPath != null && !person.picPath.isEmpty()){
            if(person.picPath.contains("drawable")){
                Drawable personDrawable;
                switch(person.picPath){
                    case "drawable 1":
                        personDrawable = activity.getResources().getDrawable(R.drawable.avatar_1);
                        break;
                    case "drawable 2":
                        personDrawable = activity.getResources().getDrawable(R.drawable.avatar_2);
                        break;
                    case "drawable 3":
                        personDrawable = activity.getResources().getDrawable(R.drawable.avatar_3);
                        break;
                    case "drawable 4":
                        personDrawable = activity.getResources().getDrawable(R.drawable.avatar_4);
                        break;
                    case "drawable 5":
                        personDrawable = activity.getResources().getDrawable(R.drawable.avatar_5);
                        break;
                    case "drawable 6":
                        personDrawable = activity.getResources().getDrawable(R.drawable.avatar_6);
                        break;
                    case "drawable 7":
                        personDrawable = activity.getResources().getDrawable(R.drawable.avatar_7);
                        break;
                    case "drawable 8":
                        personDrawable = activity.getResources().getDrawable(R.drawable.avatar_8);
                        break;
                    case "drawable 9":
                        personDrawable = activity.getResources().getDrawable(R.drawable.avatar_9);
                        break;
                    case "drawable 10":
                        personDrawable = activity.getResources().getDrawable(R.drawable.avatar_10);
                        break;
                    case "drawable 11":
                        personDrawable = activity.getResources().getDrawable(R.drawable.avatar_11);
                        break;
                    case "drawable 12":
                        personDrawable = activity.getResources().getDrawable(R.drawable.avatar_12);
                        break;
                    case "drawable 13":
                        personDrawable = activity.getResources().getDrawable(R.drawable.avatar_13);
                        break;
                    case "drawable 14":
                        personDrawable = activity.getResources().getDrawable(R.drawable.avatar_14);
                        break;
                    case "drawable 15":
                        personDrawable = activity.getResources().getDrawable(R.drawable.avatar_15);
                        break;
                    default:
                        personDrawable = activity.getResources().getDrawable(R.drawable.avatar_16);
                }
                personInfoImage.setImageDrawable(personDrawable);
            }else{
                personInfoImage.setImageDrawable(activity.getResources().getDrawable(R.drawable.avatar_1));
            }
        }else{
            personInfoImage.setImageDrawable(activity.getResources().getDrawable(R.drawable.avatar_16));
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        Intent intent = getActivity().getIntent();
        if(intent != null){
            PersonListContent.Person receivedPerson = intent.getParcelableExtra(MainActivity.personExtra);
            if(receivedPerson != null)
                displayPerson(receivedPerson);
        }
    }
}
