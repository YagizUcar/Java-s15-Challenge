package model;

public class Student extends Reader {
    private String studentId;

    public Student(String name, String studentId) {
        super(name);
        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
    }

    @Override
    public void whoYouAre() {
        System.out.println("Ben bir Öğrenci Okuyucuyum: " + getName() + " (Öğrenci No: " + studentId + ")");
    }
}