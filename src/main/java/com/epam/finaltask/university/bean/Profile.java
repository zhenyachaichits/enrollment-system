package com.epam.finaltask.university.bean;

import com.epam.finaltask.university.bean.type.MedalType;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * The type Profile.
 */
public class Profile implements Serializable {
    private long id;

    private String passportId;
    private String firstName;
    private String middleName;
    private String lastName;
    private Calendar birthDate;

    private String phone;
    private String address;

    private int points;
    private boolean isFreeForm;
    private MedalType medalType;
    private String privileges;

    private boolean isApplied;

    private long facultyId;
    private long userId;

    public Profile() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Calendar getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Calendar birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public MedalType getMedalType() {
        return medalType;
    }

    public void setMedalType(MedalType medalType) {
        this.medalType = medalType;
    }

    public String getPrivileges() {
        return privileges;
    }

    public void setPrivileges(String privileges) {
        this.privileges = privileges;
    }

    public boolean isApplied() {
        return isApplied;
    }

    public void setApplied(boolean applied) {
        isApplied = applied;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long user) {
        this.userId = user;
    }

    public long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(long faculty) {
        this.facultyId = faculty;
    }

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    public boolean isFreeForm() {
        return isFreeForm;
    }

    public void setFreeForm(boolean freeForm) {
        isFreeForm = freeForm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Profile profile = (Profile) o;

        if (id != profile.id) {
            return false;
        }
        if (points != profile.points) {
            return false;
        }
        if (isFreeForm != profile.isFreeForm) {
            return false;
        }
        if (isApplied != profile.isApplied) {
            return false;
        }
        if (facultyId != profile.facultyId) {
            return false;
        }
        if (userId != profile.userId) {
            return false;
        }
        if (passportId != null ? !passportId.equals(profile.passportId) : profile.passportId != null) {
            return false;
        }
        if (firstName != null ? !firstName.equals(profile.firstName) : profile.firstName != null) {
            return false;
        }
        if (middleName != null ? !middleName.equals(profile.middleName) : profile.middleName != null) {
            return false;
        }
        if (lastName != null ? !lastName.equals(profile.lastName) : profile.lastName != null) {
            return false;
        }
        if (birthDate != null ? !birthDate.equals(profile.birthDate) : profile.birthDate != null) {
            return false;
        }
        if (phone != null ? !phone.equals(profile.phone) : profile.phone != null) {
            return false;
        }
        if (address != null ? !address.equals(profile.address) : profile.address != null) {
            return false;
        }
        if (medalType != profile.medalType) {
            return false;
        }
        return privileges != null ? privileges.equals(profile.privileges) : profile.privileges == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (passportId != null ? passportId.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + points;
        result = 31 * result + (isFreeForm ? 1 : 0);
        result = 31 * result + (medalType != null ? medalType.hashCode() : 0);
        result = 31 * result + (privileges != null ? privileges.hashCode() : 0);
        result = 31 * result + (isApplied ? 1 : 0);
        result = 31 * result + (int) (facultyId ^ (facultyId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Profile{");
        sb.append("id=").append(id);
        sb.append(", passportId='").append(passportId).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", middleName='").append(middleName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", birthDate=").append(birthDate);
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", points=").append(points);
        sb.append(", isFreeForm=").append(isFreeForm);
        sb.append(", medalType=").append(medalType);
        sb.append(", privileges='").append(privileges).append('\'');
        sb.append(", isApplied=").append(isApplied);
        sb.append(", facultyId=").append(facultyId);
        sb.append(", userId=").append(userId);
        sb.append('}');
        return sb.toString();
    }
}
