import java.util.Random;

public class DiceConsistancy {
    
    public static int d6(Random r){
        return r.nextInt(1,7);
    }

    public static int d20(Random r){
        return r.nextInt(1,21);
    }

    public static int[][] roll_many(int cases){
        Random r = new Random();
        int[][] data = new int[2][cases];
        for(int i=0;i<cases;i++){
            data[0][i] = d6(r) + d6(r) + d6(r);
            data[1][i] = d20(r);
        }
        return data;
    }

    public static double[] calc_consis(int[][] data, int lim_20, int lim_6){
        double d20_count = 0;
        double d6_count = 0;
        for(int num : data[0]){
            if(num >= lim_6){
                d6_count++;
            }
        }
        for(int num : data[1]){
            if(num >= lim_20){
                d20_count++;
            }
        }
        double cases = data[0].length;
        double[] res = {Math.round((d6_count/cases)*100),Math.round((d20_count/cases)*100)};
        return res;
    }


    public static void show(int n, int cases, int lim_20, int lim_6){
        double d20_total = 0;
        double d6_total = 0;
        double data_d20_winner = 0;
        for(int i = 0; i<n;i++){
            double[] res = calc_consis(roll_many(cases), lim_20, lim_6);
            if(res[0] < res[1]){
                data_d20_winner++;
            }
            d6_total += res[0];
            d20_total += res[1];
        }
        System.out.println("Checking consistancy of dice rolls on small sample sizes");
        System.out.println("The d20 was more consistant "+data_d20_winner+" out of "+n+" times of "+cases+" rolls.");
        System.out.println("The d20 was more consistant "+Math.round((data_d20_winner/n)*100)+"% if the time.");
        System.out.println("The d20's average consistancy: "+Math.round(d20_total/n)+"%, the 3d6: "+Math.round(d6_total/n)+"%");
    }

}
