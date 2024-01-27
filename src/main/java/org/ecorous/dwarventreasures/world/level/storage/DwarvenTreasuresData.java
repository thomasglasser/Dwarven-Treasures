package org.ecorous.dwarventreasures.world.level.storage;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.world.entity.Entity;
import org.ecorous.dwarventreasures.DwarvenTreasures;

@SuppressWarnings({"UnstableApiUsage"})
public class DwarvenTreasuresData
{
	public static final AttachmentType<RingData> RING = AttachmentRegistry.<RingData>builder().initializer(RingData::new).persistent(RingData.CODEC).buildAndRegister(DwarvenTreasures.modLoc("ring"));

	public static <T> T get(Entity entity, AttachmentType<T> type)
	{
		return entity.getAttachedOrCreate(type);
	}

	public static <T> void set(Entity entity, AttachmentType<T> type, T data)
	{
		entity.setAttached(type, data);
	}
}
