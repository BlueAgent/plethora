package org.squiddev.plethora.integration.storagedrawers;

import com.jaquadro.minecraft.storagedrawers.StorageDrawers;
import com.jaquadro.minecraft.storagedrawers.api.storage.Drawers;
import com.jaquadro.minecraft.storagedrawers.api.storage.IDrawer;
import com.jaquadro.minecraft.storagedrawers.api.storage.IDrawerGroup;
import dan200.computercraft.api.lua.LuaException;
import org.squiddev.plethora.api.method.IContext;
import org.squiddev.plethora.api.method.TypedLuaObject;
import org.squiddev.plethora.api.method.wrapper.FromTarget;
import org.squiddev.plethora.api.method.wrapper.Optional;
import org.squiddev.plethora.api.method.wrapper.PlethoraMethod;

import static org.squiddev.plethora.api.method.ArgumentHelper.assertBetween;

public final class MethodsIDrawerGroup {
	private MethodsIDrawerGroup() {
	}

	@PlethoraMethod(modId = StorageDrawers.MOD_ID, doc = "-- Return the number of drawers inside this draw group")
	public static int getDrawerCount(@FromTarget IDrawerGroup drawer) {
		return drawer.getDrawerCount();
	}

	@Optional
	@PlethoraMethod(modId = StorageDrawers.MOD_ID, doc = "-- Return the drawer at this particular slot")
	public static TypedLuaObject<IDrawer> getDrawer(IContext<IDrawerGroup> context, int slot) throws LuaException {
		IDrawerGroup group = context.getTarget();

		assertBetween(slot, 1, group.getDrawerCount(), "Index out of range (%s)");

		IDrawer drawer = group.getDrawer(slot - 1);
		return drawer == Drawers.DISABLED ? null : context.makeChildId(drawer).getObject();

	}
}
