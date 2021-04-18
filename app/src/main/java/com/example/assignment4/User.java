package com.example.assignment4;

public class User {

    public static class Builder {
        private String name;
        private String email;
        private String userType;
        private String address;
        private String ccno;

        public Builder(String email) {
            this.email = email;
        }

        public Builder atName(String name) {
            this.name = name;

            return this;
        }

        public Builder atEmail(String email) {
            this.email = email;

            return this;
        }

        public Builder atUserType(String userType) {
            this.userType = userType;

            return this;
        }

        public Builder atAddress(String address) {
            this.address = address;

            return this;
        }

        public Builder atCcno(String ccnno) {
            this.ccno = ccnno;

            return this;
        }

        public User build() {
            User user = new User();
            user.name = this.name;
            user.email = this.email;
            user.address = this.address;
            user.userType = this.userType;
            user.ccno = this.ccno;

            return user;
        }
    }

        String name;
        String email;
        String userType;
        String address;
        String ccno;

    public User(){
        this.name = null;
        this.email = null;
        this.userType = null;
        this.address = null;
        this.ccno = null;
    }

    public User(String name, String email, String userType, String address, String ccno){
        this.name = name;
        this.email = email;
        this.userType = userType;
        this.address = address;
        this.ccno = ccno;
    }

    public User(String email, String userType) {
        this.name = "";
        this.email = email;
        this.userType = userType;
        this.address = "";
        this.ccno = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCcno() {
        return ccno;
    }

    public void setCcno(String ccno) {
        this.ccno = ccno;
    }
}
