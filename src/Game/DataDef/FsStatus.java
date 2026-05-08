package Game.DataDef;

public class FsStatus {
    public static int level;
    public static FreeSpinStatus freeSpinStatus;
    public double draw = -1;

    public FsStatus(int level,FreeSpinStatus freeSpinStatus){
        this.level = level;
        this.freeSpinStatus = freeSpinStatus;
    }

    public FsStatus() {

    }
    @Override
    public String toString() {

        return "FsStatus{" +
                "level=" + level +
                ", freeSpinStatus=" + freeSpinStatus +
                ", draw=" + draw +
                '}';
    }


}
//   public double draw;

//    public FsStatus(int i, FreeSpinStatus INIT) {
//
//    }