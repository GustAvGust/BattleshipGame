package ru.kpfu.itis.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Ship {
    // {1, 2, 3, 4}
    private Integer sizeClassOfShip;
    private Boolean isAlive = true;
    private Integer loсationOfShipBowCellX;
    private Integer loсationOfShipBowCellY;
    // 0 - default, 1 - справа, -1 - слева, 2 - сверху, -2 - снизу
    private Integer sternLocation;
}
