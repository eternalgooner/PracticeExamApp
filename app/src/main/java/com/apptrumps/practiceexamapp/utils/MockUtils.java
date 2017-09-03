package com.apptrumps.practiceexamapp.utils;

import com.apptrumps.practiceexamapp.model.Person;

import java.util.ArrayList;

/**
 * Created by David on 03-Sep-17.
 */

public class MockUtils {

    public static ArrayList<Person> getPeopleList(){
        ArrayList<Person> personList = new ArrayList<>(20);

        personList.add(new Person("David", 18, "6.4.", 123456));
        personList.add(new Person("Sean", 19, "6.4.", 856));
        personList.add(new Person("Kev", 20, "6.4.", 43437));
        personList.add(new Person("Ste", 21, "6.4.", 7286386));
        personList.add(new Person("Lin", 22, "6.4.", 7823));
        personList.add(new Person("des", 23, "6.4.", 36872));
        personList.add(new Person("nat", 24, "6.4.", 725));
        personList.add(new Person("john", 25, "6.4.", 728));
        personList.add(new Person("eoin", 26, "6.4.", 2786326));
        personList.add(new Person("arnold", 27, "6.4.", 278));
        personList.add(new Person("Donald", 28, "6.4.", 721693));
        personList.add(new Person("Dave", 29, "6.4.", 39));
        personList.add(new Person("Don", 30, "6.4.", 217));
        personList.add(new Person("vernon", 31, "6.4.", 36));
        personList.add(new Person("arnie", 32, "6.4.", 72189));
        personList.add(new Person("ian", 33, "6.4.", 21978));
        personList.add(new Person("bren", 34, "6.4.", 79));

        return personList;
    }
}
