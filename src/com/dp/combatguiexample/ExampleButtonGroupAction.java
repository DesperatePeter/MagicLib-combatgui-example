package com.dp.combatguiexample;

import com.dp.advancedgunnerycontrol.combatgui.buttongroups.ButtonGroupAction;
import com.fs.starfarer.api.Global;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ExampleButtonGroupAction implements ButtonGroupAction {
    @Override
    public void execute(@NotNull List<?> data, @Nullable Object triggeringButtonData) {
        Global.getLogger(this.getClass()).info("A button in the button group was clicked. Button group data:");
        Global.getLogger(this.getClass()).info(data);
        if(null != triggeringButtonData){ // triggeringButtonData will be null if the button was deselected
            Global.getLogger(this.getClass()).info("Triggering button data:");
            Global.getLogger(this.getClass()).info(triggeringButtonData);
        }
    }
}
