package org.squiddev.plethora.integration.vanilla.meta;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.util.FoodStats;
import org.squiddev.plethora.api.Injects;
import org.squiddev.plethora.api.meta.BasicMetaProvider;
import org.squiddev.plethora.utils.EntityPlayerDummy;
import org.squiddev.plethora.utils.WorldDummy;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

@Injects
public final class MetaEntityPlayer extends BasicMetaProvider<EntityPlayer> {
	@Nonnull
	@Override
	public Map<String, ?> getMeta(@Nonnull EntityPlayer object) {
		Map<String, Object> result = new HashMap<>();

		FoodStats stats = object.getFoodStats();
		Map<String, Object> foodMap = new HashMap<>();
		result.put("food", foodMap);
		foodMap.put("hunger", stats.getFoodLevel());
		foodMap.put("saturation", stats.getSaturationLevel());
		foodMap.put("hungry", stats.needFood());

		PlayerCapabilities capabilities = object.capabilities;
		result.put("isFlying", capabilities.isFlying);
		result.put("allowFlying", capabilities.allowFlying);
		result.put("walkSpeed", capabilities.getWalkSpeed());
		result.put("flySpeed", capabilities.getFlySpeed());

		return result;
	}

	@Nonnull
	@Override
	public EntityPlayer getExample() {
		return new EntityPlayerDummy(WorldDummy.INSTANCE);
	}
}
