package com.keepgoing.keepserver.domain.returns.service;

import com.keepgoing.keepserver.domain.book.entity.enums.BookState;
import com.keepgoing.keepserver.domain.book.entity.Book;
import com.keepgoing.keepserver.domain.book.mapper.BookMapper;
import com.keepgoing.keepserver.domain.book.repository.BookRepository;
import com.keepgoing.keepserver.domain.device.entity.Device;
import com.keepgoing.keepserver.domain.device.entity.enums.DeviceStatus;
import com.keepgoing.keepserver.domain.device.mapper.DeviceMapper;
import com.keepgoing.keepserver.domain.device.repository.DeviceRepository;
import com.keepgoing.keepserver.domain.device.service.DeviceServiceImpl;
import com.keepgoing.keepserver.domain.user.entity.user.User;
import com.keepgoing.keepserver.global.common.BaseResponse;
import com.keepgoing.keepserver.global.exception.book.BookError;
import com.keepgoing.keepserver.global.exception.book.BookException;
import com.keepgoing.keepserver.global.exception.device.DeviceError;
import com.keepgoing.keepserver.global.exception.device.DeviceException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReturnServiceImpl implements ReturnService {
    private final DeviceServiceImpl deviceService;
    private final DeviceRepository deviceRepository;
    private final DeviceMapper deviceMapper;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BaseResponse returnDevice(String deviceName, String email) {
        User user = deviceService.findUserByEmail(email);
        Device device = findDeviceByName(deviceName);
        validateDeviceBorrower(device, user);
        returnDeviceFromUser(device);
        return new BaseResponse(HttpStatus.OK, "기기 반납 성공", deviceMapper.entityToDto(device));
    }

    @Override
    public BaseResponse returnBook(String nfcCode, String email) {
        User user = deviceService.findUserByEmail(email);
        Book book = findBookByNfcCodeContaining(nfcCode);
        validateBookBorrower(book, user);
        returnBookFromUser(book);
        return new BaseResponse(HttpStatus.OK, "도서 반납 성공", bookMapper.entityToDto(book));
    }

    private Device findDeviceByName(String deviceName) {
        return deviceRepository.findByDeviceName(deviceName)
                .orElseThrow(DeviceException::notFoundDevice);
    }

    private void validateDeviceBorrower(Device device, User user) {
        if (!device.getBorrower().equals(user)) {
            throw new DeviceException(DeviceError.INVALID_BORROWER);
        }
    }

    private void returnDeviceFromUser(Device device) {
        device.setBorrower(null);
        device.setStatus(DeviceStatus.AVAILABLE);
        device.setRentDate(null);
        deviceRepository.save(device);
    }

    private Book findBookByNfcCodeContaining(String nfcCode) {
        return bookRepository.findBookByNfcCodeContaining(nfcCode)
                .orElseThrow(BookException::notFoundBook);
    }

    private void validateBookBorrower(Book book, User user) {
        if (!book.getBorrower().equals(user)) {
            throw new BookException(BookError.INVALID_BORROWER);
        }
    }

    private void returnBookFromUser(Book book) {
        book.setBorrower(null);
        book.setState(BookState.AVAILABLE);
        book.setRentDate(null);
        bookRepository.save(book);
    }
}