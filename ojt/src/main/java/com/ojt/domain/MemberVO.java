package com.ojt.domain;

public class MemberVO {
	private String id;
    private String name;  
    private String password;    //비밀번호

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id=id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
        		", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password +
                '}';
    }
}
