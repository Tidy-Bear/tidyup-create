package me.ctidy.mcmod.create.tidyspatches.compat.thresholdSwitch;

import me.ctidy.mcmod.create.tidyspatches.compat.Mods;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.items.IItemHandler;

/**
 * <h1>SophisticatedStorage</h1>
 * <a href="https://github.com/Creators-of-Create/Create/commit/d9198f678ea499bc14e7af669ff586dcc4afd6bb">Origin</a>
 * @author zelophed
 */
public class SophisticatedStorage implements ThresholdSwitchCompat {

	@Override
	public boolean isFromThisMod(BlockEntity be) {
		if (be == null)
			return false;

		ResourceLocation key = BlockEntityType.getKey(be.getType());
		if (key == null)
			return false;

		String namespace = key.getNamespace();
		return Mods.SOPHISTICATEDSTORAGE.id().equals(namespace)
				|| Mods.SOPHISTICATEDBACKPACKS.id().equals(namespace);
	}

	@Override
	public long getSpaceInSlot(IItemHandler inv, int slot) {
		return ((long) inv.getSlotLimit(slot) * inv.getStackInSlot(slot).getMaxStackSize()) / 64;
	}

}
