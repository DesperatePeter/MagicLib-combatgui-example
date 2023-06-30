package com.dp.abuseascampagingui;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;

public class UiBasePlugin extends BaseModPlugin {
    @Override
    public void onGameLoad(boolean newGame) {
        Global.getSector().addTransientScript(new UiShower());
    }
}
