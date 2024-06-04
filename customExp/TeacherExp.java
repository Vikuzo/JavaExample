package customExp;

import source.Teacher;

public class TeacherExp extends Exception{
    private Teacher teacher;

    public TeacherExp(String msg, Teacher t){
        super(msg);

        if(teacher == null){teacher = new Teacher("NO", "TEACHER", -1);} // l'implementazione dell'handling di questo errore lascia molto a desiderare
        else{teacher = t;}
    }

    @Override
    public String toString(){
        return "Teacher " + teacher.getName() + " " + teacher.getSurname() + " raises error with reason: " + super.getMessage();
    }
}
