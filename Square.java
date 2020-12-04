class Square{
  public int xCoor;
  public int yCoor;
  public String pieceName;
  public int pieceScore;

  public Square(int x, int y, String name, int score){
    xCoor = x;
    yCoor = y;
    pieceName = name;
    pieceScore = score;
  }

  public Square(int x, int y){
    xCoor = x;
    yCoor = y;
    pieceName = "";
  }

  public int getXC(){
    return xCoor;
  }

  public int getYC(){
    return yCoor;
  }

  public String getName(){
      return pieceName;
  }

  public int getPieceScore(){
    return pieceScore;
}
}
