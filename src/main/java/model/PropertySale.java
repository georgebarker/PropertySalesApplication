package model;

import org.joda.money.Money;
import org.joda.time.DateTime;

public class PropertySale {
    private String id;
    private Money price;
    private DateTime saleDate;
    private String postcode;
    private PropertyType propertyType;
    private boolean isNewBuild;
    private PropertyLeaseType propertyLeaseType;
    private String primaryAddressableObjectName;
    private String secondaryAddressableObjectName;
    private String street;
    private String locality;
    private String town;
    private String district;
    private String county;
    private PPDCategoryType category;
    private RecordStatus status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public DateTime getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(DateTime saleDate) {
        this.saleDate = saleDate;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    public boolean isNewBuild() {
        return isNewBuild;
    }

    public void setNewBuild(boolean newBuild) {
        isNewBuild = newBuild;
    }

    public PropertyLeaseType getPropertyLeaseType() {
        return propertyLeaseType;
    }

    public void setPropertyLeaseType(PropertyLeaseType propertyLeaseType) {
        this.propertyLeaseType = propertyLeaseType;
    }

    public String getPrimaryAddressableObjectName() {
        return primaryAddressableObjectName;
    }

    public void setPrimaryAddressableObjectName(String primaryAddressableObjectName) {
        this.primaryAddressableObjectName = primaryAddressableObjectName;
    }

    public String getSecondaryAddressableObjectName() {
        return secondaryAddressableObjectName;
    }

    public void setSecondaryAddressableObjectName(String secondaryAddressableObjectName) {
        this.secondaryAddressableObjectName = secondaryAddressableObjectName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public PPDCategoryType getCategory() {
        return category;
    }

    public void setCategory(PPDCategoryType category) {
        this.category = category;
    }

    public RecordStatus getStatus() {
        return status;
    }

    public void setStatus(RecordStatus status) {
        this.status = status;
    }
}
