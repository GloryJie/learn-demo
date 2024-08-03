package top.gloryjie.learn.gis.jts;

import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.PrecisionModel;
import org.locationtech.jts.index.strtree.STRtree;
import org.locationtech.jts.io.WKTReader;
import org.locationtech.jts.io.WKTWriter;

public class WKTReaderWriterDemo {

    public static void main(String[] args) throws Exception {
        // 读取几何对象
        readWriteGeometry();
        relation();

        geometryOp();

        mbr();

        rtree();

        buff();


    }

    public static void readWriteGeometry() throws Exception {
        WKTReader wktReader = new WKTReader();
        Geometry point = wktReader.read("POINT (282 455)");
        Geometry line = wktReader.read("LINESTRING (260 250, 485 248, 520 380)");
        Geometry polygon = wktReader.read("POLYGON ((320 390, 370 330, 470 360, 460 430, 375 432, 320 390))");

        WKTWriter wktWriter = new WKTWriter();
        System.out.println(wktWriter.write(point));
        System.out.println(wktWriter.write(line));
        System.out.println(wktWriter.write(polygon));
    }

    public static void relation() throws Exception {
        WKTReader wktReader = new WKTReader();
        Geometry geometryA = wktReader.read("POLYGON ((320 390, 370 330, 470 360, 460 430, 375 432, 320 390))");
        Geometry geometryB = wktReader.read("POLYGON ((500 420, 430 360, 530 260, 500 420))");
        Geometry point = wktReader.read("POINT (390 380)");

        System.out.println("Equal: " + geometryA.equals(geometryB));
        System.out.println("Disjoint: " + geometryA.disjoint(geometryB));
        System.out.println("Intersects: " + geometryA.intersects(geometryB));
        System.out.println("Within: " + geometryA.within(geometryB));
        System.out.println("Contains: " + geometryA.contains(geometryB));

        System.out.println("point in geometryA: " + geometryA.contains(point));
        System.out.println("point in geometryB: " + geometryB.contains(point));
    }

    public static void geometryOp() throws Exception {
        WKTReader wktReader = new WKTReader();
        Geometry geometryA = wktReader.read("POLYGON ((320 390, 370 330, 470 360, 460 430, 375 432, 320 390))");
        Geometry geometryB = wktReader.read("POLYGON ((500 420, 430 360, 530 260, 500 420))");

        WKTWriter wktWriter = new WKTWriter();
        wktWriter.setPrecisionModel(new PrecisionModel(0));
        System.out.println("Intersection: " + wktWriter.write(geometryA.intersection(geometryB)));
        System.out.println("Union: " + wktWriter.write(geometryA.union(geometryB)));
        System.out.println("Difference: " + wktWriter.write(geometryA.difference(geometryB)));

    }

    public static void mbr() throws Exception {
        WKTReader wktReader = new WKTReader();
        Geometry geometryA = wktReader.read("POLYGON ((320 390, 370 330, 470 360, 460 430, 375 432, 320 390))");

        Envelope envelope = geometryA.getEnvelopeInternal();
        System.out.println(envelope.getMaxX());
        System.out.println(envelope.getMaxY());
        System.out.println(envelope.getMinX());
        System.out.println(envelope.getMinY());
    }

    public static void rtree() throws Exception {
        WKTReader wktReader = new WKTReader();
        Geometry geometryA = wktReader.read("POLYGON ((320 390, 370 330, 470 360, 460 430, 375 432, 320 390))");
        Geometry geometryB = wktReader.read("POLYGON ((500 420, 430 360, 530 260, 500 420))");

        STRtree rtree = new STRtree();
        // 向R树种添加MBR，和自己的数据
        rtree.insert(geometryA.getEnvelopeInternal(), "Polygon-A");
        rtree.insert(geometryB.getEnvelopeInternal(), "Polygon-B");
        rtree.build();

        // 点只在Polygon-A中
        System.out.println(rtree.query(wktReader.read("POINT (337 391)").getEnvelopeInternal()));
        // 点只在Polygon-B中
        System.out.println(rtree.query(wktReader.read("POINT (496 390)").getEnvelopeInternal()));
        // 点在Polygon-A和Polygon-B中
        System.out.println(rtree.query(wktReader.read("POINT (452 367)").getEnvelopeInternal()));


    }

    public static void buff() throws Exception {
        WKTReader wktReader = new WKTReader();
        Geometry geometryA = wktReader.read("POLYGON ((340 490, 370 330, 730 350, 700 270, 340 490))");

        WKTWriter wktWriter = new WKTWriter();
        wktWriter.setPrecisionModel(new PrecisionModel(0));
        System.out.println(wktWriter.write(geometryA.buffer(0)));
    }


}
