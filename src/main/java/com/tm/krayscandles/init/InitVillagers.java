package com.tm.krayscandles.init;

import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import com.tm.krayscandles.main.KCReference;
import com.tm.krayscandles.mixin.StructureTemplatePoolAccessor;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.levelgen.feature.structures.LegacySinglePoolElement;
import net.minecraft.world.level.levelgen.feature.structures.StructurePoolElement;
import net.minecraft.world.level.levelgen.feature.structures.StructureTemplatePool;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

public class InitVillagers {

    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, KCReference.MOD_ID);

    public static final RegistryObject<PoiType> POI_CANDLE = POI_TYPES.register("candle", () -> new PoiType("candle", ImmutableSet.copyOf(InitItems.STONE_ALTAR_TILE.get().getStateDefinition().getPossibleStates()), 1, 1));

    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister.create(ForgeRegistries.PROFESSIONS, KCReference.MOD_ID);

    public static final RegistryObject<VillagerProfession> TYPE_CANDLE = VILLAGER_PROFESSIONS.register("candle", () -> new VillagerProfession("candle", POI_CANDLE.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_LIBRARIAN));

    /**
     * All of the credit for this method goes to the creators of the Waystones mod.
     * https://github.com/ModdingForBlockheads/Waystones/blob/1.18.x/shared/src/main/java/net/blay09/mods/waystones/worldgen/ModWorldGen.java
     */
    public static void addVillageStructure(RegistryAccess registryAccess, String villagePiece, ResourceLocation structure, int weight) {

        LegacySinglePoolElement piece = StructurePoolElement.legacy(structure.toString()).apply(StructureTemplatePool.Projection.RIGID);
        StructureTemplatePool pool = registryAccess.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).getOptional(new ResourceLocation(villagePiece)).orElse(null);

        if (pool != null) {
            var poolAccessor = (StructureTemplatePoolAccessor) pool;
            // pretty sure this can be an immutable list (when datapacked) so gotta make a copy to be safe.
            List<StructurePoolElement> listOfPieces = new ArrayList<>(poolAccessor.getTemplates());

            for (int i = 0; i < weight; i++) {
                listOfPieces.add(piece);
            }
            poolAccessor.setTemplates(listOfPieces);

            List<Pair<StructurePoolElement, Integer>> listOfWeightedPieces = new ArrayList<>(poolAccessor.getRawTemplates());
            listOfWeightedPieces.add(new Pair<>(piece, weight));
            poolAccessor.setRawTemplates(listOfWeightedPieces);
        }
    }
}
