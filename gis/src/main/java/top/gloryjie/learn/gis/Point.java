package top.gloryjie.learn.gis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Point {

    /**
     * 经度
     */
    private Double lng;
    /**
     * 纬度
     */
    private Double lat;

}
