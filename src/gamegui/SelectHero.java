package gamegui;

import hero_test.DatabaseReader;
import hero_test.Hero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SelectHero extends JFrame implements ActionListener {
    private JMenuBar menuBar;
    private JMenu about;
    private JMenu jmenu;
    private ArrayList<Hero> myHeroes=new ArrayList<>();


    // 构造函数
    public SelectHero() {
        initMenu();
        showHero();
        selectHero();
        // 设置窗口大小
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);//窗体是否可以调整大小。
        background();
        setDefaultCloseOperation(3);
        setVisible(true);//设置为窗口可见
    }

    public ArrayList<Hero> selectHero(){//下拉框选择英雄
        DatabaseReader databaseReader=new DatabaseReader();
        //创建jpanel面板，封装下拉框组件
        JPanel jPanel=new JPanel();
        //创建下拉框组件
        JComboBox<String> comboBox=new JComboBox<>();
        //添加选项
        comboBox.addItem("请选择英雄一：");
        comboBox.addItem("曹操");
        comboBox.addItem("刘备");
        comboBox.addItem("孙权");
        comboBox.addItem("司马炎");
        comboBox.addItem("司马师");
        comboBox.addItem("周瑜");
        comboBox.addItem("周泰");
        comboBox.addItem("吕布");
        comboBox.addItem("司马懿");
        comboBox.addItem("荀彧");
        comboBox.setSize(100,8);


        //创建下拉框组件
        JComboBox<String> comboBox1=new JComboBox<>();
        //添加选项
        comboBox1.addItem("请选择英雄一：");
        comboBox1.addItem("曹操");
        comboBox1.addItem("刘备");
        comboBox1.addItem("孙权");
        comboBox1.addItem("司马炎");
        comboBox1.addItem("司马师");
        comboBox1.addItem("周瑜");
        comboBox1.addItem("周泰");
        comboBox1.addItem("吕布");
        comboBox1.addItem("司马懿");
        comboBox1.addItem("荀彧");
        comboBox1.setSize(100,8);

        //创建下拉框组件
        JComboBox<String> comboBox2=new JComboBox<>();
        //添加选项
        comboBox2.addItem("请选择英雄一：");
        comboBox2.addItem("曹操");
        comboBox2.addItem("刘备");
        comboBox2.addItem("孙权");
        comboBox2.addItem("司马炎");
        comboBox2.addItem("司马师");
        comboBox2.addItem("周瑜");
        comboBox2.addItem("周泰");
        comboBox2.addItem("吕布");
        comboBox2.addItem("司马懿");
        comboBox2.addItem("荀彧");
        comboBox2.setSize(100,8);

        JLabel label=new JLabel("请选择英雄1：");
        JLabel label1=new JLabel("请选择英雄2：");
        JLabel label2=new JLabel("请选择英雄3：");
        JButton jButton = new JButton("选择关卡");
        JButton jButton1 = new JButton("返回上一步");

        jPanel.add(label);
        jPanel.add(comboBox);
        jPanel.add(label1);
        jPanel.add(comboBox1);
        jPanel.add(label2);
        jPanel.add(comboBox2);
        jPanel.add(jButton);
        jPanel.add(jButton1);


        JTextArea textArea=new JTextArea(14,34);
        JScrollPane scrollPane=new JScrollPane(textArea);
        textArea.setEditable(false);//设置透明
        textArea.setOpaque(false);//设置透明
        scrollPane.setOpaque(false);//设置透明
        scrollPane.getViewport().setOpaque(false);//设置透明
        jPanel.setOpaque(false);//设置透明
        this.add(jPanel,BorderLayout.CENTER);
        this.add(scrollPane,BorderLayout.PAGE_END);
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameGui();
            }
        });

        jButton.addActionListener(this);
        ActionListener at=new ActionListener() {//下拉框监听器选择我方英雄
            @Override
            public void actionPerformed(ActionEvent e) {
                String item= (String) comboBox.getSelectedItem();
                Hero byName = databaseReader.getByName(item);

                String item1= (String) comboBox1.getSelectedItem();
                Hero byName1 = databaseReader.getByName(item1);

                String item2= (String) comboBox2.getSelectedItem();
                Hero byName2 = databaseReader.getByName(item2);

                String hero = byName.toString();
                String hero1 = byName1.toString();
                String hero2 = byName2.toString();

                textArea.setText("");//更换英雄时把文本域里的英雄修改成重新选择的英雄。
                textArea.append("你选择的英雄队伍：\n");
                textArea.append(hero+"\n");
                textArea.append(hero1+"\n");
                textArea.append(hero2+"\n");
                textArea.setFont(new Font("宋体",Font.BOLD,15));
                myHeroes.add(byName);
                myHeroes.add(byName1);
                myHeroes.add(byName2);
            }
        };
        comboBox.addActionListener(at);
        comboBox1.addActionListener(at);
        comboBox2.addActionListener(at);

        return myHeroes;

    }//选择我方队伍

    public void showHero(){//展示所有英雄
        DatabaseReader databaseReader = new DatabaseReader();

        JScrollPane scrollPane=new JScrollPane();//滚动面板组件

        scrollPane.setSize(800,500);
        scrollPane.setVerticalScrollBarPolicy
                (ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);//垂直滚动条一直显示
        JPanel panel=new JPanel();
        //获取一个图片
        ImageIcon m1=new ImageIcon("images\\刘备.jpg");
        ImageIcon m2=new ImageIcon("images\\司马师.jpg");
        ImageIcon m3=new ImageIcon("images\\司马懿.jpg");
        ImageIcon m4=new ImageIcon("images\\司马炎.jpg");
        ImageIcon m5=new ImageIcon("images\\曹操.jpg");
        ImageIcon m6=new ImageIcon("images\\孙权.jpg");
        ImageIcon m7=new ImageIcon("images\\吕布.jpg");
        ImageIcon m8=new ImageIcon("images\\周泰.jpg");
        ImageIcon m9=new ImageIcon("images\\周瑜.jpg");
        ImageIcon m10=new ImageIcon("images\\荀彧.jpg");

        JButton b1 = new JButton(new AbstractAction() {//为按钮添加监听器，点击图片按钮弹出提示框输出英雄信息
            @Override
            public void actionPerformed(ActionEvent e) {
                Hero hero = databaseReader.getByName("刘备");
                String str="名字："+hero.getName()+"      血量："+hero.getBlood()+"      \nATK："
                        +hero.getAtk()+"      DEF："+hero.getDefense()+"      等级："+hero.getLevel()
                        +"      \n技能"+hero.getSkill();
                JFrame jFrame = new JFrame();
                int choice = JOptionPane.showConfirmDialog(jFrame,
                        str,
                        "英雄属性",
                        JOptionPane.OK_OPTION
                );
                if(choice == JOptionPane.OK_OPTION) {
                    jFrame.dispose();
                    jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                }
            }
        });
        JButton b2 = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Hero hero = databaseReader.getByName("司马师");
                hint(hero);
            }
        });
        JButton b3 = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Hero hero = databaseReader.getByName("司马懿");
                hint(hero);
            }
        });
        JButton b4 = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Hero hero = databaseReader.getByName("司马炎");
                hint(hero);
            }
        });
        JButton b5 = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Hero hero = databaseReader.getByName("曹操");
                hint(hero);
            }
        });
        JButton b6 = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Hero hero = databaseReader.getByName("孙权");
                hint(hero);
            }
        });
        JButton b7=new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Hero hero = databaseReader.getByName("吕布");
                hint(hero);
            }
        });
        JButton b8 = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Hero hero = databaseReader.getByName("周泰");
                hint(hero);
            }
        });
        JButton b9 = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Hero hero = databaseReader.getByName("周瑜");
                hint(hero);
            }
        });
        JButton b10 = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Hero hero = databaseReader.getByName("荀彧");
                hint(hero);
            }
        });

        b1.setBounds(0,100,150,230);
        b2.setBounds(160,100,150,230);
        b3.setBounds(320,100,150,230);
        b4.setBounds(480,100,150,230);
        b5.setBounds(0,340,150,230);
        b6.setBounds(160,340,150,230);
        b7.setBounds(320,340,150,230);
        b8.setBounds(480,340,150,230);
        b9.setBounds(0,340,150,230);
        b10.setBounds(160,580,150,230);

        m1.setImage(m1.getImage().getScaledInstance(150,230, 0));
        m2.setImage(m2.getImage().getScaledInstance(150,230, 0));
        m3.setImage(m3.getImage().getScaledInstance(150,230, 0));
        m4.setImage(m4.getImage().getScaledInstance(150,230, 0));
        m5.setImage(m5.getImage().getScaledInstance(150,230, 0));
        m6.setImage(m6.getImage().getScaledInstance(150,230, 0));
        m7.setImage(m7.getImage().getScaledInstance(150,230, 0));
        m8.setImage(m8.getImage().getScaledInstance(150,230, 0));
        m9.setImage(m9.getImage().getScaledInstance(150,230, 0));
        m10.setImage(m10.getImage().getScaledInstance(150,230, 0));

        //把图片放到按钮上
        b1.setIcon(m1);
        b2.setIcon(m2);
        b3.setIcon(m3);
        b4.setIcon(m4);
        b5.setIcon(m5);
        b6.setIcon(m6);
        b7.setIcon(m7);
        b8.setIcon(m8);
        b9.setIcon(m9);
        b10.setIcon(m10);


        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(b4);
        panel.add(b5);
        panel.add(b6);
        panel.add(b7);
        panel.add(b8);
        panel.add(b9);
        panel.add(b10);

        scrollPane.setViewportView(panel);//设置jpanel面板在滚动面板显示
        panel.setOpaque(false);
        scrollPane.setOpaque(false);//设置透明
        scrollPane.getViewport().setOpaque(false);//设置透明

        this.add(scrollPane,BorderLayout.PAGE_START);
    }//展示所有英雄

    public void hint(Hero hero){//为监听器重复内容
        String str="名字："+hero.getName()+"      血量："+hero.getBlood()+"      \nATK："
                +hero.getAtk()+"      DEF："+hero.getDefense()+"      等级："+hero.getLevel()
                +"      \n技能"+hero.getSkill();
        JFrame jFrame = new JFrame();
        int choice = JOptionPane.showConfirmDialog(jFrame,
                str,
                "英雄属性",

                JOptionPane.OK_OPTION
        );
        if(choice == JOptionPane.OK_OPTION) {
            jFrame.dispose();
            jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        }

    }//为监听器重复内容，实现点击英雄图片，弹出英雄属性弹窗

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

    public void background(){//背景图片
        ((JPanel)this.getContentPane()).setOpaque(false);
        ImageIcon img = new ImageIcon("images//111.jpg"); //添加图片
        img.setImage(img.getImage().getScaledInstance(800,600, 0));
        JLabel background = new  JLabel(img);
        this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
        background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
    }//背景图片


    @Override
    public void actionPerformed(ActionEvent e) {
        new RoundMenu(myHeroes);
    }
}
