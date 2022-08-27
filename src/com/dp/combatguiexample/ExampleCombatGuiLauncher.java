package com.dp.combatguiexample;

import com.dp.magiccombatgui.*;
import org.jetbrains.annotations.NotNull;

// If you already have a BaseEveryFrameCombatPlugin in your mod, consider implementing the functionality in that
// plugin instead. Also consider writing your own GuiLauncher, as the SampleGuiLauncher provided in the combatgui lib
// is rather minimal.
public class ExampleCombatGuiLauncher extends SampleGuiLauncher{
    public ExampleCombatGuiLauncher(){
        super('+');
    }
    @Override
    public @NotNull GuiBase constructGui(){
        return new ExampleCombatGui();
    }
}
