/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package excel_json;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import static java.time.Clock.system;
import java.util.HashMap;
import java.util.Map;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 *
 * @author PC
 */
public class Excel_json {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
		// d盘下的xls目录
		File dir = new File("F:\\excel");
		// 用于文件过滤
//		FilenameFilter searchSuffix = new FilenameFilter() {
//			@Override
//			public boolean accept(File dir, String name) {
//				return name.endsWith(".xls");
//			}
//		};
		// 获取xls目录下的所有文件列表
		File[] list = dir.listFiles();
		for (File file : list) {
			File dest = new File("E:\\json_"
					+ file.getName().substring(0,
							file.getName().lastIndexOf(".")) + ".json");
			if (dest.exists()) {
				dest.delete();
			}
			// 获取xls目录下的文件名
			String fileName = file.getName();
			// 获取文件后缀
			String suffix = fileName.substring(fileName.lastIndexOf("."));
			// 如果不是以xls为结尾的文件跳过
//			if (!searchSuffix.accept(file, suffix)) {
//				continue;
//			}
			try {
				Workbook wb = Workbook.getWorkbook(file); // 从文件流中获取Excel工作区对象（WorkBook）
				Sheet sheet = wb.getSheet(0); // 从工作区中取得页（Sheet）
				Cell[] header = sheet.getRow(0);
				for (int i = 0; i < sheet.getRows(); i++) { // 循环打印Excel表中的内容
					Map hashMap = new HashMap();
					for (int j = 0; j < sheet.getColumns(); j++) {
						Cell cell = sheet.getCell(j, i);
						hashMap.put(header[j].getContents(), cell.getContents());
					}
					// 这个json字符串就是我们想要的，实际应用中可以直接返回该字符串
                                        System.out.print(hashMap);
					//String json = JSONObject.toJSONString(hashMap);
					// 将转换后的json字符串写到文件当中
					//FileUtils.writeStringToFile(dest, json + "\n", "UTF-8");
				}
			} catch (BiffException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

    
}
