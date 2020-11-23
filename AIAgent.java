import java.util.*;
import java.util.concurrent.TimeUnit;

public class AIAgent extends ChessProject{
  Random rand;

  public AIAgent(){
    rand = new Random();
  }

/*
  The method randomMove takes as input a stack of potential moves that the AI agent
  can make. The agent uses a rondom number generator to randomly select a move from
  the inputted Stack and returns this to the calling agent.
*/

  public Move randomMove(Stack possibilities){

    int moveID = rand.nextInt(possibilities.size());
    System.out.println("Agent randomly selected move : "+moveID);
    for(int i=1;i < (possibilities.size()-(moveID));i++){
      possibilities.pop();
    }
    Move selectedMove = (Move)possibilities.pop();
    return selectedMove;
  }

  /*
    This strategy doesn't care about what happens once the move is made.
    This could mean that the AI agenet could take a pice even though the player will immediately gain some advantage.
    The AI agent takes a pawn with this Queen and in response to this attact the player takes the queen (AI agents queen) with another pawn.
    AI after making the move is up one point as teh pawn is worth 1 however the queen has a value of nine points and when the player takes teh ai angents it is down eight points.

    Pawn: 1
    Knight/Bishop: 3
    Rook: 5
    Queen: 9
    King is game win.

    Get all the possible moves just like above with the random agent and then apply the utility function to workout which move to make.

  */
  public Move nextBestMove(Stack possibilities){
    Move selectedMove = new Move();
    return selectedMove;
  }

  /*
  This agent extends the functionality of the agent above, this agent looks ahead and trys to predict what the player is going to do next.
  
  Sounds like a min/max routine or approach.

  We know how to get the possible movements of all the pieces as we need this functionality for making random moves. We now we to be able to capture the mvements / potential movements of the players pieces.
  Once we have this stack of movements we need a fucntionyt to caluclate the value of the mvoements.
  
  */
  public Move twoLevelsDeep(Stack possibilities){
    Move selectedMove = new Move();
    return selectedMove;
  }
}
