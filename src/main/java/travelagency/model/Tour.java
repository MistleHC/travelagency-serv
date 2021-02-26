package travelagency.model;

import travelagency.model.enums.UserRoles;

import java.util.Objects;
import java.util.Set;

public class Tour {

    private Long id;
    private String name;
    private String description;
    private String country;
    private Long peoples;
    private Long price;
    private boolean isHot;
    private HotelType hotelType;
    private TourType tourType;

    private Tour() { }

    public static Tour.Builder newBuilder() {
        return new Tour().new Builder();
    }

    public class Builder {
        private  Builder() {

        }

        public Tour.Builder setId(Long id) {
            Tour.this.id = id;

            return this;
        }

        public Tour.Builder setName(String name) {
            Tour.this.name = name;

            return this;
        }

        public Tour.Builder setDescription(String description) {
            Tour.this.description = description;

            return this;
        }

        public Tour.Builder setCountry(String country) {
            Tour.this.country = country;

            return this;
        }

        public Tour.Builder setPeoples(Long peoples) {
            Tour.this.peoples = peoples;

            return this;
        }

        public Tour.Builder setPrice(Long price) {
            Tour.this.price = price;

            return this;
        }

        public Tour.Builder setHot(boolean isHot) {
            Tour.this.isHot = isHot;

            return this;
        }

        public Tour.Builder setHotelType(HotelType hotelType) {
            Tour.this.hotelType = hotelType;

            return this;
        }

        public Tour.Builder setTourType(TourType tourType) {
            Tour.this.tourType = tourType;

            return this;
        }

        public Tour build() {
            return Tour.this;
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCountry() {
        return country;
    }

    public Long getPeoples() {
        return peoples;
    }

    public Long getPrice() {
        return price;
    }

    public boolean isHot() {
        return isHot;
    }

    public HotelType getHotelType() {
        return hotelType;
    }

    public TourType getTourType() {
        return tourType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tour tour = (Tour) o;
        return Objects.equals(name, tour.name) &&
                Objects.equals(description, tour.description) &&
                Objects.equals(country, tour.country) &&
                Objects.equals(peoples, tour.peoples) &&
                Objects.equals(price, tour.price) &&
                Objects.equals(hotelType.getName(), tour.hotelType.getName()) &&
                Objects.equals(tourType.getName(), tour.tourType.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, country, peoples, price, hotelType, tourType);
    }

    @Override
    public String toString() {
        return "Tour{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description size='" + description.length() + '\'' +
                ", country='" + country + '\'' +
                ", peoples=" + peoples +
                ", price=" + price +
                ", isHot=" + isHot +
                ", hotelType=" + hotelType +
                ", tourType=" + tourType +
                '}';
    }
}
