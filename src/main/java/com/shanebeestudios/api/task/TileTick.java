package com.shanebeestudios.api.task;

import com.shanebeestudios.api.VirtualFurnaceAPI;
import com.shanebeestudios.api.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.*;

public class TileTick extends BukkitRunnable {

    private final VirtualFurnaceAPI virtualFurnaceAPI;
  //  private final TileManager tileManager;
    private int tick;
    private int id;
    private boolean running;

    public TileTick(VirtualFurnaceAPI virtualFurnaceAPI) {
        this.virtualFurnaceAPI = virtualFurnaceAPI;
       // this.tileManager = virtualFurnaceAPI.getTileManager();
        this.tick = 0;
    }

    public void start() {
        BukkitTask task = this.runTaskTimerAsynchronously(virtualFurnaceAPI.getJavaPlugin(), 20, 1);
        id = task.getTaskId();
    }

    @Override
    public void run() {
        running = true;
        try {
/*            for (VirtualChunk chunk : tileManager.getLoadedChunks()) {
                if (!running) {
                    return;
                }
                chunk.tick();
            }*/
            Util.log("*Gasp* This shouldn't appear here!");
        } catch (Exception ignore) {
        }
        tick++;
        if (tick >= 6000) {
         //   this.tileManager.saveAllTiles();
            this.tick = 0;
        }
        running = false;
    }

    @Override
    public synchronized void cancel() throws IllegalStateException {
        this.running = false;
        Bukkit.getScheduler().cancelTask(id);
    }

}
