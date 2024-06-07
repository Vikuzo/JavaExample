package source;

//import java.io.Serializable;

// la keyword per l'ereditarietà è EXTENDS ---> IMPLEMENTS per le interfacce. Non possiamo ereditare più da più di un'altra classe ma possiamo implementare molteplici interfacce
// COMPARABLE è un'interfaccia di tipo GENERIC ---> devo necessariamente specificare il tipo <TIPO>
public class Student extends Person implements Comparable<Object>/* , Serializable*/{ // Serializable è necessario per scrivere l'oggetto su file
    // costanti
    private final int studentID;

    // attributi dinamici della classe
    
    // costruttori
    // public Student(){} Non posso utilizzare un costruttore del genere in questo contesto perché HO SEMPRE bisogno che sia dichiarate la matricola --> NO DEFAULT CONSTRUCTOR

    public Student(String name, String surname, int studentID){
        super(name, surname); // funzione che richiama il costruttore della classe madre specificata dalla signature, necessaria se non si hanno costruttori vuoti
                            // DEVE essere la prima istruzione nel costruttore delle classi figlie
                            // this ---> si riferisce a questo oggetto, super ---> si riferisce al genitore
        this.studentID = studentID;
    }

    public Student(Person person, int studentID){ // in questo caso utilizzo un'istanza di PERSON già esistente per generare il mio studente
        super(person.getName(), person.getSurname());
        this.studentID = studentID;
    }

    // metodi funzionali
    // implementazione di Comparable attraverso LAMBDA FUNCTION, posso fare la LAMBDA FUNCTIONS solo di FUNCTIONAL INTERFACE --> solo un metodo ASTRATTO (gli altri devono essere DEFAULT o STATIC)
    public int compareIDs(Student s){
        Comparable<Student> c = o -> (this.studentID - s.getStudentID()); // INTERFACCIA<TIPO(in caso GENERICS)> NOME_VAR = NOME_PAR -> {blocco di codice per la funzione}
        return c.compareTo(s);
    }

    // getters e setters
    public int getStudentID(){return studentID;}

    // metodi ereditati
    @Override
    public String toString(){
        return "Student --> " + getName() + " " + getSurname() + " with ID " + studentID; // non è possibile accedere direttamente agli attributi privati della classe madre
                                                        // l'alternativa sarebbe utilizzare protected --> l'attributo però così diventa accessibile anche al resto del package!!! 
    }

    @Override
    public boolean equals(Object obj){
        // controlliamo con cui effettuo la comparazione non assuma valore nullo
        if(obj == null) {return false;}

        // controlliamo che l'oggetto sia istanza della stessa classe di questo oggetto
        if(!(obj instanceof Student)){return false;}

        // potrei utilizzare lo stesso equals di persone ma nel caso di STUDENT è più facile poiché dobbiamo semplicemente confronate due numeri univoci
        final Student other = (Student)obj; 
        if(other.getStudentID() == studentID){return true;} 
        return false;
    }

    // metodi sovrascritti dell'interfaccia comparable
    // quando si implementa una interfaccia è necessario sovrascriverne tutti i metodi
    @Override
    public int compareTo(Object o){
        // potrei inserire STUDENT al posto di OBJECT (dovrei farlo anche sopra) ma così è più evidente la differenza
        Student other = (Student)o;
        return this.studentID - other.studentID;
    }
}
