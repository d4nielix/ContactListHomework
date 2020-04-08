package com.example.contactlisthomework;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.contactlisthomework.persons.PersonListContent;

import java.util.Random;

public class Adder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adder);
    }

    public void addClick(View view) {
        EditText personNameEditTxt = findViewById(R.id.personName);
        EditText personLastnameEditTxt = findViewById(R.id.personLastname);
        DatePicker bday = findViewById(R.id.personBirthdate);
        EditText personPhonenumberEditTxt = findViewById(R.id.personPhonenumber);

        String personName = personNameEditTxt.getText().toString();
        String personLastname = personLastnameEditTxt.getText().toString();
        String personPhonenumber = personPhonenumberEditTxt.getText().toString();
        String personBirthdate = bday.getDayOfMonth() + "." + (bday.getMonth() +1) + "." + bday.getYear();

        Random rand = new Random();
        int randpic = rand.nextInt(16) + 1;
        String selectedImage = "drawable " + randpic;


        if(!checkPhonenumber(personPhonenumber)){
            Toast.makeText(this, R.string.incorrectphone, Toast.LENGTH_SHORT).show();
            return;
        }
        if(!checkNameOrLastname(personName)){
            Toast.makeText(this, getString(R.string.incorrectname), Toast.LENGTH_SHORT).show();
            return;
        }
        if(!checkNameOrLastname(personLastname)){
            Toast.makeText(this, getString(R.string.incorrectlastname), Toast.LENGTH_SHORT).show();
            return;
        }

        PersonListContent.addItem(new PersonListContent.Person("Person." + PersonListContent.ITEMS.size() + 1,
                personName,
                personLastname,
                selectedImage,
                personBirthdate,
                personPhonenumber));

        setResult(RESULT_OK);
        finish();
    }

    boolean checkPhonenumber(String input){
        if(input.trim().length() !=9)
            return false;
        try{
            Integer.parseInt(input);
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    boolean checkNameOrLastname(String input){
        if(input.isEmpty()){
            return false;
        }
        else return true;
    }
}