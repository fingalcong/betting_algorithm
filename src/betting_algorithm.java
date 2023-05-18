
public class betting_algorithm {
    static double[] score_ratio = new double[2];
    static double[] rank_ratio = new double[2];
    static double[] pastten_ratio = new double[2];
    static double[] final_ratio = new double[2];
    static double[] bet_amount = new double[3];
    static double[] team_goal = new double[2];
    static int[] home_scorecnt = new int[6];
    static int[] away_scorecnt = new int[6];
    static double[] homeprob_goal = new double[home_scorecnt.length];
    static double[] awayprob_goal = new double[away_scorecnt.length];
    static double[][] allscore_prob = new double[homeprob_goal.length][awayprob_goal.length];
    static double[] big_small = new double[3];

    //from here is the start of bet prediction of the goal amount, the above is the win_bet part
    //There are two parts for goal prediction, one is the most possible goal ratio. The other one is the betting rate for different scores

    public static double HAS(double expected_goal, double allteam_goal){ //the score for home team for goal ability
        double HAS_score = expected_goal / allteam_goal;
        return HAS_score;
    }

    public static double HDS(double expected_loss, double allteam_loss){
        double HDS_score = expected_loss / allteam_loss;
        return HDS_score;
    }

    public static double AAS(double expected_goal, double allteam_goal){
        double AAS_score = expected_goal / allteam_goal;
        return AAS_score;
    }

    public static double ADS(double expected_loss, double allteam_loss){
        double ADS_score = expected_loss / allteam_loss;
        return ADS_score;
    }

    public static void expected_goal(double HAS, double HDS, double AAS,
                                       double ADS, double expected_homegoal, double expected_awaygoal){
        double home_expected = HAS * ADS * expected_homegoal;
        double away_expected = HDS * AAS * expected_awaygoal;
        team_goal[0] = home_expected;
        team_goal[1] = away_expected;
    }

    public static void big_or_small(double homegoal, double awaygoal, double small_betting_amount, double big_betting_amount){
        //the initialization of the small and big are both 1 at first.
        //we make this betting algorithm by the amount of the pool of different results
        //This is a betting between different participants, so the betting rate won't be known before the close of the betting
        //under this algorithm, we will use the final ratio of the betting amount to calculate the betting ratio
        double game_ratio = homegoal / awaygoal;
        double small_rate = 1.0 + small_betting_amount * 0.9 / big_betting_amount;
        double big_rate = 1.0 + big_betting_amount * 0.9 / small_betting_amount;
        big_small[0] = game_ratio; // we record this to let participants know the goal ratio we predict to help them make their choice
        big_small[1] = small_rate;
        big_small[2] = big_rate;
    }

    public static void prob_cal(){
        int sum_home = 0;
        int sum_away = 0;
        for (int k : home_scorecnt) {
            sum_home += k;
        }
        for (int i : away_scorecnt) {
            sum_away += i;
        }
        for(int i = 0; i < home_scorecnt.length; i++){
            double temp = Double.valueOf(home_scorecnt[i]);
            homeprob_goal[i] = temp / sum_home;
        }
        for (int j = 0; j < away_scorecnt.length; j++){
            double temp = Double.valueOf(away_scorecnt[j]);
            awayprob_goal[j] = temp / sum_away;
        }
        //above is calculating the probability for different goal amount
        for(int i = 0; i < homeprob_goal.length; i++){
            for(int j = 0; j < awayprob_goal.length; j++){

            }
        }
    }

    public static int total_gamescore(int win_game, int draw, int total_game){
        int loss_game = total_game - win_game - draw;
        int points = win_game * 2 + draw - loss_game;
        return points;
    }
    public static void gamescore_ratio(double home, double away){
        double home_p = home / (home + away);
        double away_p = away / (home + away);
        double home_final = home_p * 20;
        double away_final = away_p * 20;
        score_ratio[0] = home_final;
        score_ratio[1] = away_final;
        //return home_final;
    }
    public static void rank_ratio(double home_rank, double away_rank){
        double total = home_rank + away_rank;
        double home_ratio = 1.00 / (home_rank / total);
        double away_ratio = 1.00 / (away_rank / total);
        //can be estimated as 15% of calculation
        double homerank_final = home_ratio;
        double awayrank_final = away_ratio;
        rank_ratio[0] = homerank_final;
        rank_ratio[1] = awayrank_final;
    }

    public static int past_ten(int win_game, int draw, int total_game){
        int loss_game = total_game - win_game - draw;
        int points = win_game * 2 + draw - loss_game;
        return points;
    }

    public static void pastten_ratio(double home, double away){
        double home_p = home / (home + away);
        double away_p = away / (home + away);
        double home_final = home_p * 30;
        double away_final = away_p * 30;
        pastten_ratio[0] = home_final;
        pastten_ratio[1] = away_final;
    }

    public static double away_winpoint(double win_game, double draw, double total_game){
        double loss_game = total_game - win_game - draw;
        double points = win_game * 2 + draw - loss_game;
        double add_on = points * 1.5;
        return add_on;
    }

    public static double home_winpoint(double win_game, double draw, double total_game){
        double loss_game = total_game - win_game - draw;
        double points = win_game * 2 + draw - loss_game;
        double add_on = points;
        return add_on;
    }

    public static double draw_rate(double home_draw, double away_draw, double past_homedraw, double past_awaydraw, double total_game){
        double total_draw = home_draw + away_draw;
        double all_drawrate = total_draw / (total_game * 2);
        double past_draw = past_homedraw + past_awaydraw;
        double past_drawrate = past_draw / (10 * 2);
        double final_rate = (all_drawrate + past_drawrate * 1.5) / 2;
        return final_rate;
    }

    public static void win_rate(double h_total, double a_total, double h_rank, double a_rank,
                                  double h_pastten, double a_pastten, double h_point, double a_point,
                                double draw_rate, double home_goal, double away_goal){
        //return 0.0;
        double home = h_total + h_rank + h_pastten + h_point + home_goal * 5.0;
        double away = a_total + a_rank + a_pastten + a_point + away_goal * 5.0;
        double rate = 1.0 - draw_rate;
        double home_init = home / (home + away);
        double away_init = away / (home + away);
        double home_final = rate * home_init;
        double away_final = rate * away_init;
        final_ratio[0] = home_final;
        final_ratio[1] = away_final;
    }

    public static void bet_rate(double win_rate, double draw_rate, double loss_rate){
        //return 0.0;
        double bet_win = (1 / win_rate) * 0.9;
        double bet_draw = (1 / draw_rate) * 0.9;
        double bet_loss = (1 / loss_rate) * 0.9;
        bet_amount[0] = bet_win;
        bet_amount[1] = bet_draw;
        bet_amount[2] = bet_loss;
    }

    public static void main(String [] args){
        int x = 10;
        int y = 13;
        int z = 35;
        int m = 19;
        int n = 12;
        int rank_home = 3;
        int rank_away = 17;
        int pastten_win = 8;
        int pastten_draw = 1;
        int pastten_win2 = 3;
        int pastten_draw2 = 6;
        double away_win = 10.0;
        double away_draw = 5.0;
        double total_away = 18.0;
        double home_win = 9.0;
        double home_draw = 4.0;
        double total_home = 18.0;
        double HAS_expectedgoal = 3.5;
        double HAS_meangoal = 1.8;
        double HDS_expectedloss = 0.9;
        double HDS_meanloss = 1.5;
        double AAS_expectedgoal = 2.1;
        double AAS_meangoal = 1.4;
        double ADS_expectedloss = 1.8;
        double ADS_meanloss = 1.8;
        double betting_small = 1000000;
        double betting_big = 508945;
        home_scorecnt = new int[]{8, 8, 6, 5, 2, 0};
        away_scorecnt = new int[]{10, 7, 7, 3, 1, 1};

        double HAS_point = HAS(HAS_expectedgoal, HAS_meangoal);
        double HDS_point = HDS(HDS_expectedloss, HDS_meanloss);
        double AAS_point = AAS(AAS_expectedgoal, AAS_meangoal);
        double ADS_point = ADS(ADS_expectedloss, ADS_meanloss);
        expected_goal(HAS_point, HDS_point, AAS_point, ADS_point, HAS_expectedgoal, AAS_expectedgoal);
        big_or_small(team_goal[0], team_goal[1], betting_small, betting_big);
        double away_point = away_winpoint(away_win, away_draw, total_away);
        double home_point = home_winpoint(home_win, home_draw, total_home);
        int home = total_gamescore(x,y,z);
        int away = total_gamescore(m,n,z);
        gamescore_ratio(home, away);
        rank_ratio(rank_home, rank_away);
        //double ans = gamescore_ratio(home, away);
        int home_ten = past_ten(pastten_win, pastten_draw, 10);
        int away_ten = past_ten(pastten_win2, pastten_draw2, 10);
        pastten_ratio(home_ten, away_ten);
        double draw_rate = draw_rate(y,n,pastten_draw,pastten_draw2,z);
        win_rate(score_ratio[0], score_ratio[1], rank_ratio[0], rank_ratio[1],
                pastten_ratio[0], pastten_ratio[1], home_point, away_point, draw_rate, team_goal[0], team_goal[1]);
        bet_rate(final_ratio[0], draw_rate, final_ratio[1]);
        double win_bet = bet_amount[0];
        double draw_bet = bet_amount[1];
        double loss_bet = bet_amount[2];
        System.out.println(score_ratio[0]);
        System.out.println(score_ratio[1]);
        System.out.println(rank_ratio[0]);
        System.out.println(rank_ratio[1]);
        System.out.println(pastten_ratio[0]);
        System.out.println(pastten_ratio[1]);
        System.out.println(final_ratio[0]);
        System.out.println(final_ratio[1]);
        System.out.println(away_point);
        System.out.println(home_point);
        System.out.println(draw_rate);
        System.out.println("bet rate for win is :" + win_bet +
                ", bet rate for draw is :" + draw_bet + ", bet rate for loss is :" + loss_bet);
        System.out.println("expected goal for home team: " + team_goal[0] + ", expected goal for away team: " + team_goal[1]);
        System.out.println("for the predicted game point" + big_small[0] + ", the betting ratio for small is:" + big_small[1]
                + ", the betting ratio for big is:" + big_small[2]);
    }
}
