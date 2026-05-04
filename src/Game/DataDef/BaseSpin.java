package Game.DataDef;

public class BaseSpin extends Spin {
    public final String spinType = "BaseSpin";


    public BaseSpin(){
        super();
    }


    public void printBaseSpin(){
        System.out.println("baseSpin"+ this.spinType);
    }
}
