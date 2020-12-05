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
  Move bestMove;
  String landingPieceName;

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
    Move potentialBestMove = new Move();
    Stack filteredMoves = new Stack();                //Stack of Moves that meet the desired parameters of the for loop.
    Stack initalMoves = possibilities;                //Make note of moves before they're popped.
    System.out.println(initalMoves);
    for(int i = 0; i < initalMoves.size(); i++){      //Loop through each potential move.
      bestMove = (Move) possibilities.pop();          //Set best move to the lastest move.
      Square startingSquare = bestMove.getStart();
      Square landingSquare = bestMove.getLanding();
      int xPos = landingSquare.getXC();
      int yPos = landingSquare.getYC();
      landingPieceName = landingSquare.getName();
      int landingPieceScore = bestMove.getScore();

      if((landingPieceName.contains("BlackPawn")) && (landingPieceScore <= 2)){     //Used to start filtering possible moves into moves with desired parameters stack.
        if(landingPieceScore != 2){
          filteredMoves.clear();
        }
        filteredMoves.add(bestMove); 
      }
      if((landingPieceName.contains("BlackKnight")) && (landingPieceScore <= 3)){   //Used to start filtering possible moves into moves with desired parameters stack.
        if(landingPieceScore != 3){
          filteredMoves.clear();
        }
        filteredMoves.add(bestMove); 
      }
      if((landingPieceName.contains("BlackBishop")) && (landingPieceScore <= 3)){   //Used to start filtering possible moves into moves with desired parameters stack.
        if(landingPieceScore != 3){
          filteredMoves.clear();
        }
        filteredMoves.add(bestMove);
      }
      if((landingPieceName.contains("BlackRook")) && (landingPieceScore <= 5)){     //Used to start filtering possible moves into moves with desired parameters stack.
        if(landingPieceScore != 5){
          filteredMoves.clear();
        }
        filteredMoves.add(bestMove);
      }
      if((landingPieceName.contains("BlackQueen")) && (landingPieceScore <= 9)){    //Used to start filtering possible moves into moves with desired parameters stack.
        if(landingPieceScore != 9){
          filteredMoves.clear();
        }
        filteredMoves.add(bestMove);
      }
      if((landingPieceName.contains("BlackKing")) && (landingPieceScore <= 100)){   //Used to start filtering possible moves into moves with desired parameters stack.
        if(landingPieceScore != 100){
          filteredMoves.clear();
        }
        filteredMoves.add(bestMove);
      }
      if(((yPos == 3) || (yPos == 4)) &&(landingPieceScore <= 1)){    //Used to create a random move when weighted moves can't be found.
        filteredMoves.add(bestMove);
      }
    }
      int moveID = rand.nextInt(filteredMoves.size());

      for (int i = 1; i < (filteredMoves.size() - (moveID)); i++) {
		filteredMoves.pop();
      }
	  bestMove = (Move) filteredMoves.pop();
	  System.out.println("PIECE CAPTURED! : " + landingPieceName);
      return bestMove;
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
