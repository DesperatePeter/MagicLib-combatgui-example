package com.dp.combatguiexample;

import com.dp.advancedgunnerycontrol.combatgui.GuiBase;
import com.dp.advancedgunnerycontrol.combatgui.GuiLayout;
import com.dp.advancedgunnerycontrol.combatgui.buttongroups.CreateSimpleButtons;
import com.dp.advancedgunnerycontrol.combatgui.buttongroups.DataButtonGroup;
import com.dp.advancedgunnerycontrol.combatgui.buttongroups.RadioButtonRefreshAction;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Arrays;

public class ExampleCombatGui extends GuiBase {
    public ExampleCombatGui(){

        // pass new GuiLayout(...) to customize the position/size/color of stuff
        // you can alternatively just call super() to use the default layout, but I wouldn't recommend that.
        super(
                new GuiLayout(0.05f, 0.8f, 100f, 20f, 0.5f,
                Color.WHITE,5f, 0.4f, 0.2f, 25f,
                "graphics/fonts/insignia15LTaa.fnt", 0.4f, 0.4f)
        );

        // add a simple button that prints a message to the console/log when clicked
        addButton(new ExampleButtonAction(), "Test1", "tooltip1", false);

        // a button-group with two buttons that print the list of active buttons to the console when clicked
        // by passing null/null as data/tooltips, the button data will be equal to the button name and there won't be any tooltips
        addButtonGroup(
                new ExampleButtonGroupAction(),
                new CreateSimpleButtons(
                        Arrays.asList("button1", "button2"),
                        null,
                        null
                ),
                null,
                "Example button group"
        );

        // another button-group. This time, the buttons have custom data (15, 27) and tooltips
        // by passing RadioButtonRefreshAction instead of null, we ensure that only one button can
        // be active at a time (in this case, triggeringButtonData should be used in the ButtonGroupAction)
        addButtonGroup(
                new ExampleButtonGroupAction(),
                new CreateSimpleButtons(
                        Arrays.asList("AnotherBtn", "Foo"),
                        Arrays.asList(15, 27),
                        Arrays.asList("example tooltip", "Bar")
                ),
                new RadioButtonRefreshAction(), "Another example button group"
        );
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
