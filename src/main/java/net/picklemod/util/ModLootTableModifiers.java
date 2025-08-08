package net.picklemod.util;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantedCountIncreaseLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.loot.function.ApplyBonusLootFunction;


public class ModLootTableModifiers {
    private static final Identifier HUSK
            = Identifier.of("minecraft", "entities/husk");

    public static void modifyLootTables() {

        LootTableEvents.MODIFY.register((key, tableBuilder, source, registry) -> {
            if(HUSK.equals(key.getValue())) {

                LootPool.Builder firstBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        // Always drop 1 sand
                        .with(ItemEntry.builder(Items.SAND)
                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1))))
                                .apply(EnchantedCountIncreaseLootFunction.builder(registry, UniformLootNumberProvider.create(1.0f, 2.0f)));
                LootPool.Builder secondBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        // 25% chance to drop 1 more sand
                        .with(ItemEntry.builder(Items.SAND)
                        .conditionally(RandomChanceLootCondition.builder(0.25f))
                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1))));


                tableBuilder.pool(firstBuilder.build());
                tableBuilder.pool(secondBuilder.build());
            }
        });
    }
}