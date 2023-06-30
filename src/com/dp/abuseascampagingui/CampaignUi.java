package com.dp.abuseascampagingui;

import com.dp.combatguiexample.ExampleCombatGui;
import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.InteractionDialogPlugin;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.combat.EngagementResultAPI;
import org.lwjgl.input.Keyboard;

import java.util.Map;
import java.util.Objects;

public class CampaignUi implements InteractionDialogPlugin {

    private InteractionDialogAPI dialog;
    @Override
    public void init(InteractionDialogAPI dialog) {
        if(dialog == null) return;
        this.dialog = dialog;
        dialog.getVisualPanel().showCustomPanel(1000f, 1000f, new UiPanel(new ExampleCombatGui()));
        dialog.getOptionPanel().addOption("Leave", "Leave");
        dialog.getOptionPanel().setShortcut("Leave", Keyboard.KEY_ESCAPE, false, false, false, false);
    }

    @Override
    public void optionSelected(String optionText, Object optionData) {
        if(Objects.equals(optionText, "Leave")){
            dialog.dismiss();
        }
    }

    @Override
    public void optionMousedOver(String optionText, Object optionData) {}

    @Override
    public void advance(float amount) {}

    @Override
    public void backFromEngagement(EngagementResultAPI battleResult) {}

    @Override
    public Object getContext() {
        return null;
    }

    @Override
    public Map<String, MemoryAPI> getMemoryMap() {
        return null;
    }
}
