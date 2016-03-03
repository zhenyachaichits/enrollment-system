package com.epam.finaltask.university.bean;

import java.io.Serializable;
import java.util.Calendar;

/**
 * The type Terms.
 */
public class Terms implements Serializable {
    private long id;
    private Calendar startDate;
    private Calendar endDate;

    public Terms() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Terms terms = (Terms) o;

        if (id != terms.id) {
            return false;
        }
        if (startDate != null ? !startDate.equals(terms.startDate) : terms.startDate != null) {
            return false;
        }
        return endDate != null ? endDate.equals(terms.endDate) : terms.endDate == null;

    }


    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Terms{");
        sb.append("id=").append(id);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append('}');
        return sb.toString();
    }
}
