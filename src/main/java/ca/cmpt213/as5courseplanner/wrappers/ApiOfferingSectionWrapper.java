package ca.cmpt213.as5courseplanner.wrappers;

import ca.cmpt213.as5courseplanner.model.OfferingDetails;

import java.util.ArrayList;
import java.util.List;

public class ApiOfferingSectionWrapper {
    public String type;
    public int enrollmentCap;
    public int enrollmentTotal;

    public static ApiOfferingSectionWrapper makeFromSection(OfferingDetails offeringSection){
        ApiOfferingSectionWrapper wrapper = new ApiOfferingSectionWrapper();
        wrapper.type = offeringSection.getSection();
        wrapper.enrollmentCap = offeringSection.getEnrollmentCap();
        wrapper.enrollmentTotal = offeringSection.getEnrollmentTotal();
        return wrapper;
    }

    public static List<ApiOfferingSectionWrapper> makeFromSections(Iterable<OfferingDetails> offeringSections){
        List<ApiOfferingSectionWrapper> wrappers = new ArrayList<>();
        for(OfferingDetails offeringSection: offeringSections){
            ApiOfferingSectionWrapper wrapper = ApiOfferingSectionWrapper.makeFromSection(offeringSection);
            wrappers.add(wrapper);
        }
        return wrappers;
    }
}
