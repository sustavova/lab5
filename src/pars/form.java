package pars;
import java.awt.Color;
import java.awt.EventQueue;
import java.math.BigDecimal;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;

public class form extends JFrame {

	public JPanel contentPane;
	public static JTextField textField;
	public static String str = "";
	private JTextField textField2;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					form frame = new form();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }

	/**
	 * Create the frame.
	 */
	public form() {
		setResizable(false);
		setTitle("\u041A\u0430\u043B\u044C\u043A\u0443\u043B\u044F\u0442\u043E\u0440 ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 410, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		JPanel panel = new JPanel();	
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setEditable(false);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				boolean err1 = false;
				switch (arg0.getKeyChar()) {
				case '1': { str = str +'1';textField.setText(str);textField.requestFocus();  break;}
				case '2': { str = str +'2';textField.setText(str);textField.requestFocus();  break;}
				case '3': { str = str +'3';textField.setText(str);textField.requestFocus(); break;}
				case '4': { str = str +'4';textField.setText(str);textField.requestFocus(); break;}
				case '5': { str = str +'5';textField.setText(str);textField.requestFocus();  break;}
				case '6': { str = str +'6';textField.setText(str);textField.requestFocus();  break;}
				case '7': { str = str +'7';textField.setText(str);textField.requestFocus();  break;}
				case '8': { str = str +'8';textField.setText(str);textField.requestFocus();  break;}
				case '9': { str = str +'9';textField.setText(str);textField.requestFocus();  break;}
				case '0': { str = str +'0';textField.setText(str);textField.requestFocus();  break;}
				case '-': { str = str +'-';textField.setText(str); textField.requestFocus(); break;}
				case '+': { str = str +'+';textField.setText(str); textField.requestFocus(); break;}
				case '*': { str = str +'*';textField.setText(str);textField.requestFocus();  break;}
				case '/': { str = str +'/';textField.setText(str); textField.requestFocus(); break;}
				case '^': { str = str +'^';textField.setText(str);textField.requestFocus();  break;}
				case '(': { str = str +'(';textField.setText(str);textField.requestFocus();  break;}
				case ')': { str = str +')';textField.setText(str);textField.requestFocus();  break;}
				case '%': { str = str +'%';textField.setText(str);textField.requestFocus();  break;}
				case '.': { str = str +'.';textField.setText(str);textField.requestFocus();  break;}
				default: { break;}
				}
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
						BigFraction result = new BigFraction("0");
				       calk myParser = new calk();
				         try {
				            result = myParser.evaluate(str); }
				            catch(ParserException e)
				            {  err1 = true;
				            textField2.setText("Некорректное выражение");
				            }
				            catch(Exception e)
				            {  err1 = true;
				            textField2.setText("Деление на 0 !");}
				         if (!err1) {
				        	 textField2.setText(result.toString());
				        	 err1 = false;
				         }
				         textField.requestFocus(); 
				         }
				
				if (arg0.getKeyCode() == KeyEvent.VK_BACK_SPACE) { 
					if (str.length()>0) {
						str = str.substring(0,str.length()-1);
						textField.setText(str);
					}
					textField.requestFocus(); 
				} 
				}
});
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setColumns(100);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(textField, GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(textField, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JButton btnNewButton = new JButton("=");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				boolean err1 = false;
				BigFraction result = new BigFraction("0");
		        calk myParser = new calk();
		            try
		            {	
		            	str = textField.getText(); 
		                result = myParser.evaluate(str);  
		            }
		            catch(ParserException e)
		            { err1 = true;
	            	textField2.setText("Некорректное выражение");
	            	textField.requestFocus(); 
		            }
		            catch(Exception e)
		            {err1 = true;
		            textField2.setText("Деление на 0 !");
		            textField.requestFocus(); 
		            }
		            if (!err1) { 
		            	textField2.setText(result.toString());
			        	 err1 = false;
		            }
			textField.requestFocus();
			}
		});
		
	//	JLabel Label2 = new JLabel(" ");
		
		JButton button_1 = new JButton("1");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				str = str + '1';
				textField.setText(str);
				textField.requestFocus();
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton button2 = new JButton("2");
		button2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				str = str + '2';
				textField.setText(str);
				textField.requestFocus();
			}
		});
		button2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton button3 = new JButton("3");
		button3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				str = str + '3';
				textField.setText(str);
				textField.requestFocus();
			}
		});
		button3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton button4 = new JButton("4");
		button4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				str = str + '4';
				textField.setText(str);
				textField.requestFocus();
			}
		});
		button4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton button5 = new JButton("5");
		button5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				str = str + '5';
				textField.setText(str);
				textField.requestFocus();
			}
		});
		button5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton button6 = new JButton("6");
		button6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				str = str + '6';
				textField.setText(str);
				textField.requestFocus();
			}
		});
		button6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton button7 = new JButton("7");
		button7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				str = str + '7';
				textField.setText(str);
				textField.requestFocus();
			}
		});
		button7.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton button8 = new JButton("8");
		button8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				str = str + '8';
				textField.setText(str);
				textField.requestFocus();
			}
		});
		button8.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton button9 = new JButton("9");
		button9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				str = str + '9';
				textField.setText(str);
				textField.requestFocus();
			}
		});
		button9.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton button0 = new JButton("0");
		button0.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				str = str + '0';
				textField.setText(str);
				textField.requestFocus();
			}
		});
		button0.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton button_point = new JButton(".");
		button_point.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				str = str + '.';
				textField.setText(str);
				textField.requestFocus();
			}
		});
		button_point.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton button_mul = new JButton("*");
		button_mul.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				str = str + '*';
				textField.setText(str);
				textField.requestFocus();
			}
		});
		button_mul.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton button_add = new JButton("+");
		button_add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				str = str + '+';
				textField.setText(str);
				textField.requestFocus();
			}
		});
		button_add.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton button_sub = new JButton("-");
		button_sub.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				str = str + '-';
				textField.setText(str);
				textField.requestFocus();
			}
		});
		button_sub.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton button_div = new JButton("/");
		button_div.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				str = str + '/';
				textField.setText(str);
				textField.requestFocus();
			}
		});
		button_div.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnXy = new JButton("x^y");
		btnXy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				str = str + '^';
				textField.setText(str);
				textField.requestFocus();
			}
		});
		btnXy.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton button_procent = new JButton("%");
		button_procent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				str = str + '%';
				textField.setText(str);
				textField.requestFocus();
			}
		});
		button_procent.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton button_scob1 = new JButton("(");
		button_scob1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				str = str + '(';
				textField.setText(str);
				textField.requestFocus();
			}
		});
		button_scob1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton button_scob2 = new JButton(")");
		button_scob2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				str = str + ')';
				textField.setText(str);
				textField.requestFocus();
			}
		});
		button_scob2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnAc = new JButton("AC");
		btnAc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				str = "";
				textField.setText(str);
				textField.requestFocus();
				
			}
		});
		
		JButton buttonC = new JButton("C");
		buttonC.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (str.length()>0) {
					str = str.substring(0,str.length()-1);
					textField.setText(str);
					textField.requestFocus();
				}
			}
		});
		buttonC.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		textField2 = new JTextField();
		textField2.setBorder (BorderFactory.createLineBorder (new Color (0, 0, 0, 0), 2));
		textField2.setHorizontalAlignment(SwingConstants.RIGHT);
		textField2.setEditable(false);
		textField2.setColumns(100);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(40)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(5)
									.addComponent(btnXy, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
									.addGap(5)
									.addComponent(button_scob1, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
									.addGap(5)
									.addComponent(button_scob2, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
									.addGap(5)
					
									.addComponent(button_procent, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
									.addGap(5)
									.addComponent(button_div, GroupLayout.PREFERRED_SIZE,54, GroupLayout.PREFERRED_SIZE)
									
									)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button7, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
									.addGap(5)
									.addComponent(button8, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
									.addGap(5)
									.addComponent(button9, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
									
									.addGap(5)
									.addComponent(button_mul, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
									)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(button4, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
									.addGap(5)
									.addComponent(button5, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
									.addGap(5)
									.addComponent(button6, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
									
									.addGap(5)
									.addComponent(button_sub, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
									//.addGap(5)
									//.addComponent(button_div, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
									
									)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
									.addGap(5)
									.addComponent(button2, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
									.addGap(5)
									.addComponent(button3, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
									
									.addGap(5)
									.addComponent(button_add, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
									
									)
								.addGroup(gl_contentPane.createSequentialGroup()
								    //.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnAc, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
									.addGap(5)
									.addComponent(button0, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
									.addGap(5)
									.addComponent(button_point, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
									.addGap(5)
									.addComponent(buttonC, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
									)
								
								.addGroup(gl_contentPane.createSequentialGroup()
										
										)
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)	
												.addGroup(gl_contentPane.createSequentialGroup()
													.addGap(0)
													.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
													.addGap(52)
													)
										.addGroup(gl_contentPane.createSequentialGroup()
											)))
								.addGroup(gl_contentPane.createSequentialGroup()
									)))
						
						
						
						
						
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(textField2, GroupLayout.PREFERRED_SIZE, 316, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(2)
					.addComponent(textField2, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(40)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(button_scob2, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnXy, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
									.addComponent(button_procent, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)))
						            .addComponent(button_scob1, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						            .addComponent(button_div, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
									)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
								
									.addGap(10)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
											.addComponent(button8, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
											.addComponent(button7, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
										    .addComponent(button9, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
										    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
													
													.addComponent(button_sub, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
													))
									  .addGap(5)
								)
								.addGroup(gl_contentPane.createSequentialGroup()
									
									.addPreferredGap(ComponentPlacement.UNRELATED)))
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(button4, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
								.addComponent(button5, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
								.addComponent(button6, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
								.addComponent(button_mul, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
								
								)
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
								.addComponent(button2, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
								.addComponent(button3, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
								.addComponent(button_add, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
									//.addComponent(btnXy, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
									)
								.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(button_point, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
									.addComponent(button0, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(buttonC, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnAc, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)))
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
									)
							
						
					.addContainerGap(43, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
