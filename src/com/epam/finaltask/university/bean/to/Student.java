package com.epam.finaltask.university.bean.to;

import com.epam.finaltask.university.bean.Profile;
import com.epam.finaltask.university.bean.User;
import com.epam.finaltask.university.bean.type.UserType;

import java.io.Serializable;

/**
 * Created by Zheny Chaichits on 18.02.2016.
 */
public class Student implements Serializable {
    private User user;
    private Profile profile;

    public Student() {
    }

    public Student(User user, Profile profile) {
        user.setRole(UserType.STUDENT);
        this.user = user;
        this.profile = profile;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        user.setRole(UserType.STUDENT);
        this.user = user;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Student student = (Student) o;

        if (user != null ? !user.equals(student.user) : student.user != null) {
            return false;
        }

        return profile != null ? profile.equals(student.profile) : student.profile == null;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (profile != null ? profile.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Student{");
        sb.append("user=").append(user);
        sb.append(", profile=").append(profile);
        sb.append('}');
        return sb.toString();
    }
}
