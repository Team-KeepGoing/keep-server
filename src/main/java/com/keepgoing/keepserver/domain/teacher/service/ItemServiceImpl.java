package com.keepgoing.keepserver.domain.teacher.service;

import com.keepgoing.keepserver.domain.file.service.ExcelService;
import com.keepgoing.keepserver.domain.teacher.domain.entity.Item;
import com.keepgoing.keepserver.domain.teacher.domain.entity.enums.ItemStatus;
import com.keepgoing.keepserver.domain.teacher.domain.repository.ItemRepository;
import com.keepgoing.keepserver.domain.teacher.mapper.ItemMapper;
import com.keepgoing.keepserver.domain.teacher.payload.ItemExcelDto;
import com.keepgoing.keepserver.domain.teacher.payload.request.ItemRequest;
import com.keepgoing.keepserver.domain.teacher.payload.request.ItemStatusUpdateRequest;
import com.keepgoing.keepserver.domain.teacher.payload.request.ItemUpdateRequest;
import com.keepgoing.keepserver.domain.teacher.payload.response.ItemResponse;
import com.keepgoing.keepserver.domain.teacher.payload.response.ItemStatusCountResponse;
import com.keepgoing.keepserver.global.common.BaseResponse;
import com.keepgoing.keepserver.global.exception.BusinessException;
import com.keepgoing.keepserver.global.exception.teacher.ItemException;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final ExcelService excelService;

    private static final String[] ITEM_EXCEL_HEADERS = {
            "분류번호", "취득일자", "취득단가", "품명(기기명)", "세부 제품명",
            "기기 상태", "사용자", "대여 위치", "반납일", "대여일", "사용일수"
    };


    @Override
    @Transactional(readOnly = true)
    public BaseResponse findAll() {
        List<Item> items = itemRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        List<ItemResponse> dtos = itemMapper.convertItemsToDtos(items);
        return new BaseResponse(HttpStatus.OK, "Successful query of managed items list", dtos);
    }

    @Override
    @Transactional
    public BaseResponse createItem(ItemRequest request) {
        itemRepository.save(itemMapper.dtoToEntity(request));
        return new BaseResponse(HttpStatus.CREATED, "Successful creation of managed items");
    }

    @Override
    @Transactional(readOnly = true)
    public BaseResponse readItem(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(ItemException::itemNotFound);
        return new BaseResponse(HttpStatus.OK, "Successful query of managed item details", itemMapper.entityToDto(item));
    }

    @Override
    @Transactional
    public BaseResponse updateItem(Long id, ItemUpdateRequest request) {
        Item item = itemRepository.findById(id)
                .orElseThrow(ItemException::itemNotFound);

        item.updateItem(request);

        return new BaseResponse(HttpStatus.OK, "Item information has been updated successfully.");
    }


    @Override
    @Transactional(readOnly = true)
    public BaseResponse statusCount() {
        ItemStatusCountResponse response = getItemStatusCount();
        return new BaseResponse(HttpStatus.OK, "Item status count retrieved successfully", response);
    }

    @Override
    @Transactional
    public BaseResponse updateItemStatus(ItemStatusUpdateRequest request) {
        Item item = itemRepository.findById(request.itemId())
                .orElseThrow(ItemException::itemNotFound);

        item.updateStatus(request.status());

        return new BaseResponse(HttpStatus.OK, "Item status has been changed successfully");
    }

    @Override
    @Transactional
    public BaseResponse importItemsFromExcel(MultipartFile file) {
        try {
            List<Item> itemList = processExcelFile(file);
            itemRepository.saveAll(itemList);
            return new BaseResponse(HttpStatus.CREATED, "엑셀 업로딩 성공", itemList);
        }catch (BusinessException e){
            throw e;
        }catch (Exception e){
            return new BaseResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Resource> downloadItemTemplateFile() {
        return excelService.generateExcel(
                "item-template.xlsx",
                "item-list",
                ITEM_EXCEL_HEADERS,
                this::createSampleItemRow
        );
    }

    public ResponseEntity<Resource> exportItemsToExcelFile() {
        List<Item> items = itemRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

        return excelService.generateExcel(
                "item-template.xlsx",
                "item-list",
                ITEM_EXCEL_HEADERS,
                sheet -> {
                    int rowNum = 1;
                    for (Item item : items) {
                        createItemDataRow(sheet, rowNum++, item);
                    }
                }
        );
    }

    private void createItemDataRow(Sheet sheet, int rowNum, Item item) {
        Row dataRow = sheet.createRow(rowNum);
        dataRow.createCell(0).setCellValue(item.getSerialNumber());
        dataRow.createCell(1).setCellValue(item.getAcquisitionDate().toString());
        dataRow.createCell(2).setCellValue(item.getPrice());
        dataRow.createCell(3).setCellValue(item.getItem());
        dataRow.createCell(4).setCellValue(item.getDetails());
        dataRow.createCell(5).setCellValue(item.getStatus().toString());
        dataRow.createCell(6).setCellValue(item.getRentedBy() != null ? item.getRentedBy() : "");
        dataRow.createCell(7).setCellValue(item.getPlace() != null ? item.getPlace() : "");
        dataRow.createCell(8).setCellValue(item.getReturnDate() != null ? item.getReturnDate().toString() : "");
        dataRow.createCell(9).setCellValue(item.getRentalDate() != null ? item.getRentalDate().toString() : "");
        dataRow.createCell(10).setCellValue(item.getUsageDate() != null ? item.getUsageDate() : 0);
    }

    private void createSampleItemRow(Sheet sheet) {
        Row sampleRow = sheet.createRow(1);
        sampleRow.createCell(0).setCellValue("45211503-2119175");
        sampleRow.createCell(1).setCellValue("2025.7.7");
        sampleRow.createCell(2).setCellValue(2221805);
        sampleRow.createCell(3).setCellValue("노트북컴퓨터");
        sampleRow.createCell(4).setCellValue("LG GRAM15");
        sampleRow.createCell(5).setCellValue("IN_USE");
    }

        private List<Item> processExcelFile(MultipartFile file) {
        List<Item> itemList = new ArrayList<>();
        List<ItemExcelDto> excelData = excelService.parseExcelFile(file, ItemExcelDto.class);

        for (ItemExcelDto dto : excelData){
            itemList.add(itemMapper.fromExcelDto(dto));
        }
        return itemList;
    }


    private ItemStatusCountResponse getItemStatusCount() {
        return new ItemStatusCountResponse(
                itemRepository.count(),
                itemRepository.countByStatus(ItemStatus.AVAILABLE),
                itemRepository.countByStatus(ItemStatus.IN_USE),
                itemRepository.countByStatus(ItemStatus.UNAVAILABLE)
        );
    }

}
