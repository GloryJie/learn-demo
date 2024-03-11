package top.gloryjie.learn.gis;

import java.util.List;
import java.util.Objects;

public class RayCastUtil {

    private static final double PRECISION = 2e-10;

    public static boolean isPointInPolygon(Point target, List<Point> polygonPoints) {

        // 这里不需要首尾相连，有些不需要，这就
        if (Objects.equals(polygonPoints.get(0), polygonPoints.get(polygonPoints.size() - 1))) {
            polygonPoints = polygonPoints.subList(0, polygonPoints.size() - 1);
        }

        // 顶点重合的情况
        for (Point polygonPoint : polygonPoints) {
            if (Objects.equals(target, polygonPoint)) {
                return true;
            }
        }

        // X轴射线与多边形的交点数
        int intersectPointCount = 0;
        // X轴射线与多边形顶点相交的权值
        double intersectPointWeights = 0;

        // pointA -> pointB 代表一条边
        Point pointA = polygonPoints.get(0);
        Point pointB = null;

        final int pointSize = polygonPoints.size();
        for (int i = 1; i <= pointSize; i++) {
            // for中定义<=以及这里的%取余，是为了关注最后一个节点连接首节点这条边的情况
            pointB = polygonPoints.get(i % pointSize);

            double minLat = Math.min(pointA.getLat(), pointB.getLat());
            double maxLat = Math.max(pointA.getLat(), pointB.getLat());
            double minLng = Math.min(pointA.getLng(), pointB.getLng());
            double maxLng = Math.max(pointA.getLng(), pointB.getLng());

            // 目标点在的上方、下方、右侧，那么就不会相交
            if (target.getLat() < minLat || target.getLat() > maxLat || target.getLng() > maxLng) {
                pointA = pointB;
                continue;
            }

            // 边水平的情况,若点在水平的边上，则点在多边形内,
            if (Objects.equals(pointA.getLat(), pointB.getLat())) {
                if (target.getLng() <= maxLng && target.getLng() >= minLng) {
                    return true;
                }
                pointA = pointB;
                continue;
            }

            // 边界处理：射线经过边的顶点(此时边可能垂直)
            if ((Objects.equals(target.getLat(), pointA.getLat()) && target.getLng() < pointA.getLng())
                    || Objects.equals(target.getLat(), pointB.getLat()) && target.getLng() < pointB.getLng()) {
                if (pointB.getLat() < pointA.getLat()) {
                    // B点在A点的下方，那么权重值-0.5
                    intersectPointWeights -= 0.5;
                } else if (pointB.getLat() > pointA.getLat()) {
                    // B点在A点的上方，那么权重值+0.5
                    intersectPointWeights += 0.5;
                }
                pointA = pointB;
                continue;
            }

            // 边垂直的情况
            if (Objects.equals(pointA.getLng(), pointB.getLng())) {
                if (Objects.equals(target.getLng(), pointA.getLng())) {
                    // 若点在垂直的边P上，则点在多边形内
                    return true;
                } else if (target.getLng() < pointA.getLng()) {
                    // 若点在在垂直的边的左边，则点与该边必然有交点
                    intersectPointCount++;
                }
                pointA = pointB;
                continue;
            }

            // 边斜的情况
            if (target.getLng() <= minLng) {
                // 点point的x坐标在边的左侧，那么肯定相交
                intersectPointCount++;
            } else if (target.getLng() < maxLng) {
                // 点point的x坐标在点B、A的x坐标中间
                double diff = (target.getLat() - pointA.getLat()) * (pointA.getLng() - pointB.getLng()) / (pointA.getLat() - pointB.getLat()) + pointA.getLng() - target.getLng();
                if (diff >= 0) {
                    if (diff < PRECISION) {
                        // 由于double精度在计算时会有损失，故匹配一定的容差
                        // 点在斜线P1P2上
                        return true;
                    } else {
                        // 点向右射线与斜线P1P2有交点
                        intersectPointCount++;
                    }
                }
            }
            pointA = pointB;
        }
        return (intersectPointCount + Math.abs(intersectPointWeights)) % 2 != 0;
    }

}
