package com.green.team4.controller.sb;

import com.green.team4.service.sb.ProductService;
import com.green.team4.vo.sb.ProductVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/sb/product/*")
@Controller
@RequiredArgsConstructor
@Slf4j
public class AdProductController {
    @Value("${com.green.upload.path}") //application.properties 변수
    private String uploadPath;

    private final ProductService productService;

    @GetMapping("/product")
    public void product(){

    }

    @GetMapping("/upload")
    public void uploadGet(){
        log.info("uploadGet.......");

    }
    @PostMapping("/upload")
    public String uploadPost(ProductVO vo, Model model, @RequestParam("uploadFiles") MultipartFile file){
        System.out.println("fileName: " + file.getName());
        System.out.println("OriginalFileName: "+file.getOriginalFilename());
        System.out.println("Resource: "+ file.getResource());
        vo.setPImage(file.getOriginalFilename());
        productService.insert(vo);
        System.out.println(vo);
        return "redirect:/sb/product/list?pno=1";
    }

    @GetMapping("/list")
    public void list(Model model, int pno){
        List<ProductVO> list = productService.getAll();
        model.addAttribute("list", list);
        model.addAttribute("getOne", productService.getOne(pno));
    }

    @GetMapping("/modify")
    public void modifyGet(Model model, @RequestParam("pno") int pno){
        model.addAttribute("getOne", productService.getOne(pno));
    }

    @PostMapping("/modify")
    public String modifyPost(ProductVO vo, Model model){
        log.info(vo.getPno()+"번 상품 수정");
        model.addAttribute("getOne", vo);
        productService.update(vo);
        return "redirect:/sb/product/modify?pno="+vo.getPno();
    }

    @PostMapping("/remove")
    public String ProductRemove(int pno){
        productService.delete(pno);
        log.info(pno+"번 상품 삭제");
        return "redirect:/sb/product/list?pno=1";
    }
}
