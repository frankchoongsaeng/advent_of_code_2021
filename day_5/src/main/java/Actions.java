import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Actions {
//    public static void

    public static void main(String[] args) {
        String pathToFile = "./resources/testinput";
        parseInputFile(pathToFile);
        List<Coordinate> parsedInput = filterNonHorizontalOrVertical(parseInputFile(pathToFile));
        System.out.println(parsedInput);
        System.out.println("==============================");
        System.out.println(getPointsCovered(parsedInput));
//        println("==============================")
//        println(Actions.countOverlapping(Actions.getPointsCovered(parsedInput)))
    }

    private static List<Coordinate> filterNonHorizontalOrVertical(List<Coordinate> coordinates) {
        return coordinates.stream().filter(coordinate -> coordinate.isVertical() || coordinate.isHorizontal()).toList();
    }

    public static List<Coordinate> parseInputFile(String path)  {
        List<Coordinate> coordinates = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(path));
            while(sc.hasNext()) {
                Coordinate c = new Coordinate(sc.nextLine());
                coordinates.add(c);
            }

        } catch (FileNotFoundException fnfex) {
            System.out.println("File not found");
            fnfex.printStackTrace();
        }

        return coordinates;
    }

    public static List<Point> getPointsCovered(List<Coordinate> coordinates) {
        List<Point> points = List.of();
        for (Coordinate coordinate: coordinates) {
            if (coordinate.isHorizontal() && (coordinate.x1() > coordinate.x2())) {
                for(int i = coordinate.x1(); i <= coordinate.x2(); ++i)
                    points.add(new Point(i, coordinate.y1()));
            }
            else if (coordinate.isHorizontal() && (coordinate.x2() >= coordinate.x1())) {
                for(int i = coordinate.x2(); i <= coordinate.x1(); ++i)
                    points.add(new Point(i, coordinate.y1()));
            }
            else if (coordinate.isVertical() && (coordinate.y1() > coordinate.y2())) {
                for(int i = coordinate.y1(); i <= coordinate.y2(); ++i)
                    points.add(new Point(coordinate.x1(), i));
            }
            else {
                for(int i = coordinate.y2(); i <= coordinate.y1(); ++i)
                    points.add(new Point(coordinate.x1(), i));
            }
        }
        return points;
    }

    public static Map<Point, Integer> countOverlapping(List<Point> points) {
        Map<Point, Integer> pointIntegerMap = new HashMap<>();

        for (Point p: points) {
            pointIntegerMap.put(p, pointIntegerMap.getOrDefault(p, 0) + 1);
        }

        return pointIntegerMap;
    }
}



