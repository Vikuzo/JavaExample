package source;

//import customExp.*;

// ADATTATO A PRIMA DELL'AGGIUNTA DI EXAM

// questa classe fornirà un FLUENT BUILDER per le università 
public class UniversityBuilder{
    // attributi dinamici
    /*University university;

    // costruttori
    public UniversityBuilder(){university = new University();}

    public UniversityBuilder(String name){university = new University(name);}

    // metodi per costruire l'oggetto University

    // ritorniamo l'oggetto stesso per poter permettere il chaining dei nostri metodi
    public UniversityBuilder withUniversityName(String name){
        university.setName(name);
        return this;
    }

    public UniversityBuilder addStudents(Person... students){
        for(Person s: students){
            try{university.studentEnroll(s);}
            catch(StudentExp se){System.out.println(se);}
        }

        return this;
    }

    public UniversityBuilder addTeachers(Person... teachers){
        for(Person t: teachers){
            try{university.teacherEnroll(t);}
            catch(TeacherExp te){System.out.println(te);}
        }

        return this;
    }

    public UniversityBuilder addCourses(String... names){
        for(String name: names){
            try{university.courseEnroll(name);}
            catch(CourseExp ce){System.out.println(ce);} // non innalzo nuovamente gli errori perché qualsiai errore mi bloccherebbe completamente il builder
        }  
        
        return this;
    }

    // funzione che associa i corsi agli insegnanti
    public UniversityBuilder withCourses(int teacherID, int... courseID){
        for(Integer i: courseID){ // in questo caso se una singola istanza di courseID è problematica comunque svolgerò le operazioni su tutti gli altri elementi
            try{
                university.associateCourseToTeacher(teacherID, i);
            }catch(TeacherExp te){
                System.out.println(te);
            }catch(CourseExp ce){
                System.out.println(ce);
            }
        }

        return this;
    }

    public University getUniversity(){return university;}*/
}
