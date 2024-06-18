package top.szzz666.WorldRepair.config;

import cn.nukkit.utils.Config;

import java.util.HashMap;

import static top.szzz666.WorldRepair.WorldRepairMain.ConfigPath;
import static top.szzz666.WorldRepair.WorldRepairMain.plugin;


public class WrConfig {
    public static String CommandName;
    public static boolean UseShowPlayerBlockInfo;
    public static int ScopeX;
    public static int ScopeY;
    public static int ScopeZ;



    public static boolean loadConfig() {
        plugin.saveResource("config.yml");
        plugin.saveResource("blockConfig.yml");
        Config config = new Config(ConfigPath + "/config.yml", Config.YAML);
        CommandName = config.getString("CommandName");
        UseShowPlayerBlockInfo = config.getBoolean("UseShowPlayerBlockInfo");
        ScopeX = config.getInt("ScopeX");
        ScopeY = config.getInt("ScopeY");
        ScopeZ = config.getInt("ScopeZ");
        config.save();
        return true;
    }

    public static HashMap<String, String> getBlockConfig() {
        HashMap<String, String> Block;
        Config config = new Config(ConfigPath + "/blockConfig.yml", Config.YAML);
        Block = (HashMap<String, String>) config.get("Block");
        config.save();
        return Block;
    }

}
