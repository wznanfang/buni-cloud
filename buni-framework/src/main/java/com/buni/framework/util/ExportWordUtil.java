package com.buni.framework.util;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;

import java.io.FileOutputStream;
import java.util.*;

@Slf4j
public class ExportWordUtil {

    @Resource
    private ObjectMapper objectMapper;


    /**
     * 生成Word模板
     *
     * @param headStr      word表头数据
     * @param templateData 填充数据
     * @param filePath     存储路径
     */
    public void wordExport(String headStr, JSONObject templateData, String filePath) {
        try {
            // 解析JSON为数据结构
            TypeReference<List<Map<String, Object>>> typeRef = new TypeReference<>() {
            };
            List<Map<String, Object>> headData = objectMapper.readValue(headStr, typeRef);
            headData.sort(Comparator.comparingInt(o -> Integer.parseInt((String) o.get("sort"))));
            //对headData数据进行排序
            //使用list来装排序后的数据，便于数据填充
            List<String> cellList = new ArrayList<>();
            for (Map<String, Object> headDatum : headData) {
                JSONObject jsonObject = JSONObject.from(JSONUtil.parseObj(headDatum));
                if (ObjUtil.isNotEmpty(jsonObject.get("children"))) {
                    sortData(jsonObject.getJSONArray("children"), cellList);
                } else {
                    cellList.add((String) jsonObject.get("key"));
                }
            }
            // 创建Word文档
            XWPFDocument document = new XWPFDocument();
            // 表格标题
            XWPFParagraph titleParagraph = document.createParagraph();
            titleParagraph.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun titleRun = titleParagraph.createRun();
            titleRun.setText(templateData.getString("templateName"));
            titleRun.setFontSize(14);
            XWPFTable table = document.createTable();
            // 用于跟踪列的纵向合并起始位置
            Map<Integer, Integer> columnMergeStart = new HashMap<>();
            // 生成表格并处理合并
            generateTable(headData, table, 0, 0, columnMergeStart);
            //做数据填充
            setCellData(table, cellList, templateData);
            // 保存文档
            try (FileOutputStream out = new FileOutputStream(filePath)) {
                document.write(out);
            }
            log.info("--------------------Word文件生成成功！--------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 对JSONArray数据进行排序。
     *
     * @param templateArray
     */
    private static void sortData(JSONArray templateArray, List<String> cellList) {
        templateArray.sort(Comparator.comparingInt(o -> ((JSONObject) o).getIntValue("sort")));
        for (Object template : templateArray) {
            JSONObject jsonObject = JSONObject.from(JSONUtil.parseObj(template));
            if (ObjUtil.isNotEmpty(jsonObject.get("children"))) {
                JSONArray childrenJsonArray = jsonObject.getJSONArray("children");
                // 根据childrenJsonArray 的sort字段来进行排序处理
                childrenJsonArray.sort(Comparator.comparingInt(o -> ((JSONObject) o).getIntValue("sort")));
                sortData(childrenJsonArray, cellList);
            } else {
                cellList.add((String) jsonObject.get("key"));
            }
        }
    }

    /**
     * 生成表格并处理合并逻辑。
     *
     * @param headData         数据列表
     * @param table            表格对象
     * @param rowIndex         当前行索引
     * @param colIndex         当前列索引
     * @param columnMergeStart 用于跟踪列合并的起始行
     * @return 当前列的索引
     */
    private static int generateTable(List<Map<String, Object>> headData, XWPFTable table, int rowIndex, int colIndex, Map<Integer, Integer> columnMergeStart) {
        XWPFTableRow row;
        if (table.getNumberOfRows() > rowIndex) {
            row = table.getRow(rowIndex);
        } else {
            row = table.createRow();
        }
        // 当前列索引
        int currentColIndex = colIndex;
        for (Map<String, Object> item : headData) {
            String name = (String) item.get("name");
            List<Map<String, Object>> children = (List<Map<String, Object>>) item.get("children");
            // 获取或创建单元格
            XWPFTableCell cell;
            if (row.getTableCells().size() > currentColIndex) {
                cell = row.getCell(currentColIndex);
            } else {
                cell = row.addNewTableCell();
            }
            // 设置单元格内容
            cell.setText(name);
            if (children != null && !children.isEmpty()) {
                // 递归渲染子节点
                int childColSpan = generateTable(children, table, rowIndex + 1, currentColIndex, columnMergeStart) - currentColIndex;
                // 如果子节点跨列，需要进行横向合并
                if (childColSpan > 1) {
                    mergeCellsHorizontally(table, rowIndex, currentColIndex, currentColIndex + childColSpan - 1);
                }
                currentColIndex += childColSpan;
            } else {
                mergeCellsVertically(table, currentColIndex, rowIndex, table.getNumberOfRows());
                // 无子节点：检查并执行纵向合并
                if (!columnMergeStart.containsKey(currentColIndex)) {
                    columnMergeStart.put(currentColIndex, rowIndex);
                }
                currentColIndex++;
            }
        }
        // 对当前行中无子节点的列执行纵向合并
        columnMergeStart.forEach((col, startRow) -> {
            if (startRow < rowIndex) {
                mergeCellsVertically(table, col, startRow, rowIndex);
            }
        });
        return currentColIndex;
    }

    /**
     * 横向合并单元格。
     *
     * @param table    表格对象
     * @param rowIndex 行索引
     * @param startCol 起始列索引
     * @param endCol   结束列索引
     */
    private static void mergeCellsHorizontally(XWPFTable table, int rowIndex, int startCol, int endCol) {
        XWPFTableRow row = table.getRow(rowIndex);
        for (int i = startCol; i <= endCol; i++) {
            XWPFTableCell cell = row.getCell(i);
            if (cell == null) {
                cell = row.createCell();
            }
            if (i == startCol) {
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.Enum.forString("restart"));
            } else {
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.Enum.forString("continue"));
            }
        }
    }

    /**
     * 纵向合并单元格。
     *
     * @param table    表格对象
     * @param colIndex 列索引
     * @param startRow 起始行索引
     * @param endRow   结束行索引
     */
    private static void mergeCellsVertically(XWPFTable table, int colIndex, int startRow, int endRow) {
        for (int i = startRow; i <= endRow; i++) {
            XWPFTableRow row = table.getRow(i);
            if (row == null) {
                continue;
            }
            XWPFTableCell cell = row.getCell(colIndex);
            if (cell == null) {
                cell = row.createCell();
            }
            if (i == startRow) {
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.Enum.forString("restart"));
            } else {
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.Enum.forString("continue"));
            }
        }
    }

    private static void setCellData(XWPFTable table, List<String> cellList, JSONObject templateData) {
        // 获取表头行以确定列数
        XWPFTableRow headerRow = table.getRow(0);
        int columnCount = headerRow.getTableCells().size();
        // 获取模板内容
        JSONArray jsonArray = templateData.getJSONArray("fieldData");
        for (Object object : jsonArray) {
            JSONObject jsonObject = JSONObject.from(object);
            // 创建新行
            XWPFTableRow newRow = table.createRow();
            // 设置新行的单元格内容
            for (int i = 0; i < columnCount; i++) {
                // 设置单元格的内容
                String value = jsonObject.getString(cellList.get(i));
                newRow.getCell(i).setText(value);
            }
        }
    }


}
