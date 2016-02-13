package com.epam.finaltask.university.bean;

import java.io.Serializable;

/**
 * Created by Zheny Chaichits on 08.02.2016.
 */
public class Subject implements Serializable {
    private long id;
    private String name;
    private int minPoint;

    public Subject() {}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMinPoint() {
        return minPoint;
    }

    public void setMinPoint(int minPoint) {
        this.minPoint = minPoint;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Subject subject = (Subject) o;

        if (id != subject.id) {
            return false;
        }
        if (minPoint != subject.minPoint) {
            return false;
        }
        return name != null ? name.equals(subject.name) : subject.name == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + minPoint;
        return result;
    }
}
