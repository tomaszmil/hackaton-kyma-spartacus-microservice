package purchase.data;

public class OrderData {
    private String userdId;
    private String productCode;
    private String productName;

    public String getUserdId() {
        return userdId;
    }

    public void setUserdId(final String userdId) {
        this.userdId = userdId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(final String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(final String productName) {
        this.productName = productName;
    }
}
