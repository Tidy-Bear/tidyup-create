# Tidy UP: Create
Tidy's patches mod for Create and its common addon mods, mainly involving some fixes not available on the latest release.

## About: Create

Create, made by [simibubi](https://github.com/simibubi), is a mod offering a variety of tools and blocks for Building, Decoration, and Aesthetic Automation.

[github](https://github.com/Creators-of-Create/Create)
| [curseforge](https://www.curseforge.com/minecraft/mc-mods/create)
| [modrinth](https://modrinth.com/mod/create)

Thank you to [Creators-of-Create](https://github.com/Creators-of-Create) and all the contributors for developing and maintaining such an amazing mod~

## Features

### # Fix: Spacing Indentation on the Goggle Overlay

**Client side only.**

Scale spacing indentation on the goggle overlay based on a spacing width of a font. This is a bug that text on the goggle overlay will be misaligned if using a font with a different spacing width than Vanilla.

It has been fixed in Create's repo but has not been released yet ([Creators-of-Create/Create#6477](https://github.com/Creators-of-Create/Create/pull/6477)).

However, many addon mods still use outdated indentation APIs that Create has marked as deprecated.
As a result, the fix will not work on them even when it is released.

So, the patch did 2 things:

- Fixed the bug, not including the fix of overlay jiggling with Modern UI on 1.18.2.
- Patched the common addon mods to make the fix work for them as well.

Due to tech limitations, precise targets must be provided for mixin. These addon mods are including currently:
- Create: Crafts & Additions
- Create: Dreams & Desires
- Create Central Kitchen (also fixed [DragonsPlusMinecraft/CreateCentralKitchen#97](https://github.com/DragonsPlusMinecraft/CreateCentralKitchen/issues/97))
- Create Enchantment Industry
- Create Ore Excavation

These mods have been reviewed. There's no need to do anything extra:
- Create: Connected
- Create: Copycats+
- Create: Ender Transmission
- Create: Interiors
- Create: Misc & Things
- Create: Stuff & Additions
- Create: Steam 'n' Rails
- Create Deco
- Create Guardian Beam Defense

If something still doesn't work, try to enable universal fix from the config file (config/craete_tidyspatches-client.toml).
The universal fix is off as default to save some mem and time.

**Related links:**

https://github.com/BloCamLimb/ModernUI/issues/172

https://github.com/Creators-of-Create/Create/issues/6404

https://github.com/Creators-of-Create/Create/pull/6477

### # Fix: Schematic Preview Not Disappearing

**Client side only.**

Make the preview of the deployed schematic disappear when the item is moved out from the player's hotbar.

It has been fixed in Create's repo but has not been released yet ([Creators-of-Create/Create#6673](https://github.com/Creators-of-Create/Create/pull/6673)).

The patch did the same thing but using different way than Create.

**Related links:**

https://github.com/Creators-of-Create/Create/issues/6195

https://github.com/Creators-of-Create/Create/pull/6673

### # Fix: Preview of Previous Schematic Still Exists

**Client side only.**

Fix a bug that previous deployed preview was still rendering at position (0, 0, 0) after switching to another un-deployed schematic item.

**Related links:**

https://github.com/Creators-of-Create/Create/issues/6784

https://github.com/Creators-of-Create/Create/pull/6813

### # Enhancement: Display Structure Size of Schematic on Tooltip

**Client side only.**

Now you can see the structure size (x, y, z) of the schematic on its tooltip.

### # Fix: Threshold Switch Calculates Wrong Slot Size Limit Higher than Vanilla

**Server side only.**

Make Threshold Switch take into account slot size limit higher than vanilla when calculating available spaces in a container.

It has been fixed in Create's repo but has not been released yet.
Main codes come from [Creators-of-Create/Create@d9198f67](https://github.com/Creators-of-Create/Create/commit/d9198f678ea499bc14e7af669ff586dcc4afd6bb) by [zelophed](https://github.com/zelophed).

This mod additionally fixes a bug in the commit where "disabled" slots of (Simple) Compacting Drawer were being counted as available spaces.
However, similar bug is still here with Storage Controller (Expansion).
And, available spaces may not be expected if a Threshold Switch pointing at a (Simple) Compacting Drawer has a filter.

**So, still be careful to use Threshold Switch with storage block from other mods.**

**Related links:**

https://github.com/Creators-of-Create/Create/issues/5185

https://github.com/Creators-of-Create/Create/commit/d9198f678ea499bc14e7af669ff586dcc4afd6bb

### # Fix: Continuously growing data of Basin

**Server side only.**

Clear outdated data of Basin at every lazy tick (every 10 ticks) to avoid potential server OOM (out of memory) or player disconnecting by over large NBT.

**Related links:**

https://github.com/Creators-of-Create/Create/issues/6837

### # Fix: Limit max chunks on Chunk Loader's goggle overlay

**For addon mod "Create Ender Transmission"**

[github](https://github.com/RuochenFu21/CreateEnderTransmission/)
| [curseforge](https://github.com/RuochenFu21/CreateEnderTransmission/)
| [modrinth](https://modrinth.com/mod/create-ender-transmission)

**Client side only.**

Information on Chunk Loader's goggle overlay will never display chunks number greater than 25.
Yes, it can only load a maximum of 5 * 5 chunks even if the speed limit is increased by config.

**Related links:**

https://github.com/RuochenFu21/CreateEnderTransmission/issues/18

### # Fix: NPE in addon mod "Create Ender Transmission"

**For addon mod "Create Ender Transmission"**

[github](https://github.com/RuochenFu21/CreateEnderTransmission/)
| [curseforge](https://github.com/RuochenFu21/CreateEnderTransmission/)
| [modrinth](https://modrinth.com/mod/create-ender-transmission)

**Both side, but not mandatory for the other side.**

A serious NPE (Null Pointer Exception) on both client and server side.

On the client side, it will crash the game when attaching a pipe to a Fluid Transmitter.

On the server side, it will not load persistent data of fluid network when server loaded.

**Related links:**

https://github.com/RuochenFu21/CreateEnderTransmission/issues/7

https://github.com/RuochenFu21/CreateEnderTransmission/issues/9

