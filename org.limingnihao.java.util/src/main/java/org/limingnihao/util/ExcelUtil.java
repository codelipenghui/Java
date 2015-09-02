package org.limingnihao.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExcelUtil {
	/**
	 * 将Map导出Excel文件 一个map对应一个工作表，map的键值为工作表名称，map的vector为工作表内容
	 * 
	 * @param stream
	 * @param data
	 * @throws IOException
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	public static void exportExcel(final OutputStream outputStream, final Map<String, List<List<String>>> data) throws IOException,
			RowsExceededException, WriteException {
		WritableWorkbook workbook = Workbook.createWorkbook(outputStream);
		int s = 0;
		for (final String label : data.keySet()) {
			WritableSheet sheet = workbook.createSheet(label.replaceAll("\\[|\\]|\\\\|\\:|\\?|\\/", "_"), s++);
			List<List<String>> value = data.get(label);
			for (int i = 0; i < value.size(); i++) {
				List<String> items = value.get(i);
				for (int j = 0; j < items.size(); j++) {
					if (items.get(j) != null && !items.get(j).trim().equals("")) {
						sheet.addCell(new Label(j, i, items.get(j)));
					}
				}
			}
		}
		workbook.write();
		workbook.close();
		outputStream.close();
	}

}
