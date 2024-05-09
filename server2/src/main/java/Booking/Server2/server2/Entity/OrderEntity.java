package Booking.Server2.server2.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "entry_item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    private Long orderId;
    private Long awbno;
    private String itemCode;
    private String itemName;
    private double length;
    private double width;
    private double height;
    private String divisionId;
    private double actualWeight;
    private int pieces;
    private double chargeableWeight;
    private double rate;
    private double total;
    private double cubicFeet;
    private double cubicFeetWeight;
    private int quantity;
    private double quantityWeight;
}
