package com.keepgoing.keepserver.domain.teacher.service;

import com.keepgoing.keepserver.global.file.generate.ExcelGenerator;
import com.keepgoing.keepserver.global.file.generate.GenerateExcelTemplate;
import com.keepgoing.keepserver.global.file.processor.ExcelProcessor;
import com.keepgoing.keepserver.global.file.model.ExcelValidationResult;
import com.keepgoing.keepserver.domain.teacher.domain.entity.Item;
import com.keepgoing.keepserver.domain.teacher.domain.entity.enums.ItemStatus;
import com.keepgoing.keepserver.domain.teacher.domain.repository.ItemRepository;
import com.keepgoing.keepserver.domain.teacher.mapper.ItemMapper;
import com.keepgoing.keepserver.global.file.validator.ExcelValidationErrorResponse;
import com.keepgoing.keepserver.domain.teacher.payload.ItemExcelDto;
import com.keepgoing.keepserver.domain.teacher.payload.ItemExcelTemplateDto;
import com.keepgoing.keepserver.domain.teacher.payload.request.ItemRequest;
import com.keepgoing.keepserver.domain.teacher.payload.request.ItemStatusUpdateRequest;
import com.keepgoing.keepserver.domain.teacher.payload.request.ItemUpdateRequest;
import com.keepgoing.keepserver.domain.teacher.payload.response.ItemResponse;
import com.keepgoing.keepserver.domain.teacher.payload.response.ItemStatusCountResponse;
import com.keepgoing.keepserver.global.common.BaseResponse;
import com.keepgoing.keepserver.global.exception.teacher.ItemException;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ExcelProcessor<ItemExcelDto> excelProcessor;
    private final ItemMapper itemMapper;
    private final ItemRepository itemRepository;
    private final GenerateExcelTemplate generateExcelTemplate;

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
        List<ItemExcelDto> dtos = excelProcessor.parseValid(file);

        List<Item> items = dtos.stream()
                               .map(itemMapper::fromExcelDto)
                               .filter(item -> !validateBySerialNum(item.getSerialNumber()))
                               .toList();

        itemRepository.saveAll(items);

        return new BaseResponse(HttpStatus.CREATED, "유효한 항목 업로드 완료", items);
    }

    @Override
    public BaseResponse validateItemsFromExcel(MultipartFile file) {
        List<ExcelValidationResult<ItemExcelDto>> validationResults = excelProcessor.validate(file);

        List<ExcelValidationErrorResponse> errors =
                validationResults
                        .stream()
                        .filter(r -> !r.errors().isEmpty())
                        .map(r -> new ExcelValidationErrorResponse(r.rowNum(), r.errors()))
                        .toList();

        return new BaseResponse(HttpStatus.OK, "엑셀 검증 완료", errors);
    }

    @Override
    public ResponseEntity<Resource> downloadItemTemplateFile() {
        List<ItemExcelTemplateDto> sample = List.of(new ItemExcelTemplateDto());
        Workbook workbook = generateExcelTemplate.generateTemplate(sample);
        return ExcelGenerator.generate("dgsw-item-template.xlsx", workbook);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Resource> exportItemsToExcelFile() {
        List<Item> items = itemRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        Workbook workbook = generateExcelTemplate.generateTemplate(items);
        return ExcelGenerator.generate("dgsw-item-list.xlsx", workbook);
    }

    private ItemStatusCountResponse getItemStatusCount() {
        return new ItemStatusCountResponse(
                itemRepository.count(),
                itemRepository.countByStatus(ItemStatus.AVAILABLE),
                itemRepository.countByStatus(ItemStatus.IN_USE),
                itemRepository.countByStatus(ItemStatus.UNAVAILABLE)
        );
    }

    private boolean validateBySerialNum(String serialNum) {
        return itemRepository.existsBySerialNumber(serialNum);
    }
}