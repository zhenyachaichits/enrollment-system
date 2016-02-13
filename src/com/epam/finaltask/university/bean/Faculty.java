package com.epam.finaltask.university.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Zheny Chaichits on 08.02.2016.
 */
public class Faculty implements Serializable {
    private long id;
    private String name;
    private int freeQuota;
    private int paidQuota;
    private int freePoint;
    private int paidPoint;
    private long termsId;
    private Set<Long> subjects;

    public Faculty() {
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFreeQuota() {
        return freeQuota;
    }

    public void setFreeQuota(int freeQuota) {
        this.freeQuota = freeQuota;
    }

    public int getPaidQuota() {
        return paidQuota;
    }

    public void setPaidQuota(int paidQuota) {
        this.paidQuota = paidQuota;
    }

    public int getPaidPoint() {
        return paidPoint;
    }

    public void setPaidPoint(int paidPoint) {
        this.paidPoint = paidPoint;
    }

    public long getTermsId() {
        return termsId;
    }

    public void setTermsId(long termsId) {
        this.termsId = termsId;
    }

    public int getFreePoint() {
        return freePoint;
    }

    public void setFreePoint(int freePoint) {
        this.freePoint = freePoint;
    }


    public Set<Long> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Long> subjects) {
        this.subjects = subjects;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Faculty faculty = (Faculty) o;

        if (id != faculty.id) {
            return false;
        }
        if (freeQuota != faculty.freeQuota) {
            return false;
        }
        if (paidQuota != faculty.paidQuota) {
            return false;
        }
        if (freePoint != faculty.freePoint) {
            return false;
        }
        if (paidPoint != faculty.paidPoint) {
            return false;
        }
        if (termsId != faculty.termsId) {
            return false;
        }
        if (name != null ? !name.equals(faculty.name) : faculty.name != null) {
            return false;
        }
        return subjects != null ? subjects.equals(faculty.subjects) : faculty.subjects == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + freeQuota;
        result = 31 * result + paidQuota;
        result = 31 * result + freePoint;
        result = 31 * result + paidPoint;
        result = 31 * result + (int) (termsId ^ (termsId >>> 32));
        result = 31 * result + (subjects != null ? subjects.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Faculty{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", freeQuota=").append(freeQuota);
        sb.append(", paidQuota=").append(paidQuota);
        sb.append(", freePoint=").append(freePoint);
        sb.append(", paidPoint=").append(paidPoint);
        sb.append(", termsId=").append(termsId);
        sb.append(", subjects=").append(subjects);
        sb.append('}');
        return sb.toString();
    }
}
