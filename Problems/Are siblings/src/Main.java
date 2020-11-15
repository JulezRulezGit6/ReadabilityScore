import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Siblings {
    //3:04 - 3:23
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("C:\\Users\\Asus\\Desktop\\in.TXT");
        Scanner scanner = new Scanner(file);
        List<Integer> years = new ArrayList<>();
        List<Long> population = new ArrayList<>();
        String redundant = scanner.nextLine();

        while (scanner.hasNextLine()) {
            String currentLine = scanner.nextLine();
            String[] array = currentLine.split("\\W");
            years.add(Integer.parseInt(array[0]));
            population.add(Long.parseLong(array[1].replace(",", "")));
        }
        {

        }
        double maxIncrement = Double.MIN_VALUE;
        int index = 0;
        for (int i = 1; i < population.size(); i++) {
            double currentIncrement = (double) population.get(i) - population.get(i - 1);
            if (currentIncrement > maxIncrement) {
                maxIncrement = currentIncrement;
                index = i;
            }

        }
        System.out.println(years.get(index));

    }

}