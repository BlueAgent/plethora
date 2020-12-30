package org.squiddev.plethora.integration.vanilla.transfer;

import net.minecraft.entity.EntityLivingBase;
import org.squiddev.plethora.api.Injects;
import org.squiddev.plethora.api.transfer.ITransferProvider;
import org.squiddev.plethora.utils.EquipmentInvWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.Set;

/**
 * Provides inventory for {@link EntityLivingBase}'s equipment
 */
@Injects
public final class TransferEntityLivingBaseEquipment implements ITransferProvider<EntityLivingBase> {
	@Nullable
	@Override
	public Object getTransferLocation(@Nonnull EntityLivingBase object, @Nonnull String key) {
		return key.equals("equipment") ? new EquipmentInvWrapper(object) : null;
	}

	@Nonnull
	@Override
	public Set<String> getTransferLocations(@Nonnull EntityLivingBase object) {
		return Collections.singleton("equipment");
	}
}
