package customExp;

import source.Teacher;

public class TeacherExp extends Exception{
    private Teacher teacher;

    public TeacherExp(String msg, Teacher t){
        super(msg);
        teacher = t;
    }

    @Override
    public String toString(){
        return "Teacher " + teacher.getName() + " " + teacher.getSurname() + " raises error with reason: " + super.getMessage();
    }
}
