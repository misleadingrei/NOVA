package NOVATest;

import Const.NOVA_TYPE;
import Entity.Context;
import Entity.Value;
import ParseLib.NOVATools;
import org.junit.Test;
import static org.junit.Assert.*;


public class V1_test {
    Value value = new Value();
    @Test
    public  void test_parse_null(){
         Context context = new Context("null  ",0);
         assertEquals(true,NOVATools.NovaParse(context,value));
         assertEquals(NOVA_TYPE.NOVA_NULL,value.getNova_type());


    }
    @Test
    public  void test_parse_v1(){

    }
}
