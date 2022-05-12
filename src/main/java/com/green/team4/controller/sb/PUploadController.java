package com.green.team4.controller.sb;

import com.green.team4.vo.sb.UploadResultDTO;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@Log4j2
public class PUploadController {
    @Value("${com.green.upload.path}") //application.properties 변수
    private String uploadPath;

    private String makeFolder(){
        String str = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String folderPath = str.replace("/", File.separator);
        //폴더 생성
        File uploadFolder = new File(uploadPath, folderPath);
        if (uploadFolder.exists()==false) uploadFolder.mkdirs();

        return folderPath;
    }

    @PostMapping("/uploadAjax")
    public ResponseEntity<List<UploadResultDTO>> uploadFile(MultipartFile[] uploadFiles){

        System.out.println("파일 컨트롤러 들어오나" + uploadFiles);
        List<UploadResultDTO> resultDTOToList = new ArrayList<>();

        for(MultipartFile uploadFile: uploadFiles){
            if(uploadFile.getContentType().startsWith("image")==false){
                log.warn("이 파일은 이미지 형태가 아니에요");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN); //FORBIDDEN(금지)
            }
            //실제 파일 이름 IE나 Edge는 전체 경로가 들어오므로
            String originalName = uploadFile.getOriginalFilename();
            String fileName = originalName.substring(originalName.lastIndexOf("\\")+1);
            log.info("fileName: " + fileName);
            //날짜 폴더 생성
            String folderPath = makeFolder();
            //UUID
            String uuid = UUID.randomUUID().toString();
            //저장할 파일 이름 중간에 "_"를 이용해서 구분
            String saveName = uploadPath + File.separator+folderPath+File.separator+uuid+"_"+fileName;
            Path savePath = Paths.get(saveName);
            try{
                uploadFile.transferTo(savePath); //실제 이미지 저장
                //썸네일 생성
                String thumbnailSaveName = uploadPath + File.separator+folderPath + File.separator + "s_" + uuid + "_"+fileName;
                //썸네일 생성
                File thumbnailFile = new File(thumbnailSaveName);
                Thumbnailator.createThumbnail(savePath.toFile(), thumbnailFile, 100, 100);
                resultDTOToList.add(new UploadResultDTO(fileName, uuid, folderPath));

            } catch (IOException e){
                e.printStackTrace();;
            }
        }
        return new ResponseEntity<>(resultDTOToList, HttpStatus.OK);
    }
    @GetMapping("/display")
    public ResponseEntity<byte[]> getFile(String fileName){
        ResponseEntity<byte[]> result = null;
        try{
            String srcFileName = URLDecoder.decode(fileName, "UTF-8");
            log.info("fileName: " + fileName);
            File file = new File(uploadPath+File.separator+srcFileName);
            log.info("File: " + file);
            HttpHeaders header = new HttpHeaders();
            //MIME 타입 처리
            header.add("Content-Type", Files.probeContentType(file.toPath()));
            //파일 데이터 처리
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
        } catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @PostMapping("/removeFile")
    public ResponseEntity<Boolean> removeFile(String fileName){
        String srcFileName = null;
        try{
            srcFileName = URLDecoder.decode(fileName, "UTF-8");
            File file = new File(uploadPath+File.separator+srcFileName);
            boolean result = file.delete();
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
    }
}
