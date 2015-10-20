package com.prolificwebworks.theclubix.entities;

/**
 * Created by vaibhav on 10/10/15.
 */
public class ClubData {

    private String cub_id;

    private String Date;

    private String email;

    private String cub_city;

    private String cub_name;

    private String mobile_no;

    public String getCub_id() {
        return cub_id;
    }

    public void setCub_id(String cub_id) {
        this.cub_id = cub_id;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCub_city() {
        return cub_city;
    }

    public void setCub_city(String cub_city) {
        this.cub_city = cub_city;
    }

    public String getCub_name() {
        return cub_name;
    }

    public void setCub_name(String cub_name) {
        this.cub_name = cub_name;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    @Override
    public String toString() {
        return "ClassPojo [cub_id = " + cub_id + ", Date = " + Date + ", email = " + email + ", cub_city = " + cub_city + ", cub_name = " + cub_name + ", mobile_no = " + mobile_no + "]";
    }
}
