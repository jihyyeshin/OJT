package com.ojt.domain;

public class MemberVO {
	private String id;
    private String name;  
    private String password;    //비밀번호
    private String addr;
    private String agentF;//상온
    private String agentA;//신선

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr=addr;
    }

    public String getAgentF() {
        return agentF;
    }

    public void setAgentF(String agentF) {
        this.agentF = agentF;
    }

    public String getAgentA() {
        return agentA;
    }

    public void setAgentA(String agentA) {
        this.agentA=agentA;
    }

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
