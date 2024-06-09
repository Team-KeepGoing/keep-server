package com.keepgoing.keepserver.domain.student.service;

import com.keepgoing.keepserver.domain.student.repository.StudentRepository;
import com.keepgoing.keepserver.domain.student.repository.dto.ExcelDto;
import com.keepgoing.keepserver.global.common.BaseResponse;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ExcelService {
    private final StudentRepository studentRepository;
    public BaseResponse uploadExcel(MultipartFile file) throws IOException {
        List<ExcelDto> excelDtoList = new ArrayList<>();
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        if(!extension.equals("xlsx") && !extension.equals("xls")) {
            return new BaseResponse(HttpStatus.BAD_REQUEST, "엑셀 파일이 아닙니다");
        }
        Workbook workbook = null;

        if(extension.equals("xlsx")){
            workbook = new XSSFWorkbook(file.getInputStream());
        }else if (extension.equals("xls")){
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i<sheet.getPhysicalNumberOfRows();i++){
            Row row = sheet.getRow(i);

            ExcelDto excelDto = new ExcelDto();

            excelDto.setGrade((int) row.getCell(0).getNumericCellValue());
            excelDto.setGroup((int) row.getCell(1).getNumericCellValue());
            excelDto.setGroupNum((int) row.getCell(2).getNumericCellValue());
            excelDto.setName(row.getCell(3).getStringCellValue());
            excelDto.setAddress(row.getCell(4).getStringCellValue());

            excelDtoList.add(excelDto);
        }

        return new BaseResponse(HttpStatus.OK,"excel upload successful");
    }
}
