package Game.DataDef;

import java.util.HashMap;
import java.util.Map;



public enum FreeSpinStatus {
    INIT,
    SUCCEEDED,
    FAILED,
    COLLECTED;

    private static final Map<String, FreeSpinStatus> STATUS_MAP = new HashMap<>();


    static {
        for (FreeSpinStatus status : values()) {
            STATUS_MAP.put(status.name(), status);
        }
    }

    public static FreeSpinStatus fromString(String value) {
        return STATUS_MAP.get(value);
    }

}
