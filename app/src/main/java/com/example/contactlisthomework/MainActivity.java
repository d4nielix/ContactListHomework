package com.example.contactlisthomework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import com.example.contactlisthomework.persons.PersonListContent;
import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity
        implements PersonFragment.OnListFragmentInteractionListener,
        DeleteDialog.OnDeleteDialogInteractionListener,
        CallDialog.OnCallDialogInteractionListener{

    public static final String personExtra = "personExtra";
    private int currentItemPosition = -1;

    private PersonListContent.Person currentPerson;
    private final String CURRENT_PERSON_KEY = "CurrentPerson";

    private final String PERSONS_SHARED_PREFS = "PersonsSharedPrefs";
    private final String NUM_PERSONS = "NumOfPersons";
    private final String NAME = "name_";
    private final String LASTNAME = "lastname_";
    private final String PIC = "pic_";
    private final String BIRTHDATE = "birthdate_";
    private final String PHONE = "phone_";
    private final String ID = "id_";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            PersonInfoFragment personInfoFragment = (PersonInfoFragment)getSupportFragmentManager().findFragmentById(R.id.displayPersonInfoFragment);
            personInfoFragment.getView().setVisibility(View.GONE);
        }

        if (savedInstanceState != null) {
            currentPerson = savedInstanceState.getParcelable(CURRENT_PERSON_KEY);
        }

        SharedPreferences persons = getSharedPreferences(PERSONS_SHARED_PREFS, MODE_PRIVATE);
        restorePersonsFromSharedPreferences();
    }

    public void addClick(View view) {
        Intent intent = new Intent(this, Adder.class);
        startActivityForResult(intent, 1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            ((PersonFragment) getSupportFragmentManager().findFragmentById(R.id.personFragment)).notifyDataChange();
            savePersonsToSharedPreferences();
        }
    }

    private void savePersonsToSharedPreferences() {
        SharedPreferences persons = getSharedPreferences(PERSONS_SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = persons.edit();
        editor.clear();

        editor.putInt(NUM_PERSONS, PersonListContent.ITEMS.size());
        for (int i = 0; i < PersonListContent.ITEMS.size(); i++) {
            PersonListContent.Person person = PersonListContent.ITEMS.get(i);
            editor.putString(NAME + i, person.name);
            editor.putString(LASTNAME + i, person.lastname);
            editor.putString(PIC + i, person.picPath);
            editor.putString(BIRTHDATE + i, person.birthdate);
            editor.putString(PHONE + i, person.phonenumber);
            editor.putString(ID + i, person.id);
        }
        editor.apply();
    }

    private void restorePersonsFromSharedPreferences() {
        SharedPreferences persons = getSharedPreferences(PERSONS_SHARED_PREFS, MODE_PRIVATE);
        int numOfPersons = persons.getInt(NUM_PERSONS, 0);
        if (numOfPersons != 0) {
            PersonListContent.clearList();

            for (int i = 0; i < numOfPersons; i++) {
                String name = persons.getString(NAME + i, "0");
                String lastname = persons.getString(LASTNAME + i, "0");
                String picPath = persons.getString(PIC + i, "0");
                String birthdate = persons.getString(BIRTHDATE + i, "0");
                String phonenumber = persons.getString(PHONE + i, "0");
                String id = persons.getString(ID + i, "0");
                PersonListContent.addItem(new PersonListContent.Person(id, name, lastname, picPath, birthdate, phonenumber));
            }
        }
    }

    @Override
    public void onListFragmentClickInteraction(PersonListContent.Person person, int position) {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            PersonInfoFragment personInfoFragment = (PersonInfoFragment)getSupportFragmentManager().findFragmentById(R.id.displayPersonInfoFragment);
            personInfoFragment.getView().setVisibility(View.VISIBLE);
            displayPersonInFragment(person);
        } else {
            startSecondActivity(person, position);
        }
    }

    @Override
    public void onListFragmentLongClickInteraction(PersonListContent.Person person, int position) {
        showCallDialog(" " + person.name + " " + person.lastname + "?");
        currentItemPosition = position;
    }

    private void showCallDialog(String calling_to){
        CallDialog.newInstance(calling_to).show(getSupportFragmentManager(),getString(R.string.call_dialog_tag));
    }

    @Override
    public void onListBinClickInteraction(int position) {
        showDeleteDialog();
        currentItemPosition = position;
    }

    private void startSecondActivity(PersonListContent.Person person, int position){
        Intent intent = new Intent(this,PersonInfoActivity.class);
        intent.putExtra(personExtra, person);
        startActivity(intent);
    }

    private void displayPersonInFragment(PersonListContent.Person person){
        PersonInfoFragment personInfoFragment = ((PersonInfoFragment) getSupportFragmentManager().findFragmentById(R.id.displayFragment));
        if(personInfoFragment != null){
            personInfoFragment.displayPerson(person);
        }
    }

    private void showDeleteDialog(){
        DeleteDialog.newInstance().show(getSupportFragmentManager(),getString(R.string.delete_dialog_tag));
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        if(currentItemPosition != -1 && currentItemPosition < PersonListContent.ITEMS.size()){
            PersonListContent.removeItem(currentItemPosition);
            ((PersonFragment) getSupportFragmentManager().findFragmentById(R.id.personFragment)).notifyDataChange();
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        View v = findViewById(R.id.addButton);
        if(v != null){
            Snackbar.make(v,getString(R.string.delete_cancel_msg),Snackbar.LENGTH_LONG).setAction(getString(R.string.retry_msg),new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    showDeleteDialog();
                }
            }).show();
        }
    }

    @Override
    protected void onDestroy(){
        SharedPreferences persons = getSharedPreferences(PERSONS_SHARED_PREFS, MODE_PRIVATE);
        savePersonsToSharedPreferences();
        super.onDestroy();
    }

    @Override
    public void onCallDialogPositiveClick(DialogFragment dialog) {
        if(currentItemPosition != -1 && currentItemPosition < PersonListContent.ITEMS.size()){
            PersonListContent.callItem(this, currentItemPosition);
        }
    }

    @Override
    public void onCallDialogNegativeClick(DialogFragment dialog) {

    }
}