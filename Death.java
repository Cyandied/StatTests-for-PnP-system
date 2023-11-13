import java.util.Random;

public class Death {
    public static int coin(Random r) {
        return r.nextInt(2);
    }

    public static int test(int W_stat, int help_amount, Random r) {
        int coin_amount = 4;
        int result = 0;
        if (W_stat > 2) {
            coin_amount += W_stat - 2;
        }
        for (int i = 1; i < coin_amount + 1; i++) {
            int[] coins = new int[i];
            for (int coin : coins) {
                if (coin(r) == 1) {
                    result++;
                    break;
                }
            }
        }
        if (help_amount > 0) {
            for (int i = 0; i < help_amount;i++) {
                if (coin(r) == 1) {
                    result++;
                }
            }
        }
        return result;
    }

    public static int[][] flip_many(int cases) {
        Random r = new Random();
        int[][] results = new int[5][cases];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < cases; j++) {
                switch (i) {
                    case 0:
                        results[i][j] = test(0, 0, r);
                        break;
                    case 1:
                        results[i][j] = test(0, 1, r);
                        break;
                    case 2:
                        results[i][j] = test(0, 2, r);
                        break;
                    case 3:
                        results[i][j] = test(3, 0, r);
                        break;
                    case 4:
                        results[i][j] = test(3, 1, r);
                        break;
                    default:
                        break;
                }
            }
        }
        return results;
    }

    public static String distribution(int[] list) {
        int[] counts = new int[3];
        String result = "";
        for (int num : list) {
            if (num >= 2) {
                counts[0]++;
            }
            if (num >= 3) {
                counts[1]++;
            }
            if (num >= 4) {
                counts[2]++;
            }
        }
        for (int count : counts) {
            double c = count;
            double len = list.length;
            double res = 100.0 - Math.round((c / len) * 100);
            result += res + "%\t";
        }
        return result;
    }

    public static void show(int cases) {
        System.out
                .println("Chance of death in different situations, W - willpower SR, H - amount of times recived help");
        int[][] data = flip_many(cases);
        String[] labels = { "W0 H0", "W0 H1", "W0 H2", "W3 H0", "W3 H1" };
        String display = " \tmin 2\tmin 3\tmin 4\n";
        for (int i = 0; i < 5; i++) {
            display += labels[i] + ":\t" + distribution(data[i]) + "\n";
        }
        System.out.println(display);
    }

}
