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
        filteredMoves.clear();
        filteredMoves.push(bestMove);
      }
      if((landingPieceScore == 3)){   //Used to start filtering possible moves into moves with desired parameters stack.
        filteredMoves.clear();
        filteredMoves.push(bestMove);
      }
      if((landingPieceScore == 5)){     //Used to start filtering possible moves into moves with desired parameters stack.
        filteredMoves.clear();
        filteredMoves.push(bestMove);
      }
      if((landingPieceScore == 9)){    //Used to start filtering possible moves into moves with desired parameters stack.
        filteredMoves.clear();
        filteredMoves.push(bestMove);
      }
      if(landingPieceScore == 100){   //Used to start filtering possible moves into moves with desired parameters stack.
        filteredMoves.clear();
        filteredMoves.push(bestMove);
      }
    }
    if (!filteredMoves.empty()) {   
      for (int i = 0; i < (filteredMoves.size()); i++) {
        Move latestFilteredMove = (Move)filteredMoves.pop();
        System.out.println(filteredMoves.elements());
        if(latestFilteredMove.pieceScoreWeighting >= pieceScore){
          newBestMove = latestFilteredMove;
          pieceScore = latestFilteredMove.pieceScoreWeighting;
          System.out.println("New best move found");
          System.out.println("NBM START X" + newBestMove.start.xCoor);
          System.out.println("NBM START Y" + newBestMove.start.yCoor);
          System.out.println("NBM LAND X" + newBestMove.landing.xCoor);
          System.out.println("NBM LAND Y" + newBestMove.landing.yCoor);
        }
        System.out.println("AAAAAAA" + pieceScore);
        System.out.println("BBBBBBB" + landingPieceScore);
        //filteredMoves.pop();
      }
      /**
      filteredMoves.pop();
      Move latestFilteredMove = (Move)filteredMoves.pop();
        //int pieceScore = 0;
      if(pieceScore <= latestFilteredMove.pieceScoreWeighting){
        newBestMove = latestFilteredMove;
        pieceScore = latestFilteredMove.pieceScoreWeighting;
      }
        System.out.println("AAAAAAA" + landingPieceScore);
      potentialBestMove = (Move) filteredMoves.pop();
      bestMove = (Move) filteredMoves.pop();
      filteredMoves.clear();
      possibilities.clear();
      initalMoves.clear();
      possibilities.clear();
      initalMoves.clear();
      filteredMoves.clear();
       */
      pieceScore=0;
      return newBestMove;
    }
    else {
      //Stack test = (Stack)filteredMoves.clone();
      for (int i = 1; i < (initalMoves.size()); i++) {
		    initalMoves.pop();
      }
      potentialBestMove = (Move) initalMoves.pop();
      /**
      bestMove = (Move) filteredMoves.pop();
      System.out.println("PIECE CAPTURED! : " + landingPieceName);
      possibilities.clear();
      initalMoves.clear();
      filteredMoves.clear();
       */
      return potentialBestMove;
    }
    
  }
  
  /**
   * 
   * From current position check possible moves and for each possible moves
   * landing position check possible moves. 
   */
  public Move twoLevelsDeep(Stack whitePossibilities, Stack blackPossibilities2){
        Stack whiteMovePossibilities = (Stack) whitePossibilities.clone();
        Stack blackMovePossibilities = (Stack) blackPossibilities2.clone();
        Move whitePlayerMovement = null;
        Move blackPlayerMovement = null;
        Square whitePosition;
        Square blackPosition;
        Stack filteredMoves = new Stack();
        int bestBlackScore = 0;
        int AIScore = 0;
        int bestAIScore = -100; //given a minus value as when blackScore - bestWhiteScore, the value can be a minus
              while (!blackMovePossibilities.isEmpty()) { //while all black moves stack is not empty
                //int blackPieceWeighting = 0;
                blackPlayerMovement = (Move) blackMovePossibilities.pop(); //assign a variable to pop a black move
                blackPosition = blackPlayerMovement.getLanding(); //blackPosition = the current black move landing square.
                int blackLandingPieceScore = blackPlayerMovement.pieceScoreWeighting;
                /** 
                if (blackPosition.getName().contains("Pawn")) {
                  blackPieceWeighting = 2;
                } 
                else if ((blackPosition.getName().contains("Knight")) || (blackPosition.getName().contains("Bishop"))) {
                  blackPieceWeighting = 3;
                } 
                else if (blackPosition.getName().contains("Rook")) {
                  blackPieceWeighting = 5;
                } 
                else if (blackPosition.getName().contains("Queen")) {
                  blackPieceWeighting = 9;
                } 
                else if (blackPosition.getName().contains("King")) {
                  blackPieceWeighting = 100;
                } 
                else {
                  blackPieceWeighting = 0;
                }
                */
                if (bestBlackScore < blackLandingPieceScore) { //getting the highest white score that can be made
                  bestBlackScore = blackLandingPieceScore;
                }//end if for getting the highest white score

                while (!whiteMovePossibilities.isEmpty()) {//while all white moves stack is not empty
                  //int whitePieceWeighting = 0;
                  whitePlayerMovement = (Move) whiteMovePossibilities.pop();
                  whitePosition = whitePlayerMovement.getLanding();
                  int whiteLandingPieceScore = whitePlayerMovement.pieceScoreWeighting;
                  /** 
                  if((landingPieceScore == 2)){     //Used to start filtering possible moves into moves with desired parameters stack.
                    whitePieceWeighting = 2;
                  }
                  else if((landingPieceScore == 3)){   //Used to start filtering possible moves into moves with desired parameters stack.
                    whitePieceWeighting = 3;
                  }
                  else if((landingPieceScore == 5)){     //Used to start filtering possible moves into moves with desired parameters stack.
                    whitePieceWeighting = 5;
                  }
                  else if((landingPieceScore == 9)){    //Used to start filtering possible moves into moves with desired parameters stack.
                    whitePieceWeighting = 9;
                  }
                  else if(landingPieceScore == 100){   //Used to start filtering possible moves into moves with desired parameters stack.
                    whitePieceWeighting = 100;
                  }
                  else {
                    whitePieceWeighting = 0;
                  }
                  */
                  AIScore = whiteLandingPieceScore - bestBlackScore;
                  if (AIScore >= bestAIScore) {
                    if (AIScore != bestAIScore) {
                      filteredMoves.clear();
                    }
                    bestAIScore = AIScore;
                    filteredMoves.add(blackPlayerMovement);
                    return randomMove(filteredMoves);
                  }
                }
              }
        return nextBestMove(whitePossibilities);
  }

  
}
