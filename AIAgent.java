import java.util.*;
import java.util.concurrent.TimeUnit;
/*
    Pawn: 1
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
    System.out.println("Hello from Next Best");
    Move selectedMove = new Move();
    Move potentialMove;
    Stack moves = new Stack();
    for(int i=0; i < possibilities.size(); i++){
      potentialMove = (Move) possibilities.pop(); // Mismatch of type? Fixed! Cast to Move type.
      Square startSquare = potentialMove.getStart();
      Square landSquare = potentialMove.getLanding();
      int x = landSquare.getXC();
      int y = landSquare.getYC();
      int pieceScore = landSquare.getPieceScore();
      if(pieceScore == 0){
        moves.add(potentialMove);
      }
      if(pieceScore == 1){
        moves.add(potentialMove);
      }
      if(pieceScore == 3){
        moves.add(potentialMove);
      }
      if(pieceScore == 5){
        moves.add(potentialMove);
      }
      if(pieceScore == 9){
        moves.add(potentialMove);
      }
      if(pieceScore == 100){
        moves.add(potentialMove);
      }
    }
    if(!moves.empty()){
      int index = rand.nextInt(moves.size());
      for(int i=1; i < (moves.size() - (index)); i++){
        moves.pop();
      }
      System.out.println("Next Best selected move : "+index);
      selectedMove = (Move) moves.pop();
      return selectedMove;
    }
    else{
      int index = rand.nextInt(moves.size());
      for(int i=1; i < (moves.size() - (index)); i++){
        possibilities.pop();
      }
      System.out.println("Next Best selected move : "+index);
      selectedMove = (Move) possibilities.pop();
      return selectedMove;
    }
    
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
