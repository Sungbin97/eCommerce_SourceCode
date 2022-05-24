package com.green.team4.controller.sb;

import com.green.team4.mapper.sb.ProductInfoImgMapper;
import com.green.team4.mapper.sb.ProductImgMapper;
import com.green.team4.mapper.sb.ProductOptMapper;
import com.green.team4.service.sb.ProductService;
import com.green.team4.vo.JH.Product_optVO;
import com.green.team4.vo.sb.ProductImgVO;
import com.green.team4.vo.sb.ProductInfoImgVO;
import com.green.team4.vo.sb.ProductVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@RequestMapping("/sb/product/*")
@Controller
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    @Value("${com.green.upload.path}") //application.properties 변수
    private String uploadPath;

    private final ProductService productService;
    private final ProductOptMapper productOptMapper;
    private final ProductImgMapper productImgMapper;
    private final ProductInfoImgMapper productImgInfoMapper;

    private String makeFolder(){ // 파일 저장 폴더 만들기(탐색기)
        String str = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String folderPath = str.replace("/", File.separator);

        // 폴더 생성
        File uploadFolder = new File(uploadPath, folderPath);
        if(uploadFolder.exists()==false) uploadFolder.mkdirs();

        return folderPath;
    }
    private String fileSave(MultipartFile img) throws IOException {
        String folderPath = makeFolder();
        String uuid = UUID.randomUUID().toString();

        String originalImg = img.getOriginalFilename();
        String fileName = originalImg.substring(originalImg.lastIndexOf("\\") + 1);
        String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;
        String saveImgUrl = File.separator + folderPath + File.separator + uuid + "_" + fileName;
        Path saveImgPath = Paths.get(saveName);
        img.transferTo(saveImgPath);
        return saveImgUrl;
    }

    @GetMapping("/upload")
    public void uploadGet(){
        log.info("uploadGet.......");
    }
    @PostMapping("/upload")
    public String uploadPost(ProductVO vo, Model model,
                             @RequestParam("pImg") MultipartFile img,
                             @RequestParam("pInfo") MultipartFile info) throws IOException {
        String productImg = fileSave(img); //파일 생성 및 저장 함수 호출
        String InfoImg = fileSave(info);
        vo.setPImage(productImg); //db에 저장
        vo.setPInformation(InfoImg);
        productService.insert(vo);
        ProductVO eve = productService.getEvePno();
        return "redirect:/sb/product/uploadOpt?pno="+eve.getPno();
    }

    @GetMapping("/uploadOpt")
    public void uploadOptGet(int pno, Model model){
        log.info("uploadOpt pno: "+pno);
        log.info("uploadOpt getOne: "+productService.getOne(pno));
        model.addAttribute("product", productService.getOne(pno));
    }

    @PostMapping("/uploadOpt")
    public String uploadPost(int pno, ProductImgVO imgVO, Product_optVO optVO, ProductInfoImgVO infoVO,
                             @RequestParam("opt1") String[] opt1,
                             @RequestParam("opt2") String[] opt2,
//                             @RequestParam("color") String[] colors,
                             @RequestParam("uploadFilesImg") MultipartFile[] imgFiles,
                             @RequestParam("uploadFilesInfo") MultipartFile[] infoFiles) throws IOException {
        log.info("uploadOpt imgVO: " + imgVO);
        log.info("uploadOpt InfoImgVO: " + infoVO);
        log.info("uploadOpt optVO: " + optVO);
        log.info("uploadOpt opt1: " + opt1);
        log.info("uploadOpt opt2: " + opt2);

        for (String o1 : opt1){
            System.out.println("opt1: "+o1);
        }
        for (String o2 : opt2){
            System.out.println("opt2: "+o2);
        }


        //이미지 저장
        for (MultipartFile img : imgFiles) {
            String productImg = fileSave(img);
            imgVO.setPImagePath(productImg);
            imgVO.setPImage(img.getOriginalFilename());
            productImgMapper.insert(imgVO);
        }

        for (MultipartFile info : infoFiles){
            String infoImg = fileSave(info);
            infoVO.setPInfoPath(infoImg);
            infoVO.setPInformation(info.getOriginalFilename());
            productImgInfoMapper.insert(infoVO);
        }

        //옵션 저장
        productOptMapper.insert(optVO);

        return "redirect:/sb/product/list?pno="+pno;
    }

    @GetMapping("/list")
    public void list(Model model, int pno){
        List<ProductVO> list = productService.getAll();
        model.addAttribute("list", list);
        model.addAttribute("getOne", productService.getOne(pno));
//        model.addAttribute("getOpt", productOptMapper.getOpt(pno));
    }

    @GetMapping("/modify")
    public void modifyGet(Model model, @RequestParam("pno") int pno){
        model.addAttribute("getOne", productService.getOne(pno));
//        model.addAttribute("getOpt", productOptMapper.getOpt(pno));
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
        productOptMapper.delete(pno);
        productImgInfoMapper.delete(pno);
        productImgMapper.delete(pno);
        log.info(pno+"번 상품 삭제");
        return "redirect:/sb/product/list?pno=1";
    }
}
