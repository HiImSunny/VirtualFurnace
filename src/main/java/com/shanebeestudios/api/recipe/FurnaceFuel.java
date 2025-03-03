package com.shanebeestudios.api.recipe;

import com.shanebeestudios.api.util.Util;
import org.bukkit.*;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Fuel for furnace
 */
@SuppressWarnings("unused")
public class FurnaceFuel extends Fuel {

    // 1.14+
    public static final FurnaceFuel SCAFFOLDING;
    public static final FurnaceFuel CARTOGRAPHY_TABLE;
    public static final FurnaceFuel FLETCHING_TABLE;
    public static final FurnaceFuel SMITHING_TABLE;
    public static final FurnaceFuel LECTERN;
    public static final FurnaceFuel COMPOSTER;
    public static final FurnaceFuel BARREL;
    public static final FurnaceFuel LOOM;
    public static final FurnaceFuel ANY_SIGN;
    public static final FurnaceFuel BAMBOO;
    public static final FurnaceFuel ANY_WOOD_FENCE;

    private static final List<FurnaceFuel> VANILLA_FUELS = new ArrayList<>();

    public static final FurnaceFuel LAVA_BUCKET = get("lava_bucket", Material.LAVA_BUCKET, 20000);
    public static final FurnaceFuel BLOCK_OF_COAL = get("block_of_coal", Material.COAL_BLOCK, 16000);
    public static final FurnaceFuel DRIED_KELP_BLOCK = get("dried_kelp_block", Material.DRIED_KELP_BLOCK, 4000);
    public static final FurnaceFuel BLAZE_ROD = get("blaze_rod", Material.BLAZE_ROD, 2400);
    public static final FurnaceFuel COAL = get("coal", Material.COAL, 1600);
    public static final FurnaceFuel CHARCOAL = get("charcoal", Material.CHARCOAL, 1600);
    public static final FurnaceFuel ANY_BOAT = get("any_boat", Tag.ITEMS_BOATS, 1200);
    public static final FurnaceFuel ANY_LOG = get("any_log", Tag.LOGS, 300);
    public static final FurnaceFuel ANY_PLANK = get("any_plank", Tag.PLANKS, 300);
    public static final FurnaceFuel ANY_WOOD_PRESSURE_PLATE = get("any_wood_pressure_plate", Tag.WOODEN_PRESSURE_PLATES, 300);
    public static final FurnaceFuel ANY_WOOD_STAIR = get("any_wood_stair", Tag.WOODEN_STAIRS, 300);
    public static final FurnaceFuel ANY_WOOD_TRAPDOOR = get("any_wood_trapdoor", Tag.WOODEN_TRAPDOORS, 300);
    public static final FurnaceFuel CRAFTING_TABLE = get("crafting_table", Material.CRAFTING_TABLE, 300);
    public static final FurnaceFuel BOOKSHELF = get("bookshelf", Material.BOOKSHELF, 300);
    public static final FurnaceFuel CHEST = get("chest", Material.CHEST, 300);
    public static final FurnaceFuel TRAPPED_CHEST = get("trapped_chest", Material.TRAPPED_CHEST, 300);
    public static final FurnaceFuel DAYLIGHT_DETECTOR = get("daylight_detector", Material.DAYLIGHT_DETECTOR, 300);
    public static final FurnaceFuel JUKEBOX = get("jukebox", Material.JUKEBOX, 300);
    public static final FurnaceFuel NOTE_BLOCK = get("note_block", Material.NOTE_BLOCK, 300);
    public static final FurnaceFuel MUSHROOM_STEM = get("mushroom_stem", Material.MUSHROOM_STEM, 300);
    public static final FurnaceFuel BROWN_MUSHROOM_BLOCK = get("brown_mushroom_block", Material.BROWN_MUSHROOM_BLOCK, 300);
    public static final FurnaceFuel RED_MUSHROOM_BLOCK = get("red_mushroom_block", Material.RED_MUSHROOM_BLOCK, 300);
    public static final FurnaceFuel ANY_BANNER = get("any_banner", Tag.BANNERS, 300);
    public static final FurnaceFuel ANY_WOODEN_SLAB = get("any_wooden_slab", Tag.WOODEN_SLABS, 150);
    public static final FurnaceFuel BOW = get("bow", Material.BOW, 300);
    public static final FurnaceFuel FISHING_ROD = get("fishing_rod", Material.FISHING_ROD, 300);
    public static final FurnaceFuel LADDER = get("ladder", Material.LADDER, 300);
    public static final FurnaceFuel ANY_WOODEN_BUTTON = get("any_wooden_button", Tag.WOODEN_BUTTONS, 100);
    public static final FurnaceFuel WOODEN_PICKAXE = get("wooden_pickaxe", Material.WOODEN_PICKAXE, 200);
    public static final FurnaceFuel WOODEN_SHOVEL = get("wooden_shovel", Material.WOODEN_SHOVEL, 200);
    public static final FurnaceFuel WOODEN_HOE = get("wooden_hoe", Material.WOODEN_HOE, 200);
    public static final FurnaceFuel WOODEN_AXE = get("wooden_axe", Material.WOODEN_AXE, 200);
    public static final FurnaceFuel WOODEN_SWORD = get("wooden_sword", Material.WOODEN_SWORD, 200);
    public static final FurnaceFuel ANY_WOODEN_DOOR = get("any_wooden_door", Tag.WOODEN_TRAPDOORS, 200);
    public static final FurnaceFuel BOWL = get("bowl", Material.BOWL, 100);
    public static final FurnaceFuel ANY_SAPLING = get("any_sapling", Tag.SAPLINGS, 100);
    public static final FurnaceFuel STICK = get("stick", Material.STICK, 100);
    public static final FurnaceFuel ANY_WOOL = get("any_wool", Tag.WOOL, 100);
    public static final FurnaceFuel ANY_CARPET = get("any_carpet", Tag.CARPETS, 67);

    static {
        if (Util.isRunningMinecraft(1, 14)) {
            SCAFFOLDING = get("scaffolding", Material.SCAFFOLDING, 400);
            CARTOGRAPHY_TABLE = get("cartography_table", Material.CARTOGRAPHY_TABLE, 300);
            FLETCHING_TABLE = get("fletching_table", Material.FLETCHING_TABLE, 300);
            SMITHING_TABLE = get("smithing_table", Material.SMITHING_TABLE, 300);
            LECTERN = get("lectern", Material.LECTERN, 300);
            COMPOSTER = get("composter", Material.COMPOSTER, 300);
            BARREL = get("barrel", Material.BARREL, 300);
            LOOM = get("loom", Material.LOOM, 300);
            ANY_SIGN = get("any_sign", Tag.SIGNS, 200);
            BAMBOO = get("bamboo", Material.BAMBOO, 50);
            ANY_WOOD_FENCE = get("any_wood_fence", Tag.WOODEN_FENCES, 300);
        } else {
            SCAFFOLDING = null;
            CARTOGRAPHY_TABLE = null;
            FLETCHING_TABLE = null;
            SMITHING_TABLE = null;
            LECTERN = null;
            COMPOSTER = null;
            BARREL = null;
            LOOM = null;
            BAMBOO = null;
            ANY_SIGN = get("any_sign", Material.getMaterial("SIGN"), 200);
            ANY_WOOD_FENCE = get("any_wood_fence", Material.OAK_FENCE, 300);
        }
    }

    private final int burnTime;

    /**
     * Create a new fuel for furnaces
     *
     * @param key          Key for recipe
     * @param fuelMaterial Fuel to register
     * @param burnTime     Time this fuel will burn for (in ticks)
     */
    public FurnaceFuel(NamespacedKey key, Material fuelMaterial, int burnTime) {
        super(key, new ItemStack(fuelMaterial), null);
        this.burnTime = burnTime;
    }

    /**
     * Create a new fuel for furnaces
     *
     * @param key      Key for recipe
     * @param fuelTag  Tag to use as fuel
     * @param burnTime Time this fuel will burn for (in ticks)
     */
    public FurnaceFuel(NamespacedKey key, Tag<Material> fuelTag, int burnTime) {
        super(key, null, fuelTag);
        this.burnTime = burnTime;
    }

    private static FurnaceFuel get(String name, Material fuel, int burnTicks) {
        FurnaceFuel fuel1 = new FurnaceFuel(Util.getKey("mc_fuel_" + name), fuel, burnTicks);
        VANILLA_FUELS.add(fuel1);
        return fuel1;
    }

    private static FurnaceFuel get(String name, Tag<Material> fuelTag, int burnTicks) {
        FurnaceFuel fuel = new FurnaceFuel(Util.getKey("mc_fuel_" + name), fuelTag, burnTicks);
        VANILLA_FUELS.add(fuel);
        return fuel;
    }

    /**
     * Get an immutable list of vanilla MC fuels
     *
     * @return List of vanilla fuels
     */
    public static List<FurnaceFuel> getVanillaFuels() {
        return Collections.unmodifiableList(VANILLA_FUELS);
    }

    /**
     * Get the key from this fuel
     *
     * @return Key from fuel
     */
    @Override
    public @NotNull NamespacedKey getKey() {
        return this.key;
    }

    /**
     * Get the material of this fuel
     *
     * @return Material of fuel (null if non-existent)
     */
    public Material getFuel() {
        return this.fuelItem.getType();
    }

    /**
     * Get the tag of this fuel
     *
     * @return Tag of fuel (null if non-existent)
     */
    public Tag<Material> getTag() {
        return tag;
    }

    /**
     * Get the time this fuel will burn for
     *
     * @return Time fuel will burn
     */
    public int getBurnTime() {
        return this.burnTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FurnaceFuel fuel = (FurnaceFuel) o;
        return burnTime == fuel.burnTime && Objects.equals(key, fuel.key) &&
                fuelItem.getType() == fuel.fuelItem.getType() && Objects.equals(tag, fuel.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, fuelItem.getType(), tag, burnTime);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public String toString() {
        return "Fuel{" +
                "key=" + key +
                (fuelItem.getType() != null ? ", material=" + fuelItem.getType() : ", tag=" + tag.getKey()) +
                ", burnTime=" + burnTime +
                '}';
    }

}
