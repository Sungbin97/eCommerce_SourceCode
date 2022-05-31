package com.green.team4.controller.sb;

import com.github.pagehelper.PageInfo;
import com.green.team4.mapper.sb.ProductInfoImgMapper;
import com.green.team4.mapper.sb.ProductImgMapper;
import com.green.team4.mapper.sb.ProductOptMapper;
import com.green.team4.paging.PagingEntity;
import com.green.team4.vo.sb.SearchVO;
import com.green.team4.service.sb.PagingService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    private final PagingService pagingService;

    private String makeFolder(){ // 파일 저장 폴더 만들기(탐색기)
        String str = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String folderPath = str.replace("\\", File.separator);
        // 폴더 생성
        File uploadFolder = new File(uploadPath, folderPath);
        if(uploadFolder.exists()==false) uploadFolder.mkdirs();

        return folderPath;
    }

    private String saveImg(MultipartFile img) throws IOException {
        String folderPath = makeFolder();
        String uuid = UUID.randomUUID().toString();
        String originalImg = img.getOriginalFilename();
        String imgFileName = originalImg.substring(originalImg.lastIndexOf("\\") + 1);
        String saveImgName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + imgFileName;
        //String saveImgUrl = File.separator + folderPath + File.separator + uuid + "_" + imgFileName;
        String saveImgUrl = "/" + folderPath + "/" + uuid + "_" + imgFileName;
        Path saveImgPath = Paths.get(saveImgName);
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
        vo.setPImage(saveImg(img));
        vo.setPInformation(saveImg(info));
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
    public String uploadPost(Integer pno, ProductImgVO imgVO, Product_optVO optVO, ProductInfoImgVO infoVO,
                             @RequestParam("opt1") String[] opt1,
                             @RequestParam("opt2") String[] opt2,
                             @RequestParam("colorOpt") String[] colors,
                             @RequestParam("uploadFilesImg") MultipartFile[] imgFiles,
                             @RequestParam("uploadFilesInfo") MultipartFile[] infoFiles) throws IOException {
        log.info("받아온 pno: " + pno);
        log.info("받아온 pOptionName: " + optVO.getPOptionName());
        log.info("받아온 pOptionName2: " + optVO.getPOptionName2());

        for (String o1 : opt1) System.out.println("받아온 옵션1: " + o1);
        for (String o2 : opt2) System.out.println("받아온 옵션2: " + o2);
        for (String c : colors) System.out.println("받아온 색상: " + c);

        //옵션 저장
        for (String o1 : opt1){
            optVO.setPOption(o1);
            for (String o2 : opt2){
                optVO.setPOption2(o2);
                for (String c : colors){
                    optVO.setPColor(c);
                    productOptMapper.insert(optVO);
                }
            }
        }
        //이미지 저장
        for (MultipartFile img : imgFiles) {
            imgVO.setPImage(img.getOriginalFilename());
            imgVO.setPImagePath(saveImg(img));
            productImgMapper.insert(imgVO);
        }
        for (MultipartFile info : infoFiles){
            infoVO.setPInfoPath(saveImg(info));
            infoVO.setPInformation(info.getOriginalFilename());
            productImgInfoMapper.insert(infoVO);
        }

        return "redirect:/sb/product/list?pno="+pno;
    }
    @GetMapping("/list")
    public void list(int pno, @ModelAttribute SearchVO search,
                     @RequestParam(required = false, defaultValue = "1") int pageNum, Model model)throws Exception{
        List<ProductVO> list = productService.getAll();
        model.addAttribute("list", list);
        model.addAttribute("getOne", productService.getOne(pno));

        //페이징
        PageInfo<PagingEntity> products = new PageInfo<>(pagingService.getProductList(pageNum, search), 10);
        model.addAttribute("products", products);
        model.addAttribute("search", search);
//        model.addAttribute("getOpt", productOptMapper.getOpt(pno));
    }

    @GetMapping("/modify")
    public void modifyGet(Model model, @RequestParam("pno") int pno){

        model.addAttribute("product", productService.getOne(pno));

        List<Product_optVO> optList = productOptMapper.getOpt(pno);

        List<String> opt1NameList = new ArrayList<>();
        List<String> opt1List = new ArrayList<>();
        List<String> opt2NameList = new ArrayList<>();
        List<String> opt2List = new ArrayList<>();
        List<String> colorList = new ArrayList<>();

        optList.forEach(opt -> {
            opt1NameList.add(opt.getPOptionName());
            opt1List.add(opt.getPOption());
            opt2NameList.add(opt.getPOptionName2());
            opt2List.add(opt.getPOption2());
            colorList.add(opt.getPColor());
        });

        List<String> opt1NameListN = opt1NameList.stream().distinct().collect(Collectors.toList());
        List<String> opt1ListN = opt1List.stream().distinct().collect(Collectors.toList());
        List<String> opt2NameListN = opt2NameList.stream().distinct().collect(Collectors.toList());
        List<String> opt2ListN = opt2List.stream().distinct().collect(Collectors.toList());
        List<String> colorListN = colorList.stream().distinct().collect(Collectors.toList());

        model.addAttribute("opt1Name", opt1NameListN);
        model.addAttribute("opt1List", opt1ListN);
        model.addAttribute("opt2Name", opt2NameListN);
        model.addAttribute("opt2List", opt2ListN);
        model.addAttribute("colorList", colorListN);
//        model.addAttribute("getOpt", productOptMapper.getOpt(pno));
    }

    @PostMapping("/modify")
    public String modifyPost(ProductVO vo, Product_optVO optVO,ProductImgVO imgVO, ProductInfoImgVO infoVO, Model model){
        log.info(vo.getPno()+"번 상품 수정");
        model.addAttribute("product", vo);
        productService.update(vo);
        productOptMapper.update(optVO);
        productImgMapper.update(imgVO);
        productImgInfoMapper.update(infoVO);
        return "redirect:/sb/product/modify?pno="+vo.getPno();
    }

    @PostMapping("/remove")
    public String ProductRemove(int pno){
        productService.delete(pno);
        productImgMapper.delete(pno);
        productOptMapper.delete(pno);
        productImgInfoMapper.delete(pno);
        log.info(pno+"번 상품 삭제");
        return "redirect:/sb/product/list?pno=1";
    }

}
