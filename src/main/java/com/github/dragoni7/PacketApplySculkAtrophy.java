package com.github.dragoni7;

import java.util.function.Supplier;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.network.NetworkEvent;

public class PacketApplySculkAtrophy {
	
	public PacketApplySculkAtrophy() {
		
	}
	
	public PacketApplySculkAtrophy(FriendlyByteBuf buf) {
		
	}
	
	public void toBytes(FriendlyByteBuf buf) {
		
	}
	
	public boolean handle(Supplier<NetworkEvent.Context> context) {
		context.get().enqueueWork(() -> {
			if (context.get().getDirection().getReceptionSide().isServer()) {
				ServerPlayer player = context.get().getSender();
				player.addEffect(new MobEffectInstance(SculkAtrophy.SCULK_ATROPHY.get(), Config.DURATION.get()));
			}
		});
		return true;
	}

}
