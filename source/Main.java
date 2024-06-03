package source;

public class Main{
    // I metodi statici non sono legati a nessuna istanza, permettono di non generare un oggetto solo per utilizzare un metodo, sono accessibili solo utilizzando la classe 
    // ---> Utility.inverse(10). E' possibile importare tutti gli oggetti statici di una classe --> import static package.Utility.* 
    public static void main(String[] args){
        // l'operatore NEW mi permette di creare un nuovo oggetto di tipo University
        University uni = new University("Politecnico di Torino");

        // System.out.println(uni.getName());
        // o, avendo modificato toString
        // System.out.println(uni);

        // DICHIARAZIONE STUDENTI
        Person p1 = new Person("Samuele", "Savino");
        Person p2 = new Person("Giovanni", "Luciani");

        // System.out.println(p1);
        // System.out.println(p2);

        // System.out.println(p1.equals(p2));

        uni.studentEnroll(p1); uni.studentEnroll(p2);

        // System.out.println(uni.getStudentsOrderedByID());
        // System.out.println(uni.getStudentsOrderedByName());

        // DICHIARAZIONE INSEGNANTI
        Person p3 = new Person("Stefano", "Bianco");
        Person p4 = new Person("Silvia Anna", "Chiusano");

        uni.teacherEnroll(p3); uni.teacherEnroll(p4);

        // System.out.println(uni.getTeachersOrderedByID());
        // System.out.println(uni.getTeachersOrderedByName());

        uni.courseEnroll("Basi di dati"); uni.courseEnroll("Fisica I"); uni.courseEnroll("Big Data");

        // System.out.println(uni.getCoursesOrderedByName());

        uni.associateCourseToTeacher(101, 10);
        uni.associateCourseToTeacher(101, 12);
        System.out.println(uni.getCoursesOfTeacher(uni.getTeacherByID(101)));
    }
}
