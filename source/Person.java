package source;

// Sarà la nostra classe madre per Studenti e Insegnanti, non sarà un'interfaccia in quanto molti dei metodi sono in realtà già implementabili ---> non sarà un'interfaccia
// Se non si specifica nessuna classe madre da cui ereditare in automatico la nostra classe erediterà dalla classe OBJECT ---> ad esemprio @Override di toSring()
public class Person{ // avrei potuto dichiarare la classe come ABSTRACT ma non avrei potuto istanziare nessuno oggetto a partire da questa classe, con ---> PUBLIC ABSTRACT CLASS PERSON
                    // deve essere solo parzialmente non implementate ---> ci sono le INTERFACCE per classi che non implementano nulla
    // attributi dinamici della classe
    private String name;
    private String surname;

    // costruttori
    public Person(){}

    public Person(String name, String surname){
        this.name = name;
        this.surname = surname;
    }

    // metodi funzionali

    // getters e setters
    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    public String getSurname(){return surname;}
    public void setSurname(String surname){this.surname = surname;}

    // metodi ereditati
    // i metodi dichiarati FINAL non possono essere sovrascritti
    @Override
    public String toString(){
        return "Person --> " + name + " " + surname;
    }

    // metodo che permette di comparare questo e un altro oggetto
    @Override
    public boolean equals(Object obj){
        // controlliamo con cui effettuo la comparazione non assuma valore nullo
        if(obj == null) {return false;}

        // controlliamo che l'oggetto sia istanza della stessa classe di questo oggetto
        if(!(obj instanceof Person)){return false;}

        final Person other = (Person)obj; // DOWNCAST possibile solo perché mi sono assicurato che l'oggetto passato al metodo sia dello stesso tipo di questo
        if(other.getName().equals(this.getName())){return true;} // l'equals nella classe STRING compare i valori delle stringhe restituendo vero o falso di conseguenza
        return false;
    }

}
