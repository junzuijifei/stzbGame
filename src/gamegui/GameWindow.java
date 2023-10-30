package gamegui;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private JMenuBar menuBar;
    private JMenu equipmentMenu;
    private JMenuItem gemstoneItem, synthesisItem;

    // 构造函数
    public GameWindow() {
        // 初始化菜单栏
        menuBar = new JMenuBar();
        equipmentMenu = new JMenu("装备");
        gemstoneItem = new JMenuItem("宝石");
        synthesisItem = new JMenuItem("合成");

        // 设置菜单栏选项
        equipmentMenu.add(gemstoneItem);
        equipmentMenu.add(synthesisItem);
        menuBar.add(equipmentMenu);
        setJMenuBar(menuBar);

        // 设置窗口大小
        setSize(800, 600);

    }
}
