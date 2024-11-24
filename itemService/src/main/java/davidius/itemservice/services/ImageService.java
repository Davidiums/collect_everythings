package davidius.itemservice.services;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    public List<String> getImagesForItem(long itemId) {
        return List.of("image1.jpg", "image2.jpg", "image3.jpg");
    }
}
