package Game.PayLines;
import Game.Constant.GameConstant;

public class PayLines {
    public static final int NUM_PAYLINES = 10;

    public int[] payLine = new int[GameConstant.REEL_COUNT];
    public int[][] payLines = new int[NUM_PAYLINES][GameConstant.REEL_COUNT];

    public static final int[][] PAY_LINES_LIST = {
            { 1, 1, 1, 1, 1 },
            { 0, 0, 0, 0, 0 },
            { 2, 2, 2, 2, 2 },
            { 0, 1, 2, 1, 0 },
            { 2, 1, 0, 1, 2 },
            { 0, 0, 1, 0, 0 },
            { 2, 2, 1, 2, 2 },
            { 1, 2, 2, 2, 1 },
            { 1, 0, 0, 0, 1 },
            { 1, 1, 0, 1, 1 }
    };

    public static final int[][] PAY_LINES = {
            { 1, 1, 1, 1, 1 },
            { 0, 0, 0, 0, 0 },
            { 2, 2, 2, 2, 2 },
            { 0, 1, 2, 1, 0 },
            { 2, 1, 0, 1, 2 },
            { 0, 0, 1, 0, 0 },
            { 2, 2, 1, 2, 2 },
            { 1, 2, 2, 2, 1 },
            { 1, 0, 0, 0, 1 },
            { 1, 1, 0, 1, 1 }
    };

}


