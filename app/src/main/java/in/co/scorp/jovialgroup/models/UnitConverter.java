package in.co.scorp.jovialgroup.models;

import java.math.BigDecimal;

/**
 * Created by root on 12/6/15.
 */
public class UnitConverter {

    public int id;
    public String unitName;
    public double unitValue;
    public double unitMultiplier;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public double getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(double unitValue) {
        this.unitValue = unitValue;
    }

    public double getUnitMultiplier() {
        return unitMultiplier;
    }

    public void setUnitMultiplier(double unitMultiplier) {
        this.unitMultiplier = unitMultiplier;
    }
}
