package org.squiddev.plethora.gameplay.client.gui;

import dan200.computercraft.client.gui.GuiComputer;
import dan200.computercraft.shared.computer.core.ClientComputer;
import org.squiddev.plethora.gameplay.minecart.ContainerMinecartComputer;
import org.squiddev.plethora.gameplay.minecart.EntityMinecartComputer;

public class GuiMinecartComputer extends GuiComputer {
	public GuiMinecartComputer(EntityMinecartComputer minecart, ClientComputer computer) {
		super(new ContainerMinecartComputer(minecart), minecart.getFamily(), computer, 51, 19);
	}
}
