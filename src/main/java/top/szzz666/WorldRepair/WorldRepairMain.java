package top.szzz666.WorldRepair;

import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.plugin.PluginBase;
import top.szzz666.WorldRepair.command.WrCommand;
import top.szzz666.WorldRepair.event.PlayerMoveEvents;

import static top.szzz666.WorldRepair.config.WrConfig.loadConfig;
import static top.szzz666.WorldRepair.tools.pluginUtil.nkConsole;


public class WorldRepairMain extends PluginBase {
    public static Plugin plugin;
    public static Server nkServer;
    public static CommandSender consoleObjects;
    public static String ConfigPath;
    @Override
    public void onLoad(){
        //插件读取
        nkServer = getServer();
        plugin = this;
        consoleObjects = getServer().getConsoleSender();
        ConfigPath = getDataFolder().getPath();
        loadConfig();

        nkConsole("&bWorldRepair插件读取...");
    }

    @Override
    public void onEnable(){
        this.getServer().getCommandMap().register(this.getName(), new WrCommand());
            //插件开启
            getServer().getPluginManager().registerEvents(new PlayerMoveEvents(), this);

        nkConsole("&bWorldRepair插件开启");
    }

    @Override
    public void onDisable(){
        //插件关闭
        nkConsole("&bWorldRepair插件关闭");
    }

}
