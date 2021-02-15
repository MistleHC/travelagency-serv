package travelagency.controller.dto;

import java.util.Objects;

public class TourCreationDto {

    private String tourName;
    private String tourDescription;
    private String tourType;
    private String tourCountry;
    private long tourSize;
    private String tourHotel;
    private long tourPrice;

    private TourCreationDto() { }

    public static TourCreationDto.Builder newBuilder() {
        return new TourCreationDto().new Builder();
    }

    public class Builder {
        private  Builder() {

        }

        public TourCreationDto.Builder setTourName(String tourName) {
            TourCreationDto.this.tourName = tourName;

            return this;
        }

        public TourCreationDto.Builder setTourDescription(String tourDescription) {
            TourCreationDto.this.tourDescription = tourDescription;

            return this;
        }

        public TourCreationDto.Builder setTourType(String tourType) {
            TourCreationDto.this.tourType = tourType;

            return this;
        }

        public TourCreationDto.Builder setTourCountry(String tourCountry) {
            TourCreationDto.this.tourCountry = tourCountry;

            return this;
        }

        public TourCreationDto.Builder setTourSize(Long tourSize) {
            TourCreationDto.this.tourSize = tourSize;

            return this;
        }

        public TourCreationDto.Builder setTourHotel(String tourHotel) {
            TourCreationDto.this.tourHotel = tourHotel;

            return this;
        }

        public TourCreationDto.Builder setTourPrice(Long tourPrice) {
            TourCreationDto.this.tourPrice = tourPrice;

            return this;
        }

        public TourCreationDto build() {
            return TourCreationDto.this;
        }
    }

    public String getTourName() {
        return tourName;
    }

    public String getTourDescription() {
        return tourDescription;
    }

    public String getTourType() {
        return tourType;
    }

    public String getTourCountry() {
        return tourCountry;
    }

    public long getTourSize() {
        return tourSize;
    }

    public String getTourHotel() {
        return tourHotel;
    }

    public long getTourPrice() {
        return tourPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TourCreationDto that = (TourCreationDto) o;
        return tourSize == that.tourSize &&
                tourPrice == that.tourPrice &&
                Objects.equals(tourName, that.tourName) &&
                Objects.equals(tourDescription, that.tourDescription) &&
                Objects.equals(tourType, that.tourType) &&
                Objects.equals(tourCountry, that.tourCountry) &&
                Objects.equals(tourHotel, that.tourHotel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tourName, tourDescription, tourType, tourCountry, tourSize, tourHotel, tourPrice);
    }

    @Override
    public String toString() {
        return "TourCreationDto{" +
                "tourName='" + tourName + '\'' +
                ", tourDescription='" + tourDescription + '\'' +
                ", tourType='" + tourType + '\'' +
                ", tourCountry='" + tourCountry + '\'' +
                ", tourSize=" + tourSize +
                ", tourHotel='" + tourHotel + '\'' +
                ", tourPrice=" + tourPrice +
                '}';
    }

    public boolean checkEmptiness() {
        return tourName != null && !tourName.equals("")
                && tourDescription != null && !tourDescription.equals("")
                && tourType != null && !tourType.equals("")
                && tourCountry != null && !tourCountry.equals("") &&
                tourSize != 0
                && tourHotel != null && !tourHotel.equals("")
                && tourPrice != 0;
    }
}
