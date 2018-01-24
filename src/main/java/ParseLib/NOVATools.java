package ParseLib;

import Const.NOVA_TYPE;
import Entity.Context;
import Entity.Value;
import Exceptions.ExceptValueException;
import Exceptions.InvalidValueException;
import Exceptions.NumberTooBigException;
import Exceptions.RootNotSingularException;

public class NOVATools {
    //return true if success
    //throws exceptions if failed
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
              {
                      result=parseNumber(context,value);
                     if(Double.POSITIVE_INFINITY==value.getNumber()||
                        Double.NEGATIVE_INFINITY==value.getNumber())
                         throw new NumberTooBigException();
              }
          }
          if(result)
            parseWhiteSpace(context);
          //invalid value
          else
              throw new InvalidValueException();
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
    static private boolean parseNumber(Context context,Value value){
          int pos = context.getCursor();
          String json = context.getJson();
          StringBuilder number = new StringBuilder();
          //robust
          if(json==null||json.length()==0)
              throw new InvalidValueException();
          if(json.charAt(pos)=='-')
          {
              number.append(json.charAt(pos++));
          }
          if(json.length()==pos||!isDigitExceptZero(json.charAt(pos),false))
                  throw new InvalidValueException();
          else
              number.append(json.charAt(pos++));
          //scan util we meet first character which is not located in 1-9
          while(pos<json.length()&&isDigitExceptZero(json.charAt(pos),true))
          {
              number.append(json.charAt(pos++));
          }
          if(pos==json.length()){
            finishParseNumber(context,value,pos,number);
            return true;
          }
          // first not digit character
          if('.'==(json.charAt(pos))){
              number.append(json.charAt(pos++));
              //after . should be at least one number
              if(json.length()==pos||!isDigitExceptZero(json.charAt(pos),false))
                  throw new InvalidValueException();
              else
                  number.append(json.charAt(pos++));
              //scan util we meet first character which is not located in 0-9
              while(pos<json.length()&&isDigitExceptZero(json.charAt(pos),false))
              {
                  number.append(json.charAt(pos++));
              }
              //bound check
              if(pos==json.length()){
                  finishParseNumber(context,value,pos,number);
                  return true;
              }
              //pos<json.length()
              if('E'==json.charAt(pos)||'e'==json.charAt(pos)){
                  number.append(json.charAt(pos++));
              }
              //after E or e should be number or '+' or '-'
              if(pos==json.length())
                  throw new InvalidValueException();
              //pos<json.length()
              if('+'==json.charAt(pos)||'-'==json.charAt(pos))
              {
                  number.append(json.charAt(pos++));
                  //after + or - should be at least one number
                  if(json.length()==pos||!isDigitExceptZero(json.charAt(pos),false))
                      throw new InvalidValueException();
              }
              while(pos<json.length()&&isDigitExceptZero(json.charAt(pos),false))
              {
                  number.append(json.charAt(pos++));
              }
              if(pos==json.length()){
                  finishParseNumber(context,value,pos,number);
                  return true;
              }
              else {
                  throw new InvalidValueException("invalid number");
              }

          }
          else  if('E'==json.charAt(pos)||'e'==json.charAt(pos)){
              number.append(json.charAt(pos++));
              if(pos==json.length())
                  throw new InvalidValueException();
              //pos<json.length()
              if('+'==json.charAt(pos)||'-'==json.charAt(pos))
              {
                  number.append(json.charAt(pos++));
                  //after + or - should be at least one number
                  if(json.length()==pos||!isDigitExceptZero(json.charAt(pos),false))
                      throw new InvalidValueException();
              }
              while(pos<json.length()&&isDigitExceptZero(json.charAt(pos),false))
              {
                  number.append(json.charAt(pos++));
              }
              if(pos==json.length()){
                  finishParseNumber(context,value,pos,number);
                  return true;
              }
                  throw new InvalidValueException("invalid number");

          }
          else
              throw new InvalidValueException(" not a valid number");


    }
    static private boolean isDigitExceptZero(char ch,boolean except){
        if(except)
            return ch>='1'&&ch<='9';
        else
            return ch>='0'&&ch<='9';
    }
    static  private void finishParseNumber(Context context ,Value value,int pos,StringBuilder number){
        value.setNova_type(NOVA_TYPE.NOVA_NUMBER);
        value.setNumber(Double.valueOf(number.toString()));
        context.setCursor(pos);
    }
}
