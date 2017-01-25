package com.example.user.minor.Beacon;


public class AFMBeacon {
    private String name;
    private String address;
    private int major;
    private int minor;
    private double receivedDistance;

    private String information;

    Distance distance = new Distance();


    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public double getDistance() {
        return distance.getAverageDistance();
    }

    public void addDistance(double distance) {
        this.distance.add(distance);
    }

    public double getReceivedDistance() {
        return receivedDistance;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }



    public AFMBeacon(String name1, String address1, int major1, int minor1, double distance1) {
        name = name1;
        address = address1;
        major = major1;
        minor = minor1;
        receivedDistance = distance1;
        addDistance(receivedDistance);
    }
    public String toString() {
     return "Name: "+name+
             ", Address: "+address+
             ", Major: "+major+
             ", Minor: "+minor+
             ", Distance: " + getDistance();
    }
}