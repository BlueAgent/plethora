package org.squiddev.plethora.integration.vanilla.meta;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import org.squiddev.plethora.api.Injects;
import org.squiddev.plethora.api.meta.BasicMetaProvider;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

@Injects
public final class MetaBlock extends BasicMetaProvider<Block> {
	public MetaBlock() {
		super("Provide the registry name, display name and translation key of a block.");
	}

	@Nonnull
	@Override
	public Map<String, ?> getMeta(@Nonnull Block block) {
		return getBasicMeta(block);
	}

	public static Map<String, ?> getBasicMeta(@Nonnull Block block) {
		HashMap<String, Object> data = new HashMap<>(3);

		ResourceLocation name = block.getRegistryName();
		data.put("name", name == null ? "unknown" : name.toString());

		data.put("displayName", block.getLocalizedName());
		data.put("translationKey", block.getTranslationKey());

		return data;
	}

	@Override
	public Block getExample() {
		return Blocks.DIRT;
	}
}
