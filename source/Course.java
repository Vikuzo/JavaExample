package source;

public class Course{
    // costanti
    private final int courseID;

    // attributi dinamici della classe
    private String name;

    // costruttori
    public Course(int courseID){this.courseID = courseID;}

    public Course(String name, int courseID){
        this.name = name;
        this.courseID = courseID;
    }

    // metodi funzionali

    // getters e setters
    public int getCourseID(){return courseID;}
    
    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    // metodi ereditati
    @Override
    public String toString(){
        return "Course --> " + name + " with ID " + courseID; 
    }
}
