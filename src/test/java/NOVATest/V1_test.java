package NOVATest;

import Const.NOVA_TYPE;
import Entity.Context;
import Entity.ContextFactory;
import Entity.Value;
import ParseLib.NOVATools;
import org.junit.Test;
import static org.junit.Assert.*;


public class V1_test {
    Value value = new Value();
    @Test
    public void test_parse_null(){
         Context context = ContextFactory.createContext("null");
         assertEquals(true,NOVATools.NovaParse(context,value));
         assertEquals(NOVA_TYPE.NOVA_NULL,value.getNova_type());
    }
    @Test
    public void test_parse_true(){
        Context context = ContextFactory.createContext("true");
        assertEquals(true,NOVATools.NovaParse(context,value));
        assertEquals(NOVA_TYPE.NOVA_TRUE,value.getNova_type());
    }
    @Test
    public void test_parse_false(){
        Context context = ContextFactory.createContext("false");
        assertEquals(true,NOVATools.NovaParse(context,value));
        assertEquals(NOVA_TYPE.NOVA_FALSE,value.getNova_type());
    }
    @Test(expected = Exceptions.ExceptValueException.class)
    public void test_parse_except_value(){
        Context context = ContextFactory.createContext("   ");
        value.setNova_type(NOVA_TYPE.NOVA_FALSE);
        NOVATools.NovaParse(context,value);

    }
    @Test(expected = Exceptions.InvalidValueException.class)
    public void test_parse_invalid_value_1(){
        Context context = ContextFactory.createContext("?");
        value.setNova_type(NOVA_TYPE.NOVA_FALSE);
        NOVATools.NovaParse(context,value);
    }
    @Test(expected = Exceptions.InvalidValueException.class)
    public void test_parse_invalid_value_2(){
        Context context = ContextFactory.createContext("nul");
        value.setNova_type(NOVA_TYPE.NOVA_FALSE);
        NOVATools.NovaParse(context,value);
    }
    @Test(expected = Exceptions.RootNotSingularException.class)
    public void test_parse_root_not_singular(){
        Context context = ContextFactory.createContext("null x");
        value.setNova_type(NOVA_TYPE.NOVA_FALSE);
        NOVATools.NovaParse(context,value);
    }

}
