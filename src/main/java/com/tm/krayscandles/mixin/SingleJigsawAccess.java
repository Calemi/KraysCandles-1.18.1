package com.tm.krayscandles.mixin;

import com.mojang.datafixers.util.Either;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.LegacySinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

/**
 * All of the credit for this class goes to the creators of the Immersive Engineering mod.
 * https://github.com/BluSunrize/ImmersiveEngineering/blob/86211504ad15b866e6df4685eb16c239cd6f16bb/src/main/java/blusunrize/immersiveengineering/mixin/accessors/SingleJigsawAccess.java
 */
@Mixin(LegacySinglePoolElement.class)
public interface SingleJigsawAccess
{
    @Invoker("<init>")
    static LegacySinglePoolElement construct(Either<ResourceLocation, StructureTemplate> p_210348_, Holder<StructureProcessorList> p_210349_, StructureTemplatePool.Projection p_210350_) {
        throw new UnsupportedOperationException("Replaced by Mixin");
    }
}