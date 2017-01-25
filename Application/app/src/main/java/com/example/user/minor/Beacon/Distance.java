package com.example.user.minor.Beacon;

import java.util.LinkedList;
import java.util.Queue;

class Distance {
    //Default accuracy
    //Changing the value will not give greater accuracy, due to the long withdrawal sensor
    //Because of that, the new values have more weight than the old
    private int depth = 5;
    private Queue<Double> data;

    Distance() {
        data = new LinkedList<>();
    }

    void add(double d){
        if (data.size() >= depth)
            data.remove();
        data.add(d);
    }

    double getAverageDistance(){
        //Adding weights. New values more valuable than old
        double result = 0;
        int size = data.size();
        int totalWeight = size*(size+1)/2;
        int counter = 1;

        for (double d : data) {
            result += counter*d;
            counter++;
        }
        result /= totalWeight;
        return result;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}
