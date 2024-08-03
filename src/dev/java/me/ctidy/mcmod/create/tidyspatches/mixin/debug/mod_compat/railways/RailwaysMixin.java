package me.ctidy.mcmod.create.tidyspatches.mixin.debug.mod_compat.railways;

import com.railwayteam.railways.Railways;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * RailwaysMixin
 *
 * @author ctidy
 * @since 2024/8/1
 */
@Mixin(value = Railways.class, remap = false)
public abstract class RailwaysMixin {

    @Inject(method = "init", at = @At(value = "INVOKE", target = "Lcom/railwayteam/railways/util/Utils;isDevEnv()Z"), cancellable = true)
    private static void preventAudit(CallbackInfo ci) {
        ci.cancel();
    }

}
