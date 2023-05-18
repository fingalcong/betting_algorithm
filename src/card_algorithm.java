public class card_algorithm {

    //for this algorithm, we would implement an algorithm to
    // calculate the points for yellow and red card (by all past games and past tem games)
    // past ten games contributes for 65%, and all past games contributes for 35%
    // each yellow card counts for 1 point, and each red card counts for 2 points

    // I first used poisson to calculate the probability of cards for different teams,
    //yet I found that there are many games that do not have any cards in real games
    // so the past ten here would be much better compared to poisson

    static double[] allgame_mean = new double[2];
    static double[] pastten_mean = new double[2];
    static double[] small_big = new double[2];

    public static void all_cardcal(int total_gamecnt, int home_yellow,
                                   int home_red, int away_yellow, int away_red){
        int total_homepoint = home_yellow + home_red * 2;
        int total_awaypoint = away_yellow + away_red * 2;
        double home_point = Double.valueOf(total_homepoint);
        double away_point = Double.valueOf(total_awaypoint);
        double home_mean = home_point / total_gamecnt;
        double away_mean = away_point / total_gamecnt;
        allgame_mean[0] = home_mean;
        allgame_mean[1] = away_mean;
    }

    public static void pastten_cardcal(int home_yellow, int home_red, int away_yellow, int away_red){
        int total_homepoint = home_yellow + home_red * 2;
        int total_awaypoint = away_yellow + away_red * 2;
        double home_point = Double.valueOf(total_homepoint);
        double away_point = Double.valueOf(total_awaypoint);
        double home_mean = home_point / 10.0;
        double away_mean = away_point / 10.0;
        pastten_mean[0] = home_mean;
        pastten_mean[1] = away_mean;
    }

    public static double final_pointcal(){
        double final_home = allgame_mean[0] * 0.35 + pastten_mean[0] * 0.65;
        double final_away = allgame_mean[1] * 0.35 + pastten_mean[1] * 0.65;
        double total = final_home + final_away;
        return total;
    }

    //the algorithm betting is used to calculate the betting ratio after the deadline of do betting amount
    public static void betting(double total, double small_amount, double big_amount){
        double small_rate = 1.0 + (big_amount / small_amount) * 0.9;
        double big_rate = 1.0 + (small_amount / big_amount) * 0.9;
        small_big[0] = small_rate;
        small_big[1] = big_rate;
    }

    public static void main(String[] args){
        //the parameters under here are the test parameters for the algorithm
        //different from the betting algorithm, there is no much difference of the card for home and away
        int total_gamecnt = 40;
        int home_yellowcard = 15;
        int home_redcard = 8;
        int away_yellowcard = 20;
        int away_redcard = 11;
        int pastten_homey = 7;
        int pastten_homer = 3;
        int pastten_awayy = 14;
        int pastten_awayr = 6;
        double small_amount = 100000;
        double big_amount = 56546;
        all_cardcal(total_gamecnt, home_yellowcard, home_redcard, away_yellowcard, away_redcard);
        pastten_cardcal(pastten_homey, pastten_homer, pastten_awayy, pastten_awayr);
        double total = final_pointcal();
        betting(total, small_amount, big_amount);
        //if the real total is bigger than predicted total, big win. Otherwise, small wins.
        System.out.println("the betting ratio for small is: " + small_big[0] +
                ", and the betting ratio for big is: " + small_big[1]);
    }
}
