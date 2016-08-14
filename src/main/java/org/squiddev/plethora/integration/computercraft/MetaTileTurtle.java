package org.squiddev.plethora.integration.computercraft;

import com.google.common.collect.Maps;
import dan200.computercraft.api.turtle.TurtleSide;
import dan200.computercraft.shared.computer.blocks.IComputerTile;
import dan200.computercraft.shared.turtle.blocks.ITurtleTile;
import org.squiddev.plethora.api.meta.BasicMetaProvider;
import org.squiddev.plethora.api.meta.IMetaProvider;

import javax.annotation.Nonnull;
import java.util.Map;

import static org.squiddev.plethora.integration.computercraft.MetaItemTurtle.getUpgrade;

@IMetaProvider.Inject(value = IComputerTile.class, modId = "ComputerCraft", namespace = "turtle")
public class MetaTileTurtle extends BasicMetaProvider<ITurtleTile> {
	@Nonnull
	@Override
	public Map<Object, Object> getMeta(@Nonnull ITurtleTile object) {
		Map<Object, Object> out = Maps.newHashMap();

		out.put("color", object.getColour().toString());
		out.put("colour", object.getColour().toString()); // For those who can spell :p
		out.put("fuel", object.getAccess().getFuelLevel());

		out.put("left", getUpgrade(object.getUpgrade(TurtleSide.Left)));
		out.put("right", getUpgrade(object.getUpgrade(TurtleSide.Right)));

		return out;
	}
}
