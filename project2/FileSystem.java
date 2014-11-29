import java.util.*;

public class FileSystem
{
	private static Dir root;
	private static Dir currDir;

	public FileSystem()
	{
		root = null;
	}

	public FileSystem (String t)
	{ 
		root = new Dir(t); //create new directory with name t  
		currDir = root;
	}

	private void createDir (String dirName)
	{
		currDir.mkdir(dirName);
		currDir = root;
	}
	
	public void mkdir (String path)
	{
		String[] pathArr = path.split("\\\\");
		currDir = findDir(pathArr, 1);
		createDir(pathArr[pathArr.length - 1]);
		
	}
	//finds a directory and returns the directory object 
	//throw exception if not found
	private Dir findDir(String[] pathArr, int arrPos)
	{	
		if (arrPos  == pathArr.length  - 1 )
			return currDir;
		else
		{
			for (int i = 0; i < currDir.size(); i++)
			{
				String name = currDir.get(i).name;
				if (pathArr[arrPos].equals(name))
				{
					currDir = (Dir) currDir.get(i);
					break;
				}
			}
			return findDir(pathArr, arrPos + 1);
		}
	}


	private void createFile (String fileName)
	{
		currDir.touch(fileName);
		currDir = root;

	}
	public void touch (String path)
	{
		String[] pathArr = path.split("\\\\");
		currDir = findDir(pathArr,0);
		createFile(pathArr[pathArr.length-1]);
	}

	//display tree of all files/dir starting at root
	private void tree (SysObj e, int nSp)
	{
		for (int j = 0; j< nSp; j++)
		{
			System.out.print("  ");
		}
		
		System.out.print(e.name);
		
		if ((e instanceof Dir)) //if directory re-call tree
		{
			System.out.print("/\n");

			for(int i = 0; i < e.size(); i++)
			{
				tree(e.get(i),nSp+1);
			}
		}
		else
			System.out.print("\n");
		

	}
	public void tree ()
	{
		tree(root,0);
	}

	public int countFiles()
	{
		return root.countFiles();
	}

	public int countDirs()
	{
		return root.countDirs();
	}
	
	public int countChars()
	{
		return root.countChars();
	}

	public void showFileContents(String path)
	{
		String [] pathArr = path.split("\\\\");
		currDir = findDir(pathArr,0);
		File f = currDir.findFile(pathArr[pathArr.length-1]);
		System.out.println(f.getContents());
	}

	public void write (String path, String contents)
	{
		String [] pathArr = path.split("\\\\");
		currDir = findDir(pathArr,0);
		File f = currDir.findFile(pathArr[pathArr.length-1]);
		f.write(contents);
		currDir = root;
	}







public static void main (String [] args)
{
	FileSystem sys = new FileSystem("projects"); //create new system with root Dir project (project)
	//sys.touch("homework.txt");
	sys.mkdir("projects\\fall");
	sys.mkdir("projects\\fall\\fall14");
	sys.mkdir("projects\\fall\\fall14\\3340");
	sys.mkdir("projects\\fall\\fall14\\3345");
	sys.mkdir("projects\\spring");
	sys.mkdir("projects\\spring\\sp14");
	sys.mkdir("projects\\spring\\sp14\\4348");
	sys.mkdir("projects\\spring\\sp14\\4349");

	sys.touch("projects\\fall\\fall14\\3340\\homework1.txt");
	sys.write("projects\\fall\\fall14\\3340\\homework1.txt", "homework 1");

	sys.touch("projects\\fall\\fall14\\3340\\homework2.txt");
	sys.write("projects\\fall\\fall14\\3340\\homework2.txt","homework 2");
	
	sys.touch("projects\\fall\\fall14\\3345\\homework1.txt");
	sys.write("projects\\fall\\fall14\\3345\\homework1.txt", "homework 1");

	sys.touch("projects\\fall\\fall14\\3345\\homework2.txt");
	sys.write("projects\\fall\\fall14\\3345\\homework2.txt","homework 2");
	
	sys.touch("projects\\spring\\sp14\\4348\\syllabus.txt");
	sys.write("projects\\spring\\sp14\\4348\\syllabus.txt","syllabus");

	sys.tree();

	System.out.println("Number of Files: " + sys.countFiles());
	System.out.println("Number of Folders: " + sys.countDirs());
	System.out.println("Number of chars: " + sys.countChars());
	System.out.print("projects\\spring\\sp14\\4348\\syllabus.txt contains: ");
	sys.showFileContents("projects\\spring\\sp14\\4348\\syllabus.txt");

}
}