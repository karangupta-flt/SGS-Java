package Game.GameConfig;

import Game.Bet.BetConfig;
import Game.DataDef.PayTbl;


import java.util.List;

import static Game.Bet.BetConfig.ALL_BETS_CONFIG;
import static Game.Bet.BetConfig.CREDITS_PER_BET;
import static Game.Constant.GameConstant.MAX_WIN_CAP;
import static Game.PayLines.PayLines.PAY_LINES_LIST;
import static Game.PayTable.PayTableCtrl.PAY_TABLE_CONF;


public class GameConfig {


    public int creditPerBet = CREDITS_PER_BET;
    public int maxWinCap = MAX_WIN_CAP;
    public List<BetConfig> betConfig = ALL_BETS_CONFIG ;
    public List<PayTbl> payTable =  PAY_TABLE_CONF;
    public String gameCore = "bdg3479";
    public String gameStudio = "BigDaddy";;
    public String gameVersion = "1.0";;
    public String gameName = "legacyOfDesperado";
    public String variant = "variant";
    public String parSheetVersion = "v2.2";
    public int[][] payLines = PAY_LINES_LIST ;
    public Game.ReelSets.ReelSets ReelSets;

    public GameConfig(){}




}

