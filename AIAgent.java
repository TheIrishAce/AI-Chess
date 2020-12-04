import java.util.*;

/*
    Pawn: 2
    Knight/Bishop: 3
    Rook: 5
    Queen: 9
    King is game win.
  */

public class AIAgent {
  Random rand;

  public AIAgent(){
    rand = new Random();
  }

  public Move randomMove(Stack possibilities){
    System.out.println("Hello from RANDOM");
    int moveID = rand.nextInt(possibilities.size());
    System.out.println("Agent randomly selected move : "+moveID);
    for(int i=1;i < (possibilities.size()-(moveID));i++){
      possibilities.pop();
    }
    Move selectedMove = (Move)possibilities.pop();
    return selectedMove;
  }

  public Move nextBestMove(Stack possibilities){
    Move selectedMove = (Move)possibilities.pop();
    return selectedMove;
  }

  public Move twoLevelsDeep(Stack possibilities){
    int moveID = rand.nextInt(possibilities.size());
    System.out.println("Agent randomly selected move : "+moveID);
    for(int i=1;i < (possibilities.size()-(moveID));i++){
      possibilities.pop();
    }
    Move selectedMove = (Move)possibilities.pop();
    return selectedMove;
    //Move selectedMove = new Move();
    //return selectedMove;
  }
}
