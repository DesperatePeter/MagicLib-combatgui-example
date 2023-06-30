package com.dp.abuseascampagingui;

import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.GameState;
import com.fs.starfarer.api.Global;
import org.lwjgl.input.Keyboard;

public class UiShower implements EveryFrameScript {
    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public boolean runWhilePaused() {
        return true;
    }

    @Override
    public void advance(float amount) {
        if (Global.getSector().isInNewGameAdvance() || Global.getSector().getCampaignUI().isShowingDialog()
                || Global.getCurrentState() == GameState.TITLE
        ) return;
        if(Keyboard.getEventCharacter() == '+'){
            Global.getSector().getCampaignUI().showInteractionDialog(new CampaignUi(), null);
        }
    }
}
