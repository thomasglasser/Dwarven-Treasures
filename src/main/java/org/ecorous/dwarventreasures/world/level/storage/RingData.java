package org.ecorous.dwarventreasures.world.level.storage;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class RingData
{
	public static final Codec<RingData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			Codec.INT.fieldOf("susurrus_cooldown").forGetter(RingData::getSusurrusCooldown)
	).apply(instance, RingData::new));

	private int susurrusCooldown;

	public RingData(int susurrusCooldown)
	{
		this.susurrusCooldown = susurrusCooldown;
	}

	public RingData() {
		this(0);
	}

	public void setSusurrusCooldown(int susurrusCooldown)
	{
		this.susurrusCooldown = susurrusCooldown;
	}

	public void decreaseSusurrusCooldown()
	{
		this.susurrusCooldown--;
	}

	public void increaseSusurrusCooldown()
	{
		this.susurrusCooldown++;
	}

	public int getSusurrusCooldown()
	{
		return susurrusCooldown;
	}
}
