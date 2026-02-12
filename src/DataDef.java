import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class DataDef {


    public class payTbl {
        public Symbol symbol;
        public int pay;
        public int n;

    }


    public class Coordinate {
        public int x;
        public int y;
    }


    public class SymCoords {
        public int x;
        public int y;
        public Symbol symbol;
    }

    public class lineWinData {
        public Symbol symbol;
        public int totalCount;
        public int WSCount;
    }


    //List<SymCoords> symCoordsVec = new ArrayList<>();



    public class winning{
        public String type = "Normal";
        public int winAmount = 0;
        public int refWinAmount = 0;
        public int payLine = -1;
        public int symCount = 0;
        public int multiplier = 1;
        public String dir;
        public Symbol symbol;
        public List<Coordinate> Coords = new ArrayList<>();

    }

    public class Spin{
        public String reelset;
        public long winAmount = 0;
        public long refWinAmount = 0;
        public long winSoFar = 0;
        public long refWinSoFar = 0;
        public Symbol[][] symbols = new Symbol[gameConstant.REEL_COUNT][gameConstant.GRID_HEIGHT];
        public List<winning> winnings = new ArrayList<>();
        public int[] stops = new int[gameConstant.REEL_COUNT];
        public List<SymCoords> WSSyms = new ArrayList<>();
        public boolean maxWinTriggered = false;
    }

    public class BaseSpin extends Spin{
        public String spinType = "baseSpin";

    }

    public class SpecialSymWins{
        public Symbol symbol;
        public List<winning> winnings = new ArrayList<>();
        public Symbol[][] symbols = new Symbol[gameConstant.REEL_COUNT][gameConstant.GRID_HEIGHT];
        public long winAmount = 0;
        public long refWinAmount = 0;
        public long winSoFar = 0;
        public long refWinSoFar = 0;
        public boolean maxWinTriggered = false;


    }

    public class Internal{
        public List<RandRecord> rHistory =  new ArrayList<>();
    }

    public class FreeSpin extends Spin{
        public String spinType = "freeSpin";
        public int index = 0;
        public List<Symbol> SpecialSymbols = new ArrayList<>();
        public String guaranteedWinSym;
        public long baseWinAmount = 0;
        public long refBaseWinAmount = 0;
        public long ssWinAmount = 0;
        public long refSsWinAmount = 0;
        public List<SpecialSymWins> ssWinnings = new ArrayList<>();

    }

    public enum FS_STATUS {
        INIT,
        SUCCEEDED,
        FAILED,
        COLLECTED,

    }

    public class FS_StatusMap {

        public static final Map<String, FS_STATUS> FS_STATUS_MAP =
                Map.of(
                        "INIT", FS_STATUS.INIT,
                        "SUCCEEDED", FS_STATUS.SUCCEEDED,
                        "FAILED", FS_STATUS.FAILED,
                        "COLLECTED", FS_STATUS.COLLECTED
                );

    }

    public class Fs_Status{
        public int level;
        public FS_STATUS status;
        public float Draw = -1;
    }

    public class PlayOption{
        public int featureMode;
        public long betAmount;
        public long refBetAmount;
        public long currencyMultiplier = -1;
        public String action;
        public boolean buyFeature;
    }

    public class playResponse{
        public long betAmount;
        public long refBetAmount;
        public long refBetBase;
        public int featureMode;
        public boolean subGameTriggered = false;
        public boolean ended = false;
        public long winAmount = 0;
        public long refWinAmount = 0;
        public List<Fs_Status> fsStatus = new ArrayList<>();
        public String action;
        public BaseSpin baseSpin = new BaseSpin();
        public List<FreeSpin> freeSpins = new ArrayList<>();
        public boolean maxWinTriggered = false;
        public long currencyMultiplier = -1;
        public Internal internal = new Internal();


    }

    public class NextPlay{
        public boolean gamble;
    }






}
