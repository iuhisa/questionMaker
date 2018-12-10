package fill_in_the_blankQestionMaker;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class ToolBar {

	// ボタンの幅、高さ
	static final int BUTTON_WIDTH = 15;
	static final int BUTTON_HEIGHT	= 15;

	JToolBar addToolBar(){
		JToolBar toolBar = new JToolBar("ツールバー");
		ImageIcon settingIcon 	= new ImageIcon("setting.png");
		Image settingImg = settingIcon.getImage().getScaledInstance(BUTTON_WIDTH, BUTTON_HEIGHT, Image.SCALE_SMOOTH );
		settingIcon = new ImageIcon(settingImg);
		JButton settingButton = new JButton(settingIcon);
		//settingButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		settingButton.addActionListener(
					new ActionListener(){
						public void actionPerformed(ActionEvent e) {
							setting();
						}
					}
				);
		toolBar.add(settingButton);
		return toolBar;
	}

	void setting(){
		JFrame settingFrame = new JFrame("Setting");
		settingFrame.setBounds(500,500,200,200);
		JPanel settingPanel = new JPanel();
		JCheckBox[] ckbox = new JCheckBox[2];
		ckbox[0] = new JCheckBox("プリント用");
		ckbox[1] = new JCheckBox("入力用");
		settingPanel.add(ckbox[0]);
		settingPanel.add(ckbox[1]);
		Container contentPane = settingFrame.getContentPane();
		contentPane.add(settingPanel, BorderLayout.CENTER);
		settingFrame.setVisible(true);
	}
}
