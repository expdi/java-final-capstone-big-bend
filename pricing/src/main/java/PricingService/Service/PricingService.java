package PricingService.Service;

import PricingService.DOA.PricingServiceDOA;

public class PricingService {

    private PricingServiceDOA pricingServiceDOA;
    public Integer getRandomPricing(){
        return pricingServiceDOA.getRandomPricing();
    }

}
