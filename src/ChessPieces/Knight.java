package ChessPieces;

import Handlers.Board;
import Handlers.Piece;
import Handlers.Square;

public class Knight extends Piece {

    public Knight(boolean white) {
        super(white);
    }

    public boolean canMove(Board board, Square start, Square end) {

        //captured piece is not own piece
        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        //x y delta coordinates 
        int x = Math.abs(start.getY() - end.getY());
        int y = Math.abs(start.getX() - end.getX());

        return x * y == 2;
    }

    public boolean isCastlingMove(Square start, Square end) {
        return false;
    }
}
