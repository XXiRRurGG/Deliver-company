package com.codebind;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Trip {
    public List<Stops> stops = new ArrayList<Stops>();
    public List<Admin> responsileAdmins = new ArrayList<>();
    private static AtomicInteger count = new AtomicInteger(0);
    private int id;
    private String from;
    private String to;
    public int GetID()
    {
        return id;
    }
    public Trip()
    {
        id = count.incrementAndGet();
    }
    public Trip(String from,String to)
    {
        this();
        this.from = from;
        this.to = to;
    }
    public Trip(String from, String to,ArrayList<Stops> stops)
    {
        this(from,to);
        this.stops.addAll(stops);
    }
    public String Destination()
    {
        return to;
    }
    public String StartingPoint()
    {
        return from;
    }
    public void ChangeDestination(String to)
    {
        this.to = to;
    }
    public void ChangeTrip(String from, String to,List<Stops> stops)
    {
        this.from = from;
        this.to = to;
        this.stops.addAll(stops);
    }
    public void Info()
    {
        System.out.println("ID:" + id);
        System.out.println("From:"+from);
        System.out.println("To:"+to);
        for(var item : stops)
        {
            System.out.println("Stop:"+item.Stop());
        }
    }
    public String toString() {
        return id + ":" + from + "-" + to;
    }
    public String TripInfo()
    {
        String result = "From:" +from +"\n" + "To:" +to + "\n" + "Stops:\n";
        for(var stop : stops)
        {
            result += stop.Stop() + "\n";
        }
        return result;
    }
    public void SetId(int id)
    {
        this.id = id;
    }
    public String getName()
    {
        return from + " " + to;
    }
}
