package Entity;

import Const.NOVA_TYPE;

public class Value {
    public NOVA_TYPE getNova_type() {
        return nova_type;
    }

    public void setNova_type(NOVA_TYPE nova_type) {
        this.nova_type = nova_type;
    }

    private NOVA_TYPE nova_type ;

    //this method is accessable only when type is number
    public double getNumber() {
        assert (NOVA_TYPE.NOVA_NUMBER.equals(this.getNova_type()));
        return number;
    }

    public void setNumber(double number) {
        assert (NOVA_TYPE.NOVA_NUMBER.equals(this.getNova_type()));
        this.number = number;
    }

    // this value valid only when nova_type equals number
    double number ;
}
