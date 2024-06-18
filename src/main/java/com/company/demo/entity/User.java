package com.company.demo.entity;

public class User {
    protected String name;
    protected String phoneNumber;

    public static class Builder {
        private String name;
        private String phoneNumber;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public User build() {
            User user = new User();
            user.name = this.name;
            user.phoneNumber = this.phoneNumber;
            return user;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
