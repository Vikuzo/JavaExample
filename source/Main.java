package source;

import java.util.function.Consumer;

// import customExp.*;

public class Main{
    // I metodi statici non sono legati a nessuna istanza, permettono di non generare un oggetto solo per utilizzare un metodo, sono accessibili solo utilizzando la classe 
    // ---> Utility.inverse(10). E' possibile importare tutti gli oggetti statici di una classe --> import static package.Utility.* 
    public static void main(String[] args){
        // l'operatore NEW mi permette di creare un nuovo oggetto di tipo University
        University uni = new University("Politecnico di Torino");

        // System.out.println(uni.getName());
        // o, avendo modificato toString
        // System.out.println(uni); // quando stampo l'oggetto in automatico viene chiamato il metodo --> toString()

        // DICHIARAZIONE STUDENTI
        Person p1 = new Person("Samuele", "Savino");
        Person p2 = new Person("Giovanni", "Luciani");

        // System.out.println(p1);
        // System.out.println(p2);

        // System.out.println(p1.equals(p2));

        // uni.studentEnroll(p1); uni.studentEnroll(p2);

        // System.out.println(uni.getStudentsOrderedByID());
        // System.out.println(uni.getStudentsOrderedByName());

        Person p3 = new Person("Stefano", "Bianco");
        Person p4 = new Person("Silvia Anna", "Chiusano");

        // uni.teacherEnroll(p3); uni.teacherEnroll(p4); // ho bisogno di fare handling dell'errore che aggiungerò in seguito --> perché no handling dell'errore in caso NullExp?

        // System.out.println(uni.getTeachersOrderedByID());
        // System.out.println(uni.getTeachersOrderedByName());

        // uni.courseEnroll("Basi di dati"); uni.courseEnroll("Fisica I"); uni.courseEnroll("Big Data");

        // System.out.println(uni.getCoursesOrderedByName());

        /*uni.associateCourseToTeacher(101, 10);
        uni.associateCourseToTeacher(101, 12);
        System.out.println(uni.getCoursesOfTeacher(uni.getTeacherByID(101)));*/



        // PROVE CON EXCEPTION, ho impostato il massimo di insegnanti a 2
        // se possibile evitare di utilizzare TRY{}CATCH{} nel main ma meglio direttamente negli oggetti

        Person p5 = new Person("Edoardo", "Patti"); /*uni.teacherEnroll(p5);
        System.out.println(uni.getTeachersOrderedByName()); // in questo caso l'errore non sarà gestito e il programma finirà in modo brusco*/
        
        /*try{ // ein questo caso il programma continua il suo svolgimento nonostante l'occorrenza dell'eccezione 
            Person p5 = new Person("Edorardo", "Patti"); uni.teacherEnroll(p5);
        }catch(NullPointerException ne){
            System.out.println(ne);
        }

        System.out.println(uni.getTeachersOrderedByName());*/

        // nel caso di utilizzo di un errore custom devo inserire ASSOLUTAMENTE i metodi che possono generarlo in un TRY{}CATCH{} ---> ???
        /*try{
            uni.studentEnroll(p1); uni.studentEnroll(p2);
            uni.teacherEnroll(p3); uni.teacherEnroll(p4); uni.teacherEnroll(p5);
            uni.courseEnroll("Basi di dati"); uni.courseEnroll("Fisica I"); uni.courseEnroll("Big Data");
            uni.getTeacherByID(55);
        }catch(StudentExp se){ // costrutto TRY{}CATCH{} con molteplici CATCH se ci sono più eccezioni la prima sarà considerata --> in questo caso ogni eccezione è troppo specifica
                              // per essere gestita in questo modo ma si tratta di un esempio [un eccezione in Student blocca completamente anche le altre operazioni!!]
            System.out.println(se);
        }catch(TeacherExp te){
            System.out.println(te);
        }catch(CourseExp ce){
            System.out.println(ce);
        }

        System.out.println(uni.getTeachersOrderedByName());*/



        // FLUENT BUILDER
        /*University university = new UniversityBuilder("Politecnico di Torino").addStudents(p1, p2).addCourses("Basi di dati", "Fisica I", "Big Data")
        .addTeachers(p3, p4).withCourses(100, 11).withCourses(101, 10, 12).getUniversity();
        // questo builder non è affatto ottimale e non sfrutta la possiblità di legare razionalmente i vari metodi ma da un'idea generale di cosa si può fare

        System.out.println(university.getTeachersOrderedByName());*/



        // STREAM
        /*try{
            uni.studentEnroll(p1); uni.studentEnroll(p2);
            uni.teacherEnroll(p3); uni.teacherEnroll(p4); uni.teacherEnroll(p5);
            uni.courseEnroll("Basi di dati", true, false); uni.courseEnroll("Fisica I", true, true); uni.courseEnroll("Big Data", false, true);
            // uni.getStudentsOrderedByID().forEach(System.out::println); // System.out::println è il mio CONSUMER
            // utilizziamo un CONSUMER creato da noi
            Consumer<Student> c = a -> System.out.println(a);
            uni.getStudentsOrderedByID().forEach(c);
        }catch(StudentExp se){ // costrutto TRY{}CATCH{} con molteplici CATCH se ci sono più eccezioni la prima sarà considerata --> in questo caso ogni eccezione è troppo specifica
                              // per essere gestita in questo modo ma si tratta di un esempio [un eccezione in Student blocca completamente anche le altre operazioni!!]
            System.out.println(se);
        }catch(TeacherExp te){
            System.out.println(te);
        }catch(CourseExp ce){
            System.out.println(ce);
        }*/

        uni.studentEnroll(p1); uni.studentEnroll(p2);
        uni.courseEnroll("Basi di dati", true, false); uni.courseEnroll("Fisica I", true, true); uni.courseEnroll("Big Data", false, true);
        uni.associateStudentToCourse(1000, 10);
        uni.associateStudentToCourse(1001, 11);
        uni.setStudentGrade(1000, 10, 19);
        System.out.println(uni.getStudentGrade(1000, 10));
    }
}
