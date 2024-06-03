package source;

public class Teacher extends Person implements Comparable<Teacher>{
    // costanti
    private final int teacherID;

    // attributi dinamici della classe

    // costruttori
    public Teacher(String name, String surname, int teacherID){
        super(name, surname);
        this.teacherID = teacherID;
    }

    public Teacher(Person person, int teacherID){
        super(person.getName(), person.getSurname());
        this.teacherID = teacherID;
    }

    // metodi funzionali

    // getters e setters
    public int getTeacherID(){return teacherID;}

    // metodi ereditati
    @Override
    public String toString(){
        return "Teacher --> " + getName() + " " + getSurname(); 
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null){return false;}

        if(!(obj instanceof Teacher)){return false;}

        final Teacher other = (Teacher)obj; 
        if(other.getTeacherID() == teacherID){return true;} 
        return false;
    }

    // metodi sovrascritti dell'interfaccia comparable
    public int compareTo(Teacher o){
        // in questo caso ho dichiarato il tipo di Comparable<?> la classe in cui sto lavorando --> Comparable<Teacher>
        Teacher other = (Teacher)o;
        return this.teacherID - other.teacherID;
    }
    
}
