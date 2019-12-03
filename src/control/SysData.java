package control;

import java.util.ArrayList;

public class SysData {

    private static SysData single_instance = null;


    public static SysData getInstance() {
        if (single_instance == null)
            single_instance = new SysData();

        return single_instance;
    }
}
