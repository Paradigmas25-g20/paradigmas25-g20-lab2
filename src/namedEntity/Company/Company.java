package namedEntity.Company;

import namedEntity.NamedEntity;

public class Company extends NamedEntity {
    String canonicalForm;
    Integer numberOfMembers;
    String companyType;
    protected static int frequency = 0;

    public Company (String name) {
        super(name);
    }

    public void setCanonicalForm(String canonicalForm) {
        this.canonicalForm = canonicalForm;
    }

    public String getCanonicalForm() {
        return canonicalForm;
    }

    public void setNumberOfMembers(Integer numberOfMembers) {
        this.numberOfMembers = numberOfMembers;
    }

    public Integer getNumberOfMembers() {
        return numberOfMembers;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getCompanyType() {
        return companyType;
    }

    public static int getFrequency() {
        return frequency;
    }
}

