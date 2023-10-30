package gamegui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameGui extends JFrame implements MouseListener {
    private JMenuBar menuBar;
    private JMenu about;
    private JMenu jmenu;
    private JButton jButton;
    //创建一个图片ImageIcon的对象
    private ImageIcon icon = new ImageIcon("images\\率土首页4.jpg");

    // 构造函数
    public GameGui() {
        initMenu();
        homePage();
        // 设置窗口大小
        setSize(800, 600);
        setTitle("仿真率土");
        setLocationRelativeTo(null);
        setResizable(false);//窗体不可以调整大小。
        initImage();

        setDefaultCloseOperation(3);
        setVisible(true);//设置为窗口可见
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


   //初始化图片
    public void initImage() {

        icon.setImage(icon.getImage().getScaledInstance(this.getWidth(),this.getHeight(),Image.SCALE_DEFAULT));
        //创建JLabel的对象(管理容器)
        JLabel jLabel1 = new JLabel(icon);
        //指定位置
        jLabel1.setBounds(0,20,icon.getIconWidth(),icon.getIconHeight());
        jLabel1.setLocation(0,20);

        //this.add(jLabel);添加到窗口
        this.getContentPane().add(jLabel1);
        jLabel1.setOpaque(false);

    }

    private void homePage(){//开始游戏
        ImageIcon imageIcon=new ImageIcon("images\\开始2.jpg");
        jButton=new JButton("开始游戏");
        jButton.setBounds(340,250,120,40);
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(130,40, 0));
        jButton.setBorderPainted(false);//不绘制边框
        jButton.setFocusPainted(false);//选中后不绘制边框

        jButton.setIcon(imageIcon);
        jButton.setContentAreaFilled(false);//是否填充
        //jButton.setBorder(BorderFactory.createLoweredBevelBorder());//凹凸型
        Font Fonts = new Font("行书", Font.BOLD, 16);
        jButton.setFont(Fonts);
        jButton.addMouseListener(this);
        this.add(jButton);
    }//开始游戏按钮




    @Override
    public void mouseClicked(MouseEvent e) {
        new SelectHero();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
       jButton.setBounds(330,240,140,60);
        jButton.setFont(new Font("楷书",Font.BOLD,20));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        jButton.setBounds(340,250,120,40);
        Font Fonts = new Font("行书", Font.BOLD, 16);
        jButton.setFont(Fonts);
    }
}
