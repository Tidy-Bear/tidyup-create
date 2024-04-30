package me.ctidy.mcmod.demo;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(CtidyDemo.MODID)
public class CtidyDemo {

    public static final String MODID = "ctidydemo";
    public static final Logger LOGGER = LogManager.getLogger();

    public CtidyDemo() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM PRE-INIT");
    }

}
