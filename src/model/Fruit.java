package model;

import control.SysData;
import javafx.scene.paint.Color;

public class Fruit extends GameObject implements BoardObject {

    /**
     * Color of normal fruit
     */
    public static final Color FRUIT_COLOR = Color.RED;
    FruitType type;

    public Fruit(int x, int y) {
        super(x, y);
    }

    @Override
    public void addPoints() {
        switch (type) {
            case APPLE:
                SysData.game.addToScore(Consts.ApplePointsAddition);
            case PEAR:
                SysData.game.addToScore(Consts.PearPointsAddtion);
            case BANNANA:
                SysData.game.addToScore(Consts.BannanaPointsAddition);
        }
    }

    @Override
    public void removePoints() {

    }


    @Override
    public void addLength() {
        switch (type) {
            case APPLE:
                SysData.game.addToSnakeLength(Consts.AppleLengthAddition);
            case PEAR:
                SysData.game.addToSnakeLength(Consts.PearLengthAddition);
            case BANNANA:
                SysData.game.addToSnakeLength(Consts.BannanaLengthAddtion);
        }
    }

    @Override
    public void addSouls() {
    }
}
