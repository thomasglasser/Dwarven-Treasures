package org.ecorous.dwarventreasures.world.item.enchantment;

import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.phys.Vec3;
import org.ecorous.dwarventreasures.DwarvenTreasures;
import org.ecorous.dwarventreasures.server.DwarvenTreasuresServerConfig;
import org.ecorous.dwarventreasures.world.level.storage.DwarvenTreasuresData;
import org.ecorous.dwarventreasures.world.level.storage.RingData;

import java.util.List;

public class DwarvenTreasuresEnchantments
{
	public static final Enchantment SUSURRUS = register("susurrus", new RingEnchantment()
	{
		@Override
		public void tick(LivingEntity livingEntity, ItemStack stack)
		{
			super.tick(livingEntity, stack);
			if (TrinketsApi.getTrinketComponent(livingEntity).orElseThrow().isEquipped(s -> EnchantmentHelper.getItemEnchantmentLevel(this, s) > 0) || livingEntity.getMainHandItem() == stack || livingEntity.getOffhandItem() == stack)
			{
				if (livingEntity.getActiveEffects().stream().noneMatch(effect -> effect.getEffect() == MobEffects.INVISIBILITY && effect.getAmplifier() >= 0 && !effect.endsWithin(20)))
				{
					livingEntity.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 60));
				}
				RingData ringData = DwarvenTreasuresData.get(livingEntity, DwarvenTreasuresData.RING);
				int ticks = ringData.getSusurrusCooldown();
				if (!livingEntity.level().isClientSide) ringData.increaseSusurrusCooldown();
				List<MobEffect> effects = List.of(MobEffects.MOVEMENT_SLOWDOWN, MobEffects.BLINDNESS, MobEffects.WITHER);
				for (int i = 1; i <= effects.size(); i++)
				{
					int finalI = i;
					if (ticks >= FIVE_MINUTES * i && livingEntity.getActiveEffects().stream().noneMatch((effect) -> effect.getEffect() == effects.get(finalI - 1) && effect.getAmplifier() >= 0 && !effect.endsWithin(20)))
					{
						livingEntity.addEffect(new MobEffectInstance(effects.get(i - 1), 60));
					}
				}
			}
		}
	});
	public static final Enchantment ANOINTMENT = register("anointment", new RingEnchantment());
	public static final Enchantment RADIANCE = register("radiance", new RingEnchantment()
	{
		@Override
		public void tick(LivingEntity livingEntity, ItemStack stack)
		{
			super.tick(livingEntity, stack);
			if (TrinketsApi.getTrinketComponent(livingEntity).orElseThrow().isEquipped(s -> EnchantmentHelper.getItemEnchantmentLevel(this, s) > 0) || livingEntity.getMainHandItem() == stack || livingEntity.getOffhandItem() == stack)
			{
				List<Entity> entities = livingEntity.level().getEntities(livingEntity, livingEntity.getBoundingBox().inflate(8), target -> DwarvenTreasuresServerConfig.ringEffectTarget.test(livingEntity, target));
				entities.forEach(target -> target.setSecondsOnFire(5));
			}
		}
	});
	public static final Enchantment FROST = register("frost", new RingEnchantment()
	{
		@Override
		public void tick(LivingEntity livingEntity, ItemStack stack)
		{
			super.tick(livingEntity, stack);
			if (TrinketsApi.getTrinketComponent(livingEntity).orElseThrow().isEquipped(s -> EnchantmentHelper.getItemEnchantmentLevel(this, s) > 0) || livingEntity.getMainHandItem() == stack || livingEntity.getOffhandItem() == stack)
			{
				Level level = livingEntity.level();
				List<Entity> entities = level.getEntities(livingEntity, livingEntity.getBoundingBox().inflate(8), target -> DwarvenTreasuresServerConfig.ringEffectTarget.test(livingEntity, target));
				for (Entity entity : entities)
				{
					entity.makeStuckInBlock(level.getBlockState(entity.blockPosition()), new Vec3(0.9f, 1.5, 0.9f));
					if (level.isClientSide)
					{
						boolean bl;
						RandomSource randomSource = level.getRandom();
						bl = entity.xOld != entity.getX() || entity.zOld != entity.getZ();
						if (bl && randomSource.nextBoolean())
						{
							level.addParticle(ParticleTypes.SNOWFLAKE, entity.getX(), entity.getY() + 1, entity.getZ(), Mth.randomBetween(randomSource, -1.0f, 1.0f) * 0.083333336f, 0.05f, Mth.randomBetween(randomSource, -1.0f, 1.0f) * 0.083333336f);
						}
					}
					entity.setIsInPowderSnow(true);
					entity.setTicksFrozen(entity.getTicksFrozen() + 20);
					entity.setRemainingFireTicks(-1);
				}
				List<BlockPos> posList = BlockPos.betweenClosedStream(livingEntity.getBoundingBox().inflate(16)).toList();
				posList.forEach(pos ->
				{
					if (level.getBlockState(pos).getBlock() instanceof CropBlock) level.destroyBlock(pos, true);
				});
			}
		}

		@Override
		public void onUnequip(LivingEntity entity, EquipmentSlot slot, ItemStack from, ItemStack to, int level)
		{
			super.onUnequip(entity, slot, from, to, level);
			entity.level().getEntities(entity, entity.getBoundingBox().inflate(48)).forEach(entity1 ->
			{
				entity1.setTicksFrozen(0);
				entity1.setIsInPowderSnow(false);
			});
		}
	});

	private static Enchantment register(String name, Enchantment enchantment)
	{
		return Registry.register(BuiltInRegistries.ENCHANTMENT, DwarvenTreasures.modLoc(name), enchantment);
	}

	public static void init() {}
}
