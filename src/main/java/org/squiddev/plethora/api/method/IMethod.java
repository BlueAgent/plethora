package org.squiddev.plethora.api.method;

import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;

/**
 * A Lua side method targeting a class
 * Register with {@link IMethodRegistry}
 */
public interface IMethod<T> {
	/**
	 * The name of this method
	 *
	 * @return The name of this method
	 */
	String getName();

	/**
	 * If this function should be executed on the world thread
	 *
	 * @return If this function should be executed on the world thread.
	 */
	boolean worldThread();

	/**
	 * Check if this function can be applied in the given context.
	 *
	 * @param context The context to check in
	 * @return If this function can be applied.
	 * @see IContext#hasContext(Class)
	 * @see IContext#hasEnvironment(Class)
	 */
	boolean canApply(IContext<T> context);

	/**
	 * Apply the method
	 *
	 * @param context The context to apply within
	 * @param args    The arguments this function was called with
	 * @return The return values
	 * @throws LuaException     On the event of an error
	 * @throws RuntimeException Unhandled errors: these will be rethrown as {@link LuaException}s and the call stack logged.
	 * @see dan200.computercraft.api.lua.ILuaObject#callMethod(ILuaContext, int, Object[])
	 */
	Object[] apply(IContext<T> context, Object[] args) throws LuaException;
}
