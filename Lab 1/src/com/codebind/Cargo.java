package com.codebind;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Cargo {
    private static AtomicInteger count = new AtomicInteger(0);
    public int id;
    public List<String> items = new ArrayList<String>();
    public void Info()
    {
        for(var item : items)
        {
            System.out.println("Delivery:"+item);
        }
    }
    public Cargo()
    {
        id = count.incrementAndGet();
    }
    public Cargo(List<String> items)
    {
        this();
        this.items.addAll(items);
    }
    public static int GetID()
    {
        return  count.get()+1;
    }
    public int GetCargosID()
    {
        return  id;
    }
    public String GetListOfStrings()
    {
        return String.join(",",items);
    }
    public String CargoInfo()
    {
        String result = "Items:\n";
        for(var item : items)
        {
            result += item + " ";
        }
        return result;
    }
}
