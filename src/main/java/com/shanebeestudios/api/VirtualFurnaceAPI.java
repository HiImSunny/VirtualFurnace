package com.shanebeestudios.api;

import com.shanebeestudios.api.machine.*;
import com.shanebeestudios.api.property.*;
import com.shanebeestudios.api.task.FurnaceTick;
import com.shanebeestudios.api.tile.FurnaceTile;
import com.shanebeestudios.api.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Properties;

/**
 * Main API for VirtualFurnace
 */
@SuppressWarnings("unused")
public class VirtualFurnaceAPI {

    private static VirtualFurnaceAPI instance;

    static {
        ConfigurationSerialization.registerClass(Furnace.class, "furnace");
        ConfigurationSerialization.registerClass(FurnaceProperties.class, "furnace_properties");
        ConfigurationSerialization.registerClass(BrewingStand.class, "brewing_stand");
        ConfigurationSerialization.registerClass(BrewingProperties.class, "brewing_stand_properties");
        ConfigurationSerialization.registerClass(FurnaceTile.class, "tile");
    }

    private final String apiVersion;
    private final JavaPlugin plugin;
    private boolean enabled = true;
    private boolean silentStart = false;
    private boolean clearDataFileOnDisable = false;
    private RecipeManager recipeManager;
    private FurnaceManager furnaceManager;
    private BrewingManager brewingManager;
    // private TileManager tileManager;
    private FurnaceTick furnaceTick;

    public VirtualFurnaceAPI(@NotNull JavaPlugin javaPlugin, boolean silentStart, boolean disableMetrics, boolean clearDataFileOnDisable) {
        this(javaPlugin, silentStart, disableMetrics);

        this.clearDataFileOnDisable = clearDataFileOnDisable;
    }

    /**
     * Create a new instance of the VirtualFurnaceAPI
     *
     * @param javaPlugin Your plugin
     */
    public VirtualFurnaceAPI(JavaPlugin javaPlugin, boolean silentStart) {
        this(javaPlugin, silentStart, false);
    }

    /**
     * Create a new instance of the VirtualFurnaceAPI
     * <p>If you plan on using bStats metrics in your plugin,
     * disable the bStats metrics for the API to prevent conflict.</p>
     *
     * @param javaPlugin     Your plugin
     * @param disableMetrics Disable metrics for VirtualFurnaceAPI (If you are using metrics in your own plugin)
     */
    public VirtualFurnaceAPI(@NotNull JavaPlugin javaPlugin, boolean silentStart, boolean disableMetrics) {
        instance = this;
        this.plugin = javaPlugin;
        this.apiVersion = getVersion();
        this.silentStart = silentStart;
        if (!Util.classExists("org.bukkit.persistence.PersistentDataHolder")) {
            this.recipeManager = null;
            this.furnaceManager = null;
            this.brewingManager = null;
            //this.tileManager = null;
            this.furnaceTick = null;
            //this.tileTick = null;
            Util.error("&cFailed to initialize VirtualFurnaceAPI");
            Util.error("&7  - Bukkit version: &b" + Bukkit.getBukkitVersion() + " &7is not supported!");
            this.enabled = false;
            return;
        }

        if (!disableMetrics) {
            new Metrics(javaPlugin, 7021, this);
        }

        this.recipeManager = new RecipeManager();
        this.furnaceManager = new FurnaceManager(this);
        this.brewingManager = new BrewingManager(this);
        //this.tileManager = new TileManager(this);
        //this.tileManager.load();
        this.furnaceTick = new FurnaceTick(this);
        this.furnaceTick.start();
        //this.tileTick = new TileTick(this);
        //this.tileTick.start();
        Bukkit.getPluginManager().registerEvents(new FurnaceListener(this), javaPlugin);
        if (!silentStart) {
            Util.log("Initialized VirtualFurnaceAPI version: &b" + getVersion());
        }

    }
    //private TileTick tileTick;

    /**
     * Get a static instance of the VirtualFurnaceAPI
     * <p><b>NOTE:</b> You have to create once first or this will return null</p>
     *
     * @return Instance of the VirtualFurnaceAPI
     */
    public static VirtualFurnaceAPI getInstance() {
        return instance;
    }

    public boolean isSilentStart() {
        return silentStart;
    }

    public boolean isClearDataFileOnDisable() {
        return clearDataFileOnDisable;
    }

    public BrewingManager getBrewingManager() {
        return brewingManager;
    }

    /**
     * Disable the API
     * <p>Stops ticking, saves all furnaces and tiles to file.
     * This should be used in a plugin's {@link Plugin#onDisable() onDisable()} method</p>
     */
    public void disableAPI(boolean silentStop) {
        this.furnaceTick.cancel();
        //this.tileTick.cancel();
        this.furnaceTick = null;
        //this.tileTick = null;
        this.furnaceManager.shutdown();
        this.brewingManager.shutdown();
        // this.tileManager.shutdown();

        if (clearDataFileOnDisable) {
            this.furnaceManager.clearFurnaceFile();
            this.brewingManager.clearBrewingFile();
        }

        this.furnaceManager = null;
        this.brewingManager = null;
        //this.tileManager = null;
        this.recipeManager = null;

        if (!silentStop)
            Util.log("Shut down API!");
    }

    /**
     * Disable the furnace tick
     * <p>This is good to use in your onDisable() method, to prevent tasks still running on server shutdown/reload</p>
     */
    public void disableFurnaceTick() {
        this.furnaceTick.cancel();
    }

    /**
     * Get the plugin that was passed into this instance
     *
     * @return Plugin in this instance
     */
    public JavaPlugin getJavaPlugin() {
        return plugin;
    }

    /**
     * Get an instance of the recipe manager
     *
     * @return Instance of recipe manager
     */
    public RecipeManager getRecipeManager() {
        return recipeManager;
    }

    /**
     * Get an instance of the furnace manager
     *
     * @return Instance of the furnace manager
     */
    public FurnaceManager getFurnaceManager() {
        return furnaceManager;
    }


    /**
     * Get an instance of the furnace tick class
     *
     * @return Instance of furnace tick
     */
    public FurnaceTick getFurnaceTick() {
        return furnaceTick;
    }

    /**
     * Get the version of this API
     *
     * @return Version of the API
     */
    public String getAPIVersion() {
        return this.apiVersion;
    }

    /**
     * Check whether the API is enabled
     *
     * @return True if enabled, false if failed to initialize
     */
    public boolean isEnabled() {
        return this.enabled;
    }

    private String getVersion() {
        Properties prop = new Properties();
        try {
            prop.load(getJavaPlugin().getResource("VirtualFurnace.properties"));
            return prop.getProperty("api-version");
        } catch (IOException e) {
            return "unknown-version";
        }
    }

}
