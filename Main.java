import java.util.Random;

class Main {

    public static int d_9(Random r){
        int roll = r.nextInt(1,10);
        if(roll == 1){
            return -4;
        }
        if(roll == 2){
            return -3;
        }
        if(roll == 3){
            return -2;
        }
        if(roll == 4){
            return -1;
        }
        if(roll == 5){
            return 0;
        }
        if(roll == 6){
            return 1;
        }
        if(roll == 7){
            return 2;
        }
        if(roll == 8){
            return 3;
        }
        return 4;
    }

    public static int FUDGE_dice(Random r){
        int total = 0;
        for(int i = 0; i < 4; i++){
            int roll = r.nextInt(3);
            if(roll == 0){
                total += -1;
            }
            else if(roll == 2){
                total += 1;
            }
        }
        return total;
    }

    public static int two_d_20(Random r){
        int roll_m = r.nextInt(1,21)/5;
        int roll_p = r.nextInt(1,21)/5;
        return roll_p - roll_m;
    }

    public static int four_d_6(Random r){
        int min_m = 900;
        int min_p = 900;
        int[] roll_m = {r.nextInt(1,7),r.nextInt(1,7)};
        int[] roll_p = {r.nextInt(1,7),r.nextInt(1,7)};
        for(int i = 0; i < roll_m.length; i++){
            if(roll_m[i] < min_m){
                min_m = roll_m[i];
            }
            if(roll_p[i] < min_p){
                min_p = roll_p[i];
            }
        }
        if(min_m == min_p){
            return 0;
        }
        if(min_m < min_p){
            if(min_m < 5){
                return -min_m;
            }
            return -4;
        }
        if(min_p < 5){
            return min_p;
        }
        return +4;
    }

    public static String distribution(int[] list){
        int[] counts = new int[9];
        String result = "";
        for(int i = 0; i < list.length; i++){
            int num = list[i];
            switch (num) {
                case (-4):
                    counts[0]++;
                    break;
                case (-3):
                    counts[1]++;
                    break;
                case (-2):
                    counts[2]++;
                    break;
                case (-1):
                    counts[3]++;
                    break;
                case (4):
                    counts[8]++;
                    break;
                case (3):
                    counts[7]++;
                    break;
                case (2):
                    counts[6]++;
                    break;
                case (1):
                    counts[5]++;
                    break;
                case (0):
                    counts[4]++;
                    break;
                default:
                    break;
            }
        }
        for(int count : counts){
            double c = count;
            double len = list.length;
            double res = Math.round((c/len)*100);
            result += res + "%\t";
        }
        return result;
    }

    public static int[][] roll_many(int cases){
        int[][] result = new int[4][cases];
        Random r = new Random();
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < cases; j++){
                switch (i) {
                    case 0:
                        result[i][j] = FUDGE_dice(r);
                        break;
                    case 1:
                        result[i][j] = two_d_20(r);
                        break;
                    case 2:
                        result[i][j] = four_d_6(r);
                        break;
                    case 3:
                        result[i][j] = d_9(r);
                    default:
                        break;
                }
            }
        }
        return result;
    }

    public static void show(int cases){
        int[][] data = roll_many(cases);
        String[] labels = {"4dF","2d20","4d6","1d9"};
        String display = " \t-4\t-3\t-2\t-1\t0\t1\t2\t3\t4\n";
        for(int i = 0; i < 4; i++){
            display += labels[i] + ":\t"+distribution(data[i])+"\n";
        }
        System.out.println(display);
    }

    public static void main(String[] args) {
        show(3000);
    }

}