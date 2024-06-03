import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import javax.imageio.ImageIO;

public class Decoder {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java Decoder <input_base64_path> <output_image_path>");
            return;
        }

        String inputBase64Path = args[0];
        String outputImagePath = args[1];

        try {
            // Read Base64 string from file
            byte[] base64Bytes = Files.readAllBytes(Paths.get(inputBase64Path));
            String base64String = new String(base64Bytes);

            // Decode Base64 string to byte array
            byte[] imageBytes = Base64.getDecoder().decode(base64String);

            // Convert byte array to BufferedImage
            BufferedImage image = convertByteArrayToImage(imageBytes);

            // Write image to file
            ImageIO.write(image, "png", new File(outputImagePath));

            System.out.println("Image decoded and saved to " + outputImagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BufferedImage convertByteArrayToImage(byte[] imageBytes) throws IOException {
        return ImageIO.read(new ByteArrayInputStream(imageBytes));
    }
}
