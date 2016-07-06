package org.squiddev.plethora.api.method;

import org.squiddev.plethora.api.reference.IReference;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * A registry for metadata providers.
 *
 * @see IMethod
 */
public interface IMethodRegistry {
	/**
	 * Register a method
	 *
	 * @param target The class this provider targets
	 * @param method The relevant method
	 */
	<T> void registerMethod(@Nonnull Class<T> target, @Nonnull IMethod<T> method);

	/**
	 * Get all methods for a context
	 *
	 * @param context The context to execute under
	 * @return List of valid methods
	 */
	@Nonnull
	<T> List<IMethod<T>> getMethods(@Nonnull IContext<T> context);

	/**
	 * Get all methods targeting a class
	 *
	 * @param target The class to invoke with
	 * @return List of valid methods
	 */
	@Nonnull
	List<IMethod<?>> getMethods(@Nonnull Class<?> target);

	/**
	 * Build a context
	 *
	 * @param target  The object to target
	 * @param context Additional context items
	 * @return The built context
	 */
	@Nonnull
	<T> IUnbakedContext<T> makeContext(IReference<T> target, IReference<?>... context);

}
