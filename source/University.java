// packages
package source; // package del mio workspace, per invocare package esterni devo importarli ---> import packageName.className (se voglio specificare) --> * per intendere tutto

import java.io.*; // libreria per input output
import java.nio.charset.Charset;
import java.nio.file.*; // java new input output
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

// import customExp.*; // package in cui inserisco i miei errori custom

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
    // private Set<Teacher> teachers = new LinkedHashSet<Teacher>();
    private Set<Course> courses = new LinkedHashSet<Course>();

    // private Map<Teacher, Set<Course>> coursesOfTeacher = new HashMap<Teacher,Set<Course>>(); // uso una mappa per associare un insegnante ai corsi in cui insegna, le hashmap non hanno
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
    /*public Student studentEnroll(Person p) throws StudentExp{
        Student s = new Student(p, STUDENT_STARTID + students.size());
        // controllo di non aver superato il limite di studenti
        if(students.size() >= MAX_STUDENTS)
            throw(new StudentExp("Non è possibile inserire nuovi studenti", s));

        students.add(s);
        return s;
    }*/

    public Student studentEnroll(Person p){
        Student s = new Student(p, STUDENT_STARTID + students.size());
        // controllo di non aver superato il limite di studenti
        if(students.size() >= MAX_STUDENTS)
            return null;

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
    /*public Teacher teacherEnroll(Person p) throws TeacherExp{
        Teacher t = new Teacher(p, TEACHER_STARTID + teachers.size());
        if(teachers.size() >= MAX_TEACHERS)
            throw (new TeacherExp("Non è possibile inserire ulteriori insegnanti", t)); 

        teachers.add(t);
        return t;
    }*/

    // metodo per aggiungere un corso all'università
    /*public Course courseEnroll(String name, boolean written, boolean oral) throws CourseExp{
        Course c = new Course(name, COURSE_STARTID + courses.size(), written, oral);
        if(courses.size() >= MAX_COURSE)
            throw(new CourseExp("Non è possibile inserire ulteriori corsi", c));

        courses.add(c);
        return c;
    }*/

    public Course courseEnroll(String name, boolean written, boolean oral){
        Course c = new Course(name, COURSE_STARTID + courses.size(), written, oral);
        if(courses.size() >= MAX_COURSE)
            return null;

        courses.add(c);
        return c;
    }

    // metodo per associare un corso ad un insegnante
    /*public void associateCourseToTeacher(int teacherID, int courseID) throws TeacherExp, CourseExp{
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
    }*/

    public void associateStudentToCourse(int studentID, int courseID){
        Course c = getCourseByID(courseID);
        c.addStudent(getStudentByID(studentID));
    }

    public void courseFlatMapping(){ // .map() permette di rimappare uno stream di elementi su una collezione di oggetti ad essi associati Set<Course> --> Stream<Course> --> Stream<Collection<Student>>
                                    // .flatMap() permette di ridurre lo stream di una COLLECTION allo stream degli elementi che la compongono Stream<Colletcion<Student>> --> Stream<Student>
                                    // il successivo .map() mappa i miei SINGOLI elementi nel nome del metodo getName Stream<Student> --> Stream<String>
        courses.stream().map(Course::getStudents).flatMap(Set::stream).map(Student::getName).forEach(System.out::println);
    }

    // è preferibile utilizzare le versioni BUFFERED in quanto non scrivono direttamente sul file ogni volta ma lo fanno raggiunto un certo numero di elementi nel buffer,
    // richiede meno risorse come approccio
    public void writeStudentsOnFileStr(){
        Path path = Path.of("files/students.txt"); // istruzione per ottenere il percorso del mio file students.txt
        try{
            Writer w = new OutputStreamWriter(new FileOutputStream(path.toString()), "ISO-8859-1"); // il write in questo modo elimina tutto ciò presente in un file prima di iniziare a scrivere
                                                                                                                // non è aperto in append
            /*for(String name: students.stream().map(Student::getName).toList()) // tramite STREAM
                w.write(name + "\n");
            w.close();*/

            for(Student student: students){ // senza STREAM
                w.write(student.getName() + "\n");
            }
            w.close();
        }catch(IOException ioe){ // possono essere generate EXCEPTION che ereditano tutte da IOException
            System.out.println(ioe);
        }        
    }

    public void writeStudentsOnFileObj(){ // OutputStream con serializzazione, per scrivere su file direttamente degli oggetti
        Path path = Path.of("files/students.ser");

        try{
            ObjectOutputStream w = new ObjectOutputStream(new FileOutputStream(path.toString())); // non c'è bisogno di specificare la modalità di encoding
            
            for(Student student: students){
                w.writeObject(student);
            }

            w.close();
        }catch(IOException ioe){ // possono essere generate EXCEPTION che ereditano tutte da IOException
            System.out.println(ioe);
        }  
    }

    public void readStudentOnFileStr(){
        Path path = Path.of("files/students.txt");

        try{
            BufferedReader r = new BufferedReader(new FileReader(path.toString(), Charset.forName("ISO-8859-1"))); // vuole per forza un OGGETTO di tipo CHARSET
            while(true){
                String s = r.readLine();
                if(s == null)
                    break;
                System.out.println(s);
            }

            r.close();
        }catch(IOException ioe){
            System.out.println(ioe);
        }
    }

    public void readStudentOnFileObj(){
        Path path = Path.of("files/students.ser");

        try{
            ObjectInputStream r = new ObjectInputStream(new FileInputStream(path.toString()));
            while(true){
                Student s = (Student)r.readObject(); // non troverò i dati di nomi e congnomi in quanto essi appartegono alla classe PERSON!!
                                                    // devo rendere SERIALIZABLE la super classe!
                if(s == null)
                    break;
                System.out.println(s);
            }

            r.close();
        }catch(EOFException ef){
        }catch(IOException ioe){
            System.out.println(ioe);
        }catch(ClassNotFoundException cn){
            System.out.println(cn);
        }
    }

    // getters e setters
    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    public String getStudentGrade(int studentID, int courseID){
        return getCourseByID(courseID).getStudentGrade(studentID);
    }

    public void setStudentGrade(int studentID, int courseID, int grade){
        getCourseByID(courseID).addStudentGrade(studentID, grade);
    }

    public Student getStudentByID(int studentID){
        Predicate<Student> p = s -> s.getStudentID() == studentID;
        List<Student> student = students.stream().filter(p).toList(); // non utilissimo per cercare un singolo studente, non ci sono conversioni dirette in tipo CUSTOM
        return student.get(0);
    }

    /*public Teacher getTeacherByID(int ID) throws TeacherExp{
        for(Teacher t: teachers){
            if(t.getTeacherID() == ID)
                return t;
        }

        throw(new TeacherExp("Non c'è nessun professore con questo ID", null)); // in questo caso stiamo facendo  error signaling ---> informiamo la funzione chiamante di un'anomalia
    }*/

    /*public Course getCourseByID(int ID) throws CourseExp{
        for(Course c: courses){
            if(c.getCourseID() == ID)
                return c;
        }

        throw(new CourseExp("Non c'è nessun corso con questo ID", null));
    }

    public Set<Course> getCoursesOfTeacher(Teacher t){
        return coursesOfTeacher.get(t);
    }*/

    public Course getCourseByID(int ID){
        for(Course c: courses){
            if(c.getCourseID() == ID)
                return c;
        }
        return null;
    }

    // per definizione (in quanto LinkedHashSet), anche se il SET non è di per sé automaticamente in ordine, per modalità di inserimento i miei studenti saranno ordinati per ID
    // Tutte le funzioni seguenti non prevedono di ordinare la lista già esistente ma di creare una nuova versione ordinata così da la COLLECTION di origine 
    // public Set<Student> getStudentsOrderedByID(){return students;}
    public Stream<Student> getStudentsOrderedByID(){// versione di getStudentsOrderedByID che fa utilizza gli STREAM
        Comparator<Student> c; // non necessario ai fini di ciò che stiamo facendo ma fondamentalmente stiamo dicendo che sono accettati tutti i tipi che ereditano da Student
        c = (a,b) -> {return a.getStudentID() - b.getStudentID();};

        return students.stream().sorted(c);
    }

    /*public Set<Student> getStudentsOrderedByName(){
        // LAMBDA FUNCTION per dichiarare il comparator da passare alla mio set ordinao TreeSet(c)
        Comparator<Student> c = (a, b) -> {
            if(a.getSurname().compareTo(b.getSurname()) == 0){return a.getName().compareTo(b.getName());}else{return a.getSurname().compareTo(b.getSurname());}
        };

        Set<Student> students = new TreeSet<Student>(c);
        students.addAll(this.students);
        return students;
    }*/

    // public Set<Teacher> getTeachersOrderedByID(){return teachers;}

    // ritorniamo lo STREAM dei teacher
    // public Stream<Teacher> getTeachersStream(){return teachers.stream();}

    /*public Set<Teacher> getTeachersOrderedByName(){
        Comparator<Teacher> c = (a, b) -> {
            if(a.getSurname().compareTo(b.getSurname()) == 0){return a.getName().compareTo(b.getName());}else{return a.getSurname().compareTo(b.getSurname());}
        };

        Set<Teacher> teachers = new TreeSet<Teacher>(c);
        teachers.addAll(this.teachers);
        return teachers;
    }*/
    
    // public Set<Course> getCoursesOrderdByID(){return courses;}

    /*public Set<Course> getCoursesOrderedByName(){
        Comparator<Course> c = (a, b) -> {
            return a.getName().compareTo(b.getName());
        };

        Set<Course> courses = new TreeSet<Course>(c);
        courses.addAll(this.courses);
        return courses;
    }*/

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
