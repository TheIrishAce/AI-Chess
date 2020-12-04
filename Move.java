class Move{
  Square start;
  Square landing;
  int score;

  // Add start, tmp and score.
  // validMove = new Move (start, tmp, score);
  
  public Move(Square x, Square y){
    start = x;
    landing = y;
  }

  public Move(Square x, Square y, int scr){
    start = x;
    landing = y;
    score = scr;
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
    return score;
  }
}
