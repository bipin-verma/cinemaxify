package com.example.Cinemaxify;

abstract class MemberProfile implements User {

    private final String memberType;
    private String name;
    private int age;
    private long contact;
    private String address;

    protected MemberProfile(String memberType) {
        this.memberType = memberType;
    }

    @Override
    public void setUserDetails(String name, int age, Long contact, String address) {
        this.name = name;
        this.age = age;
        this.contact = contact;
        this.address = address;
    }

    @Override
    public void getUserDetails() {
        System.out.println("Hello " + name + ", you have entered the following details for " + memberType + ":");
        System.out.println("age: " + age);
        System.out.println("contact: " + contact);
        System.out.println("address: " + address);
    }
}
