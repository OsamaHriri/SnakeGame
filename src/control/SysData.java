package control;

import model.Game;
import model.Question;

import java.util.ArrayList;

public class SysData {

    private static SysData single_instance = null;
    private ArrayList<Game> history;
    private ArrayList<Question> questions;

    public static SysData getInstance() {
        if (single_instance == null)
            single_instance = new SysData();

        return single_instance;
    }
}
