public class corner_algorithm {

    //For the algorithm to betting on corners, we would also implement a method but not like the algorithm for cards betting
    //for the reason that we can use instead of poisson algorithm for this part.
    //(Because there are always corners in real games for both teams)
    //so the betting algorithm here would be different,
    //we would bet for which team have more corners in the match we predict for

    static int[] home_cornercnt = new int[11];
    static int[] away_cornercnt = new int[11];
    static double[] homeprob_corner = new double[home_cornercnt.length];
    static double[] awayprob_corner = new double[away_cornercnt.length];
    static double[][] allscore_prob = new double[homeprob_corner.length][awayprob_corner.length];
    static double[] prob_game_poisson = new double[3]; //0 stands for bigger home corner,
    // 1 stands for draw of corner, 2 stands for bigger away corner
    static double[] odds = new double[3]; //0 stands for the odd for home win,
    //1 stands for the draw, 2 stands for the away win

    public static void prob_cal(){
        int sum_home = 0;
        int sum_away = 0;

        //calculate the total matches amount for the hometeam
        for(int i : home_cornercnt){
            sum_home += i;
        }

        //calculate the total matches amount for the awayteam
        for(int j : away_cornercnt){
            sum_away += j;
        }

        //calculate the probability of different amount of corners for home team
        for(int i = 0; i < home_cornercnt.length; i++){
            double temp = Double.valueOf(home_cornercnt[i]);
            homeprob_corner[i] = temp / sum_home;
        }

        //calculate the probability of different amount of corners for away team
        for(int j = 0; j < away_cornercnt.length; j++){
            double temp = Double.valueOf(away_cornercnt[j]);
            awayprob_corner[j] = temp / sum_away;
        }

        //a 2d array for corner ratio between home and away team
        for(int i = 0; i < homeprob_corner.length; i++){
            for(int j = 0; j < awayprob_corner.length; j++){
                allscore_prob[i][j] = homeprob_corner[i] * awayprob_corner[j];
            }
        }
    }

    public static void rate_cal(){
        int home_length = allscore_prob.length;
        int away_length = allscore_prob[0].length;

        for(int i = 0; i < home_length; i++){
            for(int j = 0; j < away_length; j++){
                if(i > j){
                    prob_game_poisson[0] += allscore_prob[i][j];
                }
                else if(i == j){
                    prob_game_poisson[1] += allscore_prob[i][j];
                }
                else{
                    prob_game_poisson[2] += allscore_prob[i][j];
                }
            }
        }
    }

    public static void betting_algo(){
        double home_rate = prob_game_poisson[0];
        double draw_rate = prob_game_poisson[1];
        double away_rate = prob_game_poisson[2];
        double bet_win = (1 / home_rate) * 0.9;
        double bet_draw = (1 / draw_rate) * 0.9;
        double bet_loss = (1 / away_rate) * 0.9;
        odds[0] = bet_win;
        odds[1] = bet_draw;
        odds[2] = bet_loss;
    }

    public static void main(String[] args){
        home_cornercnt = new int[]{3,2,2,5,3,4,8,7,4,1};
        away_cornercnt = new int[]{1,2,3,4,3,6,7,8,3,2};
        prob_cal();
        rate_cal();
        betting_algo();
        System.out.println("probability of home team gets more corners is: " + prob_game_poisson[0]
                + ", and the probability of the draw of the corners is: " + prob_game_poisson[1]
                + ", and the probability of away team gets more corners is: " + prob_game_poisson[2]);
        System.out.println("betting odd for home corner is: " + odds[0]
                + ", betting odd for draw of corner is: " + odds[1]
                + ", betting odd for away corner is: " + odds[2]);
    }
}
