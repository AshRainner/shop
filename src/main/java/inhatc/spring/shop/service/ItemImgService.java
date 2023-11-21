package inhatc.spring.shop.service;

import inhatc.spring.shop.entity.ItemImg;
import inhatc.spring.shop.repository.ItemImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemImgService {

    @Value("${itemImgLocation}")
    private String itemImgLocation;

    private final FileService fileService;

    private final ItemImgRepository itemImgRepository;

    public void saveItemImg(ItemImg itemImg, MultipartFile itemImgFile) throws IOException {
        String originalFileName = itemImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        if(!StringUtils.isEmpty(originalFileName)){
            imgName = fileService.uploadFile(itemImgLocation,originalFileName,itemImgFile.getBytes());
            imgUrl = "/images/item/"+imgName;
        }
        itemImg.updateItemImg(imgName,originalFileName,imgUrl);
        itemImgRepository.save(itemImg);
    }

}
