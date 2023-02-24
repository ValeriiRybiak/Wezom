package agency.wezom.evrika.models.repo;

import agency.wezom.evrika.models.Good;

import java.math.BigDecimal;

public class GoodsRepo {

    public static Good getMonitorSamsungLC27R500FHIXCI(){
        return new Good("Монитор Samsung LC27R500FHIXCI", new BigDecimal(83990));
    }

    public static Good getMonitorHPEurope22f2XN58AA(){
        return new Good("Монитор HP Europe 22f 2XN58AA", new BigDecimal(55990));
    }
}
