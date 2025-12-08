package com.poly.da.dto;

import java.time.LocalDate;

public class BookingRequest {


 private String hotelName;
 private Long selectedRoomId; // Hoặc String, tùy thuộc vào cách bạn xử lý ID
 private String fullName;
 private String email;
 private String phone;
 private LocalDate checkIn;
 private LocalDate checkOut;
 private int numGuests;

// Cần có Constructors, Getters và Setters
    
    // ------------------------------------
    // 1. Constructors
    // ------------------------------------

    // Constructor mặc định (cần thiết cho Spring MVC)
    public BookingRequest() {
    }

    // Constructor đầy đủ (tùy chọn)
    public BookingRequest(String hotelName, Long selectedRoomId, String fullName, String email, String phone, LocalDate checkIn, LocalDate checkOut, int numGuests) {
        this.hotelName = hotelName;
        this.selectedRoomId = selectedRoomId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.numGuests = numGuests;
    }

    // ------------------------------------
    // 2. Getters và Setters
    // ------------------------------------

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Long getSelectedRoomId() {
        return selectedRoomId;
    }

    public void setSelectedRoomId(Long selectedRoomId) {
        this.selectedRoomId = selectedRoomId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public int getNumGuests() {
        return numGuests;
    }

    public void setNumGuests(int numGuests) {
        this.numGuests = numGuests;
    }
}