package me.ctidy.mcmod.create.tidyspatches.config;

import com.electronwill.nightconfig.core.Config;
import me.ctidy.mcmod.create.tidyspatches.ModEnvConstants;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

/**
 * ClientConfig
 *
 * @author ctidy
 * @since 2024/5/2
 */
@OnlyIn(Dist.CLIENT)
public class ClientConfig {
    public static final ForgeConfigSpec SPEC;
    public static final ClientConfig INSTANCE;

    public final ForgeConfigSpec.BooleanValue spaceIndentsUniversalFix;

    static {
        Config.setInsertionOrderPreserved(true);
        Pair<ClientConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ClientConfig::new);
        SPEC = specPair.getRight();
        INSTANCE = specPair.getLeft();
    }

    private static String nameTKey(final String key) {
        return ModEnvConstants.MOD_ID + ".config." + key;
    }

    private static String sectionTKey(final String key) {
        return nameTKey("section." + key);
    }

    private static String commentTKey(final String key) {
        return nameTKey(key) + ".tooltip";
    }

    public ClientConfig(ForgeConfigSpec.Builder builder) {
        builder.push("action").translation(sectionTKey("action"));

        spaceIndentsUniversalFix = builder.comment("Whether apply a universal fix of space indents at Engineer's Goggle overlay. Only enable when something still looks incorrect.",
                        "[Default: false]")
                .translation(nameTKey("space_indents_universal_fix"))
                .define("enabled", false);

        builder.pop();
    }
}
