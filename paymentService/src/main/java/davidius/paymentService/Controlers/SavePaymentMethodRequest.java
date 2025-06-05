package davidius.paymentService.Controlers;

import lombok.Data;

@Data
public class SavePaymentMethodRequest {
    private String paymentMethodId;
    private String customerId;
}
