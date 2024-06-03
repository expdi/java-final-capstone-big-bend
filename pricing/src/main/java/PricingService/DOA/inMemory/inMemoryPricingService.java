package PricingService.DOA.inMemory;

import PricingService.DOA.PricingServiceDOA;

public class inMemoryPricingService implements PricingServiceDOA {

    @Override
    public Integer getRandomPricing(){
        return 1;
    }

}
