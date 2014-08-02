package com.ngh.example.mortartest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


public class Lists
{

    public static <E> ArrayList<E> newArrayList(Iterable<? extends E> elements)
    {
        //noinspection unchecked
        return (elements instanceof Collection)
                ? new ArrayList<E>((Collection<E>) (elements))
                : newArrayList(elements.iterator());
    }

    public static <E> ArrayList<E> newArrayList(Iterator<? extends E> elements)
    {
        ArrayList<E> list = new ArrayList<E>();
        while (elements.hasNext())
        {
            list.add(elements.next());
        }
        return list;
    }
}
