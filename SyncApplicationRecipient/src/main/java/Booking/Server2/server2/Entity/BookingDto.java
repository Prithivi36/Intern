package Booking.Server2.server2.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {
    private Long autoId;
    private Long bookCnno;
    private double bookVolLength;
    private double bookVolBreadth;
    private double bookVolHeight;
    private int bookVolDivid;
    private double bookVolWeight;
    private int bookPieces;
    private boolean bookPieceSync;
    private double bookWeight;
    private String bookStatus;
    private String status;
    private boolean awbSync;
}
