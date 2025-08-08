package net.picklemod;

import net.fabricmc.api.ModInitializer;

import net.picklemod.util.ModLootTableModifiers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Pickled implements ModInitializer {
	public static final String MOD_ID = "pickled";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        ModLootTableModifiers.modifyLootTables();

	}
}