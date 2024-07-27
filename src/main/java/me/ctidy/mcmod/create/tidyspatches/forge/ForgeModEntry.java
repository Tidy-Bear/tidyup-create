package me.ctidy.mcmod.create.tidyspatches.forge;

import me.ctidy.mcmod.create.tidyspatches.ModEnvConstants;
import me.ctidy.mcmod.create.tidyspatches.config.ClientConfig;
import me.ctidy.mcmod.create.tidyspatches.utils.SpaceIndentsUtil;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLEnvironment;

import java.util.concurrent.CompletableFuture;

/**
 * ForgeModEntry
 *
 * @author ctidy
 * @since 2024/4/30
 */
@Mod(ModEnvConstants.MOD_ID)
public class ForgeModEntry {

    public static void addReloadListener(final RegisterClientReloadListenersEvent event) {
        event.registerReloadListener((preparationBarrier, resourceManager, preparationsProfiler, reloadProfiler, preparationsExecutor, reloadExecutor) -> {
            preparationsProfiler.startTick();
            CompletableFuture<Void> preparations = CompletableFuture.runAsync(SpaceIndentsUtil::onReload, preparationsExecutor);
            preparationsProfiler.endTick();
            return preparations.thenCompose(preparationBarrier::wait);  // lazy reload, so just preparations
        });
    }

    public ForgeModEntry() {
        ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(
                () -> "ANY", (remote, isServer) -> true
        ));
        if (Dist.CLIENT == FMLEnvironment.dist) {
            ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ClientConfig.SPEC);
            MinecraftForge.EVENT_BUS.addListener(ForgeModEntry::addReloadListener);
        }
    }

}
