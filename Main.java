import java.io.*;
import java.net.URI;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        String address = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png";
        String saveAs = "picture.png";

        ImageLoader task = new ImageLoader(address, saveAs);
        Thread thread = new Thread(task);
        thread.start();
        thread.join();
    }
}

class ImageLoader implements Runnable {
    private final String address;
    private final String saveAs;

    ImageLoader(String address, String saveAs) {
        this.address = address;
        this.saveAs = saveAs;
    }

    @Override
    public void run() {
        System.out.println("Скачиваем картинку...");
        try {
            URL url = URI.create(address).toURL();
            try (BufferedInputStream input = new BufferedInputStream(url.openStream());
                 BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(saveAs))) {
                int b;
                while ((b = input.read()) != -1) {
                    output.write(b);
                }
            }
            System.out.println("Готово, картинка сохранена в " + saveAs);
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
