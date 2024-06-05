package com.pricing.Service;

import com.pricing.DOA.PricingServiceDOA;

public class PricingService {

    private PricingServiceDOA pricingServiceDOA;
    public Integer getRandomPricing(){
        return pricingServiceDOA.getRandomPricing();
    }

}
