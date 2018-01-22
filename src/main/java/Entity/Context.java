package Entity;

import java.util.Stack;

//
public class Context {
    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }



    public int getCursor() {
        return cursor;
    }

    public void setCursor(int cursor) {
        this.cursor = cursor;
    }

    public Context(String json, int cursor) {
        this.json = json;
        this.cursor = cursor;
    }

    private String json ;
    private int cursor ;


}
