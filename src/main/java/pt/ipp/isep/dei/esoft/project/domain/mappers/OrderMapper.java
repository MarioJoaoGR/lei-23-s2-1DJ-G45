package pt.ipp.isep.dei.esoft.project.domain.mappers;

import pt.ipp.isep.dei.esoft.project.domain.Agent;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.AgentDTO;
import pt.ipp.isep.dei.esoft.project.domain.DTOs.OrderDTO;
import pt.ipp.isep.dei.esoft.project.domain.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * The OrderMapper class is responsible for mapping Order objects to OrderDTO objects and vice versa.
 */
public class OrderMapper {

    /**
     * Maps an Order object to an OrderDTO object.
     *
     * @param order the Order object to be mapped
     * @return the corresponding OrderDTO object
     */
    public OrderDTO toDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setClientEmail(order.getClientEmail());
        orderDTO.setValue(order.getValue());
        orderDTO.setDate(order.getDate());
        orderDTO.setState(order.getState());
        return orderDTO;
    }

    /**
     * Maps an OrderDTO object to an Order object.
     *
     * @param orderDTO the OrderDTO object to be mapped
     * @return the corresponding Order object
     */
    public Order fromDTO(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setClientEmail(orderDTO.getClientEmail());
        order.setValue(orderDTO.getValue());
        order.setDate(orderDTO.getDate());
        order.setState(orderDTO.getState());
        return order;
    }


    /**
     * Maps a list of Order objects to a list of OrderDTO objects.
     *
     * @param orderList the list of Order objects to be mapped
     * @return the corresponding list of OrderDTO objects
     */
    public List<OrderDTO> toDTO(List<Order> orderList) {
        List<OrderDTO> requestDtos = new ArrayList<>();
        for (Order order : orderList) {
            requestDtos.add(this.toDTO(order));
        }
        return requestDtos;
    }

    /**
     * Maps a list of OrderDTO objects to a list of Order objects.
     *
     * @param orderListDTO the list of OrderDTO objects to be mapped
     * @return the corresponding list of Order objects
     */
    public List<Order> fromDTO(List<OrderDTO> orderListDTO) {
        List<Order> request = new ArrayList<>();
        for (OrderDTO order : orderListDTO) {
            request.add(this.fromDTO(order));
        }
        return request;
    }

}
