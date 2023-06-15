package com.dp.combatguiexample;

import org.magiclib.kotlin.combatgui.buttons.ButtonAction;
import com.fs.starfarer.api.Global;

public class ExampleButtonAction implements ButtonAction {
    @Override
    public void execute() {
        Global.getLogger(this.getClass()).info("Button was clicked. This message should show up in starsector.log");
    }
}
