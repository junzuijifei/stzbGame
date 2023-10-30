package gamegui;

import hero_test.Hero;
import hero_test.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public  class RoundMenu extends JFrame  {
    private JMenuBar menuBar;
    private JMenu about;
    private JMenu jmenu;
    private JButton jButton;
    private JTextArea textArea;
    private ArrayList<Hero> enemyTeam;
    private ImageIcon img=new ImageIcon("images\\开始.jpg");
    private int i;//选择的关卡

    public RoundMenu(ArrayList<Hero> myHeroes){
        initMenu();
        setRound();//选择关卡
        startBattle(myHeroes);
        showEnemy();
        setSize(800, 600);// 设置窗口大小
        setLocationRelativeTo(null);
        setResizable(false);
        // 背景图片
        background();
        setDefaultCloseOperation(3);
        setVisible(true);//设置为窗口可见

    }


    public int setRound(){//选择关卡，页面上方

        JPanel jPanel=new JPanel();
        jPanel.setOpaque(false);
        JComboBox<String> comboBox=new JComboBox<>();
        comboBox.addItem("");
        comboBox.addItem("关卡1");
        comboBox.addItem("关卡2");
        comboBox.addItem("关卡3");
        comboBox.addItem("关卡4");
        comboBox.addItem("关卡5");
        comboBox.setSize(100,8);
        JLabel label=new JLabel("请选择你要挑战的关卡：");
        JButton button=new JButton("返回上一页");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //为下拉框添加监听器（返回上一步）
                new SelectHero();
            }
        });

        jPanel.add(label);
        jPanel.add(comboBox);
        jPanel.add(button);
        this.add(jPanel,BorderLayout.PAGE_START);

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object selectedItem = comboBox.getSelectedItem();
                Level level = new Level();
                if (selectedItem=="关卡1"){
                     enemyTeam = level.choice(1);
                    textArea.setText("");
                    textArea.append("敌方英雄队伍："+'\n');
                    for (Hero hero:enemyTeam){
                        String s = hero.toString();
                        textArea.append(s+'\n');
                        textArea.setFont(new Font("宋体",Font.BOLD,15));
                    }
                   i=1;
                }
                if (selectedItem=="关卡2"){
                     enemyTeam = level.choice(2);
                    textArea.setText("");
                    textArea.append("敌方英雄队伍："+'\n');
                    for (Hero hero:enemyTeam){
                        String s = hero.toString();
                        textArea.append(s+'\n');
                        textArea.setFont(new Font("宋体",Font.BOLD,15));
                    }
                    i=2;
                }
                if (selectedItem=="关卡3"){
                   enemyTeam = level.choice(3);
                    textArea.setText("");
                    textArea.append("敌方英雄队伍："+'\n');
                    for (Hero hero:enemyTeam){
                        String s = hero.toString();
                        textArea.append(s+'\n');
                        textArea.setFont(new Font("宋体",Font.BOLD,15));
                    }
                    i=3;
                }
                if (selectedItem=="关卡4"){
                    enemyTeam = level.choice(4);
                    textArea.setText("");
                    textArea.append("敌方英雄队伍："+'\n');
                    for (Hero hero:enemyTeam){
                        String s = hero.toString();
                        textArea.append(s+'\n');
                        textArea.setFont(new Font("宋体",Font.BOLD,15));
                    }
                    i=4;
                }
                if (selectedItem=="关卡5"){
                     enemyTeam = level.choice(5);
                    textArea.setText("");
                    textArea.append("敌方英雄队伍："+'\n');
                    for (Hero hero:enemyTeam){
                        String s = hero.toString();
                        textArea.append(s+'\n');
                        textArea.setFont(new Font("宋体",Font.BOLD,15));
                    }
                    i=5;
                }

            }
        });
        return i;
    }//选择关卡，页面上方



    public void startBattle(ArrayList<Hero> myHeroes){
        JPanel panel=new JPanel();
        JTextArea textArea=new JTextArea(5,600);
        textArea.setOpaque(false);
        textArea.setEditable(false);
        jButton=new JButton();//开始战斗按钮

        jButton.setBounds(480,300,200,100);
        img.setImage(img.getImage().getScaledInstance(200,100, 0));
        jButton.setIcon(img);//为按钮添加图片
        jButton.setContentAreaFilled(false);//是否填充
        jButton.setBorderPainted(false);//不绘制边框
        jButton.setFocusPainted(false);//选中后不绘制边框
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new gameBattle(myHeroes,enemyTeam,i);
            }
        });
        panel.add(textArea);
        panel.add(jButton);
        this.add(panel);
        panel.setOpaque(false);//设置panel为透明。
        this.add(panel,BorderLayout.CENTER);
    }//页面中间的开始战斗

    public void showEnemy(){//显示敌方队伍的文本域
         textArea=new JTextArea(14,34);//输出选择的关卡队伍
        textArea.setOpaque(false);
        JScrollPane scrollPane=new JScrollPane(textArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        textArea.setEditable(false);
        this.add(scrollPane,BorderLayout.PAGE_END);
    }//显示敌方队伍,页面下方


    public void initMenu() { // 初始化菜单栏

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
        cell.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        setJMenuBar(menuBar);

    }//初始化菜单
    public void background(){//背景图片
        ((JPanel)this.getContentPane()).setOpaque(false);
        ImageIcon img = new ImageIcon("images//111.jpg"); //添加图片
        img.setImage(img.getImage().getScaledInstance(800,600, 0));
        JLabel background = new  JLabel(img);
        this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
        background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
    }//背景图片

}
