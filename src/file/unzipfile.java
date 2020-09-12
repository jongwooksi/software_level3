package file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JTextArea;
import huffman.heapsort;
import huffman.table;
import huffman.tree;



public class unzipfile {

	private int pos = 7;
	private int shiftbit = 0;
	private tree root;
	private String filename;
	private JTextArea printbox;
	private JTextArea compressbox;
	private int maxcnt = 600000;
	private heapsort heap = new heapsort();
	private table[] table = new table[maxcnt];
	private String unziptext;
	private long starttime;
	private long endtime;
	private long endunencrytime;
	
	public unzipfile(String filename,JTextArea printbox, JTextArea compressbox)
	{
		
		
		this.filename = filename;
		this.printbox = printbox;
		this.compressbox = compressbox;
		
		printbox.setText("");
			
		for (int i = 0; i < maxcnt; i++) {
			table[i] = new table();
		}
		
		starttime = System.currentTimeMillis();
	}
	
  
	

	public void chconfirm(int what)
	{
		compressbox.setText("");
		compressbox.append("\t <����>               <����>                 <�������ڵ�>\n");
		
		for (int i = 0; i < maxcnt; i++)
		{
			if (table[i].cnt != 0) {
				compressbox.append("\t");
				
				
				if (i == 9)  
				{
					compressbox.append("TAP                 ");
				} 
				else if (i == 10) 
				{
					compressbox.append("NEWLINE            ");
				} 
				else if (i == 13)
				{
					compressbox.append("ENTER               ");
				} 
				
				else if (i == 32)
				{
					compressbox.append("SPACE               ");
				} 
						
				else 
				{
					compressbox.append(Character.toString(table[i].datach));
					compressbox.append("\t");
				}
				
				
				compressbox.append(String.valueOf(table[i].cnt));
				compressbox.append("\t");
				compressbox.append(String.valueOf(table[i].code));
				compressbox.append("\n");
			}
		}
		if(what == 0)
		{
			compressbox.append("######���� �������� �Ϸ�######\n");
			compressbox.append("�ɸ� �ð�: "+ (endtime-starttime)/1000.0);
		}
			
		else if(what == 1)
		{
			compressbox.append("######���� ��ȣȭ���� �Ϸ�######\n");
			compressbox.append("�ɸ� �ð�: "+ (endunencrytime-starttime)/1000.0);
		}
			
		
		
	}
	
	
	
	
public void heaptest()
{
	
	
	for (int i = 0; i < maxcnt; i++)
	{
		if (table[i].cnt != 0)
		{
			tree newNode = new tree(); 
			newNode.setter_data(table[i].cnt); 
			newNode.setter_asc((char) i);
			heap.heapinsert(newNode); 
			
		}
		
	}
	
	
	
	while(heap.getter_hsize() > 1)
	{
		tree moditree = new tree();
		moditree.setter_LNode(heap.heapdelete());
		moditree.setter_RNode(heap.heapdelete());
		moditree.setter_data(moditree.getter_LNode().getter_data() + moditree.getter_RNode().getter_data());
																			
																			
		heap.heapinsert(moditree);
	}
	
	root = heap.heapdelete();
	

	
	invtree(root);
			
	
	
}
	
	
public void invtree(tree root) 
{
		
		if (root == null) 
		{
			return;
		}
		
		
		if (root.getter_LNode() != null)
			root.getter_LNode().setter_code(root.getter_code() + "0"); 
																				
																				
		if (root.getter_RNode() != null)
			root.getter_RNode().setter_code(root.getter_code() + "1"); 
	
			
		if (root.getter_asc() != 0)
		{
			 table[(int) root.getter_asc()].code =root.getter_code();	
		}
			
		
		invtree(root.getter_LNode());
		invtree(root.getter_RNode()); 
		
	}



public void Unzipfile() {
	
	unziptext = new String();
	String a;
	int c;
	int endcnt = 0;
	
	try {
		BufferedReader unziptable = new BufferedReader(new FileReader(filename));	
		BufferedReader tokenprint = new BufferedReader(new FileReader(filename));	
		
	
		
		while ((a = unziptable.readLine()) != null) 
		{
			try
			{
				String[] tokens = a.split(","); //,�� �����ڷ� ����Ͽ� ���� ���ЩP�� ���̺� ����
				
			      table[Integer.parseInt(tokens[0])].data = tokens[0];
			      table[Integer.parseInt(tokens[0])].datach =(char) Integer.parseInt(tokens[0]);
			      table[Integer.parseInt(tokens[0])].cnt = Integer.parseInt(tokens[1]);
			  
			}
			
			catch (ArrayIndexOutOfBoundsException e) {
				break;
			}
			catch(NumberFormatException e) {}
		}
		 
		 
		 
		heaptest();
		
		
		
		
		
		while ((c = tokenprint.read()) != -1) 
		{
			try
			{
				
				if(c=='��') // �����ڰ� ����ִ��� �˱����� 
					break;
				endcnt++;				
			}
			
			catch (ArrayIndexOutOfBoundsException e) {
				break;
			}
			catch(NumberFormatException e) {}
		}
		
		unziptable.close(); 
		tokenprint.close();
		
		tree rootcpy = root; // ��Ʈ�� ī����
		
		FileInputStream unzipfile = new FileInputStream(filename);
		InputStreamReader unzipbit = new InputStreamReader(unzipfile);
		
	
		int temp = 0 ;
	
		while (true) 
		{
			if(temp<= endcnt) // �����ڰ� ������ �������� �׳� �ѱ�
			{
				shiftbit = unzipbit.read();
				temp++;
			}
			else
			{
				if ( pos == 7 )                          // �ʱ�ȭ                                    
		         {
		        	 shiftbit = unzipbit.read();                                                               
		        	 
		        	 pos = 0;                                                                     
		         }

				if( shiftbit == -1 )                                                              
	       		 break;
		         
				
				if( (shiftbit & (64>>>pos)) == 0){			// ���� 0�̸� ����
						root = root.getter_LNode();			
						if (root.getter_LNode() == null)
						{
							unziptext += root.getter_asc();				
							root = rootcpy;
							
						}
			
				}
			
			  else if( (shiftbit & (64>>>pos)) != 0){ // ���� 1�̸� ������
					
						root = root.getter_RNode();
						if (root.getter_RNode() == null)
						{
							unziptext += root.getter_asc();
			
							root = rootcpy;
						}
			
				}
				// �ڽĳ�尡 ������ �� ���� �ش��ϴ� ������ ����
				pos++;
			}
			
		
			
		}
		
		
		 
		unzipbit.close();
		
		try {
			
			FileWriter out = new FileWriter(filename+"(unzip).txt");
			
			printbox.setText("");
			
				for(int i=0; i<unziptext.length(); i++)
				{
					out.write(unziptext.charAt(i));
					printbox.append(String.valueOf(unziptext.charAt(i)));
				}
					
				out.close();
				
			} 
			catch (IOException e) {
				e.printStackTrace();
			} 
	     
		 
	       
	    
	         
	} catch (IOException e) {	
	} 
		
	
	endtime = System.currentTimeMillis();
	chconfirm(0);
}	



public void unencryfile() {
	
	String unencrytext = new String();
	

	try {
		BufferedReader encryin = new BufferedReader(new FileReader(filename+"(inform).txt"));	
		
		String a;
		
		
		while ((a = encryin.readLine()) != null) 
		{
			try
			{
				String[] tokens = a.split(","); 
				
			      table[Integer.parseInt(tokens[0])].data = tokens[0];
			      table[Integer.parseInt(tokens[0])].datach =(char) Integer.parseInt(tokens[0]);
			      table[Integer.parseInt(tokens[0])].cnt = Integer.parseInt(tokens[1]);
			  
			}
			
			catch (ArrayIndexOutOfBoundsException e) {
				break;
			}
			catch(NumberFormatException e) {}
		}
		 
		encryin.close(); 
		 
		heaptest();
		
		
		tree rootcpy = root;
		
		FileInputStream encryfile = new FileInputStream(filename+"(inform).txt");
		InputStreamReader encryinin = new InputStreamReader(encryfile);
		
		
		int c;
		while ((c = encryinin.read()) != -1) 
		{
			
			
			if( c==1)// 1�̸� ����
			{			
					root = root.getter_LNode();			
					if (root.getter_LNode() == null)
					{
						unencrytext += root.getter_asc();				
						root = rootcpy;
						
					}
		
			}
			
		
		  else if( c==0) // 0�̸� ������
		  {
				
					root = root.getter_RNode();
					if (root.getter_RNode() == null)
					{
						unencrytext += root.getter_asc();
		
						root = rootcpy;
					}
		
			}
		}
		
		
		 
		encryinin.close();
		
		try {
			
			FileWriter encryout = new FileWriter(filename+"(unencry).txt");
			
			printbox.setText("");
			
				for(int i=0; i<unencrytext.length(); i++)
				{
					encryout.write(unencrytext.charAt(i));
					printbox.append(String.valueOf(unencrytext.charAt(i)));
				}
					
				encryout.close();
				
			} 
			catch (IOException e) {
				e.printStackTrace();
			} 
	     
		 
	       
	    
	         
	} catch (IOException e)
	{
			
	} 
		
	endunencrytime = System.currentTimeMillis();
	chconfirm(1);
}	



}


