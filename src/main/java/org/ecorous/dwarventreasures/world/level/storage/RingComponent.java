package org.ecorous.dwarventreasures.world.level.storage;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.entity.PlayerComponent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;

public class RingComponent implements ComponentV3, PlayerComponent<RingComponent>, AutoSyncedComponent
{
	public static final Codec<RingComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			Codec.INT.fieldOf("susurrus_cooldown").forGetter(RingComponent::getSusurrusCooldown)
	).apply(instance, RingComponent::new));

	private int susurrusCooldown;

	public RingComponent(int susurrusCooldown)
	{
		this.susurrusCooldown = susurrusCooldown;
	}

	public RingComponent() {
		this(0);
	}

	@Override
	public void readFromNbt(CompoundTag tag)
	{
		RingComponent ringComponent = CODEC.decode(NbtOps.INSTANCE, tag.get("Ring")).get().orThrow().getFirst();

		this.susurrusCooldown = ringComponent.getSusurrusCooldown();
	}

	@Override
	public void writeToNbt(CompoundTag tag)
	{
		tag.put("Ring", CODEC.encodeStart(NbtOps.INSTANCE, this).result().orElseThrow());
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

	@Override
	public boolean shouldCopyForRespawn(boolean lossless, boolean keepInventory, boolean sameCharacter)
	{
		return keepInventory;
	}
}
