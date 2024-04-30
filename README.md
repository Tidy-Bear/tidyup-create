# CTidy's Forge Demo (Forge Mod Dev Template)

CTidy's own Forge mod development template. Implementers should change `mod_id`, `version`, `author`, `licence`, etc., to actual values.

## Built-ins
### Env
- MinecraftForge
- SpongePowered Mixin
- ParchmentMC Mappings

### Mod dependencies
Allowed to add / remove freely.
- JEI (JustEnoughItems)
- JECh (JustEnoughCharacters)
- Jade
- Catalogue
- Configured
- Controlling
- Spark
- Xaero's Minimap
- Xaero's World Map

All `.jar` files in the `<project_root>/libs` dir will be imported and de-obfuscated automatically, except those with a suffix `-sources.jar` or `-local.jar`.

## Notes
It's recommended to separate codes of mod itself (such as api), codes depending on Vanilla MC and codes depending on a specific platform (such as Forge, Fabric, etc.), though the template is only for Forge.
When implementing a certain use case, use codes from Vanilla MC instead of platform-specific APIs, unless there are striking defects on Vanilla, or you are ready to coding on each platform.
