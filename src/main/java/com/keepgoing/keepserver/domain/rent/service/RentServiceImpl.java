package com.keepgoing.keepserver.domain.rent.service;

import com.keepgoing.keepserver.domain.book.consts.BookState;
import com.keepgoing.keepserver.domain.book.entity.Book;
import com.keepgoing.keepserver.domain.book.mapper.BookMapper;
import com.keepgoing.keepserver.domain.book.repository.BookRepository;
import com.keepgoing.keepserver.domain.device.entity.Device;
import com.keepgoing.keepserver.domain.device.enums.DeviceStatus;
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

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RentServiceImpl implements RentService{
    private final DeviceServiceImpl deviceService;
    private final DeviceRepository deviceRepository;
    private final DeviceMapper deviceMapper;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BaseResponse rentDevice(String deviceName, String email) {
        User user = deviceService.findUserByEmail(email);
        Device device = findDeviceByName(deviceName);
        validateDeviceAvailability(device);
        rentDeviceToUser(device, user);
        return new BaseResponse(HttpStatus.OK, "기기 대여 성공", deviceMapper.entityToDto(device));
    }

    @Override
    public BaseResponse rentBook(String nfcCode, String email) {
        User user = deviceService.findUserByEmail(email);
        Book book = findBookByNfcCodeContaining(nfcCode);
        validateBookAvailability(book);
        rentBookToUser(book, user);
        return new BaseResponse(HttpStatus.OK, "도서 대여 성공", bookMapper.entityToDto(book));
    }

    private Device findDeviceByName(String deviceName) {
        return deviceRepository.findByDeviceName(deviceName)
                .orElseThrow(DeviceException::notFoundDevice);
    }

    private void validateDeviceAvailability(Device device) {
        if (device.getStatus() != DeviceStatus.AVAILABLE) {
            throw new DeviceException(DeviceError.DEVICE_NOT_AVAILABLE);
        }
    }

    private void rentDeviceToUser(Device device, User user) {
        device.setBorrower(user);
        device.setStatus(DeviceStatus.RENTED);
        device.setRentDate(LocalDateTime.now());
        deviceRepository.save(device);
    }

    private Book findBookByNfcCodeContaining(String nfcCode) {
        return bookRepository.findBookByNfcCodeContaining(nfcCode)
                .orElseThrow(BookException::notFoundBook);
    }

    private void validateBookAvailability(Book book) {
        if (book.getState() != BookState.AVAILABLE) {
            throw new BookException(BookError.BOOK_NOT_AVAILABLE);
        }
    }

    private void rentBookToUser(Book book, User user) {
        book.setBorrower(user);
        book.setState(BookState.RENTED);
        book.setRentDate(LocalDateTime.now());
        bookRepository.save(book);
    }
}
