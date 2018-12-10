package fill_in_the_blankQestionMaker;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PrintScreen {
	// フレームの幅、高さ
	public static final int FRAME_WIDTH    = 960;
	public static final int FRAME_HEIGHT   = 540;

	void print(JTextArea printArea){
		JFrame optionFrame = new JFrame("印刷オプション");
		Container contentPane = optionFrame.getContentPane();
		contentPane.setPreferredSize( new Dimension( FRAME_WIDTH, FRAME_HEIGHT ) );
		optionFrame.setResizable(false);
		JPanel panel = new JPanel();
		panel.setLayout( null );
		//printAreaの内容を取得してeditAreaで変更、
		//その他の印刷オプションに合わせて忠実に印刷
		JTextArea editArea = new JTextArea(printArea.getText());
		editArea.setLineWrap( true );
		editArea.setWrapStyleWord( true );

		optionFrame.pack();
		optionFrame.setLocationRelativeTo(null);
		optionFrame.setVisible(true);
	}
}
