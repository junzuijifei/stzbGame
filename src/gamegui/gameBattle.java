package gamegui;

import hero_test.Battle;
import hero_test.Hero;
import hero_test.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class gameBattle extends JFrame {
    private JMenuBar menuBar;
    private JMenu about;
    private JMenu jmenu;


    public gameBattle(ArrayList<Hero> myHeroes, ArrayList<Hero> enemyTeam, int i) {
        initMenu();
        bothTeams(myHeroes,enemyTeam);//双方队伍
        overGame(myHeroes);//战斗结束，选择是否继续战斗
        battleFlow(myHeroes,i);//输出战斗流程
        // 设置窗口大小
        setSize(800, 600);
        setTitle("仿真率土");
        setLocationRelativeTo(null);
        setResizable(false);
        //initImage();
        setDefaultCloseOperation(3);
        setVisible(true);//设置为窗口可见
    }

    private void battleFlow(ArrayList<Hero> myHeroes, int i) {
        JTextArea textArea=new JTextArea(14,34);
        JScrollPane scrollPane=new JScrollPane(textArea);
        textArea.setEditable(false);//设置透明
        textArea.setOpaque(false);//设置透明
        scrollPane.setOpaque(false);//设置透明
        scrollPane.getViewport().setOpaque(false);//设置透明
        scrollPane.setSize(800,500);
        scrollPane.setVerticalScrollBarPolicy
                (ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);//垂直滚动条一直显示
        Battle battle = new Battle(myHeroes,new Level(i));
        battle.start(textArea);
        this.add(scrollPane,BorderLayout.PAGE_END);
    }

    public void bothTeams(ArrayList<Hero> myHeroes, ArrayList<Hero> enemyTeam){
        JPanel panel=new JPanel();
        for (Hero hero : myHeroes){
            ImageIcon img=new ImageIcon("images\\"+hero.getName()+".jpg");
            JButton button=new JButton();
            button.setBounds(0,0,80,140);
            img.setImage(img.getImage().getScaledInstance(80,130, 0));
            button.setIcon(img);
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);//是否填充
            panel.add(button);
        }
        JTextField textField=new JTextField("PK");
        textField.setSize(20,8);
        textField.setFont(new Font("楷体",Font.BOLD,15));
        textField.setOpaque(false);
        panel.add(textField);
        for (Hero hero: enemyTeam){
            ImageIcon img=new ImageIcon("images\\"+hero.getName()+".jpg");
            JButton button=new JButton();
            img.setImage(img.getImage().getScaledInstance(80,130, 0));
            button.setIcon(img);
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);//是否填充
            panel.add(button);
        }
        panel.setOpaque(false);
        this.add(panel, BorderLayout.PAGE_START);
    }

    public void overGame(ArrayList<Hero> myHeroes){
        JPanel panel=new JPanel();
        ImageIcon img1=new ImageIcon("images\\开始2.jpg");
        JButton button1=new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jFrame = new JFrame();
                int choice = JOptionPane.showConfirmDialog(
                        jFrame,
                        "是否继续游戏?",
                        "请选择",
                        JOptionPane.YES_NO_OPTION
                );
                if(choice == JOptionPane.YES_OPTION) {
                    new RoundMenu(myHeroes);
                    jFrame.dispose();
                    jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                }else {
                    new GameGui();
                }
            }
        });

        button1.setBounds(0,0,80,40);
        img1.setImage(img1.getImage().getScaledInstance(80,80, 0));
        button1.setIcon(img1);
        button1.setBorderPainted(false);
        button1.setContentAreaFilled(false);//是否填充
        panel.add(button1);
        this.add(panel,BorderLayout.CENTER);
    }


    public void initMenu(){ // 初始化菜单栏

        menuBar = new JMenuBar();
        jmenu = new JMenu("菜单");
        JMenuItem item = new JMenuItem("返回主页");
        jmenu.add(item);
        menuBar.add(jmenu);

        about = new JMenu("关于");
        JMenuItem cell = new JMenuItem("微信号");
        about.add(cell);
        menuBar.add(about);

        menuBar.setOpaque(false);//设置透明
        jmenu.setOpaque(false);
        item.setOpaque(false);
        menuBar.setBorderPainted(false);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameGui();
            }
        });
        setJMenuBar(menuBar);

    }
}
