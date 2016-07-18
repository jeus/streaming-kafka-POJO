/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datis.irc.entity;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author jeus
 */
public class User {

    private String name;
    private String famil;
    private int age;
    private long registerDate;
    private List<String> phoneNumber;
    private List<Integer> rateNumber;

    public User(String name, String famil, int age, long registerDate, List<String> phoneNumber, List<Integer> rateNumber) {
        this.name = name;
        this.famil = famil;
        this.age = age;
        this.registerDate = registerDate;
        this.phoneNumber = phoneNumber;
        this.rateNumber = rateNumber;
    }

    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamil() {
        return famil;
    }

    public void setFamil(String famil) {
        this.famil = famil;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(long registerDate) {
        this.registerDate = registerDate;
    }

    public List<String> getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(List<String> phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Integer> getRateNumber() {
        return rateNumber;
    }

    public void setRateNumber(List<Integer> rateNumber) {
        this.rateNumber = rateNumber;
    }

    private void addRateNumber(Integer rateNum) {
        rateNumber.add(rateNum);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.name);
        hash = 31 * hash + Objects.hashCode(this.famil);
        hash = 31 * hash + this.age;
        hash = 31 * hash + (int) (this.registerDate ^ (this.registerDate >>> 32));
        hash = 31 * hash + Objects.hashCode(this.phoneNumber);
        hash = 31 * hash + Objects.hashCode(this.rateNumber);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.age != other.age) {
            return false;
        }
        if (this.registerDate != other.registerDate) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.famil, other.famil)) {
            return false;
        }
        if (!Objects.equals(this.phoneNumber, other.phoneNumber)) {
            return false;
        }
        if (!Objects.equals(this.rateNumber, other.rateNumber)) {
            return false;
        }
        return true;
    }

    
    
    @Override
    public String toString() {
        return "User{" + "name=" + name + ", famil=" + famil + ", age=" + age + ", registerDate=" + registerDate + ", phoneNumber=" + phoneNumber + ", rateNumber=" + rateNumber + '}';
    }

}
