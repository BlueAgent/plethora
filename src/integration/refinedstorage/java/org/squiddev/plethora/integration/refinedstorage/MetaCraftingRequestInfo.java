package org.squiddev.plethora.integration.refinedstorage;

import com.raoulvdberge.refinedstorage.RS;
import com.raoulvdberge.refinedstorage.api.autocrafting.task.ICraftingRequestInfo;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import org.squiddev.plethora.api.Injects;
import org.squiddev.plethora.api.meta.BaseMetaProvider;
import org.squiddev.plethora.api.method.IPartialContext;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

@Injects(RS.ID)
public final class MetaCraftingRequestInfo extends BaseMetaProvider<ICraftingRequestInfo> {
	@Nonnull
	@Override
	public Map<String, ?> getMeta(@Nonnull IPartialContext<ICraftingRequestInfo> context) {
		ICraftingRequestInfo info = context.getTarget();
		Map<String, Object> out = new HashMap<>();

		ItemStack item = info.getItem();
		if (item != null) out.put("item", context.makePartialChild(item).getMeta());

		FluidStack fluid = info.getFluid();
		if (fluid != null) out.put("fluid", context.makePartialChild(fluid).getMeta());

		return out;
	}
}
