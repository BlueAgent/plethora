package org.squiddev.plethora.integration.refinedstorage;

import com.raoulvdberge.refinedstorage.RS;
import com.raoulvdberge.refinedstorage.api.autocrafting.task.ICraftingRequestInfo;
import com.raoulvdberge.refinedstorage.api.autocrafting.task.ICraftingTask;
import org.squiddev.plethora.api.Injects;
import org.squiddev.plethora.api.meta.BaseMetaProvider;
import org.squiddev.plethora.api.method.IPartialContext;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

@Injects(RS.ID)
public final class MetaCraftingTask extends BaseMetaProvider<ICraftingTask> {
	@Nonnull
	@Override
	public Map<String, ?> getMeta(@Nonnull IPartialContext<ICraftingTask> context) {
		ICraftingTask task = context.getTarget();
		Map<String, Object> out = new HashMap<>();

		ICraftingRequestInfo requested = task.getRequested();
		if (requested != null) out.put("requested", context.makePartialChild(requested));
		out.put("quantity", task.getQuantity());
		out.put("valid", !task.hasMissing());

		return out;
	}
}
