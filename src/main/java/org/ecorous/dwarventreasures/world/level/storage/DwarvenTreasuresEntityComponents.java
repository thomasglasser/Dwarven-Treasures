package org.ecorous.dwarventreasures.world.level.storage;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import net.minecraft.world.entity.LivingEntity;
import org.ecorous.dwarventreasures.DwarvenTreasures;

public class DwarvenTreasuresEntityComponents implements EntityComponentInitializer
{
	public static final ComponentKey<RingComponent> RING = ComponentRegistry.getOrCreate(DwarvenTreasures.modLoc("ring"), RingComponent.class);

	@Override
	public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry)
	{
		registry.registerForPlayers(RING, entity -> new RingComponent());

		registry.registerFor(LivingEntity.class, RING, entity -> new RingComponent());
	}
}
