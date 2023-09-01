package ChessPieces;

import Handlers.Board;
import Handlers.Piece;
import Handlers.Square;

public class King extends Piece {

    private boolean castled = false;
    private int startingPos;

    public King(boolean white) {
        super(white);
        startingPos = white ? 7 : 0;
    }

    private boolean isCastled() {
        return castled;
    }

    private void setCastled(boolean castled) {
        this.castled = castled;
    }

    public boolean canMove(Board board, Square start, Square end) {

        //captured piece is not own piece
        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        //x y delta
        int x = Math.abs(start.getY() - end.getY());
        int y = Math.abs(start.getX() - end.getX());

        //is king initial position checked?
        // this.setChecked(this.isChecked(board, start));

        //castling
        if (this.canCastle(board, start, end)) {

            int direction = start.getY() < end.getY() ? -1 : 1;
            int position = start.getY() < end.getY() ? 7 : 0;

            board.graphics.movePiece(startingPos, position, startingPos, end.getY() + direction);
            board.getBox(startingPos, end.getY() + direction).setPiece(board.getBox(startingPos, position).getPiece());
            board.getBox(startingPos, position).setPiece(null);
            
            this.setCastled(true);
            return true;
        }

        //ensure king only moves 1 square at a time
        if (!(x < 2 && y < 2)) {
            return false;
        }

        //does end move result in a check?
        // this.setChecked(this.isChecked(board, end));

        //king has moved and therefore cannot castle
        if (!this.isCastled()) {
            this.setCastled(true);
        }

        return true;
    }

    public boolean canCastle(Board board, Square start, Square end) {

        //check if castled already
        if (this.isCastled()) {
            return false;
        }
        
        //check that start and end square indicate intention to castle
        if (Math.abs(start.getY() - end.getY()) != 2) {
            return false;
        }

        //check if any pieces are obstructing King and Rook
        if (start.getY() < end.getY()) {   
            for (int i = 5; i < 7; i++) {
                if (board.getBox(startingPos, i).getPiece() != null) {
                    return false;
                }
            }
        }
        else {
            for (int i = 3; i > 0; i--) {
                if (board.getBox(startingPos, i).getPiece() != null) {
                    return false;
                }
            }
        }

        return true;
    }

}
