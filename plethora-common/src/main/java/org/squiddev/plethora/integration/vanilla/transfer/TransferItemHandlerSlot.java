package org.squiddev.plethora.integration.vanilla.transfer;

import net.minecraftforge.items.IItemHandler;
import org.squiddev.plethora.api.Injects;
import org.squiddev.plethora.api.transfer.ITransferProvider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Transfer location that allows accessing child slots
 *
 * We block primary accesses as they end up being rather noisy
 */
@Injects
public final class TransferItemHandlerSlot implements ITransferProvider<IItemHandler> {
	@Nullable
	@Override
	public Object getTransferLocation(@Nonnull IItemHandler object, @Nonnull String key) {
		try {
			int value = Integer.parseInt(key) - 1;
			if (value >= 0 && value < object.getSlots()) {
				return object.getStackInSlot(value);
			}
		} catch (NumberFormatException ignored) {
		}

		return null;
	}

	@Nonnull
	@Override
	public Set<String> getTransferLocations(@Nonnull IItemHandler object) {
		final int size = object.getSlots();
		if (size == 0) return Collections.emptySet();
		Set<String> slots = new HashSet<>();
		for (int i = 0; i < size; i++) {
			if (!object.getStackInSlot(i).isEmpty()) slots.add(Integer.toString(i));
		}
		return slots;
	}

	@Override
	public boolean primary() {
		return false;
	}
}
