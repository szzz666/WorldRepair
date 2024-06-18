package top.szzz666.WorldRepair.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;

import static top.szzz666.WorldRepair.config.WrConfig.CommandName;
import static top.szzz666.WorldRepair.form.WrForm.mainForm;


public class WrCommand extends Command {
    public WrCommand() {
        super(CommandName, "世界修复");
    }
    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        Player player = (Player) sender;
        if (sender.isOp()) {
            if (args.length == 0) {
                mainForm(player);
                return true;
            }
        }else {
            player.sendMessage("§c你没有权限执行该命令");
        }
        return false;
    }

}
