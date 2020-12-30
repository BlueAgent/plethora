package org.squiddev.plethora.integration.computercraft;

import dan200.computercraft.ComputerCraft;
import dan200.computercraft.api.peripheral.IPeripheral;
import org.squiddev.plethora.api.Injects;
import org.squiddev.plethora.api.converter.ConstantConverter;
import org.squiddev.plethora.integration.PlethoraIntegration;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.List;

@Injects(ComputerCraft.MOD_ID)
public final class ConverterTargetedPeripheral implements ConstantConverter<IPeripheral, Object> {
	private boolean fetched;

	// Computronics multiperipheral
	private Class<?> multiPeripheral;
	private Field multiPeripheralPeripherals;

	private Class<?> wrappedMultiPeripheral;
	private Field wrappedMultiPeripheralPeripheral;

	private void fetchReflection() {
		if (fetched) return;

		try {
			multiPeripheral = Class.forName("pl.asie.computronics.cc.multiperipheral.MultiPeripheral");
			multiPeripheralPeripherals = multiPeripheral.getDeclaredField("peripherals");
			multiPeripheralPeripherals.setAccessible(true);

			wrappedMultiPeripheral = Class.forName("pl.asie.computronics.api.multiperipheral.WrappedMultiPeripheral");
			wrappedMultiPeripheralPeripheral = wrappedMultiPeripheral.getDeclaredField("peripheral");
			wrappedMultiPeripheralPeripheral.setAccessible(true);
		} catch (ReflectiveOperationException ignored) {
		}


		fetched = true;
	}

	@Nullable
	@Override
	@SuppressWarnings("unchecked")
	public Object convert(@Nonnull IPeripheral from) {
		fetchReflection();

		Object to = from.getTarget();
		if (from != to) return to;

		// Try to unwrap Computronics's multi-peripherals
		if (multiPeripheral != null && multiPeripheralPeripherals != null && multiPeripheral.isInstance(from)) {
			try {
				List<IPeripheral> peripherals = (List<IPeripheral>) multiPeripheralPeripherals.get(from);
				for (IPeripheral child : peripherals) {
					to = child.getTarget();
					if (child != to) return to;

					if (wrappedMultiPeripheral != null && wrappedMultiPeripheralPeripheral != null && wrappedMultiPeripheral.isInstance(child)) {
						IPeripheral wrapped = (IPeripheral) wrappedMultiPeripheralPeripheral.get(child);

						to = wrapped.getTarget();
						if (wrapped != to) return to;
					}
				}
			} catch (ReflectiveOperationException e) {
				PlethoraIntegration.LOG.error("Cannot extract peripherals from multi-peripheral", e);
			}
		}

		// Handle the case where the multiperipheral is wrapped
		if (wrappedMultiPeripheral != null && wrappedMultiPeripheralPeripheral != null && wrappedMultiPeripheral.isInstance(from)) {
			try {
				IPeripheral wrapped = (IPeripheral) wrappedMultiPeripheralPeripheral.get(from);

				to = wrapped.getTarget();
				if (wrapped != to) return to;
			} catch (ReflectiveOperationException e) {
				PlethoraIntegration.LOG.error("Cannot extract peripherals from multi-peripheral", e);
			}
		}

		return null;
	}
}
