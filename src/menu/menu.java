package menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import file.zipfile;

import file.unzipfile;

@SuppressWarnings("serial")
public class menu extends JFrame {


	
	private JButton zipbtn = new JButton("허프만 압축");
	private JButton unzipbtn = new JButton("허프만 압축해제");
	private JButton encrybtn = new JButton("허프만 암호화");
	private JButton unencrybtn = new JButton("허프만 암호화해제");	
	private JButton exitbtn = new JButton("프로그램 종료");
	private JTextArea printbox = new JTextArea(300,3000);
	private JTextArea compressbox = new JTextArea(300, 1000);
	private JTextField filename = new JTextField(20);
	private JPanel cpanel = new JPanel();
	private JPanel spanel = new JPanel();
	private JPanel npanel = new JPanel();

	menu()
	{
		super("Huffman coding (Programmed by 시종욱)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		setup();
		setSize(1000, 600);
		setLocation(300, 300);
		setVisible(true);
		setResizable(false);
	}
	
	public void setup()
	{
		
		
		Color color = new Color(220,100,50);
		spanel.setLayout(new FlowLayout());
		spanel.setBackground(color);
		npanel.setLayout(new FlowLayout());
		npanel.setBackground(color);
		cpanel.setLayout(null);
		
		printbox.setBounds(30, 30, 450, 440);	
		compressbox.setBounds(520, 30, 450, 440);
		
		JPanel textbox = new JPanel();
		textbox.setBounds(0, 0, 1000, 600);
		textbox.setLayout(null);
			
		JLabel progname = new JLabel(" < 파일 내부 정보 >                                                                                                                           <파일 상세 정보>  ");
		JLabel filenamela = new JLabel("파일 명: ");
		
		
		JScrollPane printscroll = new JScrollPane(printbox, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);	
		JScrollPane compressscroll = new JScrollPane(compressbox, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		printscroll.setBounds(30, 30, 450, 440);
		compressscroll.setBounds(520, 30, 450, 440);
		printbox.setLineWrap(true);
		compressbox.setLineWrap(true);
		
		
		spanel.add(filenamela);
		spanel.add(filename);
		spanel.add(zipbtn);
		spanel.add(unzipbtn);
		spanel.add(encrybtn);
		spanel.add(unencrybtn);
		spanel.add(exitbtn);
		
		zipbtn.addActionListener(new myActionListener());
		unzipbtn.addActionListener(new myActionListener());
		encrybtn.addActionListener(new myActionListener());
		unencrybtn.addActionListener(new myActionListener());
		exitbtn.addActionListener(new myActionListener());
		
		add(cpanel, BorderLayout.CENTER);
		add(spanel, BorderLayout.SOUTH); 
		add(npanel, BorderLayout.NORTH);
			
		textbox.add(printscroll);
		textbox.add(compressscroll);
		
		npanel.add(progname);
		cpanel.add(textbox);
		
	
		
			
		
	}
	
	private class myActionListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e)
		{
			JButton selected = (JButton)e.getSource();
			
			if((selected.getText()).equals("허프만 압축"))
			{
				
				zipfile pf = new zipfile(filename.getText(), printbox, compressbox,0);
				pf.chcounting();
				pf.heaptest();				
			
			}
			
			else if((selected.getText()).equals("허프만 압축해제"))
			{
				unzipfile uf = new unzipfile(filename.getText(), printbox, compressbox);
					
				uf.Unzipfile();
			}
			
		
			
			else if((selected.getText()).equals("허프만 암호화"))
			{
				zipfile pf = new zipfile(filename.getText(), printbox, compressbox,1);
				pf.chcounting();
				pf.heaptest();	
				
				pf.encryfile();
				
			}
			
			else if((selected.getText()).equals("허프만 암호화해제"))
			{
				
				unzipfile uf = new unzipfile(filename.getText(), printbox, compressbox);
				
				uf.unencryfile();
			}
			
			else if((selected.getText()).equals("프로그램 종료"))
				System.exit(0);
		
		}

	}
	
	

	public static void main(String[] args) {
		new menu();

	}

	
}
