import java.util.*;
public class Main {
    public static void main (String[] args){
        //Scanner s = new Scanner(System.in);
        Robot robert = new Robot(1);
        Robot rob = new Robot(2);
        int lrobertmove = 0;
        int lrobmove = 0;
        int last1state = 0;
        int last2state = 0;
        for(int i = 0; i < 1000; i++) {
            Board a = new Board();

            while (!a.game_end()) {
                a.display_turn();
                if (a.get_turn() % 2 == 0) {
                    lrobertmove = rob.robotMove(a);
                    a.dropPiece(lrobertmove);
                } else {
                    lrobmove = robert.robotMove(a);
                    a.dropPiece(lrobmove);
                }
                System.out.println();
                last2state = last1state;
                last1state = a.getState();
                if (a.game_end()) {
                    if (a.isWin() && ((a.get_turn()) % 2 == rob.get_player() % 2)) {
                        rob.updateQValue(last1state, lrobmove, 100, a.getState());
                        robert.updateQValue(last2state, lrobertmove, -100, last1state);
                    } else if (a.isWin() && (((a.get_turn()) % 2) + 1 == rob.get_player() % 2)) {
                        rob.updateQValue(last2state, lrobmove, -100, last1state);
                        robert.updateQValue(last1state, lrobertmove, 100, a.getState());
                    } else if (a.isDraw()) {
                        rob.updateQValue(last1state, lrobmove, 0, a.getState());
                        robert.updateQValue(last2state, lrobertmove, 0, last1state);
                    }
                    a.boardState();
                    System.out.println("The Game has Ended! Player " + (((a.get_turn() - 1) % 2) + 1) + " wins!");
                    break;
                }
                if (((a.get_turn())) % 2 == rob.get_player() % 2) {
                    rob.updateQValue(last1state, lrobmove, -1, a.getState());
                }
                else {
                    robert.updateQValue(last1state, lrobertmove, -1, a.getState());
                }

            }
        }
    }
}