package com.github.dragoni7;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber
public class Config {

	public static final String CATEGORY_GENERAL = "general";
	
	public static ForgeConfigSpec.IntValue DAMAGE;
	public static ForgeConfigSpec.IntValue PROBABILITY;
	public static ForgeConfigSpec.IntValue DURATION;
	public static ForgeConfigSpec.BooleanValue SCULKBLOCK;
	
	public static ForgeConfigSpec COMMON_CONFIG;
	
	static {
		ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
		
		COMMON_BUILDER.comment("Sculk Atrophy Settings").push(CATEGORY_GENERAL);
		DAMAGE = COMMON_BUILDER.comment("Damage per tick sculk atrophy applies when player is crouching. Default = 1 (half a heart)").defineInRange("damage", 1, 0, Integer.MAX_VALUE);
		PROBABILITY = COMMON_BUILDER.comment("Chance to apply sculk atrophy when walking over sculk vein blocks. Default = 1/64").defineInRange("probability", 64, 1, Integer.MAX_VALUE);
		DURATION = COMMON_BUILDER.comment("Duration in ticks of the effect (1 second = 20 ticks)").defineInRange("duration", 200, 0, Integer.MAX_VALUE);
		SCULKBLOCK = COMMON_BUILDER.comment("If sculk blocks should also apply the effect, not just sculk veins").define("Sculk blocks apply atrophy?", false);
		COMMON_BUILDER.pop();
		
		COMMON_CONFIG = COMMON_BUILDER.build();
	}
	

	@SubscribeEvent
	public static void onLoad(final ModConfigEvent.Loading configEvent) {
		
	}
	
	@SubscribeEvent
	public static void onReload(final ModConfigEvent.Reloading configEvent) {
		
	}
}
