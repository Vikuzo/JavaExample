package source;

import java.util.*;

public class Course{
    // costanti
    private final int courseID;

    // attributi dinamici della classe
    private String name;
    private Map<Student, Exam> studentsExams = new HashMap<Student, Exam>();
    private boolean written;
    private boolean oral;

    // NESTED CLASS, valgono le solite regole di visibilità
    private class Exam{
        static boolean written;
        static boolean oral;
        private int grade;

        public Exam(boolean written, boolean oral){
            Exam.written = written;
            Exam.oral = oral;
        }

        public boolean setGrade(int grade){
            if(grade > 17 && grade < 31){
                this.grade = grade;
                return true;
            }
            return false;
        }
        public String getGrade(){
            if(grade == 0){
                return "L'esame non è stato ancora sostenuto";
            }

            return "Il voto è: " + grade;
        }
    }

    // costruttori
    // public Course(int courseID){this.courseID = courseID;}

    public Course(String name, int courseID, boolean written, boolean oral){
        this.name = name;
        this.courseID = courseID;
        this.written = written;
        this.oral = oral;
    }

    // metodi funzionali
    public void addStudent(Student s){
        studentsExams.put(s, new Exam(written, oral));
    }

    public boolean addStudentGrade(int studentID, int grade){
        for(Student s: studentsExams.keySet()){
            if(s.getStudentID() == studentID){
                if(studentsExams.get(s).setGrade(grade))
                    return true;
                else
                    return false; // potrei fare il raise di un errore
            }
        }

        return false; // qui potrei fare il raise di un errore
    }

    public String getStudentGrade(int studentID){
        for(Student s: studentsExams.keySet()){
            if(s.getStudentID() == studentID)
                return studentsExams.get(s).getGrade();
        }

        return "Nessuno studente con questo ID"; // meglio fare il raise di un errore
    }

    // getters e setters
    public int getCourseID(){return courseID;}
    
    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    public void setWritten(boolean written){Exam.written = written;}
    public void setOral(boolean oral){Exam.oral = oral;}

    public boolean isWritten(){return Exam.written;}
    public boolean isOral(){return Exam.oral;}

    public Set<Student> getStudents(){
        return studentsExams.keySet();
    }

    // metodi ereditati
    @Override
    public String toString(){
        return "Course --> " + name + " with ID " + courseID; 
    }
}
