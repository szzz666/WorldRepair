package top.szzz666.WorldRepair.form;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.handler.FormResponseHandler;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.form.window.FormWindowModal;
import cn.nukkit.form.window.FormWindowSimple;

import java.util.HashMap;

import static top.szzz666.WorldRepair.config.WrConfig.getBlockConfig;
import static top.szzz666.WorldRepair.tools.pluginUtil.analysisAndRun;


public class WrForm {
    public static void mainForm(Player player) {
        FormWindowSimple form = new FormWindowSimple("§b§lWorldRepair", "§b选择你要的功能");
        form.addButton(new ElementButton("§b运行文件中的配置"));
        form.addButton(new ElementButton("§b填写方块Id和Data"));
        form.addHandler(FormResponseHandler.withoutPlayer(ignored -> {
            int buttonIndex = form.getResponse().getClickedButtonId();
            if (buttonIndex == 0) {
                Form0(player);
            } else {
                Form1(player);
            }
        }));
        player.showFormWindow(form);
    }

    public static void Form0(Player player) {
        FormWindowModal form = new FormWindowModal("§b确认", "§c将运行文件中的配置，该操作会替换世界方块，请先备份好地图！", "确认", "取消");
        form.addHandler(FormResponseHandler.withoutPlayer(ignored -> {
            if (form.wasClosed()) return;
            if (form.getResponse().getClickedButtonId() == 0) {
                HashMap<String, String> block = getBlockConfig();
                player.sendMessage("§e开始运行，请等待...");
                //遍历block
                for (String key : block.keySet()) {
                    analysisAndRun(key, block.get(key), player);
                }
                player.sendMessage("§a操作已完成");
            } else {
                mainForm(player);
            }
        }));
        player.showFormWindow(form);
    }

    public static void Form1(Player player) {
        FormWindowCustom form = new FormWindowCustom("§b填写方块Id和方块Data");
        form.addElement(new ElementInput("§b老方块Id和Data：", "0:0"));
        form.addElement(new ElementInput("§b新方块Id和Data：", "0:0"));
        form.addHandler(FormResponseHandler.withoutPlayer(ignored -> {
            if (form.wasClosed()) return;
            String inputText0 = form.getResponse().getInputResponse(0);
            String inputText1 = form.getResponse().getInputResponse(1);
            if (inputText0.isEmpty() || inputText1.isEmpty()) {
                player.sendMessage("§c输入不能为空");
            } else {
                player.sendMessage("§e开始运行，请等待...");
                analysisAndRun(inputText0, inputText1, player.getPosition());
                player.sendMessage("§a操作已完成");
            }
        }));
        player.showFormWindow(form);
    }


}
