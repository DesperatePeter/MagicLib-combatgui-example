package com.dp.combatguiexample;

import org.magiclib.combatgui.*;
import org.jetbrains.annotations.NotNull;

// If you already have a BaseEveryFrameCombatPlugin in your mod, consider implementing the functionality in that
// plugin instead. Also consider writing your own GuiLauncher, as the SampleGuiLauncher provided in the combatgui lib
// is rather minimal.
public class ExampleCombatGuiLauncher extends SampleMagicCombatGuiLauncher{
    // define the hotkey to open/close the GUI by calling super and passing a character representing that hotkey
    // please make sure to use a hotkey that is not used by Starsector or other mods
    public ExampleCombatGuiLauncher(){
        super('+');
    }
    // override constructGui and return a new instance of your GUI class
    @Override
    public @NotNull MagicCombatGuiBase constructGui(){
        return new ExampleCombatGui();
    }
}
