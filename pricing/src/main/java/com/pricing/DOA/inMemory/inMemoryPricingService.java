package com.pricing.DOA.inMemory;

import com.pricing.DOA.PricingServiceDOA;

public class inMemoryPricingService implements PricingServiceDOA {

    @Override
    public Integer getRandomPricing(){
        return 1;
    }

}
