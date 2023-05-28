package objects;

import java.util.Date;

public class Professor {
    @Generate
    private String name;
    @Generate
    private int seniority;
    @Generate
    private Date birthDate;

    public Professor() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSeniority(int age) {
        this.seniority = age;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "name='" + name + '\'' +
                ", seniority=" + seniority +
                ", birthDate=" + birthDate +
                '}';
    }
}
