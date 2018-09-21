package org.didxga;

import org.didxga.equalish.ListEqualish;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        List<Object> l11 = new ArrayList<>();
        l11.add(8);
        l11.add(7);

        List<Object> l1 = new ArrayList<>();
        l1.add(1);
        l1.add(3);
        l1.add(2);
        l1.add(l11);
        l1.add(7);



        List<Object> l21 = new ArrayList<>();
        l21.add(7);
        l21.add(8);

        List<Object> l2 = new ArrayList<>();
        l2.add(1);
        l2.add(l21);
        l2.add(3);
        l2.add(2);
        l2.add(7);

        boolean isqualish = ListEqualish.equalish(l1, l2, false);
        System.out.println(isqualish);
    }
}
