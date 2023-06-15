# Combat GUI example

![Showcase](imgs/combatGuiLibUsageExample.gif)

A simple example showcasing how to use the combat GUI lib.

The relevant files are everything in src and data/config/settings.json. The rest is pretty much just clutter from
the mod template used to create this.

This repo obviously needs some cleanup...

To see what the example GUI does, load this mod, enter combat and press the '+'-Key.

## Step-by-step guide

1. Include MagicLib as a dependency in your mod_info.json
   1. cf. <https://fractalsoftworks.com/forum/index.php?topic=25868.0> for details
2. Create a new class that extends org.magiclib.kotlin.combatgui.GuiBase
   1. Override the method getTitleString with a title to display when the GUI is opened
   2. (optional) Override getMessageString if you want to display general messages. Feel free to put logic in here that changes the message based on context
   3. In the constructor, call super and pass it a new GuiLayout to define fonts, colors and positions
   4. Afterwards, call the addButton and addButtonGroup methods in the constructor to create actual buttons
      1. you will need to define actions by implementing the ButtonAction and ButtonGroupAction interfaces
      2. For button groups, you will also need a CreateButtonsAction (or use the CreateSimpleButtons class)
      3. You can also define a RefreshAction, or simply pass null
3. Create a GUI-Launcher class that creates a new GUI object when a certain condition is met and deletes it again afterwards
   1. If you simply want to open/close the GUI when a hotkey is pressed, you can extend the SampleGuiLauncher class
   2. If your mod already has a EveryFramePlugin, feel free to implement the logic there instead
4. Register the GUI-launcher as a plugin to load in data/config/settings.json (or mod_info.json)
5. Additional documentation in the MagicLib-Repo will follow