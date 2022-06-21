package com.github.dragoni7;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(SculkAtrophy.MODID)
public class SculkAtrophy
{
    public static final String MODID = "sculkatrophy";
    public static final Logger LOGGER = LogManager.getLogger();
    public static DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, SculkAtrophy.MODID);
    public static final RegistryObject<MobEffect> SCULK_ATROPHY = EFFECTS.register("sculk_atrophy", () -> new SculkAtrophyEffect());
    
    public SculkAtrophy()
    {
    	ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
    	
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        EFFECTS.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    private void commonSetup(FMLCommonSetupEvent event) {
    	event.enqueueWork(() -> {
    		Networking.registerMessages();
    	});
    }
    
    @Mod.EventBusSubscriber(modid = MODID)
    public static class ServerModEvents {
    	
    	@SubscribeEvent
    	public static void onSculkStep(PlayerTickEvent event) {
    		Player player = event.player;
    		RandomSource rand = player.getRandom();
    		Boolean check = rand.nextInt(Config.PROBABILITY.get()) == 0;
    		
    		if (isOnSculkBlock(player) && !player.hasEffect(SCULK_ATROPHY.get())) {
    			Vec3 motion = player.getDeltaMovement();
    			if ((motion.x != 0 || motion.z != 0) && check) {
    				Networking.sendToServer(new PacketApplySculkAtrophy());
    			}
    		}
        }
    	
    	private static boolean isOnSculkBlock(Player player) {
    		if (player.getFeetBlockState().is(Blocks.SCULK_VEIN)) {
    			return true;
    		}
    		else if (Config.SCULKBLOCK.get() && player.getFeetBlockState().is(Blocks.SCULK)) {
    			return true;
			}
    		
    		return false;
    	}
    }
}
