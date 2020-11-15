class Problem {

    public static void main(String[] args) {
        String operator = args[0];
        int result = Integer.parseInt(args[1]);
        switch (operator) {
            case "SUM":
                for (int i = 2; i < args.length; i++) {
                    result += Integer.parseInt(args[i]);
                }
                break;
            case "MIN":
                for (int i = 2; i < args.length; i++) {
                    if (Integer.parseInt(args[i]) < result) {
                        result = Integer.parseInt(args[i]);
                    }
                }
                break;
            case "MAX":
                for (int i = 2; i < args.length; i++) {
                    if (Integer.parseInt(args[i]) > result) {
                        result = Integer.parseInt(args[i]);
                    }
                }
                break;
            default:
                System.out.println("Wrong Input");
                break;
        }
        System.out.println(result);
    }
}