package huffman;


public class tree {
	
	private tree LNode; // 왼쪽노드
	private tree RNode; // 오른쪽노드
	private int data; 
	private char ascii;
	private StringBuffer hcode=new StringBuffer();
	
	public tree(){}
	  
	public tree(int treedata)
	{		
		
		data = treedata;
	}
	
	public tree(int treedata, tree LNodedata, tree RNodedata)
	{
		LNode = LNodedata;
		RNode = RNodedata;
		data = treedata;
		
	}
	
	// 아래는 점부 set, get함수
	public void setter_LNode(tree LNodedata)
	{
		LNode = LNodedata;
	}
	
	public void setter_RNode(tree RNodedata)
	{
		RNode = RNodedata;
	}
	public tree getter_LNode()
	{
		return LNode;
	}
	
	public tree getter_RNode()
	{
		return RNode;
	}
	
	
	
	
	
	
	
	public void setter_data(int treedata)
	{
		data = treedata;
	}
	public int getter_data()
	{
		return data;
	}
	
	
	public void setter_asc(char asc)
	{
		ascii = asc;
	}

	public char getter_asc()
	{
		return ascii;
	}
	
	
	public void setter_code(String treecd)
	{
		hcode.append(treecd);
	}
	
	public StringBuffer getter_code()
	{
		return hcode;
	}
	
	
	
	
	
	
}
