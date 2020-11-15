import java.util.ArrayList;
import java.util.List;

class Problem {

    public static void main(String[] args) {
        List<String> keys = new ArrayList<>();
        List<String> values = new ArrayList<>();

        for (int i = 0; i < args.length; i++) {
            if (i % 2 == 0) {
                keys.add(args[i]);
            } else values.add(args[i]);

        }

        for (int i = 0; i < keys.size(); i++) {
            System.out.println(keys.get(i) + "=" + values.get(i));
        }
    }
}