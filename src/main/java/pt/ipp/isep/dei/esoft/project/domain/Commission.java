package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.Serialization.Serialization;

import java.io.Serializable;

/**
 * The type Commission.
 */
public class Commission implements Serializable {

    private final String COMMISSION_TYPE_FIXED = "fixed";
    private final String COMMISSION_TYPE_PERCENTAGE = "percentage";
    private double commissionValue;
    private String commissionType;

    /**
     * Instantiates a new Commission.
     */
    public Commission() {

    }

    /**
     * Instantiates a new Commission.
     *
     * @param commissionValue the commission value
     * @param commissionType  the commission type
     */
    public Commission(double commissionValue, String commissionType) {
        this.commissionValue = commissionValue;
        this.commissionType = commissionType;
    }

    /**
     * Gets commission value.
     *
     * @return the commission value
     */
    public double getCommissionValue() {
        return commissionValue;
    }

    /**
     * Sets commission value.
     *
     * @param commissionValue the commission value
     */
    public void setCommissionValue(double commissionValue) {
        this.commissionValue = commissionValue;
    }

    /**
     * Gets commission type.
     *
     * @return the commission type
     */
    public String getCommissionType() {
        return commissionType;
    }

    /**
     * Sets commission type fixed.
     */
    public void setCommissionTypeFixed() {
        this.commissionType = COMMISSION_TYPE_FIXED;
    }

    /**
     * Sets commission type percentage.
     */
    public void setCommissionTypePercentage() {
        this.commissionType = COMMISSION_TYPE_PERCENTAGE;
    }

    /**
     * Gets commission type fixed.
     *
     * @return the commission type fixed
     */
    public String getCOMMISSION_TYPE_FIXED() {
        return COMMISSION_TYPE_FIXED;
    }

    /**
     * Gets commission type percentage.
     *
     * @return the commission type percentage
     */
    public String getCOMMISSION_TYPE_PERCENTAGE() {
        return COMMISSION_TYPE_PERCENTAGE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commission commission = (Commission) o;
        return commissionValue == commission.getCommissionValue() && commissionType.equalsIgnoreCase(commission.getCommissionType());

    }
}
