import java.io.*;
import java.util.LinkedList;

public class FileHandler {
    public LinkedList<Member> readFile(){  //readFile() - метод, который возвращает коллекции типом Member
    //читает данный из csv файла
    LinkedList<Member> m = new LinkedList(); //создаем коллекцию
    String lineRead;
    String[] splitLine;
    Member mem;

    try (BufferedReader reader = new BufferedReader(new FileReader("members.csv")))
    {
        lineRead = reader.readLine(); //Считал первую строчку.  lineRead="S, 1, Petr, 12000,2"

        while (lineRead != null)
        {
            splitLine = lineRead.split(", "); // splitLine[0]=S splitLine[1]=1 splitLine[2]=Petr splitLine[3]=2

            if (splitLine[0].equals("S"))
            {
                mem = new SingleClubMember('S', Integer.parseInt(splitLine[1]), splitLine[2],
                        Double.parseDouble(splitLine[3]), Integer.parseInt(splitLine[4]));
            }else
            {
                mem = new MultiClubMember('M', Integer.parseInt(splitLine[1]), splitLine[2],
                        Double.parseDouble(splitLine[3]), Integer.parseInt(splitLine[4]));
            }

            m.add(mem);
            //S, 1, Petr, 12000,2  (тип посетителя, id,Имя,номер клуба
            //M, 20, Иван, 25000,100 (баллы)
            lineRead = reader.readLine();
        }
    }
    catch (IOException e)
    {
        System.out.println(e.getMessage());
    }

    return m;
}

    public void appendFile(String mem){

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("members.csv", true))){
            writer.write(mem + "\n");
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void overwriteFile(LinkedList<Member> m){

        String s;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("members.temp", false)))
        {
            for (int i=0; i< m.size(); i++)
            {
                s = m.get(i).toString();
                writer.write(s + "\n");
            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        try
        {
            File f = new File("members.csv");
            File tf = new File("members.temp");
            f.delete();
            tf.renameTo(f);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
