package davidius.paymentService.entities;

public enum PaymentType {
    CREDIT_CARD("Credit Card"),
    PAYPAL("PayPal"),
    BANK_TRANSFER("Bank Transfer"),
    CRYPTOCURRENCY("Cryptocurrency");

    private final String displayName;

    PaymentType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
