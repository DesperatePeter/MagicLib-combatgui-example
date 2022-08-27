package com.dp.combatguiexample;

import com.dp.advancedgunnerycontrol.combatgui.GuiBase;
import com.dp.advancedgunnerycontrol.combatgui.buttongroups.CreateSimpleButtons;
import com.dp.advancedgunnerycontrol.combatgui.buttongroups.RadioButtonRefreshAction;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class ExampleCombatGui extends GuiBase {
    public ExampleCombatGui(){
        // pass new GuiLayout(...) to customize the position/size of stuff
        super();
        // add a simple button that prints a message to the console/log when clicked
        addButton(new ExampleButtonAction(), "Test1", "tooltip1", false);
        // a button group with two buttons that print the list of active buttons to the console when clicked
        // for a real GUI, consider implementing your own creation action to be able to define custom data and tooltips
        addButtonGroup(new ExampleButtonGroupAction(), new CreateSimpleButtons(Arrays.asList("button1", "button2")),
                null, "Example button group");
        addButtonGroup(new ExampleButtonGroupAction(), new CreateExampleButtons(),
                new RadioButtonRefreshAction(), "Another example button group");
    }

    @Nullable
    @Override
    protected String getTitleString() {
        return "This is an example title";
    }

    @Nullable
    @Override
    protected String getMessageString() {
        return "This is a sample message";
    }
}
