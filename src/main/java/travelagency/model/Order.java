package travelagency.model;

import java.util.Objects;

public class Order {

    private Long id;
    private User customer;
    private Tour tour;
    private Status status;
    private Long customerId;

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

        public Order.Builder setCustomer(User customer) {
            Order.this.customer = customer;

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

    public User getCustomer() {
        return customer;
    }

    public Tour getTour() {
        return tour;
    }

    public Status getStatus() {
        return status;
    }

    public Long getCustomerId() { return customerId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(customer, order.customer) &&
                Objects.equals(tour, order.tour) &&
                Objects.equals(status, order.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, tour, status);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", tour=" + tour +
                ", status=" + status +
                '}';
    }
}
