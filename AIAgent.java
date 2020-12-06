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
  //Move bestMove;
  String landingPieceName;
  int landingPieceScore;
  int pieceScore = 0;
  Move newBestMove;

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
    Move bestMove;
    Move potentialBestMove = new Move();
    Stack filteredMoves = new Stack();                //Stack of Moves that meet the desired parameters of the for loop.
    Stack initalMoves = (Stack) possibilities.clone();                //Make note of moves before they're popped.
    for(int i = 0; i < initalMoves.size(); i++){      //Loop through each potential move.
      bestMove = (Move) possibilities.pop();          //Set best move to the lastest move.
      Square landingSquare = (Square) bestMove.getLanding();
      int yPos = landingSquare.getYC();
      landingPieceName = landingSquare.getName();
      landingPieceScore = bestMove.pieceScoreWeighting;
      //System.out.println("-=== CURRENT SCORE FOR MOVE ===-" + landingPieceScore);
      if((landingPieceScore == 1) && (yPos == 3 || yPos == 4)){     //Used to start filtering possible moves into moves with desired parameters stack.
        //filteredMoves.clear();
        filteredMoves.push(bestMove);
      }
      if((landingPieceScore == 2)){     //Used to start filtering possible moves into moves with desired parameters stack.
        //filteredMoves.clear();
        filteredMoves.push(bestMove);
      }
      if((landingPieceScore == 3)){   //Used to start filtering possible moves into moves with desired parameters stack.
        //filteredMoves.clear();
        filteredMoves.push(bestMove);
      }
      if((landingPieceScore == 5)){     //Used to start filtering possible moves into moves with desired parameters stack.
        //filteredMoves.clear();
        filteredMoves.push(bestMove);
      }
      if((landingPieceScore == 9)){    //Used to start filtering possible moves into moves with desired parameters stack.
        //filteredMoves.clear();
        filteredMoves.push(bestMove);
      }
      if(landingPieceScore == 100){   //Used to start filtering possible moves into moves with desired parameters stack.
        //filteredMoves.clear();
        filteredMoves.push(bestMove);
      }
    }
    if (!filteredMoves.empty()) {
      //Stack test = (Stack)filteredMoves.clone();
      int moveID = rand.nextInt(filteredMoves.size());
     
      for (int i = 0; i < (filteredMoves.size() - (moveID)); i++) {
        Move latestFilteredMove = (Move)filteredMoves.pop();
        //int pieceScore = 0;
        if(latestFilteredMove.pieceScoreWeighting >= pieceScore){
          newBestMove = latestFilteredMove;
          pieceScore = latestFilteredMove.pieceScoreWeighting;
        }
        System.out.println("AAAAAAA" + landingPieceScore);
        //filteredMoves.pop();
      }
      //filteredMoves.pop();
      //Move latestFilteredMove = (Move)filteredMoves.pop();
        // //int pieceScore = 0;
      // if(pieceScore <= latestFilteredMove.pieceScoreWeighting){
      //   newBestMove = latestFilteredMove;
      //   pieceScore = latestFilteredMove.pieceScoreWeighting;
      // }
        // System.out.println("AAAAAAA" + landingPieceScore);
      //potentialBestMove = (Move) filteredMoves.pop();
      //bestMove = (Move) filteredMoves.pop();
      //filteredMoves.clear();
      return newBestMove;
    }
    else {
      //Stack test = (Stack)filteredMoves.clone();
      int moveID = rand.nextInt(initalMoves.size());
      for (int i = 0; i < (initalMoves.size()); i++) {
		    initalMoves.pop();
      }
      potentialBestMove = (Move) initalMoves.pop();
	    //bestMove = (Move) filteredMoves.pop();
	    //System.out.println("PIECE CAPTURED! : " + landingPieceName);
      return potentialBestMove;
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
