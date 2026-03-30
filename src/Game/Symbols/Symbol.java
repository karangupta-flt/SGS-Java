package Game.Symbols;

import java.util.Map;
import java.util.HashMap;

public enum Symbol {

    WS(0, "WS"),
    H1(1, "H1"),
    H2(2, "H2"),
    H3(3, "H3"),
    H4(4, "H4"),
    L5(5, "L5"),
    L6(6, "L6"),
    L7(7, "L7"),
    L8(8, "L8"),
    L9(9, "L9"),
    INVALID(10, "INVALID");



    private final int id;
    private final String code;

    private static final Map<String, Symbol> SYMBOL_MAP = new HashMap<>();

    static {
        for (Symbol s : values()) {
            SYMBOL_MAP.put(s.code, s);
        }
    }

    Symbol(int id, String code) {
        this.id = id;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public static Symbol fromString(String value) {
        return SYMBOL_MAP.getOrDefault(value, INVALID);
    }

    public int size() {
        return 0;
    }

    public Symbol get(int index) {
        return null;
    }
}