package Game.PayTable;
import Game.DataDef.Coordinate;
import Game.DataDef.LineWinData;
import Game.Symbols.Symbol;
import Game.Constant.GameConstant;

import java.util.ArrayList;
import java.util.List;


 public class PayTable extends PayTableCtrl {


    public PayTable(Symbol symbol, int payout, int count) {
        super(symbol, payout, count);
    }

//     public PayTable() {
//         super();
//     }

//     public static int[][] get(Symbol sym) {
//
//         return new int[0][];
//     }



     public static void evaluateLine(Symbol[][] window, int[] payLine, int id, LineWinData winData) {

        Symbol matchedSym = Symbol.INVALID;
        Token token;
        STATE state = STATE.INIT;
        STATE prev = STATE.INVALID_STATE;
        int totalCount = 0;
        int WSCount = 0;

         /*This code walks through a payLine from left to right and checks if the symbols
          form a winning combination while handling wild symbols correctly.
         */

        for (int x = 0; x < GameConstant.REEL_COUNT; x++) {
            int y = payLine[x];
            Symbol sym = window[x][y];

            if (sym == Symbol.WS) {
                token = Token.SYM_WD;
            } else if (state == STATE.INIT || state == STATE.WILD || state == STATE.WILD_2 || sym == matchedSym) {
                token = Token.SYM_N1;
            } else {
                token = Token.SYM_NX;
            }

            prev = state;
            state = nextState(token, state);

            if(state == STATE.COMPLETED || state == STATE.INVALID_STATE){
                break;
            }


            if ((state == STATE.WILD || state == STATE.WILD_2) && sym == Symbol.WS) {
                WSCount++;
            }

            if (state == STATE.MATCHING) {
                switch (prev) {
                    case INIT:
                        matchedSym = sym;
                        totalCount = 1;
                        break;
                    case WILD:
                    case WILD_2:
                        matchedSym = sym;
                        totalCount = WSCount + 1;
                        break;
                    case MATCHING:
                        matchedSym = sym;
                        totalCount++;
                        break;
                    default:
                        throw new IllegalStateException("reached MATCHING state from invalid state");

                }
            }
//            if (state == STATE.COMPLETED) break;
        }

        winData.symbol = matchedSym;
        winData.totalCount = totalCount;
        winData.WSCount = WSCount;


    }


    protected static STATE nextState(Token token, STATE state) {
        STATE next = STATE.INVALID_STATE;


        switch (state) {
            case INIT:
                next = token == Token.SYM_WD ? STATE.WILD : STATE.MATCHING;
                break;
            case WILD:
            case WILD_2:
                next = token == Token.SYM_WD ? STATE.WILD_2 : STATE.MATCHING;
                break;
            case MATCHING:
                next = token == Token.SYM_N1 ? STATE.MATCHING : STATE.INVALID_STATE;
                break;
            case COMPLETED:
                next = STATE.COMPLETED;
                break;
            case INVALID_STATE:
                next = STATE.INVALID_STATE;
                break;

        }
        return next;
    }

    public static List<Coordinate> getCoords(int[] payLine, int symCount) {
        List<Coordinate> coords = new ArrayList<>();
        for (int reel = 0; reel < symCount; reel++) {
            coords.add(new Coordinate(reel, payLine[reel]));


        }

        return coords;

    }
}
