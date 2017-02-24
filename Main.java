import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {


        long start = System.currentTimeMillis();
        try
        {
            TetriParser.readFile(args[0]);
        }catch (IndexOutOfBoundsException ex)
        {
            System.out.println("Usage: you need to add file !)");
            return;
        }
        ArrayList<Tetriminous> list = (ArrayList<Tetriminous>) TetriParser.parse();
        Tetriminous tetriminous = list.get(0);
        TetriMap.algo(list,list.size() / 2, 0);
        System.out.println(System.currentTimeMillis() - start);
    }
}
