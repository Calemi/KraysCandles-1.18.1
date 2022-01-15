package com.tm.krayscandles.item;

import com.tm.krayscandles.init.InitItems;
import com.tm.krayscandles.ritual.RitualRecipe;
import net.minecraft.world.item.Item;

public class ItemCrystal extends ItemRitual {

    public ItemCrystal(RitualRecipe ritualRecipe) {
        super(ritualRecipe);
    }

    public enum CrystalType {

        AMPLIFYING (0, InitItems.CRYSTAL_AMPLIFYING.get()),
        POTENCY (1, InitItems.CRYSTAL_POTENCY.get()),
        INVERTING (2, InitItems.CRYSTAL_INVERTING.get());

        private final int id;
        private final Item item;

        CrystalType(int id, Item item) {
            this.id = id;
            this.item = item;
        }

        public int getId() {
            return id;
        }

        public Item getItem() {
            return item;
        }

        public static CrystalType getCrystalTypeFromId(int id) {

            for (CrystalType type : values()) {
                if (type.getId() == id) return type;
            }

            return null;
        }

        public static CrystalType getCrystalTypeFromItem(Item item) {

            for (CrystalType type : values()) {
                if (type.getItem() == item) return type;
            }

            return null;
        }
    }
}
