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
        String folderPath = str.replace("\\", File.separator);
        log.info("folderPath : "+folderPath);
        // 폴더 생성
        File uploadFolder = new File(uploadPath, folderPath);
        if(uploadFolder.exists()==false) uploadFolder.mkdirs();

        return folderPath;
    }

    @GetMapping("/upload")
    public void uploadGet(){
        log.info("uploadGet.......");
    }
    @PostMapping("/upload")
    public String uploadPost(ProductVO vo, Model model,
                             @RequestParam("pImg") MultipartFile img,
                             @RequestParam("pInfo") MultipartFile info) throws IOException {

        String folderPath = makeFolder();
        String uuid = UUID.randomUUID().toString();

        String originalImg = img.getOriginalFilename();
        log.info("originalImg : " +originalImg );
        String imgFileName = originalImg.substring(originalImg.lastIndexOf("\\") + 1);
        log.info("imgFileName : "+imgFileName);
        String saveImgName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + imgFileName;
        log.info("saveImgName : " +saveImgName);
//        String saveImgUrl = File.separator + folderPath + File.separator + uuid + "_" + imgFileName;
        String saveImgUrl = "/" + folderPath + "/" + uuid + "_" + imgFileName;
        log.info("saveImgUrl : " + saveImgUrl);
        Path saveImgPath = Paths.get(saveImgName);
        log.info("saveImgPath : " + saveImgPath);
        img.transferTo(saveImgPath);

        vo.setPImage(saveImgUrl);
        log.info("pImage"+vo.getPImage());
        String originalInfo = info.getOriginalFilename();
        String infoFileName = originalInfo.substring(originalInfo.lastIndexOf("\\") + 1);
        String saveInfoName = uploadPath + File.separator + folderPath +"/" + uuid + "_" + infoFileName;
//        String saveInfoUrl= File.separator + folderPath + File.separator + uuid + "_" + infoFileName;
        String saveInfoUrl= "/" + folderPath + "/" + uuid + "_" + infoFileName;
        log.info("saveInfoUrl : " + saveInfoUrl);
        Path saveInfoPath = Paths.get(saveInfoName);
        info.transferTo(saveInfoPath);

        vo.setPInformation(saveInfoUrl);
        log.info("imformation : "+vo.getPInformation());
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
//                             @RequestParam("opt1") String[] opt1,
//                             @RequestParam("opt2") String[] opt2,
//                             @RequestParam("color") String[] colors,
                             @RequestParam("uploadFilesImg") MultipartFile[] imgFiles,
                             @RequestParam("uploadFilesInfo") MultipartFile[] infoFiles) throws IOException {
        log.info("uploadOpt imgVO: " + imgVO);
        log.info("uploadOpt InfoImgVO: " + infoVO);
        log.info("uploadOpt optVO: " + optVO);
//        log.info("uploadOpt opt1: " + opt1);
//        log.info("uploadOpt opt2: " + opt2);
//        log.info("uploadOpt color: " + colors);
        //이미지 저장
        String folderPath = makeFolder();
        String uuid = UUID.randomUUID().toString();

        for (MultipartFile img : imgFiles) {
            String originalImg = img.getOriginalFilename();
            String fileName = originalImg.substring(originalImg.lastIndexOf("\\") + 1);
            String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;
            String saveUrl = File.separator + folderPath + File.separator + uuid + "_" + fileName;
            Path savePath = Paths.get(saveName);
            img.transferTo(savePath);
            imgVO.setPImage(img.getOriginalFilename());
            imgVO.setPImagePath(saveUrl);
            productImgMapper.insert(imgVO);
        }
        for (MultipartFile info : infoFiles){
            String originalInfo = info.getOriginalFilename();
            String infoFileName = originalInfo.substring(originalInfo.lastIndexOf("\\")+1);
            String infoSaveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" +infoFileName;
            String infoSaveUrl = File.separator + folderPath + File.separator + uuid + "_" +infoFileName;
            Path saveInfoPath = Paths.get(infoSaveName);
            info.transferTo(saveInfoPath);
            infoVO.setPInfoPath(infoSaveUrl);
            infoVO.setPInformation(info.getOriginalFilename());
            productImgInfoMapper.insert(infoVO);
        }

        //옵션 저장
        productOptMapper.insert(optVO);
//        for (String o1 : opt1){
//            optVO.setPOption(o1);
//            productOptMapper.insert(optVO);
//            for (String o2: opt2){
//                optVO.setPOption2(o2);
//                for (String c : colors){
//                    optVO.setPColor(c);
//                }
//            }
//        }

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
        log.info(pno+"번 상품 삭제");
        return "redirect:/sb/product/list?pno=1";
    }
}
