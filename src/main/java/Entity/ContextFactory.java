package Entity;

public class ContextFactory {
    public static  Context createContext(String json){
        return new Context(json,0);
    }
}
