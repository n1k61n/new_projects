package com.example.fruitables.controllers.dashboard;

import com.example.fruitables.dtos.order.OrderDto;
import com.example.fruitables.enums.OrderStatus;
import com.example.fruitables.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dashboard/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public String index(@RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortDir, Model model){

        Sort sort = sortDir.equalsIgnoreCase("asc") ?
                Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        List<OrderDto> allOrderDtos = orderService.findAllOrders(sort);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("recentOrders", allOrderDtos);
        model.addAttribute("allStatuses", OrderStatus.values());
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        return "dashboard/order/index";
    }

    @PostMapping("/update-status")
    public String updateOrderStatus(@RequestParam Long orderId, @RequestParam String newStatus) {
        boolean result = orderService.updateOrderStatusById(orderId , newStatus);
        return "redirect:/dashboard/order?success=status_updated";
    }


//    @GetMapping("/create")
//    public String create(){
//        return "dashboard/order/create";
//    }
//    @GetMapping("/update")
//    public String update(){
//        return "dashboard/order/update";
//    }
//    @GetMapping("/delete")
//    public String delete(){
//        return "dashboard/order/delete";
//    }
//    @GetMapping("/details")
//    public String details(){
//        return "dashboard/order/details";
//    }

}

