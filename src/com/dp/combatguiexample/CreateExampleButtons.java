package com.dp.combatguiexample;

import com.dp.advancedgunnerycontrol.combatgui.buttongroups.CreateButtonsAction;
import com.dp.advancedgunnerycontrol.combatgui.buttongroups.DataButtonGroup;
import org.jetbrains.annotations.NotNull;

public class CreateExampleButtons implements CreateButtonsAction {
    @Override
    public void createButtons(@NotNull DataButtonGroup group) {
        group.addButton("AnotherButton", 27, "custom tooltip for AnotherButton", false);
        group.addButton("Foo", 15f, "bar", false);
    }
}
