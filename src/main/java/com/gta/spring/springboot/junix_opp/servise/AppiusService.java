package com.gta.spring.springboot.junix_opp.servise;

import com.gta.spring.springboot.junix_opp.entity.Appius;
import com.gta.spring.springboot.junix_opp.repository.AppiusRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;

@Service
@RequiredArgsConstructor
public class AppiusService {

    private final AppiusRepository appiusRepository;
    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public void saveExcelData(MultipartFile file) throws IOException {

        // Очистка таблицы и сброс идентификаторов
        clearAndResetTable();

        // Загрузка данных из Excel
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            if (row.getRowNum() == 0) { // Пропустить строку заголовков
                continue;
            }

            Appius appius = new Appius();
            appius.setObject(getCellValueAsString(row.getCell(0)));
            appius.setPodobject(getCellValueAsString(row.getCell(1)));
            appius.setNomerPozPoGp(getCellValueAsString(row.getCell(2)));
            appius.setOboznachenie(getCellValueAsString(row.getCell(3)));
            appius.setRazdelProekta(getCellValueAsString(row.getCell(4)));
            appius.setIzm(getCellValueAsString(row.getCell(5)));
            appius.setDataDocumenta(convertToSqlDate(getCellValueAsDate(row.getCell(6))));
            appius.setNaimenovanie(getCellValueAsString(row.getCell(7)));
            appius.setSostoyanie(getCellValueAsString(row.getCell(8)));
            appius.setPlanovayaDataVipuska(convertToSqlDate(getCellValueAsDate(row.getCell(9))));
            appius.setDataVhodyashegoPisma(convertToSqlDate(getCellValueAsDate(row.getCell(10))));
            appius.setNomerVhodyashegoPisma(getCellValueAsString(row.getCell(11)));
            appius.setDataIshodyashegoPisma(convertToSqlDate(getCellValueAsDate(row.getCell(12))));
            appius.setNomerIshodyashegoPisma(getCellValueAsString(row.getCell(13)));
            appius.setWebSsilka(getCellValueAsString(row.getCell(14)));
            appius.setStadiaProektirovaniya(getCellValueAsString(row.getCell(15)));
            appius.setVidDocumenta(getCellValueAsString(row.getCell(16)));
            appius.setDataVpr(convertToSqlDate(getCellValueAsDate(row.getCell(17))));
            appius.setShifrN(getCellValueAsString(row.getCell(18)));
            appius.setRevN(getCellValueAsString(row.getCell(19)));
            appius.setProjectString(getCellValueAsString(row.getCell(20)));

            appiusRepository.save(appius);
        }
        workbook.close();
    }

    private void clearAndResetTable() {
        jdbcTemplate.execute("TRUNCATE TABLE appius RESTART IDENTITY CASCADE;");
    }
    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return null;
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return Double.toString(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return Boolean.toString(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return null;
        }
    }

    private java.util.Date getCellValueAsDate(Cell cell) {
        if (cell == null) {
            return null;
        }
        if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
            return cell.getDateCellValue();
        }
        return null;
    }

    private Date convertToSqlDate(java.util.Date utilDate) {
        if (utilDate == null) {
            return null;
        }
        return new Date(utilDate.getTime());
    }
}
