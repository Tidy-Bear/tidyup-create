package me.ctidy.mcmod.create.tidyspatches.forge;

import me.ctidy.mcmod.create.tidyspatches.ModEnvConstants;
import me.ctidy.mcmod.create.tidyspatches.config.ClientConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLEnvironment;

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
        if (Dist.CLIENT == FMLEnvironment.dist) {
            ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ClientConfig.SPEC);
        }
    }

}
