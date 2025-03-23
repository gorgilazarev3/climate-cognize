package mk.ukim.finki.climatecognize.client;

import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import mk.ukim.finki.climatecognize.constants.StripeChargeParamKeys;
import mk.ukim.finki.climatecognize.constants.StripeConstants;
import mk.ukim.finki.climatecognize.constants.StripeCustomerParamKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;
@Component
public class StripeClient {

    private final Environment env;

    @Autowired
    StripeClient(Environment env) {
        this.env = env;
        Stripe.apiKey = this.env.getProperty(StripeConstants.STRIPE_SECRET_KEY_PROPERTY);
    }
    public Customer createCustomer(String token, String email) throws Exception {
        Map<String, Object> customerParams = new HashMap<String, Object>();
        customerParams.put(StripeCustomerParamKeys.EMAIL, email);
        customerParams.put(StripeCustomerParamKeys.SOURCE, token);
        return Customer.create(customerParams);
    }
    private Customer getCustomer(String id) throws Exception {
        return Customer.retrieve(id);
    }
    public Charge chargeNewCard(String token, double amount) throws Exception {
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put(StripeChargeParamKeys.AMOUNT, (int)(amount * 100));
        chargeParams.put(StripeChargeParamKeys.CURRENCY, StripeConstants.DEFAULT_CURRENCY);
        chargeParams.put(StripeChargeParamKeys.SOURCE, token);
        return Charge.create(chargeParams);
    }
    public Charge chargeCustomerCard(String customerId, int amount) throws Exception {
        String sourceCard = getCustomer(customerId).getDefaultSource();
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put(StripeChargeParamKeys.AMOUNT, amount);
        chargeParams.put(StripeChargeParamKeys.CURRENCY, StripeConstants.DEFAULT_CURRENCY);
        chargeParams.put(StripeChargeParamKeys.CUSTOMER, customerId);
        chargeParams.put(StripeChargeParamKeys.SOURCE, sourceCard);
        return Charge.create(chargeParams);
    }
}
