package com.practice.e_centrar;

public class Customer {

    private int Id;
    private String FK_SalesManager;
    private String FirstName;
    private String LastName;
    private String EnterpriseName;
    private String Email;
    private String Address;
    private String PhoneNo;
    private String MobileNo;
    private String CreatedBy;
    private String CreatedDate;
    private String IsActive;
    private String UpdatedDate;
    private String UpdatedBy;
    private String PaymentMethod;
    private String JobPosition;

    public Customer(int id, String FK_SalesManager, String firstName, String lastName, String enterpriseName, String email, String address, String phoneNo, String mobileNo, String createdBy, String createdDate, String isActive, String updatedDate, String updatedBy, String paymentMethod, String jobPosition) {
        Id = id;
        this.FK_SalesManager = FK_SalesManager;
        FirstName = firstName;
        LastName = lastName;
        EnterpriseName = enterpriseName;
        Email = email;
        Address = address;
        PhoneNo = phoneNo;
        MobileNo = mobileNo;
        CreatedBy = createdBy;
        CreatedDate = createdDate;
        IsActive = isActive;
        UpdatedDate = updatedDate;
        UpdatedBy = updatedBy;
        PaymentMethod = paymentMethod;
        JobPosition = jobPosition;
    }

    public Customer(String enterpriseName){
        this.EnterpriseName= enterpriseName;
    }


    public Customer(String firstName, String enterpriseName ,String jobPosition){

        FirstName = firstName;
        EnterpriseName = enterpriseName;
        JobPosition = jobPosition;
    }
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFK_SalesManager() {
        return FK_SalesManager;
    }

    public void setFK_SalesManager(String FK_SalesManager) {
        this.FK_SalesManager = FK_SalesManager;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEnterpriseName() {
        return EnterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        EnterpriseName = enterpriseName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public String getIsActive() {
        return IsActive;
    }

    public void setIsActive(String isActive) {
        IsActive = isActive;
    }

    public String getUpdatedDate() {
        return UpdatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        UpdatedDate = updatedDate;
    }

    public String getUpdatedBy() {
        return UpdatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        UpdatedBy = updatedBy;
    }

    public String getPaymentMethod() {
        return PaymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        PaymentMethod = paymentMethod;
    }

    public String getJobPosition() {
        return JobPosition;
    }

    public void setJobPosition(String jobPosition) {
        JobPosition = jobPosition;
    }
}
