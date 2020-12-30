package org.squiddev.plethora.integration.computercraft;

import dan200.computercraft.api.turtle.ITurtleAccess;
import net.minecraftforge.items.IItemHandler;
import org.squiddev.plethora.api.method.ContextKeys;
import org.squiddev.plethora.api.method.IContext;
import org.squiddev.plethora.api.method.TypedLuaObject;
import org.squiddev.plethora.api.method.wrapper.FromSubtarget;
import org.squiddev.plethora.api.method.wrapper.PlethoraMethod;
import org.squiddev.plethora.api.module.IModuleContainer;
import org.squiddev.plethora.gameplay.modules.PlethoraModules;

/**
 * Provide access to a turtle's inventory via an introspection module
 */
public final class MethodsIntrospectionTurtle {
	private MethodsIntrospectionTurtle() {
	}

	@PlethoraMethod(
		module = PlethoraModules.INTROSPECTION_S, worldThread = false,
		doc = "-- Get this turtle's inventory"
	)
	public static TypedLuaObject<IItemHandler> getInventory(IContext<IModuleContainer> context, @FromSubtarget(ContextKeys.ORIGIN) ITurtleAccess turtle) {
		return context.makeChildId((IItemHandler) turtle.getItemHandler()).getObject();
	}
}
