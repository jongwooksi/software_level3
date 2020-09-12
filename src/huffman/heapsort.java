//Heap.java
package huffman;



public class heapsort {

	
	private int heapsize;
	private tree heaptree[];
	private tree temp;
	private tree temptree;
	private int index;
	
	public heapsort()
	{	//heap �ʱ�ȭ
		
		heapsize = 0;
		heaptree = new tree[300]; // ��Ʈ���� 300���� ����
			
	}
	
	
	
	
	public tree heapdelete()
	{	
		
		temp = heaptree[1]; // �� ����
		temptree = heaptree[heapsize--]; // �� �Ʒ���
		
		int PNode=1; // �θ���
		int CNode=2; // �ڽĳ��
		
		while(CNode <= heapsize)
		{
			if( heaptree[CNode].getter_data() >= temptree.getter_data() ) 
				break;
			
			if((CNode<heapsize) && (heaptree[CNode]).getter_data() > heaptree[CNode+1].getter_data())
				CNode++;
			
				
			heaptree[PNode] = heaptree[CNode];
			PNode = CNode;
			CNode *= 2; // �����ϰ� ���� �ٲٴ� ����
		}
		
		heaptree[PNode] = temptree;
		return temp;
	}
	

	public void heapinsert(tree tree)	
	{
		index = ++heapsize;
		
		while((index!=1) && (heaptree[index/2].getter_data()>tree.getter_data())) // �θ� �� ũ��
		{
			heaptree[index] = heaptree[index/2];
			index = index/2;
		}
		heaptree[index] = tree;
	}
	
	
	public tree Min()
	{
		if(heapsize == 0)	//0�̸� ��
			return null;
		else // �ƴϸ� ��Ʈ��
			return heaptree[1];
	}
	
	public int getter_hsize()
	{
		return heapsize;
	}
	
}



