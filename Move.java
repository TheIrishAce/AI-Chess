class Move{
  Square start;
  Square landing;
  int pieceScoreWeighting;

  // Add start, tmp and score.
  // validMove = new Move (start, tmp, score);
  
  public Move(Square x, Square y){
    start = x;
    landing = y;
  }

  public Move(Square x, Square y, int scr){
    start = x;
    landing = y;
    pieceScoreWeighting = scr;
    System.out.println("======================================~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~======================================");
    System.out.print("START:" + start.pieceName + "\n");
    System.out.print("LAND:" + landing.pieceName + "\n");
    System.out.print("MOVE WEIGHTING:" + pieceScoreWeighting + "\n\n");
    System.out.print("FROM X: " + x.xCoor + " Y " + x.yCoor + "\n\n");
    System.out.print("TO X: " + y.xCoor + " Y " + y.yCoor + "\n\n");
    System.out.println("======================================-------------------------------======================================" + "\n");
  }

  public Move(){
    
  }

  public Square getStart(){
    return start;
  }

  public Square getLanding(){
    return landing;
  }

  public int getScore(){
    return pieceScoreWeighting;
  }
}
