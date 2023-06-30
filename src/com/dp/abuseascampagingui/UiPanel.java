package com.dp.abuseascampagingui;

import com.fs.starfarer.api.campaign.CustomUIPanelPlugin;
import com.fs.starfarer.api.input.InputEventAPI;
import com.fs.starfarer.api.ui.PositionAPI;
import org.magiclib.combatgui.GuiBase;

import java.util.List;

public class UiPanel implements CustomUIPanelPlugin {

    UiPanel(GuiBase gui){
        this.gui = gui;
    }
    private final GuiBase gui;
    @Override
    public void positionChanged(PositionAPI position) {

    }

    @Override
    public void renderBelow(float alphaMult) {

    }

    @Override
    public void render(float alphaMult) {
        gui.render();
    }

    @Override
    public void advance(float amount) {
        gui.advance();
    }

    @Override
    public void processInput(List<InputEventAPI> events) {

    }
}
