package objects;

public class Student {
    @Generate
    private String name;
    @Generate
    private int age;
    @Generate
    private boolean grant;

    public Student() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGrant(boolean grant) {
        this.grant = grant;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", grant=" + grant +
                '}';
    }
}
