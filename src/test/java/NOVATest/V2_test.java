package NOVATest;

import Const.Math;
import Entity.Context;
import Entity.ContextFactory;
import Entity.Value;
import ParseLib.NOVATools;
import org.junit.Test;
import static org.junit.Assert.*;
public class V2_test {
    Value value = new Value();

    @Test
    public void test_number_success() {
        //first 0
        Context context = ContextFactory.createContext("0");
        NOVATools.NovaParse(context, value);
        assertEquals(0.0, value.getNumber(), Math.EPSILON);
        //-0
        context = ContextFactory.createContext("-0");
        NOVATools.NovaParse(context, value);
        assertEquals(0.0, value.getNumber(), Math.EPSILON);
        //-0.0
        context = ContextFactory.createContext("-0.0");
        NOVATools.NovaParse(context, value);
        assertEquals(0.0, value.getNumber(), Math.EPSILON);
        //-1.0
        context = ContextFactory.createContext("-1.0");
        NOVATools.NovaParse(context, value);
        assertEquals(-1.0, value.getNumber(), Math.EPSILON);
        //1.0
        context = ContextFactory.createContext("1.0");
        NOVATools.NovaParse(context, value);
        assertEquals(1.0, value.getNumber(), Math.EPSILON);
        //-1.5
        context = ContextFactory.createContext("-1.5");
        NOVATools.NovaParse(context, value);
        assertEquals(-1.5, value.getNumber(), Math.EPSILON);
        //1.5
        context = ContextFactory.createContext("1.5");
        NOVATools.NovaParse(context, value);
        assertEquals(1.5, value.getNumber(), Math.EPSILON);
        //3.1415692
        context = ContextFactory.createContext("3.1415692");
        NOVATools.NovaParse(context, value);
        assertEquals(3.1415692, value.getNumber(), Math.EPSILON);
        //1E10
        context = ContextFactory.createContext("1E10");
        NOVATools.NovaParse(context, value);
        assertEquals(1E10, value.getNumber(), Math.EPSILON);
        //1e10
        context = ContextFactory.createContext("1e10");
        NOVATools.NovaParse(context, value);
        assertEquals(1e10, value.getNumber(), Math.EPSILON);
        //1E+10
        context = ContextFactory.createContext("1E+10");
        NOVATools.NovaParse(context, value);
        assertEquals(1E+10, value.getNumber(), Math.EPSILON);
        //1e+10
        context = ContextFactory.createContext("1e+10");
        NOVATools.NovaParse(context, value);
        assertEquals(1e+10, value.getNumber(), Math.EPSILON);
        //1E-10
        context = ContextFactory.createContext("1E-10");
        NOVATools.NovaParse(context, value);
        assertEquals(1E-10, value.getNumber(), Math.EPSILON);
        //1e-10
        context = ContextFactory.createContext("1e-10");
        NOVATools.NovaParse(context, value);
        assertEquals(1e-10, value.getNumber(), Math.EPSILON);
        //-1E10
        context = ContextFactory.createContext("-1E10");
        NOVATools.NovaParse(context, value);
        assertEquals(-1E10, value.getNumber(), Math.EPSILON);
        //-1e10
        context = ContextFactory.createContext("-1e10");
        NOVATools.NovaParse(context, value);
        assertEquals(-1e10, value.getNumber(), Math.EPSILON);
        //-1E+10
        context = ContextFactory.createContext("-1E+10");
        NOVATools.NovaParse(context, value);
        assertEquals(-1E+10, value.getNumber(), Math.EPSILON);
        //-1E-10
        context = ContextFactory.createContext("-1E-10");
        NOVATools.NovaParse(context, value);
        assertEquals(-1E-10, value.getNumber(), Math.EPSILON);
        // the smallest number > 1
        //1.0000000000000002
        context = ContextFactory.createContext("1.0000000000000002");
        NOVATools.NovaParse(context, value);
        assertEquals(1.0000000000000002, value.getNumber(), Math.EPSILON);
        // 4.9406564584124654e-324, "4.9406564584124654e-324"
        context = ContextFactory.createContext("4.9406564584124654e-324");
        NOVATools.NovaParse(context, value);
        assertEquals(4.9406564584124654e-324, value.getNumber(), Math.EPSILON);
        //-4.9406564584124654e-324, "-4.9406564584124654e-324"
        context = ContextFactory.createContext("-4.9406564584124654e-324");
        NOVATools.NovaParse(context, value);
        assertEquals(-4.9406564584124654e-324, value.getNumber(), Math.EPSILON);
        //1.7976931348623157e+308, "1.7976931348623157e+308"
        //max double
        context = ContextFactory.createContext("1.7976931348623157e+308");
        NOVATools.NovaParse(context, value);
        assertEquals(1.7976931348623157e+308, value.getNumber(), Math.EPSILON);
        //-1.7976931348623157e+308, "-1.7976931348623157e+308"
        context = ContextFactory.createContext("-1.7976931348623157e+308");
        NOVATools.NovaParse(context, value);
        assertEquals(-1.7976931348623157e+308, value.getNumber(), Math.EPSILON);

    }
    @Test(expected = Exceptions.InvalidValueException.class)
    public void test_number_invalid_0(){
        Context context = ContextFactory.createContext("+0");
        NOVATools.NovaParse(context,value);

    }
    @Test(expected = Exceptions.InvalidValueException.class)
    public void test_number_invalid_1(){
        Context context = ContextFactory.createContext("+1");
        NOVATools.NovaParse(context,value);

    }
    // at least one number before .
    @Test(expected = Exceptions.InvalidValueException.class)
    public void test_number_invalid_2(){
        Context context = ContextFactory.createContext(".123");
        NOVATools.NovaParse(context,value);

    }
    // at least one number after .
    @Test(expected = Exceptions.InvalidValueException.class)
    public void test_number_invalid_3(){
        Context context = ContextFactory.createContext("12.");
        NOVATools.NovaParse(context,value);

    }
    //NAN
    @Test(expected = Exceptions.InvalidValueException.class)
    public void test_number_invalid_4(){
        Context context = ContextFactory.createContext("NAN");
        NOVATools.NovaParse(context,value);

    }
    //nan
    @Test(expected = Exceptions.InvalidValueException.class)
    public void test_number_invalid_5(){
        Context context = ContextFactory.createContext("nan");
        NOVATools.NovaParse(context,value);

    }
    //INF
    @Test(expected = Exceptions.InvalidValueException.class)
    public void test_number_invalid_6(){
        Context context = ContextFactory.createContext("INF");
        NOVATools.NovaParse(context,value);

    }
    //inf
    @Test(expected = Exceptions.InvalidValueException.class)
    public void test_number_invalid_7(){
        Context context = ContextFactory.createContext("inf");
        NOVATools.NovaParse(context,value);

    }
    //13e2e12
    @Test(expected = Exceptions.InvalidValueException.class)
    public void test_number_invalid_8(){
        Context context = ContextFactory.createContext("13e2E1");
        NOVATools.NovaParse(context,value);

    }
    //number too big
    @Test(expected = Exceptions.NumberTooBigException.class)
    public void test_number_too_big_0(){
        Context context = ContextFactory.createContext("1e309");
        NOVATools.NovaParse(context,value);

    }
    @Test(expected = Exceptions.NumberTooBigException.class)
    public void test_number_too_big_1(){
        Context context = ContextFactory.createContext("-1e309");
        NOVATools.NovaParse(context,value);

    }



}
