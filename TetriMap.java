import java.util.ArrayList;

public class TetriMap {


    private static char [][] map;

    private static void initMap(int size)
    {
        map = new char [size][size];
        for (int i = 0; i < map.length; i++)
        {
            for (int j = 0; j < map.length; j++)
            {
                map[i][j] = '*';
            }
        }
    }

    private static void print()
    {
        for (char [] subArray : map)
        {
            for (char point :  subArray)
            {
                System.out.print(point);
            }
            System.out.println();
        }
    }

    private static boolean tryPlaceFigure(Tetriminous tetriminous, int y, int x, boolean clear)
    {
        int [] points;
        points = tetriminous.getPoints();

        if (clear) {
            for (int i = 0; i < points.length - 1; i += 2) {
                TetriMap.map[points[i] + y][points[i + 1] + x] = '*';
            }
            return true;
        }
        try {

            for (int i = 0; i < points.length - 1; i += 2) {
                if (TetriMap.map[points[i] + y][points[i + 1] + x] == '*')
                    continue;
                else
                    return false;
            }
        }catch (IndexOutOfBoundsException ex)
        {
            return false;
        }

            for (int i = 0; i < points.length - 1; i += 2) {
                if (TetriMap.map[points[i] + y][points[i + 1] + x] == '*') {
                    TetriMap.map[points[i] + y][points[i + 1] + x] = tetriminous.getOrder();
                } else
                    return false;
            }
        return true;
    }

    private static boolean solve(ArrayList<Tetriminous> list, int size, int pos)
    {
        int y;
        int x;


        y = 0;
        x = 0;
        try
        {
            list.get(pos);
        }catch (IndexOutOfBoundsException ex)
        {
            return true;
        }

        while (y < size)
        {

            while (x < size)
            {
                if (tryPlaceFigure(list.get(pos), y, x, false)) {

                    if (solve(list, size, pos + 1))
                    {
                        return true;
                    }else
                    {
                        tryPlaceFigure(list.get(pos), y , x, true);
                    }

                }
                x++;
            }
            x = 0;
            y++;
        }
        return false;
    }



    public static void algo(ArrayList<Tetriminous> list, int size, int pos)
    {
        initMap(size);
        while (!solve(list, size, pos))
        {
            size++;
            initMap(size);
        }
        print();
    }
}
