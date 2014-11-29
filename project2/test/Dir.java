// import java.util.*;
//  class Dir extends SysObj {
// 	/*==========================================
// 	=            Vars for Directory            =
// 	==========================================*/
// 	private LinkedList<SysObj> sysObjs;
	
// 	public Dir (String name)
// 	{
// 		super(name);
// 		sysObjs = new LinkedList<>(); //linked list of files/directories
// 	}
// 	public void mkdir(String dirName)
// 	{
// 		sysObjs.add(new Dir(dirName));
// 	}
// 	public void touch(String fileName)
// 	{
// 		sysObjs.add(new File(fileName));
// 	}

// 	@Override
// 	public int size()
// 	{
// 		return sysObjs.size();
// 	}

// 	public SysObj get(int i)
// 	{
// 		return sysObjs.get(i);
// 	}
// }