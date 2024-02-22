package com.dp.combatguiexample;

import org.magiclib.combatgui.buttons.MagicCombatButtonAction;
import com.fs.starfarer.api.Global;

public class ExampleButtonAction implements MagicCombatButtonAction {
    @Override
    public void execute() {
        Global.getLogger(this.getClass()).info("Button was clicked. This message should show up in starsector.log");
    }
}
