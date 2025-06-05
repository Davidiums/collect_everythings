package davidius.paymentService.entities;

public enum PaymentProvider {
    STRIPE("Stripe")
//    ,PAYPAL("PayPal")
//    ,SQUARE("Square")
    ;

    private final String displayName;
    PaymentProvider(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return displayName;
    }
}
