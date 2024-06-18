package top.szzz666.WorldRepair.event;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerMoveEvent;

import static top.szzz666.WorldRepair.config.WrConfig.UseShowPlayerBlockInfo;
import static top.szzz666.WorldRepair.tools.pluginUtil.ShowPlayerBlockInfo;


public class PlayerMoveEvents implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (UseShowPlayerBlockInfo) {
            ShowPlayerBlockInfo(player);
        }
    }
}