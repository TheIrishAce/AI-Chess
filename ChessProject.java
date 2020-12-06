import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;

/*
This class can be used as a starting point for creating your Chess game project. The only piece that 
has been coded is a white pawn...a lot done, more to do!
*/

public class ChessProject extends JFrame implements MouseListener, MouseMotionListener {
	JLayeredPane layeredPane;
	JPanel chessBoard;
	JLabel chessPiece;
	int xAdjustment;
	int yAdjustment;
	int startX;
	int startY;
	int initialX;
	int initialY;

	String pieceName;
	JPanel panels;
	JLabel pieces;
	Boolean agentwins = false;
	Boolean white2Move = false;
	String rand = "rand";
	static int aiType; //Desired AI type 0 = Random, 1 = Next Best, 2 = 2x Deep.
	AIAgent agent = new AIAgent();
	int pieceScore =0;

	public ChessProject() {
		Dimension boardSize = new Dimension(600, 600);

		// Use a Layered Pane for this application
		layeredPane = new JLayeredPane();
		getContentPane().add(layeredPane);
		layeredPane.setPreferredSize(boardSize);
		layeredPane.addMouseListener(this);
		layeredPane.addMouseMotionListener(this);

		// Add a chess board to the Layered Pane
		chessBoard = new JPanel();
		layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
		chessBoard.setLayout(new GridLayout(8, 8));
		chessBoard.setPreferredSize(boardSize);
		chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

		for (int i = 0; i < 64; i++) {
			JPanel square = new JPanel(new BorderLayout());
			chessBoard.add(square);

			int row = (i / 8) % 2;
			if (row == 0)
				square.setBackground(i % 2 == 0 ? Color.white : Color.gray);
			else
				square.setBackground(i % 2 == 0 ? Color.gray : Color.white);
		}

		// Setting up the Initial Chess board.
		for (int i = 8; i < 16; i++) {
			pieces = new JLabel(new ImageIcon("WhitePawn.png"));
			panels = (JPanel) chessBoard.getComponent(i);
			panels.add(pieces);
		}
		pieces = new JLabel(new ImageIcon("WhiteRook.png"));
		panels = (JPanel) chessBoard.getComponent(0);
		panels.add(pieces);
		pieces = new JLabel(new ImageIcon("WhiteKnight.png"));
		panels = (JPanel) chessBoard.getComponent(1);
		panels.add(pieces);
		pieces = new JLabel(new ImageIcon("WhiteKnight.png"));
		panels = (JPanel) chessBoard.getComponent(6);
		panels.add(pieces);
		pieces = new JLabel(new ImageIcon("WhiteBishop.png"));
		panels = (JPanel) chessBoard.getComponent(2);
		panels.add(pieces);
		pieces = new JLabel(new ImageIcon("WhiteBishop.png"));
		panels = (JPanel) chessBoard.getComponent(5);
		panels.add(pieces);
		pieces = new JLabel(new ImageIcon("WhiteKing.png"));
		panels = (JPanel) chessBoard.getComponent(3);
		panels.add(pieces);
		pieces = new JLabel(new ImageIcon("WhiteQueen.png"));
		panels = (JPanel) chessBoard.getComponent(4);
		panels.add(pieces);
		pieces = new JLabel(new ImageIcon("WhiteRook.png"));
		panels = (JPanel) chessBoard.getComponent(7);
		panels.add(pieces);
		for (int i = 48; i < 56; i++) {
			pieces = new JLabel(new ImageIcon("BlackPawn.png"));
			panels = (JPanel) chessBoard.getComponent(i);
			panels.add(pieces);
		}
		pieces = new JLabel(new ImageIcon("BlackRook.png"));
		panels = (JPanel) chessBoard.getComponent(56);
		panels.add(pieces);
		pieces = new JLabel(new ImageIcon("BlackKnight.png"));
		panels = (JPanel) chessBoard.getComponent(57);
		panels.add(pieces);
		pieces = new JLabel(new ImageIcon("BlackKnight.png"));
		panels = (JPanel) chessBoard.getComponent(62);
		panels.add(pieces);
		pieces = new JLabel(new ImageIcon("BlackBishop.png"));
		panels = (JPanel) chessBoard.getComponent(58);
		panels.add(pieces);
		pieces = new JLabel(new ImageIcon("BlackBishop.png"));
		panels = (JPanel) chessBoard.getComponent(61);
		panels.add(pieces);
		pieces = new JLabel(new ImageIcon("BlackKing.png"));
		panels = (JPanel) chessBoard.getComponent(59);
		panels.add(pieces);
		pieces = new JLabel(new ImageIcon("BlackQueen.png"));
		panels = (JPanel) chessBoard.getComponent(60);
		panels.add(pieces);
		pieces = new JLabel(new ImageIcon("BlackRook.png"));
		panels = (JPanel) chessBoard.getComponent(63);
		panels.add(pieces);
	}

	/*
	 * This method checks if there is a piece present on a particular square.
	 */
	public Boolean piecePresent(int x, int y) {
		Component c = chessBoard.findComponentAt(x, y);
		if (c instanceof JPanel) {
			return false;
		} else {
			return true;
		}
	}

	// public String findPiece(int x, int y){
    //     x=x*75;
    //     y=y*75;
    //     if(chessBoard.findComponentAt(x, y) instanceof JLabel){
    //         Component c1 = chessBoard.findComponentAt(x, y);
    //         JLabel awaitingPiece = (JLabel) c1;
    //         return awaitingPiece.getIcon().toString();
    //     }else{
    //         return "";
    //     }
	// }

	public int findPiece(int x, int y){
        x=x*75;
		y=y*75;
		int pieceWeighting;
        if(chessBoard.findComponentAt(x, y) instanceof JLabel){
			Component c1 = chessBoard.findComponentAt(x, y);
			JLabel awaitingPiece = (JLabel) c1;
			String name = awaitingPiece.getIcon().toString();
			if(name.contains("BlackKing")){
				//System.out.println("Black King found with a value of 100");
				return pieceWeighting = 100;
			}
			else if(name.contains("BlackQueen")){
				//System.out.println("Black Queen found with a value of 9");
				return pieceWeighting = 9;
			}
			else if(name.contains("BlackRook")){
				//System.out.println("Black Rook found with a value of 5");
				return pieceWeighting = 5;
			}
			else if(name.contains("BlackBishop")){
				//System.out.println("Black Bishop found with a value of 3");
				return pieceWeighting = 3;
			}
			else if(name.contains("BlackKnight")){
				//System.out.println("Black Knight found with a value of 3");
				return pieceWeighting = 3;
			}
			else if(name.contains("BlackPawn")){
				//System.out.println("Black Pawn found with a value of 2");
				return pieceWeighting = 2;
			}
			else if(name.isEmpty()){
				//System.out.println("No Piece Found | No Points");
				return pieceWeighting = 1;
			}
		}
		return pieceWeighting = 0;
	}

	/*
	 * This is a method to check if a piece is a Black piece.
	 */
	public Boolean checkWhiteOponent(int landingX, int landingY) {
		Boolean oponent;
		//pieceScore = 0;
		Component c1 = chessBoard.findComponentAt(landingX, landingY);
		JLabel awaitingPiece = (JLabel) c1;
		String tmp1 = awaitingPiece.getIcon().toString();
		if (((tmp1.contains("Black")))) {
			oponent = true;
		}
		else {
			oponent = false;
		}
		return oponent;
	}

	/*
	 * This is a method to check if a piece is a White piece.
	 */
	public Boolean checkBlackOponent(int landingX, int landingY) {
		Boolean oponent;
		Component c1 = chessBoard.findComponentAt(landingX, landingY);
		JLabel awaitingPiece = (JLabel) c1;
		String tmp1 = awaitingPiece.getIcon().toString();
		if (((tmp1.contains("White")))) {
			oponent = true;
		} else {
			oponent = false;
		}
		return oponent;
	}

	/*
	 * This method is called when we press the Mouse. So we need to find out what
	 * piece we have selected. We may also not have selected a piece!
	 */
	public void mousePressed(MouseEvent e) {
		chessPiece = null;
		Component c = chessBoard.findComponentAt(e.getX(), e.getY());
		if (c instanceof JPanel)
			return;

		Point parentLocation = c.getParent().getLocation();
		xAdjustment = parentLocation.x - e.getX();
		yAdjustment = parentLocation.y - e.getY();
		chessPiece = (JLabel) c;
		initialX = e.getX();
		initialY = e.getY();
		startX = (e.getX() / 75); // get the starting X tile, current X tile.
		startY = (e.getY() / 75); // get the starting Y tile, current Y tile.
		chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
		chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
		layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
	}

	public void mouseDragged(MouseEvent me) {
		if (chessPiece == null)
			return;
		chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
	}
	/*
	 * This method is used when the Mouse is released...we need to make sure the
	 * move was valid before putting the piece back on the board.
	 */

	public void mouseReleased(MouseEvent e) {
		if (chessPiece == null)
			return;

		chessPiece.setVisible(false);
		Boolean success = false;
		Component c = chessBoard.findComponentAt(e.getX(), e.getY());
		String tmp = chessPiece.getIcon().toString();
		pieceName = tmp.substring(0, (tmp.length() - 4));
		Boolean validMove = false;

		// Debug code used for seeing piece information and movement information.
		int landingX = (e.getX() / 75);
		int landingY = (e.getY() / 75);
		int xMovement = Math.abs((e.getX() / 75) - startX);
		int yMovement = Math.abs((e.getY() / 75) - startY);
		System.out.println("------------------------");
		System.out.println("The piece that is being moved is: " + pieceName);
		System.out.println("The starting coordinates are: " + " ( " + startX + "," + startY + ")");
		System.out.println("The xMovement is: " + xMovement);
		System.out.println("The yMovement is: " + yMovement);
		System.out.println("The landing coordinates are: " + "( " + landingX + "," + landingY + ")");
		System.out.println("Piece " + getPieceName(startX, startY) +  " currently moveing to : " + "( " + landingX + "," + landingY + ")" + "to get piece" + getPieceName(landingX, landingY) + "with a score weighting of = " + pieceScore);

		/*
		 * The only piece that has been enabled to move is a White Pawn...but we should
		 * really have this as a separate method somewhere...how would this work.
		 * 
		 * So a Pawn is able to move two squares forward on its first go but only one
		 * square after that. The Pawn is the only piece that cannot move backwards in
		 * chess...so be careful when committing a pawn forward. A Pawn is able to take
		 * any of the opponent’s pieces but they have to be one square forward and one
		 * square over, i.e. in a diagonal direction from the Pawns original position.
		 * If a Pawn makes it to the top of the other side, the Pawn can turn into any
		 * other piece, for demonstration purposes the Pawn here turns into a Queen.
		 */
		if (pieceName.equals("BlackPawn")) {
			if (white2Move == false) {
				if (startY == 6) { // Black pawn makes its first move from here.
					if ((yMovement == 1 || yMovement == 2) && (startY > landingY) && (xMovement == 0)) {
						if (yMovement == 2) {
							if (!piecePresent(e.getX(), (e.getY())) && (!piecePresent(e.getX(), e.getY() + 75))) {
								validMove = true;
								white2Move = true;
							}
							// if((piecePresent(e.getX(),
							// (e.getY())))&&((((landingX==(startX+1)&&(startX+1<=7)&&(landingY==(startY+1)&&(startY+1<=7)))))||(((landingX==(startX-1)&&(startX-1>=0)&&(landingY==(startY+1)&&(startY+1>=0)))))))
							// //Added and made use of the the landingY variable
							// //||(landingY==(startY+1)&&(startY+1>=0))))
							else if (piecePresent(e.getX(), e.getY())) {
								if (checkBlackOponent(e.getX(), e.getY())) {
									validMove = true;
									white2Move = true;
									if (startY == 1) {
										success = true;
									}
								}
							} else {
								validMove = false;
							}
						} else {
							if (!piecePresent(e.getX(), e.getY())) {
								validMove = true;
								white2Move = true;
								if (startY == 1) {
									success = true;
								}
							} else if (piecePresent(e.getX(), e.getY())) {
								if (checkBlackOponent(e.getX(), e.getY())) {
									validMove = true;
									white2Move = true;
									if (startY == 1) {
										success = true;
									}
								}
							} else {
								validMove = false;
							}
						}
					} else if ((yMovement == 1) && (startY > landingY) && (xMovement == 1)) {
						if (piecePresent(e.getX(), e.getY())) {
							if (checkBlackOponent(e.getX(), e.getY())) {
								validMove = true;
								white2Move = true;
								if (startY == 1) {
									success = true;
								}
							}
						}
					}
				} else { // This is where the pawn makes all moves after its inital start one.
					if ((yMovement == 1) && (startY > landingY) && (xMovement == 0)) {
						if (!piecePresent(e.getX(), e.getY())) {
							validMove = true;
							white2Move = true;
							if (startY == 1) {
								success = true;
							}
						} else {
							validMove = false;
						}
					} else if ((yMovement == 1) && (startY > landingY) && (xMovement == 1)) {
						if (piecePresent(e.getX(), e.getY())) {
							if (checkBlackOponent(e.getX(), e.getY())) {
								validMove = true;
								white2Move = true;
								if (startY == 1) {
									success = true;
								}
							}
						}
					}
				}
			}
		}
		// Potentially redo white pawn to use variables like black pawn instead of the
		// raw maths
		else if (pieceName.equals("WhitePawn")) {
			if (white2Move) {
				if (startY == 1) // Game start pawn rules
				{
					if ((startX == (e.getX() / 75))
							&& ((((e.getY() / 75) - startY) == 1) || ((e.getY() / 75) - startY) == 2)) {
						if ((((e.getY() / 75) - startY) == 2)) { // if statement fires if pawn moves by 2 spaces
							if ((!piecePresent(e.getX(), (e.getY()))) && (!piecePresent(e.getX(), (e.getY() - 75)))) {
								validMove = true;
								white2Move = false;
							} else {
								validMove = false;
							}
						} else { // else statement fires if pawn moves by any other spaces (1)
							if ((!piecePresent(e.getX(), (e.getY())))) {
								validMove = true;
								white2Move = false;
							} else {
								validMove = false;
							}
						}
					} else {
						validMove = false;
					}
				} else { // Every turn after the start pawn rules.
					if ((startX - 1 >= 0) || (startX + 1 <= 7)) {
						if ((piecePresent(e.getX(), (e.getY()))) && ((((landingX == (startX + 1) && (startX + 1 <= 7)
								&& (landingY == (startY + 1) && (startY + 1 <= 7)))))
								|| (((landingX == (startX - 1) && (startX - 1 >= 0)
										&& (landingY == (startY + 1) && (startY + 1 >= 0))))))) // Added and made use of
																								// the the landingY
																								// variable
																								// //||(landingY==(startY+1)&&(startY+1>=0))))
						{
							if (checkWhiteOponent(e.getX(), e.getY())) {
								validMove = true;
								white2Move = false;
								if (startY == 6) {
									success = true;
								}
							} else {
								validMove = false;
							}
						} else {
							if (!piecePresent(e.getX(), (e.getY()))) {
								if ((startX == (e.getX() / 75)) && ((e.getY() / 75) - startY) == 1) {
									if (startY == 6) {
										success = true;
									}
									validMove = true;
									white2Move = false;
								} else {
									validMove = false;
								}
							} else {
								validMove = false;
							}
						}
					} else {
						validMove = false;
					}
				}
			}
		}

		else if (pieceName.contains("Knight")) {
			if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
				validMove = false;
			} else {
				if ((((landingX == startX + 1) && (landingY == startY + 2))
						|| ((landingX == startX - 1) && (landingY == startY + 2))
						|| ((landingX == startX + 2) && (landingY == startY + 1))
						|| ((landingX == startX - 2) && (landingY == startY + 1))
						|| ((landingX == startX - 1) && (landingY == startY - 2))
						|| ((landingX == startX + 1) && (landingY == startY - 2))
						|| ((landingX == startX + 2) && (landingY == startY - 1))
						|| ((landingX == startX - 2) && (landingY == startY - 1))) && (landingY >= 0 && landingY <= 7)
						&& (landingX >= 0 && landingX <= 7)) {

					if (piecePresent(e.getX(), e.getY())) {
						// if(white2Move == true){
						if (pieceName.contains("White") && white2Move == true) {
							if (checkWhiteOponent(e.getX(), (e.getY()))) {
								validMove = true;
								white2Move = false;
								if (startY == 6) {
									success = true;
								}
							} else {
								validMove = false;
							}
						}
						// }
						else {
							if (pieceName.contains("Black") && white2Move == false) {
								if (checkBlackOponent(e.getX(), (e.getY()))) {
									validMove = true;
									white2Move = true;
									if (startY == 6) {
										success = true;
									}
								} else {
									validMove = false;
								}
							}
						}
					}

					if (!piecePresent(e.getX(), e.getY())) {
						// if(white2Move == true){
						if (pieceName.contains("White") && white2Move == true) {
							white2Move = false;
							validMove = true;
							if (startY == 6) {
								success = true;
							}
						}
						// }
						if (pieceName.contains("Black") && white2Move == false) {
							white2Move = true;
							validMove = true;
							if (startY == 6) {
								success = true;
							}
						}
					}
				} else {
					validMove = false;
				}
			}
		}

		else if (pieceName.contains("Bishop")) {
			Boolean inTheWay = false;
			int distance = Math.abs(startX - landingX);
			if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
				validMove = false;
			} else {
				validMove = bishopMovement(e, landingX, landingY, xMovement, yMovement, distance, inTheWay, validMove,
						pieceName);
			}
		}

		else if (pieceName.contains("Rook")) {
			Boolean inTheWay = false;
			if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
				validMove = false;
			} else {
				if (((Math.abs(startX - landingX) != 0) && (Math.abs(startY - landingY) == 0))
						|| ((Math.abs(startX - landingX) == 0) && (Math.abs(landingY - startY) != 0))) {
					if (Math.abs(startX - landingX) != 0) {
						xMovement = Math.abs(startX - landingX);
						if (startX - landingX > 0) {
							for (int i = 0; i < xMovement; i++) {
								if (piecePresent(initialX - (i * 75), e.getY())) {
									inTheWay = true;
									break;
								} else {
									inTheWay = false;
								}
							}
						} else {
							for (int i = 0; i < xMovement; i++) {
								if (piecePresent(initialX + (i * 75), e.getY())) {
									inTheWay = true;
									break;
								} else {
									inTheWay = false;
								}
							}
						}
					} else {
						yMovement = Math.abs(startY - landingY);
						if (startY - landingY > 0) {
							for (int i = 0; i < yMovement; i++) {
								if (piecePresent(e.getX(), initialY - (i * 75))) {
									inTheWay = true;
									break;
								} else {
									inTheWay = false;
								}
							}
						} else {
							for (int i = 0; i < yMovement; i++) {
								if (piecePresent(e.getX(), initialY + (i * 75))) {
									inTheWay = true;
									break;
								} else {
									inTheWay = false;
								}
							}
						}
					}

					if (inTheWay) {
						validMove = false;
					} else {
						if (piecePresent(e.getX(), (e.getY()))) {
							if (pieceName.contains("White") && white2Move == true) {
								if (checkWhiteOponent(e.getX(), e.getY())) {
									validMove = true;
									white2Move = false;
								} else {
									validMove = false;
									white2Move = true;
								}
							} else {
								if (checkBlackOponent(e.getX(), e.getY())) {
									validMove = true;
								} else {
									validMove = false;
								}
							}
						} else {
							validMove = true;
						}
					}
				} else {
					validMove = false;
				}
			}
		}

		else if (pieceName.contains("Queen")) {
			Boolean inTheWay = false;
			int distance = Math.abs(startX - landingX);
			if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
				validMove = false;
			} else {
				if (((Math.abs(startX - landingX) != 0) && (Math.abs(startY - landingY) == 0))
						|| ((Math.abs(startX - landingX) == 0) && (Math.abs(landingY - startY) != 0))) {
					validMove = rookMovement(e, landingX, landingY, xMovement, yMovement, inTheWay, validMove,
							pieceName);
				} else {
					validMove = bishopMovement(e, landingX, landingY, xMovement, yMovement, distance, inTheWay,
							validMove, pieceName);
				}
			}
		}

		else if (pieceName.contains("King")) {
			Boolean inTheWay = false;
			int distance = Math.abs(startX - landingX);
			if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
				validMove = false;
			} else {
				if (((yMovement <= 1) || (yMovement <= -1)) && ((xMovement <= -1) || (xMovement <= 1))) {
					if ((landingX == startX)&&(landingY == startY)) {
						validMove = false;
					}
					else{
						if(!piecePresent(e.getX(), e.getY())){
							validMove = true;
						}
						else{
							if (pieceName.contains("White")) {
								if (checkWhiteOponent(e.getX(), e.getY())) {
									validMove = true;
								}
							} 
							else if (pieceName.contains("Black")) {
								if (checkBlackOponent(e.getX(), e.getY())) {
									validMove = true;
								}
							} 
							else {
								validMove = false;
							}
						}
					}
				}

				// else if((yMovement == 1)&&(startY > landingY)&&(xMovement == 1)){
				// if(piecePresent(e.getX(), e.getY())){
				// if(checkBlackOponent(e.getX(), e.getY())){
				// validMove = true;
				// }
				// }
				// }
			}
		}

		if (!validMove) {
			int location = 0;
			if (startY == 0) {
				location = startX;
			} else {
				location = (startY * 8) + startX;
			}
			String pieceLocation = pieceName + ".png";
			pieces = new JLabel(new ImageIcon(pieceLocation));
			panels = (JPanel) chessBoard.getComponent(location);
			panels.add(pieces);
		} else {
			if (success) {
				int whiteLocation = 56 + (e.getX() / 75);
				int blackLocation = (e.getX() / 75);

				if (c instanceof JLabel && pieceName.contains("Black")) {
					Container parent = c.getParent();
					parent.remove(0);
					pieces = new JLabel(new ImageIcon("BlackQueen.png"));
					parent = (JPanel) chessBoard.getComponent(blackLocation);
					parent.add(pieces);
				}
				// else{

				// Container parent = (Container)c;
				// pieces = new JLabel( new ImageIcon("BlackQueen.png") );
				// parent = (JPanel)chessBoard.getComponent(blackLocation);
				// parent.add(pieces);
				// }
				if (c instanceof JLabel && pieceName.contains("White")) {
					Container parent = c.getParent();
					parent.remove(0);
					pieces = new JLabel(new ImageIcon("WhiteQueen.png"));
					parent = (JPanel) chessBoard.getComponent(whiteLocation);
					parent.add(pieces);
				}
				// else{

				// Container parent = (Container)c;
				// pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
				// parent = (JPanel)chessBoard.getComponent(whiteLocation);
				// parent.add(pieces);
				// }
			} else {
				if (c instanceof JLabel) {
					Container parent = c.getParent();
					parent.remove(0);
					parent.add(chessPiece);
				} else {
					Container parent = (Container) c;
					parent.add(chessPiece);
				}
				chessPiece.setVisible(true);
			}
		}
		if(validMove){
			makeAIMove();
		}
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseMoved(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public boolean rookMovement(MouseEvent e, int landingX, int landingY, int xMovement, int yMovement,
			boolean inTheWay, boolean validMove, String pieceName) {
		if (((Math.abs(startX - landingX) != 0) && (Math.abs(startY - landingY) == 0))
				|| ((Math.abs(startX - landingX) == 0) && (Math.abs(landingY - startY) != 0))) {
			if (Math.abs(startX - landingX) != 0) {
				xMovement = Math.abs(startX - landingX);
				if (startX - landingX > 0) {
					for (int i = 0; i < xMovement; i++) {
						if (piecePresent(initialX - (i * 75), e.getY())) {
							inTheWay = true;
							break;
						} else {
							inTheWay = false;
						}
					}
				} else {
					for (int i = 0; i < xMovement; i++) {
						if (piecePresent(initialX + (i * 75), e.getY())) {
							inTheWay = true;
							break;
						} else {
							inTheWay = false;
						}
					}
				}
			} else {
				yMovement = Math.abs(startY - landingY);
				if (startY - landingY > 0) {
					for (int i = 0; i < yMovement; i++) {
						if (piecePresent(e.getX(), initialY - (i * 75))) {
							inTheWay = true;
							break;
						} else {
							inTheWay = false;
						}
					}
				} else {
					for (int i = 0; i < yMovement; i++) {
						if (piecePresent(e.getX(), initialY + (i * 75))) {
							inTheWay = true;
							break;
						} else {
							inTheWay = false;
						}
					}
				}
			}

			if (inTheWay) {
				return validMove = false;
			} else {
				if (piecePresent(e.getX(), (e.getY()))) {
					if (pieceName.contains("White") && white2Move == true) {
						if (checkWhiteOponent(e.getX(), e.getY())) {
							white2Move = false;
							return validMove = true;
						} else {
							white2Move = true;
							return validMove = false;
						}
					} else {
						if (checkBlackOponent(e.getX(), e.getY())) {
							return validMove = true;
						} else {
							return validMove = false;
						}
					}
				} else {
					if((landingX == startX)&&(landingY == startY)){
						return validMove = false;
					}
					else {
						return validMove = true;
					}
				}
			}
		} else {
			return validMove = false;
		}
	}

	public boolean bishopMovement(MouseEvent e, int landingX, int landingY, int xMovement, int yMovement, int distance,
			boolean inTheWay, boolean validMove, String pieceName) {
		// validMove = true;
		if (Math.abs(startX - landingX) == Math.abs(startY - landingY)) {
			if ((startX - landingX < 0) && (startY - landingY < 0)) {
				for (int i = 0; i < distance; i++) {
					if (piecePresent((initialX + (i * 75)), (initialY + (i * 75)))) {
						inTheWay = true;
					}
				}
			} else if ((startX - landingX < 0) && (startY - landingY > 0)) {
				for (int i = 0; i < distance; i++) {
					if (piecePresent((initialX + (i * 75)), (initialY - (i * 75)))) {
						inTheWay = true;
					}
				}
			} else if ((startX - landingX > 0) && (startY - landingY > 0)) {
				for (int i = 0; i < distance; i++) {
					if (piecePresent((initialX - (i * 75)), (initialY - (i * 75)))) {
						inTheWay = true;
					}
				}
			} else if ((startX - landingX > 0) && (startY - landingY < 0)) {
				for (int i = 0; i < distance; i++) {
					if (piecePresent((initialX - (i * 75)), (initialY + (i * 75)))) {
						inTheWay = true;
					}
				}
			}
			if (inTheWay) {
				return validMove = false;
			} else {
				if (piecePresent(e.getX(), (e.getY()))) {
					if (pieceName.contains("White") && white2Move == true) {
						if (checkWhiteOponent(e.getX(), e.getY())) {
							white2Move = false;
							return validMove = true;
						} else {
							white2Move = true;
							return validMove = false;
						}
					} else {
						if (checkBlackOponent(e.getX(), e.getY())) {
							white2Move = true;
							return validMove = true;
						} else {
							white2Move = false;
							return validMove = false;
						}
					}
				} else {
					if((landingX == startX)&&(landingY == startY)){
						return validMove = false;
					}
					else {
						return validMove = true;
					}
				}
			}
		} else {
			return validMove = false;
		}
	}

	// Invalid Call From Here
	public String getPieceName(int x, int y) {
		return pieceName;
	}

	private Boolean checkSurroundingSquares(Square s) {
		Boolean possible = false;
		int x = s.getXC() * 75;
		int y = s.getYC() * 75;
		if (!((getPieceName((x + 75), y).contains("BlackKing")) || (getPieceName((x - 75), y).contains("BlackKing"))
				|| (getPieceName(x, (y + 75)).contains("BlackKing"))
				|| (getPieceName((x), (y - 75)).contains("BlackKing"))
				|| (getPieceName((x + 75), (y + 75)).contains("BlackKing"))
				|| (getPieceName((x - 75), (y + 75)).contains("BlackKing"))
				|| (getPieceName((x + 75), (y - 75)).contains("BlackKing"))
				|| (getPieceName((x - 75), (y - 75)).contains("BlackKing")))) {
			possible = true;
		}
		return possible;
	}

	public int getPieceScore(int x, int y, String name){
		if(piecePresent(x, y)) {
			pieceScore = 0;
			if(name.contains("BlackPawn")){
				//oponent = true;
				pieceScore = 2;
				System.out.println("Black Pawn found with a value of " + pieceScore);
			}
			if(name.contains("BlackKnight")){
				//oponent = true;
				if(piecePresent(x,y)){
					pieceScore = 3;
				}
				System.out.println("Black Knight found with a value of " + pieceScore);
			}
			if(name.contains("BlackBishop")){
				//oponent = true;
				if(piecePresent(x,y)){
					pieceScore = 3;
				}
				System.out.println("Black Bishop found with a value of " + pieceScore);
			}
			if(name.contains("BlackRook")){
				//oponent = true;
				if(piecePresent(x,y)){
					pieceScore = 5;
				}
				System.out.println("Black Rook found with a value of " + pieceScore);
			}
			if(name.contains("BlackQueen")){
				//oponent = true;
				if(piecePresent(x,y)){
					pieceScore = 9;
				}
				System.out.println("Black Queen found with a value of " + pieceScore);
			}
			if(name.contains("BlackKing")){
				//oponent = true;
				if(piecePresent(x,y)){
					pieceScore = 100;
				}
				System.out.println("Black King found with a value of " + pieceScore);
			}
			if(name.isEmpty()){
				pieceScore = 1;
				System.out.println("No Piece Found | No Points");
			}
			// else {
			// 	pieceScore = 0;
			// }
		}
		else {
			return 0;
		}
		
		return pieceScore;
	}

	// AI element of the application.
	private Stack getWhitePawnSquares(int x, int y, String piece) {
		Stack moves = new Stack();
        Square startingSquare = new Square(x, y, piece);
        Move validM;
        int tmpx1 = x + 1;
        int tmpx2 = x - 1;
        int tmpy1 = y + 1;
        int tmpy2 = y + 2;
        if (y == 1) {
            if (!piecePresent((x * 75) + 20, (tmpy1 * 75) + 20)) {
                Square tmp = new Square(x, tmpy1);
                validM = new Move(startingSquare, tmp, findPiece(x, tmpy1));
                moves.push(validM);
            }
            if (!piecePresent((x * 75) + 20, (tmpy1 * 75) + 20) && !piecePresent((x * 75) + 20, (tmpy2 * 75) + 20)) {
                Square tmp = new Square(x, tmpy2);
                validM = new Move(startingSquare, tmp, findPiece(x, tmpy2));
                moves.push(validM);
            }
            if (piecePresent((tmpx1 * 75) + 20, (tmpy1 * 75) + 20) && tmpx1>=0 && tmpx1<=7 && tmpy1>=0 && tmpy1<=7) {
                if (checkWhiteOponent((tmpx1 * 75) + 20, (tmpy1 * 75) + 20)) {
                    Square tmp = new Square(tmpx1, tmpy1);
                    validM = new Move(startingSquare, tmp, findPiece(tmpx1, tmpy1));
                    moves.push(validM);
                }
            }
            if (piecePresent((tmpx2 * 75) + 20, (tmpy1 * 75) + 20) && tmpx2>=0 && tmpx2<=7 && tmpy1>=0 && tmpy1<=7) {
                if (checkWhiteOponent((tmpx2 * 75) + 20, (tmpy1 * 75) + 20)) {
                    Square tmp = new Square(tmpx2, tmpy1);
                    validM = new Move(startingSquare, tmp, findPiece(tmpx2, tmpy1));
                    moves.push(validM);
                }
            }
        } else {
            if (!piecePresent((x * 75) + 20, (tmpy1 * 75) + 20) && tmpy1>=0 && tmpy1<=7) {
                Square tmp = new Square(x, tmpy1);
                validM = new Move(startingSquare, tmp, findPiece(x, tmpy1));
                moves.push(validM);
            }
            if (piecePresent((tmpx1 * 75) + 20, (tmpy1 * 75) + 20) && tmpx1>=0 && tmpx1<=7 && tmpy1>=0 && tmpy1<=7) {
                if (checkWhiteOponent((tmpx1 * 75) + 20, (tmpy1 * 75) + 20)) {
                    Square tmp = new Square(tmpx1, tmpy1);
                    validM = new Move(startingSquare, tmp, findPiece(tmpx1, tmpy1));
                    moves.push(validM);
                }
            }
            if (piecePresent((tmpx2 * 75) + 20, (tmpy1 * 75) + 20) && tmpx2>=0 && tmpx2<=7 && tmpy1>=0 && tmpy1<=7) {
                if (checkWhiteOponent((tmpx2 * 75) + 20, (tmpy1 * 75) + 20)) {
                    Square tmp = new Square(tmpx2, tmpy1);
                    validM = new Move(startingSquare, tmp, findPiece(tmpx2, tmpy1));
                    moves.push(validM);
                }
            }
        }
        return moves;
	}

	private Stack getKingSquares(int x, int y, String piece) {
		Stack moves = new Stack();
		Square startingSquare = new Square(x, y, piece);
		Move validM, validM2, validM3, validM4;
		int tmpx1 = x + 1;
		int tmpx2 = x - 1;
		int tmpy1 = y + 1;
		int tmpy2 = y - 1;

		if (!((tmpx1 > 7))) {
			Square tmp = new Square(tmpx1, y);
			Square tmp1 = new Square(tmpx1, tmpy1);
			Square tmp2 = new Square(tmpx1, tmpy2);
			if (checkSurroundingSquares(tmp)) {
				validM = new Move(startingSquare, tmp, findPiece(tmpx1, y));	//Added getPieceScore Here | Used to send weighting of piece.
				if (!piecePresent(((tmp.getXC() * 75) + 20), (((tmp.getYC() * 75) + 20)))) {
					moves.push(validM);
				} else {
					if (checkWhiteOponent(((tmp.getXC() * 75) + 20), (((tmp.getYC() * 75) + 20)))) {
						moves.push(validM);
					}
				}
			}
			if (!(tmpy1 > 7)) {
				if (checkSurroundingSquares(tmp1)) {
					validM2 = new Move(startingSquare, tmp1, findPiece(tmpx1, tmpy1));		//Added getPieceScore Here | Used to send weighting of piece.
					if (!piecePresent(((tmp1.getXC() * 75) + 20), (((tmp1.getYC() * 75) + 20)))) {
						moves.push(validM2);
					} else {
						if (checkWhiteOponent(((tmp1.getXC() * 75) + 20), (((tmp1.getYC() * 75) + 20)))) {
							moves.push(validM2);
						}
					}
				}
			}
			if (!(tmpy2 < 0)) {
				if (checkSurroundingSquares(tmp2)) {
					validM3 = new Move(startingSquare, tmp2, findPiece(tmpx1, tmpy2));		//Added getPieceScore Here | Used to send weighting of piece.
					if (!piecePresent(((tmp2.getXC() * 75) + 20), (((tmp2.getYC() * 75) + 20)))) {
						moves.push(validM3);
					} else {
						System.out.println("The values that we are going to be looking at are : "
								+ ((tmp2.getXC() * 75) + 20) + " and the y value is : " + ((tmp2.getYC() * 75) + 20));
						if (checkWhiteOponent(((tmp2.getXC() * 75) + 20), (((tmp2.getYC() * 75) + 20)))) {
							moves.push(validM3);
						}
					}
				}
			}
		}
		if (!((tmpx2 < 0))) {
			Square tmp3 = new Square(tmpx2, y);
			Square tmp4 = new Square(tmpx2, tmpy1);
			Square tmp5 = new Square(tmpx2, tmpy2);
			if (checkSurroundingSquares(tmp3)) {
				validM = new Move(startingSquare, tmp3, findPiece(tmpx2, y));		//Added getPieceScore Here | Used to send weighting of piece.
				if (!piecePresent(((tmp3.getXC() * 75) + 20), (((tmp3.getYC() * 75) + 20)))) {
					moves.push(validM);
				} else {
					if (checkWhiteOponent(((tmp3.getXC() * 75) + 20), (((tmp3.getYC() * 75) + 20)))) {
						moves.push(validM);
					}
				}
			}
			if (!(tmpy1 > 7)) {
				if (checkSurroundingSquares(tmp4)) {
					validM2 = new Move(startingSquare, tmp4, findPiece(tmpx2, tmpy1));		//Added getPieceScore Here | Used to send weighting of piece.
					if (!piecePresent(((tmp4.getXC() * 75) + 20), (((tmp4.getYC() * 75) + 20)))) {
						moves.push(validM2);
					} else {
						if (checkWhiteOponent(((tmp4.getXC() * 75) + 20), (((tmp4.getYC() * 75) + 20)))) {
							moves.push(validM2);
						}
					}
				}
			}
			if (!(tmpy2 < 0)) {
				if (checkSurroundingSquares(tmp5)) {
					validM3 = new Move(startingSquare, tmp5, findPiece(tmpx2, tmpy2));		//Added getPieceScore Here | Used to send weighting of piece.
					if (!piecePresent(((tmp5.getXC() * 75) + 20), (((tmp5.getYC() * 75) + 20)))) {
						moves.push(validM3);
					} else {
						if (checkWhiteOponent(((tmp5.getXC() * 75) + 20), (((tmp5.getYC() * 75) + 20)))) {
							moves.push(validM3);
						}
					}
				}
			}
		}
		Square tmp7 = new Square(x, tmpy1);
		Square tmp8 = new Square(x, tmpy2);
		if (!(tmpy1 > 7)) {
			if (checkSurroundingSquares(tmp7)) {
				validM2 = new Move(startingSquare, tmp7, findPiece(x, tmpy1));		//Added getPieceScore Here | Used to send weighting of piece.
				if (!piecePresent(((tmp7.getXC() * 75) + 20), (((tmp7.getYC() * 75) + 20)))) {
					moves.push(validM2);
				} else {
					if (checkWhiteOponent(((tmp7.getXC() * 75) + 20), (((tmp7.getYC() * 75) + 20)))) {
						moves.push(validM2);
					}
				}
			}
		}
		if (!(tmpy2 < 0)) {
			if (checkSurroundingSquares(tmp8)) {
				validM3 = new Move(startingSquare, tmp8, findPiece(x, tmpy2));		//Added getPieceScore Here | Used to send weighting of piece.
				if (!piecePresent(((tmp8.getXC() * 75) + 20), (((tmp8.getYC() * 75) + 20)))) {
					moves.push(validM3);
				} else {
					if (checkWhiteOponent(((tmp8.getXC() * 75) + 20), (((tmp8.getYC() * 75) + 20)))) {
						moves.push(validM3);
					}
				}
			}
		}
		return moves;
	} // end of the method getKingSquares()

	/*
	 * Method to return all the possible moves that a Queen can make
	 */
	private Stack getQueenMoves(int x, int y, String piece) {
		Stack completeMoves = new Stack();
		Stack tmpMoves = new Stack();
		Move tmp;
		/*
		 * The Queen is a pretty easy piece to figure out if you have completed the
		 * Bishop and the Rook movements. Either the Queen is going to move like a
		 * Bishop or its going to move like a Rook, so all we have to do is make a call
		 * to both of these methods.
		 */
		tmpMoves = getRookMoves(x, y, piece);
		while (!tmpMoves.empty()) {
			tmp = (Move) tmpMoves.pop();
			completeMoves.push(tmp);
		}
		tmpMoves = getBishopMoves(x, y, piece);
		while (!tmpMoves.empty()) {
			tmp = (Move) tmpMoves.pop();
			completeMoves.push(tmp);
		}
		return completeMoves;
	}

	private Stack getRookMoves(int x, int y, String piece) {
		Square startingSquare = new Square(x, y, piece);
		Stack moves = new Stack();
		Move validM, validM2, validM3, validM4;

		for (int i = 1; i < 8; i++) {
			int tmpx = x + i;
			int tmpy = y;
			if (!(tmpx > 7 || tmpx < 0)) {
				Square tmp = new Square(tmpx, tmpy);
				validM = new Move(startingSquare, tmp, findPiece(tmpx, tmpy));	//Added getPieceScore Here | Used to send weighting of piece.
				if (!piecePresent(((tmp.getXC() * 75) + 20), (((tmp.getYC() * 75) + 20)))) {
					moves.push(validM);
				} else {
					if (checkWhiteOponent(((tmp.getXC() * 75) + 20), ((tmp.getYC() * 75) + 20))) {
						moves.push(validM);
						break;
					} else {
						break;
					}
				}
			}
		} // end of the loop with x increasing and Y doing nothing...
		for (int j = 1; j < 8; j++) {
			int tmpx1 = x - j;
			int tmpy1 = y;
			if (!(tmpx1 > 7 || tmpx1 < 0)) {
				Square tmp2 = new Square(tmpx1, tmpy1);
				validM2 = new Move(startingSquare, tmp2, findPiece(tmpx1, tmpy1));		//Added getPieceScore Here | Used to send weighting of piece.
				if (!piecePresent(((tmp2.getXC() * 75) + 20), (((tmp2.getYC() * 75) + 20)))) {
					moves.push(validM2);
				} else {
					if (checkWhiteOponent(((tmp2.getXC() * 75) + 20), ((tmp2.getYC() * 75) + 20))) {
						moves.push(validM2);
						break;
					} else {
						break;
					}
				}
			}
		} // end of the loop with x increasing and Y doing nothing...
		for (int k = 1; k < 8; k++) {
			int tmpx3 = x;
			int tmpy3 = y + k;
			if (!(tmpy3 > 7 || tmpy3 < 0)) {
				Square tmp3 = new Square(tmpx3, tmpy3);
				validM3 = new Move(startingSquare, tmp3, findPiece(tmpx3, tmpy3));		//Added getPieceScore Here | Used to send weighting of piece.
				if (!piecePresent(((tmp3.getXC() * 75) + 20), (((tmp3.getYC() * 75) + 20)))) {
					moves.push(validM3);
				} else {
					if (checkWhiteOponent(((tmp3.getXC() * 75) + 20), ((tmp3.getYC() * 75) + 20))) {
						moves.push(validM3);
						break;
					} else {
						break;
					}
				}
			}
		} // end of the loop with x increasing and Y doing nothing...
		for (int l = 1; l < 8; l++) {
			int tmpx4 = x;
			int tmpy4 = y - l;
			if (!(tmpy4 > 7 || tmpy4 < 0)) {
				Square tmp4 = new Square(tmpx4, tmpy4);
				validM4 = new Move(startingSquare, tmp4, findPiece(tmpx4, tmpy4));		//Added getPieceScore Here | Used to send weighting of piece.
				if (!piecePresent(((tmp4.getXC() * 75) + 20), (((tmp4.getYC() * 75) + 20)))) {
					moves.push(validM4);
				} else {
					if (checkWhiteOponent(((tmp4.getXC() * 75) + 20), ((tmp4.getYC() * 75) + 20))) {
						moves.push(validM4);
						break;
					} else {
						break;
					}
				}
			}
		} // end of the loop with x increasing and Y doing nothing...
		return moves;
	}// end of get Rook Moves.

	private Stack getBishopMoves(int x, int y, String piece) {
		Square startingSquare = new Square(x, y, piece);
		Stack moves = new Stack();
		Move validM, validM2, validM3, validM4;
		/*
		 * The Bishop can move along any diagonal until it hits an enemy piece or its
		 * own piece it cannot jump over its own piece. We need to use four different
		 * loops to go through the possible movements to identify possible squares to
		 * move to. The temporary squares, i.e. the values of x and y must change by the
		 * same amount on each iteration of each of the loops.
		 * 
		 * If the new values of x and y are on the board, we create a new square and a
		 * new move (from the original square to the new square). We then check if there
		 * is a piece present on the new square: - if not we add the move as a possible
		 * new move - if there is a piece we make sure that we can capture our opponents
		 * piece and we cannot take our own piece and then we break out of the loop
		 * 
		 * This process is repeated for each of the other three possible diagonals that
		 * the Bishop can travel along.
		 * 
		 */
		for (int i = 1; i < 8; i++) {
			int tmpx = x + i;
			int tmpy = y + i;
			if (!(tmpx > 7 || tmpx < 0 || tmpy > 7 || tmpy < 0)) {
				Square tmp = new Square(tmpx, tmpy);
				validM = new Move(startingSquare, tmp, findPiece(tmpx, tmpy));	//Added getPieceScore Here | Used to send weighting of piece.
				if (!piecePresent(((tmp.getXC() * 75) + 20), (((tmp.getYC() * 75) + 20)))) {
					moves.push(validM);
				} else {
					if (checkWhiteOponent(((tmp.getXC() * 75) + 20), ((tmp.getYC() * 75) + 20))) {
						moves.push(validM);
						break;
					} else {
						break;
					}
				}
			}
		} // end of the first for Loop
		for (int k = 1; k < 8; k++) {
			int tmpk = x + k;
			int tmpy2 = y - k;
			if (!(tmpk > 7 || tmpk < 0 || tmpy2 > 7 || tmpy2 < 0)) {
				Square tmpK1 = new Square(tmpk, tmpy2);
				validM2 = new Move(startingSquare, tmpK1, findPiece(tmpk, tmpy2));	//Added getPieceScore Here | Used to send weighting of piece.
				if (!piecePresent(((tmpK1.getXC() * 75) + 20), (((tmpK1.getYC() * 75) + 20)))) {
					moves.push(validM2);
				} else {
					if (checkWhiteOponent(((tmpK1.getXC() * 75) + 20), ((tmpK1.getYC() * 75) + 20))) {
						moves.push(validM2);
						break;
					} else {
						break;
					}
				}
			}
		} // end of second loop.
		for (int l = 1; l < 8; l++) {
			int tmpL2 = x - l;
			int tmpy3 = y + l;
			if (!(tmpL2 > 7 || tmpL2 < 0 || tmpy3 > 7 || tmpy3 < 0)) {
				Square tmpLMov2 = new Square(tmpL2, tmpy3);
				validM3 = new Move(startingSquare, tmpLMov2, findPiece(tmpL2, tmpy3));	//Added getPieceScore Here | Used to send weighting of piece.
				if (!piecePresent(((tmpLMov2.getXC() * 75) + 20), (((tmpLMov2.getYC() * 75) + 20)))) {
					moves.push(validM3);
				} else {
					if (checkWhiteOponent(((tmpLMov2.getXC() * 75) + 20), ((tmpLMov2.getYC() * 75) + 20))) {
						moves.push(validM3);
						break;
					} else {
						break;
					}
				}
			}
		} // end of the third loop
		for (int n = 1; n < 8; n++) {
			int tmpN2 = x - n;
			int tmpy4 = y - n;
			if (!(tmpN2 > 7 || tmpN2 < 0 || tmpy4 > 7 || tmpy4 < 0)) {
				Square tmpNmov2 = new Square(tmpN2, tmpy4);
				validM4 = new Move(startingSquare, tmpNmov2, findPiece(tmpN2, tmpy4));	//Added getPieceScore Here | Used to send weighting of piece.
				if (!piecePresent(((tmpNmov2.getXC() * 75) + 20), (((tmpNmov2.getYC() * 75) + 20)))) {
					moves.push(validM4);
				} else {
					if (checkWhiteOponent(((tmpNmov2.getXC() * 75) + 20), ((tmpNmov2.getYC() * 75) + 20))) {
						moves.push(validM4);
						break;
					} else {
						break;
					}
				}
			}
		} // end of the last loop
		return moves;
	}

	private Stack getKnightMoves(int x, int y, String piece) {
		Square startingSquare = new Square(x, y, piece);
		Stack moves = new Stack();
		Stack attackingMove = new Stack();
		Square s = new Square(x + 1, y + 2);
		moves.push(s);
		Square s1 = new Square(x + 1, y - 2);
		moves.push(s1);
		Square s2 = new Square(x - 1, y + 2);
		moves.push(s2);
		Square s3 = new Square(x - 1, y - 2);
		moves.push(s3);
		Square s4 = new Square(x + 2, y + 1);
		moves.push(s4);
		Square s5 = new Square(x + 2, y - 1);
		moves.push(s5);
		Square s6 = new Square(x - 2, y + 1);
		moves.push(s6);
		Square s7 = new Square(x - 2, y - 1);
		moves.push(s7);

		for (int i = 0; i < 8; i++) {
			Square tmp = (Square) moves.pop();
			Move tmpmove = new Move(startingSquare, tmp, findPiece(tmp.getYC(), tmp.getYC()));	//Added getPieceScore Here | Used to send weighting of piece.
			if ((tmp.getXC() < 0) || (tmp.getXC() > 7) || (tmp.getYC() < 0) || (tmp.getYC() > 7)) {

			} else if (piecePresent(((tmp.getXC() * 75) + 20), (((tmp.getYC() * 75) + 20)))) {
				if (piece.contains("White")) {
					if (checkWhiteOponent(((tmp.getXC() * 75) + 20), ((tmp.getYC() * 75) + 20))) {
						attackingMove.push(tmpmove);
					}
				}
			} else {
				attackingMove.push(tmpmove);
			}
		}
		return attackingMove;
	}

	/*
	 * Method to colour a stack of Squares
	 */
	private void colorSquares(Stack squares) {
		Border greenBorder = BorderFactory.createLineBorder(Color.GREEN, 3);
		while (!squares.empty()) {
			Square s = (Square) squares.pop();
			int location = s.getXC() + ((s.getYC()) * 8);
			JPanel panel = (JPanel) chessBoard.getComponent(location);
			panel.setBorder(greenBorder);
		}
	}

	/*
	 * Method to get the landing square of a bunch of moves...
	 */
	private void getLandingSquares(Stack found) {
		Move tmp;
		Square landing;
		Stack squares = new Stack();
		while (!found.empty()) {
			tmp = (Move) found.pop();
			landing = (Square) tmp.getLanding();
			squares.push(landing);
		}
		colorSquares(squares);
	}

	/*
	 * Method to find all the White Pieces.
	 */
	private Stack findWhitePieces() {
		Stack squares = new Stack();
		String icon;
		int x;
		int y;
		String pieceName;
		for (int i = 0; i < 600; i += 75) {
			for (int j = 0; j < 600; j += 75) {
				y = i / 75;
				x = j / 75;
				Component tmp = chessBoard.findComponentAt(j, i);
				if (tmp instanceof JLabel) {
					chessPiece = (JLabel) tmp;
					icon = chessPiece.getIcon().toString();
					pieceName = icon.substring(0, (icon.length() - 4));
					if (pieceName.contains("White")) {
						Square stmp = new Square(x, y, pieceName);
						squares.push(stmp);
					}
				}
			}
		}
		return squares;
	}

	private void resetBorders() {
		Border empty = BorderFactory.createEmptyBorder();
		for (int i = 0; i < 64; i++) {
			JPanel tmppanel = (JPanel) chessBoard.getComponent(i);
			tmppanel.setBorder(empty);
		}
	}

	/*
	 * The method printStack takes in a Stack of Moves and prints out all possible
	 * moves.
	 */
	private void printStack(Stack input) {
		Move m;
		Square s, l;
		while (!input.empty()) {
			m = (Move) input.pop();
			s = (Square) m.getStart();
			l = (Square) m.getLanding();
			System.out.println("The possible move that was found is : (" + s.getXC() + " , " + s.getYC()
					+ "), landing at (" + l.getXC() + " , " + l.getYC() + ")");
		}
	}

	private void makeAIMove() {
		/*
		 * When the AI Agent decides on a move, a red border shows the square from where
		 * the move started and the landing square of the move.
		 */
		resetBorders();
		layeredPane.validate();
		layeredPane.repaint();
		Stack white = findWhitePieces();
		Stack completeMoves = new Stack();
		Move selectedMove = null;
		Move tmp;
		Stack temporary = new Stack();
		while (!white.empty()) {
			Square s = (Square) white.pop();
			String tmpString = s.getName();
			Stack tmpMoves = new Stack();

			/*
			 * We need to identify all the possible moves that can be made by the AI
			 * Opponent
			 */
			if (tmpString.contains("Knight")) {
				tmpMoves = getKnightMoves(s.getXC(), s.getYC(), s.getName());
			} else if (tmpString.contains("Bishop")) {
				tmpMoves = getBishopMoves(s.getXC(), s.getYC(), s.getName());
			} else if (tmpString.contains("Pawn")) {
				tmpMoves = getWhitePawnSquares(s.getXC(), s.getYC(), s.getName());
			} else if (tmpString.contains("Rook")) {
				tmpMoves = getRookMoves(s.getXC(), s.getYC(), s.getName());
			} else if (tmpString.contains("Queen")) {
				tmpMoves = getQueenMoves(s.getXC(), s.getYC(), s.getName());
			} else if (tmpString.contains("King")) {
				tmpMoves = getKingSquares(s.getXC(), s.getYC(), s.getName());
			}

			while (!tmpMoves.empty()) {
				tmp = (Move) tmpMoves.pop();
				completeMoves.push(tmp);
			}
		}
		temporary = (Stack) completeMoves.clone(); // Added stack here.
		getLandingSquares(temporary);
		printStack(temporary);
		/*
		 * So now we should have a copy of all the possible moves to make in our Stack
		 * called completeMoves
		 */
		if (completeMoves.size() == 0) {
			/*
			 * In Chess if you cannot make a valid move but you are not in Check this state
			 * is referred to as a Stale Mate
			 */
			JOptionPane.showMessageDialog(null,
					"Cogratulations, you have placed the AI component in a Stale Mate Position");
			System.exit(0);

		} else {
			System.out.println("=============================================================");
			Stack moves = new Stack();
			while (!completeMoves.empty()) {
				Move tmpMove = (Move) completeMoves.pop();
				Square s1 = (Square) tmpMove.getStart();
				Square s2 = (Square) tmpMove.getLanding();
				//System.out.println("The " + s1.getName() + " can move from (" + s1.getXC() + ", " + s1.getYC()+ ") to the following square: (" + s2.getXC() + ", " + s2.getYC() + ")");
				moves.push(tmpMove);
			}
			System.out.println("=============================================================");
			Border redBorder = BorderFactory.createLineBorder(Color.RED, 3);
			if(aiType == 0){
				selectedMove = agent.randomMove(moves);
				System.out.println("Random AI Selected");
			}
			if (aiType == 1){
				selectedMove = agent.nextBestMove(moves);
				System.out.println("Next Best AI Selected");
			}
			if (aiType == 2){
				selectedMove = agent.twoLevelsDeep(moves);
				System.out.println("2 Deep AI Selected");
			}
			
			Square startingPoint = (Square) selectedMove.getStart();
			Square landingPoint = (Square) selectedMove.getLanding();
			int startX1 = (startingPoint.getXC() * 75) + 20;
			int startY1 = (startingPoint.getYC() * 75) + 20;
			int landingX1 = (landingPoint.getXC() * 75) + 20;
			int landingY1 = (landingPoint.getYC() * 75) + 20;
			System.out.println("-------- Move " + startingPoint.getName() + " (" + startingPoint.getXC() + ", "
					+ startingPoint.getYC() + ") to (" + landingPoint.getXC() + ", " + landingPoint.getYC() + ")");

			Component c = (JLabel) chessBoard.findComponentAt(startX1, startY1);
			Container parent = c.getParent();
			parent.remove(c);
			int panelID = (startingPoint.getYC() * 8) + startingPoint.getXC();
			panels = (JPanel) chessBoard.getComponent(panelID);
			panels.setBorder(redBorder);
			parent.validate();

			Component l = chessBoard.findComponentAt(landingX1, landingY1);
			if (l instanceof JLabel) {
				Container parentlanding = l.getParent();
				JLabel awaitingName = (JLabel) l;
				String agentCaptured = awaitingName.getIcon().toString();
				if (agentCaptured.contains("King")) {
					agentwins = true;
				}
				parentlanding.remove(l);
				parentlanding.validate();
				pieces = new JLabel(new ImageIcon(startingPoint.getName() + ".png"));
				int landingPanelID = (landingPoint.getYC() * 8) + landingPoint.getXC();
				panels = (JPanel) chessBoard.getComponent(landingPanelID);
				panels.add(pieces);
				panels.setBorder(redBorder);
				layeredPane.validate();
				layeredPane.repaint();

				if (agentwins) {
					JOptionPane.showMessageDialog(null, "The AI Agent has won!");
					System.exit(0);
				}
			} else {
				pieces = new JLabel(new ImageIcon(startingPoint.getName() + ".png"));
				int landingPanelID = (landingPoint.getYC() * 8) + landingPoint.getXC();
				panels = (JPanel) chessBoard.getComponent(landingPanelID);
				panels.add(pieces);
				panels.setBorder(redBorder);
				layeredPane.validate();
				layeredPane.repaint();
			}
			white2Move = false; // was false
		}
	}

	public static void initalCreation() {
		ChessProject frame = new ChessProject();
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);		
	}

	/*
	 * Main method that gets the ball moving.
	 */
	public static void main(String[] args) {
		//String[] strategy = new String[] { "Random", "Next Best", "2 Deep" };
		//aiType = JOptionPane.showOptionDialog(null, "Message", "Title", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, strategy, strategy[0]);
		aiType = 1;
		initalCreation(); //Create the chess board.
	}
}