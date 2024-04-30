package me.ctidy.mcmod.create.moderngoggle.forge;

import me.ctidy.mcmod.create.moderngoggle.ModEnvConstants;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;

/**
 * ForgeModEntry
 *
 * @author ctidy
 * @since 2024/4/30
 */
@Mod(ModEnvConstants.MOD_ID)
public class ForgeModEntry {

    public ForgeModEntry() {
        ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(
                () -> "ANY", (remote, isServer) -> true
        ));
    }

}
