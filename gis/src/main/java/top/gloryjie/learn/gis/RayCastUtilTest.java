package top.gloryjie.learn.gis;

import com.alibaba.fastjson.JSON;

import java.util.List;

public class RayCastUtilTest {

    public static void main(String[] args) {

        String json = "[{\"lng\":110.269889,\"lat\":21.308982},{\"lng\":110.277883,\"lat\":21.231378},{\"lng\":110.275219,\"lat\":21.178585},{\"lng\":110.309195,\"lat\":21.166692},{\"lng\":110.345361,\"lat\":21.166692},{\"lng\":110.345361,\"lat\":21.225846},{\"lng\":110.37871,\"lat\":21.218632},{\"lng\":110.416809,\"lat\":21.210863},{\"lng\":110.41919,\"lat\":21.224181},{\"lng\":110.419648,\"lat\":21.234822},{\"lng\":110.385148,\"lat\":21.282747},{\"lng\":110.351299,\"lat\":21.286387},{\"lng\":110.269889,\"lat\":21.308982}]";

        List<Point> polygonPointList = JSON.parseArray(json, Point.class);

        System.out.println("图形外，射线和线水平，不在线上：" + RayCastUtil.isPointInPolygon(new Point(110.301195,21.166692), polygonPointList));
        System.out.println("图形内, 射线和线水平，在线上：" + RayCastUtil.isPointInPolygon(new Point(110.319195,21.166692), polygonPointList));

        System.out.println("图形内，射线和线垂直：" + RayCastUtil.isPointInPolygon(new Point(110.319195,21.186692), polygonPointList));
        System.out.println("图形外，射线和线垂直：" + RayCastUtil.isPointInPolygon(new Point(110.219195,21.186692), polygonPointList));

        System.out.println("图形内，经过线顶点：" + RayCastUtil.isPointInPolygon(new Point(110.305361,21.225846), polygonPointList));
        System.out.println("图形外，经过线顶点：" + RayCastUtil.isPointInPolygon(new Point(110.245361,21.225846), polygonPointList));

        System.out.println("图形内，和一条斜线相交：" + RayCastUtil.isPointInPolygon(new Point(110.305361,21.265846), polygonPointList));
        System.out.println("图形外，和一条斜线相交：" + RayCastUtil.isPointInPolygon(new Point(110.245361,21.265846), polygonPointList));

        System.out.println("图形内，和多条线相交：" + RayCastUtil.isPointInPolygon(new Point(110.305361,21.221846), polygonPointList));
        System.out.println("图形外，和多条线相交：" + RayCastUtil.isPointInPolygon(new Point(110.245361,21.221846), polygonPointList));

    }

}
