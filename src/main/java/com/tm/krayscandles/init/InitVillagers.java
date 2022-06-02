package com.tm.krayscandles.init;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Lifecycle;
import com.tm.calemicore.util.helper.LogHelper;
import com.tm.krayscandles.main.KCReference;
import com.tm.krayscandles.mixin.SingleJigsawAccess;
import it.unimi.dsi.fastutil.objects.Object2IntLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.core.WritableRegistry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.ProcessorLists;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.Collectors;

public class InitVillagers {

    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, KCReference.MOD_ID);

    public static final RegistryObject<PoiType> POI_CANDLE = POI_TYPES.register("candle", () -> new PoiType("candle", ImmutableSet.copyOf(InitItems.WAX_MELTER.get().getStateDefinition().getPossibleStates()), 1, 1));

    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister.create(ForgeRegistries.PROFESSIONS, KCReference.MOD_ID);

    public static final RegistryObject<VillagerProfession> TYPE_CANDLE = VILLAGER_PROFESSIONS.register("candle", () -> new VillagerProfession("candle", POI_CANDLE.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_LIBRARIAN));

    /**
     * All of the credit for this method goes to the creators of the Immersive Engineering mod.
     * https://github.com/BluSunrize/ImmersiveEngineering/blob/86211504ad15b866e6df4685eb16c239cd6f16bb/src/main/java/blusunrize/immersiveengineering/common/world/Villages.java
     */
    public static void addVillageStructure(String biome, String structureName, int weight) {

        ResourceLocation pool = new ResourceLocation("village/" + biome + "/houses");
        ResourceLocation toAdd = new ResourceLocation(KCReference.MOD_ID, "village/" + biome + "/" + structureName);

        LogHelper.log(KCReference.MOD_NAME, "Using: " + pool);
        LogHelper.log(KCReference.MOD_NAME, "Adding: "+ toAdd);

        StructureTemplatePool old = BuiltinRegistries.TEMPLATE_POOL.get(pool);
        int id = BuiltinRegistries.TEMPLATE_POOL.getId(old);

        // Fixed seed to prevent inconsistencies between different worlds
        List<StructurePoolElement> shuffled;
        if ( old != null) shuffled = old.getShuffledTemplates(new Random(0));
        else shuffled = ImmutableList.of();

        Object2IntMap<StructurePoolElement> newPieces = new Object2IntLinkedOpenHashMap<>();

        for(StructurePoolElement p : shuffled) newPieces.computeInt(p, (StructurePoolElement pTemp, Integer i) -> (i==null?0: i)+1);

        newPieces.put(SingleJigsawAccess.construct(Either.left(toAdd), ProcessorLists.EMPTY, StructureTemplatePool.Projection.RIGID), weight);

        List<Pair<StructurePoolElement, Integer>> newPieceList = newPieces.object2IntEntrySet().stream()
                .map(e -> Pair.of(e.getKey(), e.getIntValue()))
                .collect(Collectors.toList());

        ResourceLocation name = old.getName();

        ((WritableRegistry<StructureTemplatePool>)BuiltinRegistries.TEMPLATE_POOL).registerOrOverride(
                OptionalInt.of(id),
                ResourceKey.create(BuiltinRegistries.TEMPLATE_POOL.key(), name),
                new StructureTemplatePool(pool, name, newPieceList),
                Lifecycle.stable()
        );
    }
}
