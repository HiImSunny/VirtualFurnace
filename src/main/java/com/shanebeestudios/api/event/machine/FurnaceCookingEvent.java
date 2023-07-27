package com.shanebeestudios.api.event.machine;

import com.shanebeestudios.api.event.Event;
import com.shanebeestudios.api.machine.Furnace;
import org.bukkit.event.*;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * Called when an ItemStack is cooking in a {@link Furnace}
 */
public class FurnaceCookingEvent extends Event implements Cancellable {

    private static final HandlerList handlerList = new HandlerList();

    private final Furnace furnace;
    private final ItemStack source;
    private boolean cancelled;

    public FurnaceCookingEvent(@NotNull Furnace furnace, @NotNull ItemStack source) {
        super(true);

        this.furnace = furnace;
        this.source = source;
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
     * Get the source item, the item that was cooking.
     *
     * @return Source item
     */
    public ItemStack getSource() {
        return source;
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
