package org.squiddev.plethora.integration.vanilla.meta;

import net.minecraft.block.material.Material;
import org.squiddev.plethora.api.Injects;
import org.squiddev.plethora.api.meta.BasicMetaProvider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

@Injects
public final class MetaMaterial extends BasicMetaProvider<Material> {
	@Nonnull
	@Override
	public Map<String, ?> getMeta(@Nonnull Material material) {
		Map<String, Object> data = new HashMap<>();

		data.put("replaceable", material.isReplaceable());
		data.put("liquid", material.isLiquid());
		data.put("opaque", material.isOpaque());
		data.put("solid", material.isSolid());
		data.put("blocksMovement", material.blocksMovement());
		data.put("blocksLight", material.blocksMovement());
		data.put("requiresTool", !material.isToolNotRequired());
		data.put("burns", material.getCanBurn());

		return data;
	}

	@Nullable
	@Override
	public Material getExample() {
		return Material.ROCK;
	}
}
