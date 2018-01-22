package ParseLib;

import Const.NOVA_TYPE;
import Entity.Context;
import Entity.Value;
import Exceptions.ExceptValueException;
import Exceptions.InvalidValueException;
import Exceptions.RootNotSingularException;

public class NOVATools {
    //return true if success
    //false if failed
    // value is allocated by the caller
    static public boolean NovaParse(Context context , Value value) throws  RuntimeException{
          String json = context.getJson();
          assert (value!=null);
          if(json==null) return  false;
          value.setNova_type(NOVA_TYPE.NOVA_NULL);
          parseWhiteSpace(context);
          if(context.getCursor()==json.length()) throw new ExceptValueException();
          char now = json.charAt(context.getCursor());
          boolean result = false;
          switch (now){
              case 't':
                  result=parseLiteral(context,"true",value);
                  break;
              case 'f':
                  result=parseLiteral(context,"false",value);
                  break;
              case 'n':
                  result=parseLiteral(context,"null",value);
                  break;
              default:
                  throw  new InvalidValueException();
          }
          if(result)
            parseWhiteSpace(context);
          else
              return result;
          if(context.getCursor()<json.length())
              throw new RootNotSingularException();

          return result ;



    }
    static private boolean parseLiteral(Context context ,String literal,Value value) throws  RuntimeException{
        int pos = context.getCursor();
        int bound = context.getJson().length();
        String json = context.getJson();
        for(int i = 0 ;i<literal.length();i++){
            if(pos>=bound||literal.charAt(i)!=json.charAt(pos++))
                return false;
        }
        if("null".equals(literal))
           value.setNova_type(NOVA_TYPE.NOVA_NULL);
        if("true".equals(literal))
            value.setNova_type(NOVA_TYPE.NOVA_TRUE);
        if("false".equals(literal))
            value.setNova_type(NOVA_TYPE.NOVA_FALSE);
        context.setCursor(pos);
        return true;
    }
    //scan String from the pos util first character which is not  ws
    //return the pos of the first which is not ws
    static private void  parseWhiteSpace(Context context){
         String json = context.getJson();
         int pos = context.getCursor();
           for( ;pos<json.length();pos++){
               char ch = json.charAt(pos);
               if(ch==' '||ch=='\t'||ch=='\n'&&ch=='\r')
                   continue;
               else
                   break;
           }
        context.setCursor(pos);
    }
}
