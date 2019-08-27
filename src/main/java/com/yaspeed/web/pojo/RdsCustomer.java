package com.yaspeed.web.pojo;

public class RdsCustomer {


    @Override
    public String toString() {
        return "RdsCustomer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", hobbies='" + hobbies + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    private String id;

    private String name;

    private String hobbies;

    private String gender;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies == null ? null : hobbies.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }
}