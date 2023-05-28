package reflectionAnnotation;

import objects.Professor;
import objects.Student;

public class ReflectionTest {
    public static void main(String[] args) {
        Student student = new Student();
        Professor professor = new Professor();
        BaseMethod baseMethod = new BaseMethod();

        baseMethod.fillObjectsFields(student);
        System.out.println(student);

        baseMethod.fillObjectsFields(professor);
        System.out.println(professor);
    }
}
