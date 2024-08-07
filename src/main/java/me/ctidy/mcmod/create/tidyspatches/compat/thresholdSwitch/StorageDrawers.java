package me.ctidy.mcmod.create.tidyspatches.compat.thresholdSwitch;

import com.simibubi.create.compat.Mods;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.items.IItemHandler;

/**
 * <h1>StorageDrawers</h1>
 * <a href="https://github.com/Creators-of-Create/Create/commit/d9198f678ea499bc14e7af669ff586dcc4afd6bb">Origin</a>
 * @author zelophed
 */
public class StorageDrawers implements ThresholdSwitchCompat {

	@Override
	public boolean isFromThisMod(BlockEntity be) {
		if (be == null)
			return false;

		ResourceLocation key = BlockEntityType.getKey(be.getType());
		if (key == null)
			return false;

		return Mods.STORAGEDRAWERS.id().equals(key.getNamespace());
	}

	@Override
	public long getSpaceInSlot(IItemHandler inv, int slot) {
		if (slot == 0)
			return 0;

		return inv.getSlotLimit(slot);
	}

}
