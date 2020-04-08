package com.example.contactlisthomework.persons;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.core.app.ActivityCompat;

import com.example.contactlisthomework.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PersonListContent {

    public static final List<Person> ITEMS = new ArrayList<Person>();
    public static final Map<String, Person> ITEM_MAP = new HashMap<String, Person>();

     static {
        for(int i=1; i<=5; i++){
            addItem(new Person(String.valueOf(i),
                    "Name" + i,
                    "Lastname" + i,
                    "drawable " + i,
                    "01.01.1990",
                    "607123456"));
        }
    }

    public static void addItem(Person item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    public static class Person implements Parcelable {
        public final String id;
        public final String name;
        public final String lastname;
        public final String picPath;
        public final String birthdate;
        public final String phonenumber;

        public Person(String id, String name, String lastname, String picPath, String birthdate, String phonenumber) {
            this.id = id;
            this.name = name;
            this.lastname = lastname;
            this.picPath = picPath;
            this.birthdate = birthdate;
            this.phonenumber = phonenumber;
        }

        protected Person(Parcel in) {
            id = in.readString();
            name = in.readString();
            lastname = in.readString();
            picPath = in.readString();
            birthdate = in.readString();
            phonenumber = in.readString();
        }

        public static final Creator<Person> CREATOR = new Creator<Person>() {
            @Override
            public Person createFromParcel(Parcel in) {
                return new Person(in);
            }

            @Override
            public Person[] newArray(int size) {
                return new Person[size];
            }
        };

        @Override
        public String toString() {
            return name;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(name);
            dest.writeString(lastname);
            dest.writeString(picPath);
            dest.writeString(birthdate);
            dest.writeString(phonenumber);
        }
    }

    public static void removeItem(int position){
         String itemId = ITEMS.get(position).id;
         ITEMS.remove(position);
         ITEM_MAP.remove(itemId);
    }

    public static void clearList(){
         ITEMS.clear();
         ITEM_MAP.clear();
    }

    public static void callItem(MainActivity mainActivity, int position){
         Intent callIntent = new Intent(Intent.ACTION_CALL);
         callIntent.setData(Uri.parse("tel:"+
                 ITEMS.get(position).phonenumber.substring(0,3) + " " +
                 ITEMS.get(position).phonenumber.substring(3,6) + " " +
                 ITEMS.get(position).phonenumber.substring(6)));

         if(ActivityCompat.checkSelfPermission(mainActivity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
             ActivityCompat.requestPermissions(mainActivity, new String[]{Manifest.permission.CALL_PHONE}, 1);
             return;
         }
         mainActivity.startActivity(callIntent);
    }
}
