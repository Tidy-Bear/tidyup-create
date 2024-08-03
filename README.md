# Create: Tidy's Patches
Tidy's patches mod for Create, mainly involving some fixes not available on the latest release.

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

Not been fixed or even issued.
