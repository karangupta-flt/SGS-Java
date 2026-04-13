package Game.PayTable;

import Game.DataDef.Coordinate;
import Game.DataDef.LineWinData;
import Game.DataDef.PayTbl;
import Game.PayLines.PayLines;
import Game.Symbols.Symbol;

import java.util.List;

 public class PayTableCtrl {


     public Symbol symbol;
     public int payout;
     public int count;
     int[] payTblItem = new int[4];
     int[][] payTableArray = new int[10][4];


     public PayTableCtrl(Symbol symbol, int payout, int count) {
         this.symbol = symbol;
         this.payout = payout;
         this.count = count;
     }



     public static final int[][] PAY_TABLE = {
             /* sym count =  2   3    4     5 */
             /* WS */     {10, 100, 1000, 5000},
             /* H1 */     {10, 100, 1000, 5000},
             /* H2 */     {5,   30, 300, 1600},
             /* H3 */     {5,   20, 80,  600},
             /* H4 */     {5,   20, 80,  600},
             /* L5 */     {0,   5,  30,  120},
             /* L6 */     {0,   5,  30,  120},
             /* L7 */     {0,   5,  20,  80},
             /* L8 */     {0,   5,  20,  80},
             /* L9 */     {0,   5,  20,  80}
     };

     public static final List<PayTbl> PAY_TABLE_CONF = List.of(
             new PayTbl(Symbol.WS, 10, 2),
             new PayTbl(Symbol.WS, 100, 3),
             new PayTbl(Symbol.WS, 1000, 4),
             new PayTbl(Symbol.WS, 5000, 5),

             new PayTbl(Symbol.H1, 10, 2),
             new PayTbl(Symbol.H1, 100, 3),
             new PayTbl(Symbol.H1, 1000, 4),
             new PayTbl(Symbol.H1, 5000, 5),

             new PayTbl(Symbol.H2, 5, 2),
             new PayTbl(Symbol.H2, 30, 3),
             new PayTbl(Symbol.H2, 300, 4),
             new PayTbl(Symbol.H2, 1600, 5),

             new PayTbl(Symbol.H3, 5, 2),
             new PayTbl(Symbol.H3, 20, 3),
             new PayTbl(Symbol.H3, 80, 4),
             new PayTbl(Symbol.H3, 600, 5),

             new PayTbl(Symbol.H4, 5, 2),
             new PayTbl(Symbol.H4, 20, 3),
             new PayTbl(Symbol.H4, 80, 4),
             new PayTbl(Symbol.H4, 600, 5),

             new PayTbl(Symbol.L5, 0, 2),
             new PayTbl(Symbol.L5, 5, 3),
             new PayTbl(Symbol.L5, 30, 4),
             new PayTbl(Symbol.L5, 120, 5),

             new PayTbl(Symbol.L6, 0, 2),
             new PayTbl(Symbol.L6, 5, 3),
             new PayTbl(Symbol.L6, 30, 4),
             new PayTbl(Symbol.L6, 120, 5),

             new PayTbl(Symbol.L7, 0, 2),
             new PayTbl(Symbol.L7, 5, 3),
             new PayTbl(Symbol.L7, 20, 4),
             new PayTbl(Symbol.L7, 80, 5),

             new PayTbl(Symbol.L8, 0, 2),
             new PayTbl(Symbol.L8, 5, 3),
             new PayTbl(Symbol.L8, 20, 4),
             new PayTbl(Symbol.L8, 80, 5),

             new PayTbl(Symbol.L9, 0, 2),
             new PayTbl(Symbol.L9, 5, 3),
             new PayTbl(Symbol.L9, 20, 4),
             new PayTbl(Symbol.L9, 80, 5)
     );


     public static final List<String> STATE_NAMES = List.of(
             "INIT",
             "WILD",
             "WILD_2",
             "MATCHING",
             "COMPLETED",
             "INVALID_STATE"
     );


     public enum STATE {
         INIT,
         WILD,
         WILD_2,
         MATCHING,
         COMPLETED,
         INVALID_STATE,
     }


     public enum Token {
         SYM_WD,
         SYM_N1,
         SYM_NX,
         SYM_END,
     }



     public static int[][] lines() {
         return PayLines.PAY_LINES;
     }


     public static void evaluateLine(Symbol[][] window, int[] lines, int id, LineWinData winData) {
     }

     public static List<Coordinate> getCoords(int[] lines, int symCount) {
         return null;
     }



     protected static STATE nextState(Token token, STATE state) {
         return null;

     }
 }