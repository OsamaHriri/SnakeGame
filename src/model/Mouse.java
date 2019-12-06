package model;

import control.SysData;

public class Mouse extends GameObject implements BoardObject {
    public Mouse(int x, int y) {
        super(x, y);
    }

    @Override
    public void addPoints() {
        SysData.game.addToScore(Consts.MousePointsAddition);
    }

    @Override
    public void removePoints() {

    }

    @Override
    public void addLength() {
        SysData.game.addToSnakeLength(Consts.MouseLengthAddition);
    }

    @Override
    public void addSouls() {
        SysData.game.addToSouls(Consts.MouseSoulsAddition);
    }
}
