package com.example.fruitables.controllers.dashboard;

import com.example.fruitables.dtos.comment.CommentDto;
import com.example.fruitables.dtos.coupon.CouponDto;
import com.example.fruitables.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dashboard/comment")
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public String index(@RequestParam(defaultValue = "id") String sortField,
                        @RequestParam(defaultValue = "asc") String sortDir,
                        Model model){

        // Sıralama obyektini yaradırıq
        Sort sort = sortDir.equalsIgnoreCase("asc") ?
                Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        List<CommentDto> commentDtos = commentService.findAll(sort);

        model.addAttribute("comments", commentDtos);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        return "dashboard/comment/index";
    }


    @PostMapping("/delete/{id}")
    public String delete (@PathVariable Long id){
        boolean result = commentService.deleteComment(id);
        return "redirect:/dashboard/comment";
    }

}
