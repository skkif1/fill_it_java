import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TetriParser {

    private  static   List<String> file = new LinkedList<>();
    private  static   State state = new State();

    public static void readFile(String fileName) throws IOException
    {
        String tempStr;

        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        while ((tempStr = reader.readLine()) != null)
        file.add(tempStr);
    }

    public static List<Tetriminous> parse()
    {
        StringBuilder temp = new StringBuilder();
        int line = 0;
        int fileLines = 0;
        char order = 'A';
        int fileSize;

        List<Tetriminous> tetriminousList = new ArrayList<>(20);
        fileSize = file.size();
        while (fileLines < fileSize)
        {
            for (int i = 0; i < 4; i++)
            {
                temp.append(file.get(line++));
                fileLines++;
            }
            String tempstr = new String(temp);
            for (int i = 0; i < 19; i++)
            {
                if (tempstr.contains(state.storage[i]))
                {
                    Tetriminous tet = new Tetriminous(state.storage_pos[i], order++);
                    tetriminousList.add(tet);
                    break;
                }
            }
            temp = new StringBuilder();
            fileLines++;
            line++;
        }
        return tetriminousList;
    }

}
