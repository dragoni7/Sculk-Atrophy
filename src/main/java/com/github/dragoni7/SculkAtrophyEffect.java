package com.github.dragoni7;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

public class SculkAtrophyEffect extends MobEffect {

	public SculkAtrophyEffect() {
		super(MobEffectCategory.HARMFUL, 0Xb1fcb6);
		this.addAttributeModifier(Attributes.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160890", -0.2D, AttributeModifier.Operation.MULTIPLY_BASE);
	}
	
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		
		if (entity instanceof Player) {
			if (entity.isCrouching()) {
				entity.hurt(DamageSource.MAGIC, Config.DAMAGE.get().floatValue());
			}
		}
	}
	
	public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration > 0;
    }
	
	public String getDescriptionId() {
        return "sculkatrophy.effect.sculk_atrophy";
    }

}
