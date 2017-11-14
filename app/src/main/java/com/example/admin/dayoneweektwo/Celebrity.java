package com.example.admin.dayoneweektwo;

/**
 * Created by  Admin on 11/13/2017.
 */

public class Celebrity {
    String Name;
    String Gender;


    @Override
    public String toString() {
        return "Celebrity{" +
                "Name='" + Name + '\'' +
                ", Gender='" + Gender + '\'' +

                '}';
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }



    public Celebrity(String name, String gender) {
        Name = name;
        Gender = gender;

    }
}
