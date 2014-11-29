//project 2 tree
import java.util.*;

abstract class SysObj {
	/*========================================
	=            Member variables            =
	========================================*/
	public String name; //name of file/directory
	public SysObj ()
	{
		name = null;
	}
	public SysObj (String s) 
	{
		name = s;
	}	

	abstract int size();

	//int needed for getoverload Dir get
	abstract SysObj get (int i);

	abstract SysObj get ();
}

class Dir extends SysObj {
	/*==========================================
	=            Vars for Directory            =
	==========================================*/
	private ArrayList<SysObj> sysObjs;
	
	public Dir (String name)
	{
		super(name);
		sysObjs = new ArrayList<>(5); //linked list of files/directories
	}
	public void mkdir(String dirName)
	{
		sysObjs.add(new Dir(dirName));
	}
	public void touch(String fileName)
	{
		sysObjs.add(new File(fileName));
	}
	public void touch(String fileName, String contents)
	{
		sysObjs.add(new File(fileName, contents));
	}
	//search for directory/file in current directory 
	//and return position in linkedlist  
	public int search (String name)
	{
		//O(n^2) bad find fix later (useing array list now O(n)
		int pos;
		for(int i = 0; i < sysObjs.size(); i++)
		{
			if (sysObjs.get(i).name == name)
			{
				return i;
			}
		}
		return -1;
	}

	int size()
	{
		return sysObjs.size();
	}

	SysObj get(int i)
	{
		return sysObjs.get(i);
	}
	
	SysObj get()
	{
		return this;
	}
	
	public int countFiles()
	{
		int count = 0;
		for (int i=0; i < size(); i++)
		{
			if (sysObjs.get(i) instanceof File)
				count += 1;
			else 
			{
				Dir temp = (Dir)sysObjs.get(i);
				count += temp.countFiles();
			}
		}
		return count;
	}
	public int countChars()
	{
		int count = 0;
		for (int i=0; i < size(); i++)
		{
			if (sysObjs.get(i) instanceof File)
			{
				File f = (File) sysObjs.get(i);
				count += f.getContents().length();
			}
			else 
			{
				Dir temp = (Dir)sysObjs.get(i);
				count += temp.countChars();
			}
		}
		return count;
	}
	//find name of file and return contents
	public File findFile(String name)
	{
		File t = new File("temp");
		for (int i=0; i < sysObjs.size(); i++)
		{
			if (sysObjs.get(i).name.equals(name))
				t =  (File) sysObjs.get(i);
		}
		return t ;
	}

	public int countDirs()
	{
		int count = 1;
		for (int i=0; i < size(); i++)
		{
			if (sysObjs.get(i) instanceof Dir)
			{
				Dir temp = (Dir)sysObjs.get(i);
				count += temp.countDirs();
			}
		}
		return count;
	}

}

class File extends SysObj {
	/*=====================================
	=            Vars for File            =
	=====================================*/
	private String contents; //contents of a file
	
	public File (String name)
	{
		super(name);
		contents = null;
	}

	public File (String name, String c)
	{
		super(name);
		contents = c;
	}

	public void write(String s) 
	{

		contents = s;
	}
	public String read()
	{
		return contents;
	}
	
	public String getContents()
	{
		return contents;
	}

	int size() { return contents.length(); }

	
	File get()
	{
		return this;
	}
	
	File get(int i)
	{
		return this;
	}
}






