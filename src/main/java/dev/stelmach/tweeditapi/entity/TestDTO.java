package dev.stelmach.tweeditapi.entity;

public class TestDTO {

    private int age;
    private String name;

    public TestDTO() {
    }

    public TestDTO(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
