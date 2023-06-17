# MagicLib Combat-GUI example

![Showcase](imgs/combatGuiLibUsageExample.gif)

This is a showcase of how to use the MagicLib combatgui module.

Note that that module is written in Kotlin, but can be imported and used in Java code just like regular Java modules.

## Why use a combat GUI?

Most mods probably won't need a combat GUI. But if you ever want to present multiple options to the player during combat,
a GUI is a nice way to do so.
Maybe you want to implement a cool ship system, where, when piloted by the player, the player can choose between multiple modes.
If there are only two modes, toggling between them by activating the system is probably fine, but if there are many options,
a GUI is probably a more elegant solution.
Maybe you want to create a mod, where the player can issue more advanced commands to the fleet or to fighters
from the flagship (please do! Those would be cool! :P)? A GUI would probably be easier to use than a ton of keyboard
shortcuts.

## Introduction

The relevant files are everything in src and data/config/settings.json. The rest is just here to make the build, repo and mod work.
If you already have a mod set up and want to use the combatgui in that mod, you can ignore all that stuff.
If you don't yet have a mod set up, this is probably not the right place to look for learning how to do so, so you should probably
also ignore all that stuff =D

This whole folder is loadable as a Starsector mod. If you want to see live how a combat GUI built using the combatgui module
looks like, you can clone or download this repository and put it into your Starsector mods folder.

To see what the example GUI does, load this mod, enter combat and press the '+'-Key.
If you want to see what a bigger GUI with actual functionality would look like, have a look at
the AdvancedGunneryControl mod:
<https://fractalsoftworks.com/forum/index.php?topic=21280.0>

You can find the Javadoc documentation for the classes in the combatgui module here: 
<https://magiclibstarsector.github.io/MagicLib/index.html>

## Quick step-by-step guide

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

## Creating a GUI class

This will form the basis of any kind of GUI you want to create. Have a look at the file 
src/com/dp/combatguiexample/ExampleCombatGui.java to see an example implementation.

Create a new class that extends org.magiclib.kotlin.combatgui.GuiBase.
You will need to overwrite two methods and implement a constructor for your GUI.

### Title and message strings

Let's start with the easy part, the two methods getTitleString and getMessageString. These simply
return a string that will be displayed in the GUI. 
The title string will be displayed above the GUI, the message string, by default, towards the bottom.
If you don't care about displaying any texts, feel free to simply return an empty string.

You can change both the title and message based on the current circumstances.
For the message, it probably makes sense to usually return an empty string and only display a message
when something noteworthy happens, that you want to inform the user of.

### Constructor

In the constructor, you will have to set up how your GUI will look like and add buttons to your GUI.

#### GUI layout

First, you will need to call the super-method, which will call the constructor of the base class.
If you just want to get started quickly, feel free to call it with no arguments. In that case, the default layout
for the GUI will be used.

Later, you will probably want to define your own layout. To do so, pass a GuiLayout object to the super-method:

```java
super(
       new GuiLayout(
        // GUI anchor. Reference point is the top left corner of the first button group of the GUI       
        0.05f, // x anchor position of the GUI in relative screen coordinates. 0.05f means a left margin of 5% of the screen
        0.8f, // relative y anchor position, 0.0f is the bottom of the screen, 1.0f is the top of the screen
        100f, // width of a single button in pixels. Will automatically get adjusted based on UI scaling.
        20f, // height of a single button
        0.5f, // opacity of button outlines
        Color.WHITE, // Color of the outlines of active buttons and text. Inactive buttons are always grey
        5f, // padding in pixels, i.e. distance between two buttons
        0.4f, // relative screen x position where hover tooltips (when a user hovers over a button) will be displayed 
        0.2f, // relative screen y position for tooltips
        25f, // space in pixels to allocate in pixels. Should be ~1.5-2 times font size. Setting it to 0 would display the title at the same position as the first button row      
       "graphics/fonts/insignia15LTaa.fnt", // font to use. Have a look at graphics/fonts in Starsector directory for options
        0.4f, // relative screen x position where messages will be displayed
        0.4f // relative screen y position for messages
        )
);
```

#### Adding buttons

There are two types of buttons: Buttons and ButtonGroups.
A button is simply a UI element that will execute an action when clicked. A button group is a row of buttons, where
each individual button can be activated or de-activated by clicking it. Clicking any button in a button group will trigger the
action of that button. In that action, the information which buttons are active will be available. A button group can be thought
of as checkboxes, where the user can select one or more options.

The first row of the GUI is reserved for regular buttons, the rows below are for button groups. This behavior can be modified,
though that is an advanced topic that will be covered later.

To add a new button, call the addButton-method in the constructor after the super-call.
You will need to pass it an action (which will be covered in the next part), a text (i.e. what will be displayed on the button), 
a tooltip, which will be displayed if the user hovers over the button and whether the button shall be disabled (i.e. greyed-out
and unclickable). Every time you call addButton, a new button will be added to the right of the previous button.

To add a new button group, you will need to pass a ButtonGroupAction object, which defines what happens when a button in that
group gets clicked. Next, you will need to pass a CreateButtonsAction, which defines the number, names, tooltips and data of the buttons
in that group. Then you will need to pass a RefreshAction (though you can simply pass null) and a description text, which will
be displayed on top of the button group. Every time you call addButtonGroup, a new button group will be added below
the previous group.

And now you are done with creating your GUI class!

## Actions

There are four types of actions:
- ButtonActions, which get executed when a regular button gets clicked
- ButtonGroupActions, which get executed when a button in a button group is clicked
- CreateButtonsActions, which are used to generate buttons in a button group
- RefreshButtonsActions, which are optional and can be used to dynamically change button groups

### ButtonActions

ButtonActions are fairly simple. Simply create a new class that implements the ButtonAction interface and overrides
the execute-method. That method takes no arguments. In there, you can execute any code you want. Whenever the button
that this action was assigned to gets clicked, the execute-method gets executed.
Whenever you call addButton, as discussed previously, create a new object of this type and pass it to that method.

```java
public class MyButtonAction implements ButtonAction {
    
    // you only need an explicit constructor if you want to pass some data to your action
    public MyButtonAction(/*pass any data your action needs as arguments in here*/){
        // initialize your member variables etc.
    }
    @Override
    public void execute() {
        // Your code goes here!
    }
}
```

If your implemented ButtonAction needs some kind of data to work, pass that data when calling the constructor before
passing it to the addButton-method.

### ButtonGroupActions

ButtonGroupActions are the equivalent of ButtonActions for ButtonGroups. Implement the ButtonGroupAction interface.
You will need to override two methods, the execute-method and the onHover-method, though the latter can usually be left
blank.

```java
public class MyButtonGroupAction implements ButtonGroupAction {
    
    public MyButtonGroupAction(/*pass any data your action needs as arguments in here*/){
       // initialize your member variables etc.
    }
    
    @Override
    public void execute(@NotNull List<?> data, @Nullable Object selectedButtonData, @Nullable Object unselectedButtonData) {
        // Your code goes here!
       // data is a list containing the data of all currently active buttons
      // selectedButtonData contains the data of the button that was clicked if it was activated, or null otherwise
     // unselectedButtonData is the opposite, it contains the data of the button if it was deactivated, or null otherwise  
    }

    @Override
    public void onHover() {
        // If you want something to happen when a user hovers over a button, implement it here
    }
}
```

### CreateButtonsAction

This action is used to tell button groups which and how many buttons to add.
If you simply want a static button group, you won't need to create your own CreateButtonsAction class, just use the
CreateSimpleButtonsAction. When you construct an object of type CreateSimpleButtonsAction, you will need to pass three lists:
- A list of strings containing the names of the buttons, e.g. Arrays.asList("MyFirstButton", "MySecondButton")
- A list containing the data associated to the buttons, in the same order as the names. If you pass null, the names will be used as data
- A list of strings containing the tooltips for the buttons

If you want to create dynamic button groups, that change based on the current context, you will need to write your own
CreateButtonsAction. Implement the interface CreateButtonsAction and override the createButtons-method.
In that method, call group.addButton for each button you want to create. You will need to pass the name, data and tooltip text to that method:

```java
class CreateSimpleButtons implements CreateButtonsAction {
    @Override
    void createButtons(DataButtonGroup group) {
        Boolean someCondition = false; // some condition, probably should be set from somewhere else
        group.addButton("GroupButton1", "data1", "tooltip");
        if(someCondition){
           group.addButton("ConditionalButton", "data2", "tooltip");
        }
        // call addButton as many times as you need buttons in this group
    }
}
```

If something happens that should change the buttons after they have already been created, you will have to call
the reRenderButtonGroups-method of GuiBase.

### RefreshButtonsAction

Usually, you can just pass null for the RefreshButtonAction. However, if you want to perform some kind of action
whenever a button gets clicked, you can implement a RefreshButtonsAction to achieve that goal.
To do so, create a new class the implements the RefreshButtonsAction and overrides the refreshButtons-method.

There already is one RefreshButtonsAction available, the RadioButtonRefreshAction. If you pass a new instance of
that action to the addButtonGroup-method, it will enforce radio-button behavior for the group, i.e. there will always
only be a single active button and pressing a new button will deactivate the previous button.
If you decide to use the RadioButtonRefreshAction, please only use selectedButtonData in that groups ButtonGroupAction. 

## Launching your GUI

You will need some kind of mechanism to actually display your GUI when appropriate and to call its 
advance- and render-methods every frame.

If you simply want your GUI to open when the user presses a Hotkey, you can use the SampleGuiLauncher base-class.
Create your own class that extends SampleGuiLauncher. Have a look at src/com/dp/combatguiexample/ExampleCombatGuiLauncher.java
for an example.

Now all that's left to do is to tell Starsector to load your GUI-launcher. You can do so by adding it as a plugin to
data/config/settings.json

If you want some more complex launching mechanism, you will need to write your own launcher class. You will probably
want to base that class on BaseEveryFrameCombatPlugin.
In there, override the advance and renderInUICoords methods.

In the advance-method, handle your launching logic and call the advance-method of your GUI class.

In the renderInUICoords-method, call the GUI's render-method.

The result could look something like this:

```java
public class MyGuiLauncher extends BaseEveryFrameCombatPlugin {
    
    private GuiBase gui = null;

    @Override 
    void advance(Float amount, List<InputEventAPI>events) {
        // call the advance method every frame if GUI exists
        if(gui != null){
            gui.advance();
        }
        // replace that line with your logic for when the GUI should be opened/closed
        Boolean shouldOpenOrCloseGui = false;
        if (shouldOpenOrCloseGui) {
            // create a new GUI if it currently doesn't exist
           if (null == gui) {
               gui = new MyGui(); 
               
               // pause the game and lock the camera (optional)
               Global.getCombatEngine().setPaused(true);
               Global.getCombatEngine().getViewport().setExternalControl(true); 
           }else { // delete the GUI if it did exist
               gui = null;
               // unlock the camera (if you previously locked it)
               Global.getCombatEngine().getViewport().setExternalControl(false);
           }
        }

    }

    @Override 
    void renderInUICoords(ViewportAPI viewport) {
        // call the render-method of your GUI every frame if it exists.
        if(gui != null){
            gui.render();
        }
    }
}
```

## Advanced 

For now at least, more advanced topics will not be covered in this document. I believe that most likely not enough people
will use those to justify the effort of writing documentation about them. Instead, please feel free to DM or mention me
on the unofficial Starsector Discord or the MagicLib Discord (@desperatepeter) if you are interested in using advanced
features and need assistance.
