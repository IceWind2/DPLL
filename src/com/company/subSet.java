package com.company;

import java.util.Vector;

public class subSet {
    public Vector<var> set = new Vector<>();

    public subSet(){};

    public subSet(var l) {
        this.set.add(l);
    }


    public subSet(Vector<var> v) {
        this.set = new Vector<>(v);
    }

    public subSet(subSet a) {
        this.set = new Vector<>(a.set);
    }
}
