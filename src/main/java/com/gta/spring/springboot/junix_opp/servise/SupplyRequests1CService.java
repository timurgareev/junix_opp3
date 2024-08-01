package com.gta.spring.springboot.junix_opp.servise;

import com.gta.spring.springboot.junix_opp.entity.SupplyRequests1C;
import com.gta.spring.springboot.junix_opp.repository.SupplyRequests1CRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;

@Service
@RequiredArgsConstructor
public class SupplyRequests1CService {

    private final SupplyRequests1CRepository supplyRequests1CRepository;
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

            SupplyRequests1C supplyRequest = new SupplyRequests1C();
//            supplyRequest.setDrawingsId((long) getCellValueAsNumeric(row.getCell(0)));
//            supplyRequest.setRevisionId((int) getCellValueAsNumeric(row.getCell(1)));
            supplyRequest.setDate(convertToSqlDate(getCellValueAsDate(row.getCell(3))));
            supplyRequest.setNumber((int) getCellValueAsNumeric(row.getCell(4)));
            supplyRequest.setRegNumber(getCellValueAsString(row.getCell(5)));
            supplyRequest.setRegDate(convertToSqlDate(getCellValueAsDate(row.getCell(6))));
            supplyRequest.setStatus(getCellValueAsString(row.getCell(7)));
            supplyRequest.setCondition(getCellValueAsString(row.getCell(8)));
            supplyRequest.setAuthor(getCellValueAsString(row.getCell(9)));
            supplyRequest.setComment(getCellValueAsString(row.getCell(10)));
            supplyRequest.setDrawingString(getCellValueAsString(row.getCell(11)));
            supplyRequest.setStatusSign(getCellValueAsString(row.getCell(12)));
            supplyRequest.setYear((int) getCellValueAsNumeric(row.getCell(13)));
            supplyRequest.setStock(getCellValueAsString(row.getCell(14)));
            supplyRequest.setProject(getCellValueAsString(row.getCell(15)));
            supplyRequest.setDatePlan(convertToSqlDate(getCellValueAsDate(row.getCell(16))));
//            supplyRequest.setSupplyRequestsId((long) getCellValueAsNumeric(row.getCell(17)));
//            supplyRequest.setProjectId((int) getCellValueAsNumeric(row.getCell(18)));

            supplyRequests1CRepository.save(supplyRequest);
        }
        workbook.close();
    }

    private void clearAndResetTable() {
        jdbcTemplate.execute("TRUNCATE TABLE supply_requests_1c RESTART IDENTITY CASCADE;");
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

    private double getCellValueAsNumeric(Cell cell) {
        if (cell == null) {
            return 0;
        }
        if (cell.getCellType() == CellType.NUMERIC) {
            return cell.getNumericCellValue();
        }
        return 0;
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
