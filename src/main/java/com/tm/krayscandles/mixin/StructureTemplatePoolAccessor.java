package com.tm.krayscandles.mixin;

import com.mojang.datafixers.util.Pair;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

/**
 * All of the credit for this class goes to the creators of the Waystones mod.
 * https://github.com/ModdingForBlockheads/Waystones/blob/1.18.x/shared/src/main/java/net/blay09/mods/waystones/mixin/StructureTemplatePoolAccessor.java
 */
@Mixin(StructureTemplatePool.class)
public interface StructureTemplatePoolAccessor {
    @Accessor
    List<Pair<StructurePoolElement, Integer>> getRawTemplates();

    @Accessor
    @Mutable
    void setRawTemplates(List<Pair<StructurePoolElement, Integer>> rawTemplates);

    @Accessor
    List<StructurePoolElement> getTemplates();

    @Accessor
    @Mutable
    void setTemplates(List<StructurePoolElement> templates);
}