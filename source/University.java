// packages
package source; // package del mio workspace, per invocare package esterni devo importarli ---> import packageName.className (se voglio specificare) --> * per intendere tutto

import java.util.*;
import customExp.*; // package in cui inserisco i miei errori custom

// la visibilità della classe determina se sarà visibile solo all'interno del package o anche dalle classi che importano quel package 
public class University{
    // costanti
    // le variabili statiche sono generate una sola volta e sono comuni a tutte le istanze. Una modifica su uno di questi valore e visibile da tutti gli oggetti istanziati
    // il modificatore FINAL rende impossibile modficare il valore dell'attributo. E' possibile inizializzare il valore direttamente alla dichiarazione o al limite nei costruttori
    public static final int MAX_STUDENTS = 1000; // Integer classe wrapper di int --> possiede i metodi di conversione per gli interi
    public static final int MAX_TEACHERS = 100; // anche per loro potrebbe valere quanto detto sotto ma li tratterò come static per usarli come esempio
    public static final int MAX_COURSE = 50;

    private final int STUDENT_STARTID = 1000; // non li dichiariamo PUBLIC STATIC perché in questo caso li abbiamo dichiarati esplicitamente ma tipicamente, in un'applicazione reale
    private final int TEACHER_STARTID = 100; // questi valori non sono fissi, possono cambiare nel tempo e dipendeno, inoltre, dalla singola istanza di università che li genera
    private final int COURSE_STARTID = 10;

    // attributi dinamici della classe
    private String name; /* un oggetto di tipo Stringa privato (accessibile solo dall'interno) ---> devo utilizzare get e set per accederci (se voglio permetterlo)
                         gli oggetti di tipo Stirng non possono essere modificati (con la concatenazione con operatore + ne creiamo uno nuovo). Se è necessario modificare 
                         il contenuto meglio StringBuffer */
    private Set<Student> students = new LinkedHashSet<Student>(); // non esiste una implementazione di SET semplicemente Set() 
                                                                // utilizzo SET perché non voglio duplicati
    private Set<Teacher> teachers = new LinkedHashSet<Teacher>();
    private Set<Course> courses = new LinkedHashSet<Course>();

    private Map<Teacher, Set<Course>> coursesOfTeacher = new HashMap<Teacher,Set<Course>>(); // uso una mappa per associare un insegnante ai corsi in cui insegna, le hashmap non hanno
                                                                                            // un ordine particolare

    // costruttori
    public University(){} // genera un oggetto university senza effettuare nessuna operazione, rischioso non è detto che le variabili, specialmente i puntatori siano NULL

    // possibile avere due costruttori diversi perché con firma (signature) diversa
    // this ---> self di python, non è necessario specificarlo se le variabili delle funzioni hanno nomi diversi da quelle degli attributi
    public University(String name){
        this.name = name;
    } 

    // metodi funzionali
    // metodo per aggiungere uno studente all'università
    public Student studentEnroll(Person p) throws StudentExp{
        Student s = new Student(p, STUDENT_STARTID + students.size());
        // controllo di non aver superato il limite di studenti
        if(students.size() >= MAX_STUDENTS)
            throw(new StudentExp("Non è possibile inserire nuovi studenti", s));

        students.add(s);
        return s;
    }

    // metodo per aggiungere un insegnante all'università
    // in questo caso implemento un errore già dichiarato nelle librerie di base di java
    /*public Teacher teacherEnroll(Person p) throws NullPointerException{ // attraverso la keyword THROWS definisco gli errori che il mio metodo potrebbe innalzare
        if(teachers.size() >= MAX_TEACHERS)
            throw (new NullPointerException("Non è possibile inserire ulteriori insegnanti")); // una volta giunto nella parte di codice problematica innalzo un nuovo errore di
                                                                                                // ti tipo NullPointerException (specifico del mio caso) --> CHECKED EXCEPTION

        Teacher t = new Teacher(p, TEACHER_STARTID + teachers.size());
        teachers.add(t);
        return t;
    }*/

    // in questo caso utilizzo un errore CUSTOM (personalizzato)
    public Teacher teacherEnroll(Person p) throws TeacherExp{
        Teacher t = new Teacher(p, TEACHER_STARTID + teachers.size());
        if(teachers.size() >= MAX_TEACHERS)
            throw (new TeacherExp("Non è possibile inserire ulteriori insegnanti", t)); 

        teachers.add(t);
        return t;
    }

    // metodo per aggiungere un corso all'università
    public Course courseEnroll(String name) throws CourseExp{
        Course c = new Course(name, COURSE_STARTID + courses.size());
        if(courses.size() >= MAX_COURSE)
            throw(new CourseExp("Non è possibile inserire ulteriori corsi", c));

        courses.add(c);
        return c;
    }

    // metodo per associare un corso ad un insegnante
    public void associateCourseToTeacher(int teacherID, int courseID) throws TeacherExp, CourseExp{
        Teacher t;
        Course c;
        
        try{ // in questo caso a prescindera da quale sia l'eccezione l'esecuzione del mio metodo è interrotta quindi, essendo comunque fra loro gli errori completamente separati,
            // non mi interessa separare i due TRY{}CATCH{}
            t = getTeacherByID(teacherID);
            c = getCourseByID(courseID);
        }catch(TeacherExp te){
            throw te; // in questo caso stiamo effettuando propagazione (dispatch) dell'errore a codice più in alto (l'errore in questo caso è sempre causato dai metodi che stiamo chiamando
                    // e non direttamente da questo)
        }catch(CourseExp ce){
            throw ce;
        }

        if(coursesOfTeacher.containsKey(t)){
            Set<Course>courses = coursesOfTeacher.get(t);
            courses.add(c);
            coursesOfTeacher.replace(t, coursesOfTeacher.get(t), courses);
        }else{
            Set<Course>courses = new HashSet<Course>();
            courses.add(c);
            coursesOfTeacher.put(t, courses);
        }
    }

    // getters e setters
    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    public Teacher getTeacherByID(int ID) throws TeacherExp{
        for(Teacher t: teachers){
            if(t.getTeacherID() == ID)
                return t;
        }

        throw(new TeacherExp("Non c'è nessun professore con questo ID", null)); // in questo caso stiamo facendo  error signaling ---> informiamo la funzione chiamante di un'anomalia
    }

    public Course getCourseByID(int ID) throws CourseExp{
        for(Course c: courses){
            if(c.getCourseID() == ID)
                return c;
        }

        throw(new CourseExp("Non c'è nessun corso con questo ID", null));
    }

    public Set<Course> getCoursesOfTeacher(Teacher t){
        return coursesOfTeacher.get(t);
    }

    // per definizione (in quanto LinkedHashSet), anche se il SET non è di per sé automaticamente in ordine, per modalità di inserimento i miei studenti saranno ordinati per ID
    // Tutte le funzioni seguenti non prevedono di ordinare la lista già esistente ma di creare una nuova versione ordinata così da la COLLECTION di origine 
    public Set<Student> getStudentsOrderedByID(){return students;}

    public Set<Student> getStudentsOrderedByName(){
        // LAMBDA FUNCTION per dichiarare il comparator da passare alla mio set ordinao TreeSet(c)
        Comparator<Student> c = (a, b) -> {
            if(a.getSurname().compareTo(b.getSurname()) == 0){return a.getName().compareTo(b.getName());}else{return a.getSurname().compareTo(b.getSurname());}
        };

        Set<Student> students = new TreeSet<Student>(c);
        students.addAll(this.students);
        return students;
    }

    public Set<Teacher> getTeachersOrderedByID(){return teachers;}

    public Set<Teacher> getTeachersOrderedByName(){
        Comparator<Teacher> c = (a, b) -> {
            if(a.getSurname().compareTo(b.getSurname()) == 0){return a.getName().compareTo(b.getName());}else{return a.getSurname().compareTo(b.getSurname());}
        };

        Set<Teacher> teachers = new TreeSet<Teacher>(c);
        teachers.addAll(this.teachers);
        return teachers;
    } 
    
    public Set<Course> getCoursesOrderdByID(){return courses;}

    public Set<Course> getCoursesOrderedByName(){
        Comparator<Course> c = (a, b) -> {
            return a.getName().compareTo(b.getName());
        };

        Set<Course> courses = new TreeSet<Course>(c);
        courses.addAll(this.courses);
        return courses;
    }

    // metodi ereditati
    // toString è il metodo che viene chiamato quando si prova a stampare l'oggetto ---> sovrascrivibile tra noi tramite @Override
    @Override
    public String toString(){
        return "Università --> " + name;
    }

    // distruttore
    // public void finalize(){} --> distruttore. Potremmo programmarlo noi ma non è un compito svolto tipicamente dai programmatori. Se non lo scriviamo sarà presente di DEFAULT
    // è invocata dal GC

}
