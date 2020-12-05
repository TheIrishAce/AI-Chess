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
    System.out.print("START:" + start.pieceName + "\n");
    System.out.print("LAND:" + landing.pieceName + "\n");
    System.out.print("MOVE WEIGHTING:" + pieceScoreWeighting + "\n\n");
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
