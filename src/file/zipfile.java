package file;

import javax.swing.JTextArea;
import huffman.table;
import huffman.tree;
import huffman.heapsort;
import java.io.*;


public class zipfile {

	
	private JTextArea printbox;
	private JTextArea compressbox;
	private heapsort heap = new heapsort();	
	private int textsize = 0;
	private StringBuffer ziptext; 
	private int maxcnt = 600000;
	private tree root;
	private String filename;
	private table[] table = new table[maxcnt];
	private int what;
	private long starttime;
	private long endtime;
	private long endencrytime;

	
	
	public zipfile(String filename,JTextArea printbox, JTextArea compressbox, int what) // menu 클래스에서 받아온 후 , 테이블을 생성한다. 또 시작시간을 기록한다.
	{
		
		
		this.filename = filename;
		this.printbox = printbox;
		this.compressbox = compressbox;
		this.what = what;
		
		printbox.setText("");
			
		for (int i = 0; i < maxcnt; i++)
		{
			table[i] = new table();
		}
		starttime = System.currentTimeMillis();
	}
	
    
	
	
	public void chcounting() {
		
		
		int  c;

		try
		{
			@SuppressWarnings("resource")
			FileReader filecnt = new FileReader(filename);
				
			while ((c = filecnt.read()) != -1) // 파일을 읽으면서 테이블을 작성한다.
			{				
				table[c].data = Character.toString((char) c);
				table[c].cnt++;
				table[c].datach = (char) c;	
				textsize++;
			}	
		
			for (int i = 0; i < textsize; i++) // 아스키코드값을 저장한다.
			{
				table[i].setter_asc(i);
			}
			
			
		} 
		catch (FileNotFoundException e)
		{
			
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		
		
	}

	
	public void chconfirm()
	{
		compressbox.setText("");
		compressbox.append("\t <문자>               <개수>                 <허프만코드>\n");
		
		for (int i = 0; i < maxcnt; i++) // 화면에 출력하기 위하여 반복문을 사용하여 append한다.
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
		
		
		compressbox.append("\n");
		if(what == 0) // 압축인 경우
		{
			compressbox.append("#########파일 압축 완료#########\n");
			compressbox.append("걸린 시간: "+ (double)(endtime-starttime)/1000+"sec\n"); // 차이가 걸린 시간이다.
			
			compressbox.append("파일 압축 전: "+textsize+"byte\n");
	        compressbox.append("내용만 압축 후: "+(int)ziptext.length()/8+"byte\n");
	        
	        compressbox.append("압축률: "+(double)textsize/((double)ziptext.length()/8)+" : 1 \n");
	        
		}
			
		else if(what == 1) // 암호화인 경우
		{
			compressbox.append("#########파일 암호화 완료#########\n");
			compressbox.append("걸린 시간: "+ (double)(endencrytime-starttime)/1000);
		}
			
		
		
	}
	
	
	
	
public void heaptest()
{
	
	
	for (int u = 0; u < maxcnt; u++) // 테이블을 받아와서 트리를 구성한다.
	{
		if (table[u].cnt != 0)
		{
			tree newNode = new tree(); 
			newNode.setter_data(table[u].cnt); 
			newNode.setter_asc((char) u);
			heap.heapinsert(newNode); 
		}
	}
	
	
	
	while(heap.getter_hsize() > 1) // 삽입과 삭제륿 반복하면서 허프만 트리를 만든다.
	{
		tree moditree = new tree();
		moditree.setter_LNode(heap.heapdelete());
		moditree.setter_RNode(heap.heapdelete());
		moditree.setter_data(moditree.getter_LNode().getter_data() + moditree.getter_RNode().getter_data()); 
																			
		heap.heapinsert(moditree);
	}
	
	root = heap.heapdelete();
	

	invtree(root); // 만든 후 루트를 함수의 인자로 보낸다.
			
	printbox.append(String.valueOf(secondplay()));
	
}
	
	
public void invtree(tree root) // 재귀함수를 통하여 허프만코드를 작성한다.
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
			 int temp =  root.getter_asc();
			 
			 table[temp].code =root.getter_code();	
		}
			

		invtree(root.getter_LNode());
		invtree(root.getter_RNode());
		
	}

	
	
	public StringBuffer secondplay()
	{
		ziptext = new StringBuffer();
		int c;
			
	
		try {
			@SuppressWarnings("resource")
			FileReader secondopen = new FileReader(filename);
		
			
			
			for (int i = 0; i < textsize; i++) // ziptext에 허프만코드만을 넣어서 한번에 화면에 출력할 수 있도록 한다.
			{
				while ((c = secondopen.read()) != -1) 
				{	
					ziptext.append(table[c].code);	
				}
			
			}			
			
		}
		
	
		catch (IOException e) {}
			
		if(what == 0)
			fileprint();
		else if(what == 1)
			fileencryprint();
		
		endtime = System.currentTimeMillis();
		
		chconfirm();
		
		return ziptext;
		 
	}
	
public void fileencryprint()
{

	FileWriter writeencry;
	try {
		int arycnt = 0;
		char[] encryary = new char[ziptext.length()];
		
		
		writeencry = new FileWriter (filename+"(encry).txt(inform).txt");
		BufferedWriter encryout = new BufferedWriter(writeencry, 2000);
		
		for(int j = 0; j < textsize ; j++) // 파일에 허프만테이블을 넣는다. ,을 이용하여 문자의아스키와 빈도수를 구분한다.
		{
			if (table[j].cnt != 0)
			{
				
				encryout.write(table[j].data);
				encryout.write(",");
				encryout.write(String.valueOf(table[j].cnt));
				encryout.newLine();
			}
				
		}	
		
		
		for (int i = 0; i <ziptext.length(); i++) //암호화에서 1과 0을 바꾸기 위함이다.
		{
			if (ziptext.charAt(i) == '0')
			{
				
				encryary[arycnt] = 1;
			}
				
			else if(ziptext.charAt(i) == '1')
			{
				
				encryary[arycnt] = 0;
						
			}
			
			arycnt++;	
		}
		
		encryout.write(encryary);
		encryout.close();
		
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	
}
	
public void fileprint()
{
	try {
		int shiftbit = 0;
		int pos = 0;
		
		
		
		FileWriter writezip = new FileWriter (filename+"(zip).txt");
		BufferedWriter zipout = new BufferedWriter(writezip, 2000);
	
		
		
	

		for(int j = 0; j < textsize ; j++) // 테이블을 작성한다. ,을 이용해 문자의아스키와 빈도수를 구분한다.
		{
			if (table[j].cnt != 0)
			{
				zipout.write(table[j].data);
				zipout.write(",");
				zipout.write(String.valueOf(table[j].cnt));
				zipout.newLine();
				
				
			}
			
			if(j == textsize-1) // 마지막인 경우 구분자를 넣는다.
			{
				zipout.write("√");
				
				
			}
				
		}
		
		
		for (int i = 0; i <ziptext.length(); i++) 
		{
			
				
			if(ziptext.charAt(i) == '1') // 1이면 시프트연산자를 이용하여 옮긴후 or한다.
			{
				
				shiftbit |= (64 >>> pos);
				
				
				
			}
			pos++;
		
			if(pos == 7) // 7이면 초기화한다.
			{
			
				zipout.write(shiftbit);
				shiftbit = 0;
				pos = 0;
			}
			
			if(i == ziptext.length()-1) // 마지막인 경우 점부다 넣는다.
			{
				zipout.write(shiftbit);
			}
				
			
				
		}
		
	
		
		
		
		
		
		zipout.close();
	
		
	}  catch (IOException e) {
		e.printStackTrace();
	}
}

	

public void encryfile()  {
	
	String unziptext = new String();
	tree rootcpy = root;
		
	FileInputStream encryfile;
	try {
		encryfile = new FileInputStream(filename+"(encry).txt(inform).txt");
		InputStreamReader encryin = new InputStreamReader(encryfile);
		int c;
		try {
			while ((c = encryin.read()) != -1) 
				{
								
					if( c==0) // 암호화파일을 만들기 위해 0왼쪽을 한다.
					{			
						root = root.getter_LNode();			
						if (root.getter_LNode() == null)
						{
							unziptext += root.getter_asc();				
							root = rootcpy;
								
						}
				
					}
				
				  else if( c==1){ // 암호화파일을 만들기 위해 1오른쪽을 한다.
						
						root = root.getter_RNode();
						if (root.getter_RNode() == null)
						{
							unziptext += root.getter_asc();
				
							root = rootcpy;
						}
				
					}
				}
		} catch (IOException e1) {
		
			e1.printStackTrace();
		}
			
	} catch (FileNotFoundException e2) {
		compressbox.append("파일을 찾을수 없어요!");
		e2.printStackTrace();
		
	}

		
	
	
		

		
	try {
			
			FileWriter encryout = new FileWriter(filename+"(encry).txt");
			
			printbox.setText("");
			
			
			
				for(int i=0; i<unziptext.length(); i++)
				{
					encryout.write(unziptext.charAt(i));
					printbox.append(String.valueOf(unziptext.charAt(i)));
				}
					
				encryout.close();
				
			} 
			catch (IOException e) {
				e.printStackTrace();
			} 
	     
		
	
	endencrytime = System.currentTimeMillis();
	chconfirm();
}       
	 
		
	
	



}

