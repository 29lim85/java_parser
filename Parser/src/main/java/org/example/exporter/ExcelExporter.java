package org.example.exporter;
// THIS CLASS I DOWNLOAD IN CHINA  FORUM  - THEY SAID IT TOTALLY SAFE TO USE IN MY PROJECT😅
// 这个类我在中国黑客论坛下载的——他们说在我的项目中使用完全安全 😅
import org.apache.poi.ss.usermodel.*;  // Apache POI：处理Excel文件的强大工具包
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  // XSSFWorkbook：处理.xlsx文件的类

import javax.swing.*;  // Swing：Java的GUI工具包
import javax.swing.table.TableModel;  // TableModel：JTable的数据模型
import java.io.FileOutputStream;  // FileOutputStream：文件输出流，用于写文件
import java.io.IOException;  // IOException：处理输入输出异常

public class ExcelExporter {  // Excel导出器：将数据导出到Excel文件的类

    public void exportToExcel(TableModel tableModel, String filePath) throws IOException {  // 导出到Excel方法
        try (Workbook workbook = new XSSFWorkbook()) {  // 创建一个新的工作簿
            Sheet sheet = workbook.createSheet("Parsed Data");  // 创建一个名为“Parsed Data”的工作表
            Row headerRow = sheet.createRow(0);  // 创建表头行

            for (int i = 0; i < tableModel.getColumnCount(); i++) {  // 遍历列数
                Cell cell = headerRow.createCell(i);  // 在表头行中创建单元格
                cell.setCellValue(tableModel.getColumnName(i));  // 设置单元格值为列名
            }

            for (int i = 0; i < tableModel.getRowCount(); i++) {  // 遍历行数
                Row row = sheet.createRow(i + 1);  // 创建新行
                for (int j = 0; j < tableModel.getColumnCount(); j++) {  // 遍历列数
                    Cell cell = row.createCell(j);  // 创建单元格
                    cell.setCellValue(tableModel.getValueAt(i, j).toString());  // 设置单元格值为表格数据
                }
            }

            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {  // 创建文件输出流
                workbook.write(fileOut);  // 将工作簿写入文件输出流
                JOptionPane.showMessageDialog(null, "数据成功导出到 " + filePath,  // 显示成功消息
                        "成功", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
