package travelagency.controller.dto;

import java.util.Objects;

public class TourFilterDto {

    private String country;
    private String hotel;
    private String lowerPrice;
    private String higherPrice;
    private String lowerGroup;

    private TourFilterDto() { }

    public static TourFilterDto.Builder newBuilder() {
        return new TourFilterDto().new Builder();
    }

    public class Builder {
        private  Builder() { }

        public TourFilterDto.Builder setCountry(String country) {
            TourFilterDto.this.country = country;

            return this;
        }

        public TourFilterDto.Builder setHotel(String hotel) {
            TourFilterDto.this.hotel = hotel;

            return this;
        }

        public TourFilterDto.Builder setLowerPrice(String lowerprice) {
            TourFilterDto.this.lowerPrice = lowerprice;

            return this;
        }

        public TourFilterDto.Builder setHigherPrice(String higherprice) {
            TourFilterDto.this.higherPrice = higherprice;

            return this;
        }

        public TourFilterDto.Builder setLowerGroup(String lowergroup) {
            TourFilterDto.this.lowerGroup = lowergroup;

            return this;
        }

        public TourFilterDto build() {
            return TourFilterDto.this;
        }
    }

    public String getCountry() {
        return country;
    }

    public String getHotel() {
        return hotel;
    }

    public String getLowerPrice() {
        return lowerPrice;
    }

    public String getHigherPrice() {
        return higherPrice;
    }

    public String getLowerGroup() {
        return lowerGroup;
    }

    public void changeDefaultValues() {
        if (country == null || country.equals("all")) {
            country = "";
        }
        if (hotel == null || hotel.equals("all")) {
            hotel = "";
        }
        if (lowerPrice == null) {
            lowerPrice = "";
        }
        if (higherPrice == null) {
            higherPrice = "";
        }
        if (lowerGroup == null) {
            lowerGroup = "";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TourFilterDto that = (TourFilterDto) o;
        return Objects.equals(country, that.country) &&
                Objects.equals(hotel, that.hotel) &&
                Objects.equals(lowerPrice, that.lowerPrice) &&
                Objects.equals(higherPrice, that.higherPrice) &&
                Objects.equals(lowerGroup, that.lowerGroup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, hotel, lowerPrice, higherPrice, lowerGroup);
    }

    @Override
    public String toString() {
        return "TourFilterDto{" +
                "country='" + country + '\'' +
                ", hotel='" + hotel + '\'' +
                ", lowerprice='" + lowerPrice + '\'' +
                ", higherprice='" + higherPrice + '\'' +
                ", lowergroup='" + lowerGroup + '\'' +
                '}';
    }
}
