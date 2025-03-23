package mk.ukim.finki.climatecognize.web;


import com.stripe.model.Charge;
import mk.ukim.finki.climatecognize.client.StripeClient;
import mk.ukim.finki.climatecognize.constants.StripeChargeParamKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin("*")
@RequestMapping("/api/payment")
public class PaymentGatewayController {

    private StripeClient stripeClient;
    @Autowired
    PaymentGatewayController(StripeClient stripeClient) {
        this.stripeClient = stripeClient;
    }
    @PostMapping("/charge")
    public Charge chargeCard(@RequestHeader(value= StripeChargeParamKeys.TOKEN) String token, @RequestHeader(value=StripeChargeParamKeys.AMOUNT) Double amount) throws Exception {
        return this.stripeClient.chargeNewCard(token, amount);
    }
}
