package org.example;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        StringListImpl stringList = new StringListImpl(3);
        System.out.println(stringList.toString());

        stringList.add("test");
        System.out.println(stringList.toString());

        stringList.set(2, "test2");
        System.out.println(stringList.toString());

        stringList.add("test3");
        System.out.println(stringList.toString());

        stringList.remove("test3");
        System.out.println(stringList.toString());

        stringList.remove(2);
        System.out.println(stringList.toString());

        if (stringList.contains("test")) {
            System.out.println("есть в списке");
        }

        stringList.set(2, "test2");
        System.out.println(stringList.get(2));


        stringList.size();

        stringList.isEmpty();

        stringList.clear();
        stringList.add(2,"test");
        System.out.println(stringList.toString());
        stringList.toArray();
        stringList.add("sss");
        System.out.println(stringList);

        System.out.println(stringList.get(0));

        StringListImpl stringList1 = new StringListImpl(3);
        if (stringList.equals(stringList1)) {
            System.out.println("есть");
        }

        stringList.toArray();
        System.out.println(stringList);

        stringList.addPlus("rrr");
        System.out.println(stringList);
        stringList.addPlus("lll");
        System.out.println(stringList);

       // stringList.remove("lll");
        //System.out.println(stringList);
        stringList.removeMinus("lll");
        System.out.println(stringList);



    }
}