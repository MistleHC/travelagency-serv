package travelagency.controller.validator;


import travelagency.controller.dto.TourCreationDto;
import travelagency.exceptions.ValidationException;

public class TourCreationValidator {
    private static final String EMPTY_PROPERTY_EXCEPTION_MESSAGE = "Tour creation field parameter '%s' must be provided";

    public static void validate(TourCreationDto tourCreationDto) throws ValidationException {
        validateNotEmptyProperty(tourCreationDto.getTourName(), "name");
        validateNotEmptyProperty(tourCreationDto.getTourDescription(), "description");
        validateNotEmptyProperty(tourCreationDto.getTourCountry(), "country");
        validateNotEmptyProperty(tourCreationDto.getTourHotel(), "hotel");
        validateNotEmptyProperty(tourCreationDto.getTourType(), "type");
    }

    private static void validateNotEmptyProperty(String value, String propertyName) {
        if (value == null ||  value.equals("")) {
            throw new ValidationException(String.format(EMPTY_PROPERTY_EXCEPTION_MESSAGE, propertyName));
        }
    }
}
