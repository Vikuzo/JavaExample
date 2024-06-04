package customExp;

import source.Student;

public class StudentExp extends Exception{
    private Student student;

    public StudentExp(String msg, Student s){
        super(msg); 
        
        if(student== null){student = new Student("NO", "STUDENT", -1);}
        else{student = s;}
    }

    @Override
    public String toString(){
        return "Student " + student.getName() + " " + student.getSurname() + " raises error with reason: " + super.getMessage();
    }
}
