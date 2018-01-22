package Const;

//this package is used for NOVA(LEPTON JSON BY JAVA)'s TYPE
//same as json_type
//here uses true and false to diff boolean
public enum NOVA_TYPE {
    NOVA_NULL(0,"json_null"),NOVA_TRUE(1,"json_true"),NOVA_FALSE(2,"json_false"),NOVA_NUMBER(3,"json_number")
    ,NOVA_STRING(4,"json_string"),NOVA_ARRAY(5,"json_array"),NOVA_OBJECT(6,"json_object");

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public String getDesc() {
        return desc;
    }

    private int  index ;
    private String  desc ;
    private NOVA_TYPE(int index ,String desc){
        this.index=index;
        this.desc=desc;
    }
}
