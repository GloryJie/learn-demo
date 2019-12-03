package top.gloryjie.learn.poi;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookFactory;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author jie
 * @since 2019/11/30
 */
public class POIUtil {


    /**
     * @param mapping  key为中文, value为英文.若需要顺序,具体实例为LinkHashMap
     * @param dataList 数据记录
     * @return 返回的数据量可以不调用close
     */
    public static byte[] generateExcel(Map<String, String> mapping, List<Map<String, String>> dataList) {
        try (Workbook workbook = XSSFWorkbookFactory.createWorkbook()) {
            Sheet sheet = workbook.createSheet();
            List<String> zhKeyList = new ArrayList<>(mapping.keySet());
            // 标题栏
            Row row = sheet.createRow(0);
            for (int i = 0; i < zhKeyList.size(); i++) {
                row.createCell(i, CellType.STRING).setCellValue(zhKeyList.get(i));
            }

            // 填充数据
            for (int recordIndex = 0; recordIndex < dataList.size(); recordIndex++) {
                Map<String, String> record = dataList.get(recordIndex);
                row = sheet.createRow(recordIndex + 1);
                for (int columnIndex = 0; columnIndex < zhKeyList.size(); columnIndex++) {
                    String zhKey = zhKeyList.get(columnIndex);
                    String fieldKey = mapping.get(zhKey);
                    if (fieldKey != null && record.get(fieldKey) != null) {
                        row.createCell(columnIndex, CellType.STRING).setCellValue(record.get(fieldKey));
                    }
                }
            }
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            workbook.write(os);
            return os.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
