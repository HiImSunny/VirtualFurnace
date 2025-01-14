package com.shanebeestudios.api.event.machine;

import com.shanebeestudios.api.event.Event;
import com.shanebeestudios.api.machine.Furnace;
import com.shanebeestudios.api.recipe.FurnaceFuel;
import org.bukkit.event.*;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * Called when an ItemStack starts burning as a fuel in a {@link Furnace}
 */
@SuppressWarnings("unused")
public class FurnaceFuelIgniteEvent extends Event implements Cancellable {

    private static final HandlerList handlerList = new HandlerList();

    private final Furnace furnace;
    private final ItemStack fuelItem;
    private final FurnaceFuel fuel;
    private int burnTime;
    private boolean cancelled;

    public FurnaceFuelIgniteEvent(@NotNull Furnace furnace, @NotNull ItemStack fuelItem, @NotNull FurnaceFuel fuel, int burnTime) {
        super(true);

        this.furnace = furnace;
        this.fuelItem = fuelItem;
        this.fuel = fuel;
        this.burnTime = burnTime;
        this.cancelled = false;
    }

    /**
     * Get the Furnace for this event
     *
     * @return Furnace for this event
     */
    public Furnace getFurnace() {
        return furnace;
    }

    /**
     * Get the ItemStack that will burn in this even
     *
     * @return ItemStack that will burn in this even
     */
    public ItemStack getFuelItem() {
        return fuelItem;
    }

    /**
     * Get the Fuel for this event
     *
     * @return Fuel for this event
     */
    public FurnaceFuel getFuel() {
        return fuel;
    }

    /**
     * Get the time the fuel will burn for
     *
     * @return Time the fuel will burn for
     */
    public int getBurnTime() {
        return burnTime;
    }

    /**
     * Set the time the fuel will burn for
     *
     * @param burnTime Time the fuel will burn for
     */
    public void setBurnTime(int burnTime) {
        this.burnTime = burnTime;
    }

    /**
     * Gets the cancellation state of this event.
     * <p>A cancelled event will not be executed in the server,
     * but will still pass to other plugins</p>
     *
     * @return true if this event is cancelled
     */
    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    /**
     * Sets the cancellation state of this event.
     * <p>A cancelled event will not be executed in the server,
     * but will still pass to other plugins</p>
     *
     * @param cancel Whether to cancel the event
     */
    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

}
