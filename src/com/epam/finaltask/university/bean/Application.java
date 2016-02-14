package com.epam.finaltask.university.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Zheny Chaichits on 08.02.2016.
 */
public class Application implements Serializable {
    private long id;
    private boolean outOfCompetition;
    private Calendar date;
    private long facultyId;
    private long profileId;

    public Application() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public boolean isOutOfCompetition() {
        return outOfCompetition;
    }

    public void setOutOfCompetition(boolean outOfCompetition) {
        this.outOfCompetition = outOfCompetition;
    }

    public long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(long facultyId) {
        this.facultyId = facultyId;
    }

    public long getProfileId() {
        return profileId;
    }

    public void setProfileId(long profileId) {
        this.profileId = profileId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Application that = (Application) o;

        if (id != that.id) {
            return false;
        }
        if (outOfCompetition != that.outOfCompetition) {
            return false;
        }
        if (facultyId != that.facultyId) {
            return false;
        }
        if (profileId != that.profileId) {
            return false;
        }
        return date != null ? date.equals(that.date) : that.date == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (outOfCompetition ? 1 : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (int) (facultyId ^ (facultyId >>> 32));
        result = 31 * result + (int) (profileId ^ (profileId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Application{");
        sb.append("id=").append(id);
        sb.append(", outOfCompetition=").append(outOfCompetition);
        sb.append(", date=").append(date);
        sb.append(", facultyId=").append(facultyId);
        sb.append(", profileId=").append(profileId);
        sb.append('}');
        return sb.toString();
    }
}
