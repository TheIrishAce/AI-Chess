import java.util.*;
import java.util.concurrent.TimeUnit;
/*
    Pawn: 2
    Knight/Bishop: 3
    Rook: 5
    Queen: 9
    King is game win.
  */

public class AIAgent {
  Random rand;
  int landSquareScore = 0;

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
    Move selectedMove = new Move();
    Move potentialMove;
    Stack moves = new Stack();
    Stack moves2 = (Stack)possibilities.clone();
    for(int i=0; i < moves2.size(); i++){
      potentialMove = (Move) possibilities.pop(); // Mismatch of type? Fixed! Cast to Move type.
      Square startSquare = potentialMove.getStart();
      Square landSquare = potentialMove.getLanding();
      landSquareScore = potentialMove.getScore();
      System.out.println("AI Can Land On : " + potentialMove.getName());
      //int test = potentialMove.getScore();
      int x = landSquare.getXC();
      int y = landSquare.getYC();
      String pieceName = landSquare.getName();
      int pieceScore = landSquare.getPieceScore();
      if(landSquareScore == 2 && pieceName.contains("BlackPawn")){
        //moves.clear();
        moves.add(potentialMove);
      }
      if(landSquareScore == 3 && (pieceName.contains("BlackBishop")|| pieceName.contains("BlackKnight"))){
        //moves.clear();
        moves.add(potentialMove);
      }
      if(landSquareScore == 5 && pieceName.contains("BlackRook")){
        //moves.clear();
        moves.add(potentialMove);
      }
      if(landSquareScore == 9 && pieceName.contains("BlackQueen")){
        //moves.clear();
        moves.add(potentialMove);
      }
      if(landSquareScore == 100 && pieceName.contains("BlackKing")){
        //moves.clear();
        moves.add(potentialMove);
      }
      //if(pieceName == "" && y == 3 || y == 4 && landSquareScore == 0){
        //moves.add(potentialMove);
      //}
    }
    if(!moves.empty()){
      //int index = rand.nextInt(moves.size());
      int bestMoveScore = 0;
      for(int i=1; i < (moves.size()); i++){
        Move potentialNewBestMove = (Move) moves.pop();
         if(potentialNewBestMove.score >= bestMoveScore){
           potentialNewBestMove.score = bestMoveScore;
           System.out.print("New Best Score = " + bestMoveScore);
           selectedMove = (Move) potentialNewBestMove;
         }
        //moves.pop();
      }
      System.out.println("Next Best selected move with score of : "+ landSquareScore + " \n");
      selectedMove = (Move) moves.pop();
      return selectedMove;
    }
    else{
      int index = rand.nextInt(moves2.size());
      for(int i=1; i < (moves2.size() - (index)); i++){
        moves2.pop();
      }
      System.out.println("Next Best selected move : "+ selectedMove.score);
      selectedMove = (Move) moves2.pop();
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
