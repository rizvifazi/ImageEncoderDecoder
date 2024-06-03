import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import javax.imageio.ImageIO;

public class Encoder {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java Encoder <input_image_path> <output_base64_path>");
            return;
        }

        String inputImagePath = args[0];
        String outputBase64Path = args[1];

        try {
            // Read the image
            BufferedImage image = ImageIO.read(new File(inputImagePath));

            // Convert image to byte array
            byte[] imageBytes = convertImageToByteArray(image);

            // Encode byte array to Base64 string
            String base64String = Base64.getEncoder().encodeToString(imageBytes);

            // Write Base64 string to file
            Files.write(Paths.get(outputBase64Path), base64String.getBytes());

            System.out.println("Image encoded and saved to " + outputBase64Path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static byte[] convertImageToByteArray(BufferedImage image) throws IOException {
        File tempFile = new File("temp_image_file");
        ImageIO.write(image, "png", tempFile);
        byte[] imageBytes = Files.readAllBytes(tempFile.toPath());
        tempFile.delete();
        return imageBytes;
    }
}
