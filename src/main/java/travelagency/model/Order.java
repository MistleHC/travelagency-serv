package travelagency.model;

import java.util.Objects;

public class Order {

    private Long id;
    private Long customerId;
    private Tour tour;
    private Status status;

    private Order() { }

    public static Order.Builder newBuilder() {
        return new Order().new Builder();
    }

    public class Builder {
        private  Builder() {

        }

        public Order.Builder setId(Long id) {
            Order.this.id = id;

            return this;
        }

        public Order.Builder setCustomerId(Long customerId) {
            Order.this.customerId = customerId;

            return this;
        }

        public Order.Builder setTour(Tour tour) {
            Order.this.tour = tour;

            return this;
        }

        public Order.Builder setStatus(Status status) {
            Order.this.status = status;

            return this;
        }

        public Order build() {
            return Order.this;
        }
    }

    public Long getId() {
        return id;
    }

    public Long getCustomer() {
        return customerId;
    }

    public Tour getTour() {
        return tour;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(customerId, order.customerId) &&
                Objects.equals(tour, order.tour) &&
                Objects.equals(status, order.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, tour, status);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customerId +
                ", tour=" + tour +
                ", status=" + status +
                '}';
    }
}
