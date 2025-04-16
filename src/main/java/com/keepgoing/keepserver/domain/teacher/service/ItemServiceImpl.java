package com.keepgoing.keepserver.domain.teacher.service;

import com.keepgoing.keepserver.domain.file.service.generate.ExcelGenerator;
import com.keepgoing.keepserver.domain.file.service.parser.ExcelParser;
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
import com.keepgoing.keepserver.global.exception.teacher.ItemException;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ExcelParser<ItemExcelDto> parser;
    private final ItemMapper itemMapper;
    private final ItemRepository itemRepository;

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
        List<ItemExcelDto> dtos = parser.parse(file);
        List<Item> items = dtos.stream().map(itemMapper::fromExcelDto).collect(Collectors.toList());
        itemRepository.saveAll(items);
        return new BaseResponse(HttpStatus.CREATED, "엑셀 업로딩 성공", items);
    }

    @Override
    public ResponseEntity<Resource> downloadItemTemplateFile() {
        return ExcelGenerator.generateExcel(
                "item-template.xlsx",
                "item-list",
                List.of("분류번호", "취득일자", "금액", "물품명", "세부정보", "상태", "대여자", "보관장소", "반납일자", "대여일자", "사용일수"),
                sheet -> {
                    Row sampleRow = sheet.createRow(1);
                    sampleRow.createCell(0).setCellValue("45211503-2119175");
                    sampleRow.createCell(1).setCellValue("YYYY.M.D");
                    sampleRow.createCell(2).setCellValue(2221805);
                    sampleRow.createCell(3).setCellValue("노트북컴퓨터");
                    sampleRow.createCell(4).setCellValue("LG GRAM15");
                    sampleRow.createCell(5).setCellValue("AVAILABLE/ UNAVAILABLE / IN_USE");
                    sampleRow.createCell(6).setCellValue("상태가 IN_USE 일 경우만 입력해주세요.");
                    sampleRow.createCell(7).setCellValue("사용자가 사용 시 (상태 = IN_USE), 개인휴대 / 이 외의 상황에선 기기 보관 위치를 입력해주세요");
                    sampleRow.createCell(8).setCellValue("상태가 IN_USE 일 경우만 입력해주세요.");
                    sampleRow.createCell(9).setCellValue("상태가 IN_USE 일 경우만 입력해주세요.");
                    sampleRow.createCell(10).setCellValue("상태가 IN_USE 일 경우만 입력해주세요. (함수 사용하셔도 됩니다. =I2-J2)");

                }
        );
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Resource> exportItemsToExcelFile() {
        List<Item> items = itemRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

        return ExcelGenerator.generateExcel(
                "dgsw-item-list.xlsx",
                "item-list",
                List.of("분류번호", "취득일자", "금액", "물품명", "세부정보", "상태", "대여자", "보관장소", "반납일자", "대여일자", "사용일수"),
                sheet -> {
                    int rowNum = 1;
                    for (Item item : items) {
                        Row row = sheet.createRow(rowNum++);
                        row.createCell(0).setCellValue(item.getSerialNumber());
                        row.createCell(1).setCellValue(item.getAcquisitionDate().toString());
                        row.createCell(2).setCellValue(item.getPrice());
                        row.createCell(3).setCellValue(item.getItem());
                        row.createCell(4).setCellValue(item.getDetails());
                        row.createCell(5).setCellValue(item.getStatus().toString());
                        row.createCell(6).setCellValue(item.getRentedBy() != null ? item.getRentedBy() : "");
                        row.createCell(7).setCellValue(item.getPlace() != null ? item.getPlace() : "");
                        row.createCell(8)
                           .setCellValue(item.getReturnDate() != null ? item.getReturnDate().toString() : "");
                        row.createCell(9)
                           .setCellValue(item.getRentalDate() != null ? item.getRentalDate().toString() : "");
                        row.createCell(10).setCellValue(item.getUsageDate() != null ? item.getUsageDate() : 0);
                    }
                }
        );
    }

    private ItemStatusCountResponse getItemStatusCount() {
        return new ItemStatusCountResponse(
                itemRepository.count(),
                itemRepository.countByStatus(ItemStatus.AVAILABLE),
                itemRepository.countByStatus(ItemStatus.IN_USE),
                itemRepository.countByStatus(ItemStatus.UNAVAILABLE)
        );
    }

    private void validateBySerialNum(String serialNum) {
        if (itemRepository.existsBySerialNumber(serialNum)) {
            throw ItemException.itemSerialNumExist();
        }
    }
}