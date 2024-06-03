package source;

// classe GENERICS di tipo T (posso assegnare qualsiasi tipo al posto di <T>)
public interface Locale<T>{
    // metodi static
    // la <T> indica il tipo che il/i tipi che i miei parametri possono assumere e mi ritorna qualcosa sempre di tipo T (verrà deciso al momento della dichiarazione della classe)
    // <T> deve essere necessariamente una COLLECTION in quanto, altrimenti, ITERABLE non sarebbe automaticamente implementato ---> niente FOR EACH
    public static <T extends Comparable<T>> T latest(T[] array){
        T max = null;
        for(T current: array){ // FOR EACH
            if(max == null || max.compareTo(current) < 0){max = current;}
        }
        return max;
    }
}
