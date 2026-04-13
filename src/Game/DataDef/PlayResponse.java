package Game.DataDef;

import Game.Bet.BetMode;

import java.util.ArrayList;
import java.util.List;

public class PlayResponse {


    public long betAmount;
    public long refBetAmount;
    public long refBetBase;
    public int featureMode;
    public boolean subGameTriggered;
    public static boolean ended = false;
    public long winAmount = 0;
    public long refWinAmount = 0;
    public static List<FsStatus> fsStatus = new ArrayList<>();
    public String action;
    public BaseSpin baseSpin;
    public List<FreeSpin> freeSpins = new ArrayList<>();
    public boolean maxWinTriggered = false;
    public long currencyMultiplier = -1;
    public Internal internal;

    public PlayResponse() {
    }

    // =======================
    // Getters & Setters
    // =======================

//    public void setWinAmount(long winAmount) {
//
//        this.winAmount = winAmount;
//    }
//
//    public long getWinAmount() {
//        return winAmount;
//    }
//
//    public void setBetAmount(long betAmount) {
//
//        this.betAmount = betAmount;
//    }
//
//    public void setRefBetBase(long refBetBase) {
//        this.refBetBase = refBetBase;
//    }
//
//    public long getRefBetBase() {
//        return refBetBase;
//    }
//
//    public void setRefBetAmount(long refBetAmount) {
//        this.refBetAmount = refBetAmount;
//    }
//
//    public void setCurrencyMultiplier(long currencyMultiplier) {
//
//        this.currencyMultiplier = currencyMultiplier;
//    }
//
//    public void setFeatureMode(BetMode featureMode) {
//        this.featureMode = featureMode;
//    }
//
//    public void setAction(String action) {
//        this.action = action;
//    }
//
//    public void setRefWinAmount(long refWinAmount) {
//        this.refWinAmount = refWinAmount;
//    }
//
//    public BaseSpin getBaseSpin() {
//        return baseSpin;
//    }
//
//    public void getBetAmount() {}
//
//    public void getFeatureMode() {}
//
//    public void getRefBetAmount() {}
//
//    public void getCurrencyMultiplier() {}
//
//
}