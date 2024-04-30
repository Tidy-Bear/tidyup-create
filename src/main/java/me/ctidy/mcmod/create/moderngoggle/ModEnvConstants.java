package me.ctidy.mcmod.create.moderngoggle;

import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * ModEnvConstants
 *
 * @author ctidy
 * @since 2024/4/30
 */
public class ModEnvConstants {

    public static final String MOD_ID = "createmoderngoggle";
    public static final Logger LOGGER = LogManager.getLogger();

    public static ResourceLocation id(String id) {
        return new ResourceLocation(MOD_ID, id);
    }

}
