import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamTest {
    public static void main(String[] args) {

        Student student1 = new Student(
                "John",
                20,
                new Address("1234"),
                Arrays.asList(new MobileNumber("1233"), new MobileNumber("1234")));

        Student student2 = new Student(
                "Kiran",
                20,
                new Address("1235"),
                Arrays.asList(new MobileNumber("1111"), new MobileNumber("3333"), new MobileNumber("1233")));

        Student student3 = new Student(
                "Jacob",
                20,
                new Address("1236"),
                Arrays.asList(new MobileNumber("3333"), new MobileNumber("4444")));

        List<Student> students = Arrays.asList(student1, student2, student3);

        /*****************************************************
         Get student with exact match name "John"
         *****************************************************/
        Optional<Student> studentOptional = students.stream().filter(s->s.getName().equals("John")).findFirst();
        System.out.println(studentOptional.isPresent() ? studentOptional.get() : "Not found");
        System.out.println("--------------------");

        /*****************************************************
         Get student with matching address "1235"
         *****************************************************/
        Optional<Student> studentOptional1 = students.stream().filter(s->s.getAddress().getZipcode().equals("1235")).findFirst();
        System.out.println(studentOptional.isPresent() ? studentOptional.get() : "Not found");
        System.out.println("--------------------");

        /*****************************************************
         Get all student having mobile numbers 3333.
         *****************************************************/
        List<Student> students1 = students.stream().filter(s->s.getMobileNumbers().stream().anyMatch(m->m.getNumber().equals("3333"))).collect(Collectors.toList());
        System.out.println(students1.size() == 0 ? "Not found" : students1);
        System.out.println("--------------------");

        /*****************************************************
         Get all student having mobile number 1233 and 1234
         *****************************************************/
        List<Student> students2 = students.stream().filter(s->s.getMobileNumbers().stream().allMatch(m->(m.getNumber().equals("1233"))||(m.getNumber().equals("1234")))).collect(Collectors.toList());
        System.out.println(students2.size() == 0 ? "Not found" : students2);
        System.out.println("--------------------");

        /*****************************************************
         Create a List<Student> from the List<TempStudent>
         *****************************************************/
        TempStudent tmpStud1 = new TempStudent(
                "Mahesh",
                201,
                new Address("12341"),
                Arrays.asList(new MobileNumber("12331"), new MobileNumber("12341")));

        TempStudent tmpStud2 = new TempStudent(
                "Ramesh",
                202,
                new Address("12351"),
                Arrays.asList(new MobileNumber("11111"), new MobileNumber("33331"), new MobileNumber("12331")));

        List<TempStudent> tmpStudents = Arrays.asList(tmpStud1, tmpStud2);

        List<Student> students3 = tmpStudents.stream().map(tempStudent -> new Student(tempStudent.name, tempStudent.age, tempStudent.address, tempStudent.mobileNumbers)).collect(Collectors.toList());
        System.out.println(students3.size() == 0 ? "Not found" : students3);
        System.out.println("--------------------");

        /*****************************************************
         Convert List<Student> to List<String> of student name
         *****************************************************/
        List<String> studentNames = students3.stream().map(student -> student.getName()).collect(Collectors.toList());
        System.out.println(studentNames.size() == 0 ? "Not found" : studentNames);
        System.out.println("--------------------");

        /*****************************************************
         Convert List<students> to String
         *****************************************************/
        String nameCombined = studentNames.stream().collect(Collectors.joining(", "));
        System.out.println(nameCombined);
        System.out.println("--------------------");

        /*****************************************************
         Change the case of List<String>
         *****************************************************/
        List<String> caseChanged = studentNames.stream().map(s -> s.toLowerCase()).collect(Collectors.toList());
        System.out.println(caseChanged);
        System.out.println("--------------------");

        /*****************************************************
         Sort List<String>
         *****************************************************/
        System.out.println(caseChanged.stream().sorted());
        System.out.println("--------------------");

        /*****************************************************
         Conditionally apply Filter condition, say if flag is enabled then
         *****************************************************/
        boolean sortConditionFlag = true;

        System.out.println("Before sorting :");

        System.out.println("After filter and conditional sorting :");
    }
}