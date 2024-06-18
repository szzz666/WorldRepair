package top.szzz666.WorldRepair.tools;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.level.Position;
import cn.nukkit.utils.TextFormat;

import static top.szzz666.WorldRepair.WorldRepairMain.plugin;
import static top.szzz666.WorldRepair.config.WrConfig.*;


public class pluginUtil {
    //分析与运行
    public static void analysisAndRun(String o, String n, Position player) {
        String[] oldBlock = o.split(":");
        String[] newBlock = n.split(":");
        int oldBlockId = Integer.parseInt(oldBlock[0]);
        int oldBlockDate = Integer.parseInt(oldBlock[1]);
        int newBlockId = Integer.parseInt(newBlock[0]);
        int newBlockDate = Integer.parseInt(newBlock[1]);
        BlockReplacement(player, oldBlockId, oldBlockDate, newBlockId, newBlockDate);
    }
    //显示玩家脚块 ID
    public static void ShowPlayerBlockInfo(Player player) {
        Position position = player.getPosition();
        Position feetBlockPosition = new Position(position.getX(), position.getY() - 1, position.getZ(), position.getLevel());
        Block block = feetBlockPosition.getLevelBlock();
        int blockId = block.getId();
        int blockData = block.getDamage();
        player.sendActionBar("§e脚方块ID§b [" + blockId + " : " + blockData + "]");
    }

    // 方块替换
    public static void BlockReplacement(Position p, int oldBlockId, int oldBlockDate, int newBlockId, int newBlockDate) {
        Position p1 = new Position(p.getX() + ScopeX, p.getY() + ScopeY, p.getZ() + ScopeZ);
        Position p2 = new Position(p.getX() - ScopeX, p.getY() - ScopeY, p.getZ() - ScopeZ);
        //遍p1p2区域里的方块，替换成空气方块
        // 获取p1和p2之间的最小和最大坐标值
        int minX = (int) Math.min(p1.getX(), p2.getX());
        int maxX = (int) Math.max(p1.getX(), p2.getX());
        int minY = (int) Math.min(p1.getY(), p2.getY());
        int maxY = (int) Math.max(p1.getY(), p2.getY());
        int minZ = (int) Math.min(p1.getZ(), p2.getZ());
        int maxZ = (int) Math.max(p1.getZ(), p2.getZ());

        // 遍历定义区域内的每个方块
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    Position currentPosition = new Position(x, y, z, p.getLevel());
                    if (currentPosition.getLevelBlock().getId() == oldBlockId && currentPosition.getLevelBlock().getDamage() == oldBlockDate) {
                        currentPosition.getLevel().setBlock(currentPosition, Block.get(newBlockId, newBlockDate));
                    }
                }
            }
        }
    }

    //使用nk插件的控制台输出
    public static void nkConsole(String msg) {
        plugin.getLogger().info(TextFormat.colorize('&', msg));
    }

    public static void nkConsole(String msg, int typeNum) {
        if (typeNum == 1) {
            plugin.getLogger().warning(TextFormat.colorize('&', msg));
        } else if (typeNum == 2) {
            plugin.getLogger().error(TextFormat.colorize('&', msg));
        } else {
            plugin.getLogger().info(TextFormat.colorize('&', msg));
        }
    }
}
