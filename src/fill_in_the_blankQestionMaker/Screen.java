package fill_in_the_blankQestionMaker;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Screen {

	// フレームの幅、高さ
	static final int FRAME_WIDTH    = 960;
	static final int FRAME_HEIGHT   = 540;
	// テキストエリアの幅、高さ
	static final int AREA_WIDTH     = 450;
	static final int AREA_HEIGHT    = 450;
	// ボタンの幅、高さ
	static final int BUTTON_WIDTH   = 120;
	static final int BUTTON_HEIGHT	= 80;

	//プリントモードか解答モードか
	static final int MODE_PRINT = 0;
	static final int MODE_ANS = 1;

	private String[] combodata = { "易しい" , "普通" , "難しい" , "前置詞" };

	private String[] data;

	void beginDrawing(){
		// フレーム作成
				JFrame frame = new JFrame( "穴埋め問題メーカー ver1.1" );
				// コンテントペイン作成
				Container contentPane = frame.getContentPane();
				contentPane.setPreferredSize( new Dimension( FRAME_WIDTH, FRAME_HEIGHT ) );
				frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
				frame.setResizable(false);



				// パネル作成
				JPanel panel = new JPanel();
				panel.setLayout( null );

				// テキストエリア作成
				JTextArea area = new JTextArea( "最初は詰めて記述してください\n"
												  +"単語と単語の間には『空白』か『改行』を確実に挿入してください。" );
				area.setLineWrap( true );
				area.setWrapStyleWord( true );
				// テキストエリアにスクロールパネル適用
				JScrollPane scrollpane = new JScrollPane( area );
				scrollpane.setBounds( FRAME_WIDTH/4-AREA_WIDTH/2 , 0 , AREA_WIDTH , AREA_HEIGHT );
				panel.add( scrollpane );
				contentPane.add( panel );

				// プリント用テキストエリア作成
				JTextArea printArea = new JTextArea( "MAKEボタンを押すと印刷情報が表示されます\n"
														+"手動で書き込むこともできます" );
				printArea.setLineWrap( true );
				printArea.setWrapStyleWord( true );
				printArea.setFont( new Font( "HTOWERT" , Font.PLAIN , 20 ) );
				// プリント用テキストエリアにスクロールパネル適用
				JScrollPane printScrollpane = new JScrollPane( printArea );
				printScrollpane.setBounds( FRAME_WIDTH/4*3-AREA_WIDTH/2 , 0 , AREA_WIDTH , AREA_HEIGHT );
				panel.add( printScrollpane );

				// 難易度変更コンボボックスの作成
				JComboBox comboDifficulty = new JComboBox( combodata );
				comboDifficulty.setBounds(15,460,70,70);
				panel.add(comboDifficulty);

				// フォントの種類選択用コンボボックス
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
				Font fonts[] = ge.getAllFonts();
				String fontsNames[] = new String[fonts.length];
				for( int i = 0; i < fonts.length; i++ ){
					fontsNames[i] = (String) fonts[i].getName();
				}
				JComboBox comboFontType = new JComboBox( fontsNames );
				comboFontType.setBounds( 650 , 455 , 150 , 35 );
				panel.add( comboFontType );

				// フォントサイズ選択用コンボボックス
				String fontSize[] = new String[20];
				for( int i = 0; i < fontSize.length; i++){
					fontSize[i] = String.valueOf((i+1)*2);
				}
				JComboBox comboFontSize = new JComboBox( fontSize );
				comboFontSize.setBounds( 650 , 495 , 150 , 35);
				panel.add( comboFontSize );

				// フォント設定コンボボックスのリスナー
				comboFontType.addActionListener(
						new ActionListener(){
							public void actionPerformed( ActionEvent event ){
								printArea.setFont( new Font( fontsNames[comboFontType.getSelectedIndex()] , Font.PLAIN , Integer.parseInt(fontSize[comboFontSize.getSelectedIndex()]) ) );
							}
						}
					);
				comboFontSize.addActionListener(
						new ActionListener(){
							public void actionPerformed( ActionEvent event ){
								printArea.setFont( new Font( fontsNames[comboFontType.getSelectedIndex()] , Font.PLAIN , Integer.parseInt(fontSize[comboFontSize.getSelectedIndex()]) ) );
							}
						}
					);

				MediaTracker tracker = new MediaTracker(contentPane);
				//jarにすると画像が表示されなくなるのでその対策(nullぽ)
				//ClassLoader cl = this.getClass().getClassLoader();
				//ImageIcon icon = new ImageIcon(cl.getResource("make_button.jpg"));
				ImageIcon icon 	= new ImageIcon("make_button.png");
				JButton 	button = new JButton();
				PrintArea print = new PrintArea();
				Image 		img   	= icon.getImage().getScaledInstance(BUTTON_WIDTH, BUTTON_HEIGHT, Image.SCALE_SMOOTH );
				// 処理の終了を待つ
				tracker.addImage( img , 1 );
				button.setBounds( FRAME_WIDTH/2-BUTTON_WIDTH/2 , AREA_HEIGHT+((FRAME_HEIGHT-AREA_HEIGHT)/2)-BUTTON_HEIGHT/2 , BUTTON_WIDTH, BUTTON_HEIGHT );
				icon = new ImageIcon( img );
				button.setIcon( icon );
				button.addActionListener(
							new ActionListener(){
								public void actionPerformed( ActionEvent event ){
									data = area.getText().split("[\n\\s]+",0);
									print.makePrint( comboDifficulty.getSelectedIndex() , data );
									data = print.getData();

									String str = "";
									for( int i = 0; i < data.length; i++ ){
										str += data[i]+" ";
									}
									printArea.setText(str);
									printArea.append("\n");
									printArea.append("\n");
									printArea.append("---解答---");
									printArea.append("\n");
									printArea.append(print.getAnswer());
								}
							}
						);
				panel.add( button );

				JButton printButton = new JButton("印刷");
				PrintScreen printScreen = new PrintScreen();
				printButton.setBounds( printScrollpane.getX()+AREA_WIDTH-BUTTON_WIDTH, AREA_HEIGHT+((FRAME_HEIGHT-AREA_HEIGHT)/2)-BUTTON_HEIGHT/2 , 120 , 80 );
				printButton.addActionListener(new ActionListener() {
			        public void actionPerformed(ActionEvent event) {
			        	printScreen.print(printArea);
			            try {
			                // テキストコンポーネントの印刷
			                printArea.print();
			            } catch (PrinterException ex) {
			                JOptionPane.showMessageDialog(
			                    frame,
			                    "印刷に失敗しました",
			                    "印刷失敗",
			                    JOptionPane.WARNING_MESSAGE);
			            }
			        }
			    });
				panel.add( printButton );

				/*
				// ツールバーの作成
				ToolBar toolBar = new ToolBar();
				contentPane.add(toolBar.addToolBar(), BorderLayout.NORTH);
				*/
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);

	}


}
