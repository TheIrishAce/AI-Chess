class Move{
  Square start;
  Square landing;

  // Add start, tmp and score.
  // validMove = new Move (start, tmp, score);
  
  public Move(Square x, Square y){
    start = x;
    landing = y;
  }

  public Move(){
    
  }

  public Square getStart(){
    return start;
  }

  public Square getLanding(){
    return landing;
  }
}
