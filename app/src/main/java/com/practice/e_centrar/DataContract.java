package com.practice.e_centrar;

import android.provider.BaseColumns;

public class  DataContract {

    public static final class Customer_Entry implements BaseColumns{

        public static final String TABLE_NAME = "Customer";
        public static final String COLUMN_Customer_ID = BaseColumns._ID;
        public static final String COLUMN_FirstName = "FirstName";
        public static final String COLUMN_LastName = "LastName";
        // public static final String COLUMN_SalesManager_ID = "SalesManager_ID";
        public static final String COLUMN_EnterpriseName = "EnterpriseName";
        public static final String COLUMN_JobPosition = "JobPosition";
        public static final String COLUMN_Adress = "Adress";
        public static final String COLUMN_Email = "Email";
        public static final String COLUMN_PhoneNo = "PhoneNo";
        public static final String COLUMN_MobileNo = "MobileNo";
        public static final String COLUMN_DateCreated = "DateCreated";
        public static final String COLUMN_CreatedBy = "CreatedBy";
        public static final String COLUMN_UpdatedDate= "UpdatedDate";
        public static final String COLUMN_UpdatedBy = "UpdatedBy";
        public static final String COLUMN_IsActive = "IsActive";
        public static final String COLUMN_PaymentMethod = "PaymentMethod";
    }

    public  static  final class Product_Entry implements  BaseColumns{

        public static final String PRODUCT_TABLE_NAME ="Product";
        public static final String COLUMN_PRODUCT_ID=BaseColumns._ID;
        public static final String COLUMN_PRODUCT_NAME="Product_Name";
        public static final String COLUMN_PRODUCT_IMAGE="Product_Image";
        public static final String COLUMN_PRODUCT_SKU="Product_SKU";
        public static final String COLUMN_PRODUCT_VARIANTS="Product_Variants";
        public static final String COLUMN_PRODUCT_PRODUCTTYPEID_FK="ProductTypeID_FK";
        public static final String COLUMN_PRODUCT_CATEGORYID_FK_="ProductCategoryID_FK";
        public static final String COLUMN_PRODUCT_ONHAND="Product_Onhand";
        public static final String COLUMN_PRODUCT_FULFILLED="Product_Fulfilled";
        public static final String COLUMN_PRODUCT_INSTOCK="Product_InStock";
        public static final String COLUMN_PRODUCT_CREATEDBY="Product_CreatedBy";
        public static final String COLUMN_PRODUCT_CREATEDDATE="Product_CreatedDate";
        public static final String COLUMN_PRODUCT_UPDATEDBY="Product_UpdatedtedBy";
        public static final String COLUMN_PRODUCT_UPDATEDDATE="Product_UpdatedtedDate";
        public static final String COLUMN_PRODUCT_ISACTIVE="IsActive";
    }

    public  static  final class PurchaseInvoice_Entry implements  BaseColumns{
        public static final String PURCHASE_ORDER_TABLE_NAME = "Purchase_Invoice";
        public static final String PURCHASE_INVOICE_ID = BaseColumns._ID;
        public static final String PURCHASE_SUPPLIER = "Supplier";
        public static final String PURCHASE_PAYMENT_DATE = "Payment_Date";
        public static final String PURCHASE_DUEDATE = "Due_Date";
        public static final String PURCHASE_BALANCE = "Balance";
        public static final String PURCHASE_PAIDAMOUNT = "PaidAmount";
        public static final String PURCHASE_TOTAL = "Total";
        public static final String PURCHASE_STATUS = "Status";
        public static final String PURCHASE_INVOICEDATE = "Invoice_Date";
        public static final String PURCHASE_REVENUE = "Revenue";
        public static final String PURCHASE_CREATEDBY = "CreatedBy";
        public static final String PURCHASE_CREATEDDATE = "CreatedDate";
        public static final String PURCHASE_UPDATEDBY = "UpdatedBy";
        public static final String PURCHASE_UPDATEDDATE = "UpdatedDate";
        public static final String PURCHASE_ISACTIVE = "IsActive";
    }

    public  static  final class GoodNotes_Entry implements  BaseColumns{
        public static final String GOODNOTES_TABLE_NAME="Good_Notes";
        public static final String GOODNOTES_ID=BaseColumns._ID;
        public static final String GOODNOTES_ORDERID_FK="OrderID_FK";
        public static final String GOODNOTES_ORDERSTATUS="Order_Status";
        public static final String GOODNOTES_DELIVERTO="Deliver_T0";
        public static final String GOODNOTES_WAREHOUSE="Warehouse";
        public static final String GOODNOTES_PRINTED="Printed";
        public static final String GOODNOTES_PICKED="Picked";
        public static final String GOODNOTES_PACKED="Packed";
        public static final String GOODNOTES_SHIPPED="Shipped";
        public static final String GOODNOTES_CREATEDBY="Created_By";
        public static final String GOODNOTES_CREATEDDATE="Created_Date";
        public static final String GOODNOTES_UPDATEDBY="Updated_By";
        public static final String GOODNOTES_UPDATEDDATE="Updated_Date";
        public static final String GOODNOTES_ISACTIVE="IsActive";
    }

    public static final class  Locations_entry implements BaseColumns {
        public static  String LOCATION_TABLE_NAME = "locations_track";
        public static  String LOCATION_ID = BaseColumns._ID;
        public static String LOCATION_LATITUDE = "latitude";
        public static String LOCATION_LONGITUDE = "longitude";
        public static  String LOCATION_ZOOM = "ZOOM";
        public static  String CURRENT_DATE = "col_current_date";

    }
}


