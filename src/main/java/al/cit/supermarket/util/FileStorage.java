package al.cit.supermarket.util;

import al.cit.supermarket.exeption.FileStorageException;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public class FileStorage {

    private Path fileStorageLocation;


    public void setFileStorageLocation(String childPath) {
        String dir = "./public/resources/images" + childPath;
//        System.err.println(dir);
        this.fileStorageLocation = Paths.get(dir)
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {

            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String storeFile(MultipartFile file, String path) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        setFileStorageLocation(path);

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {

                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return this.fileStorageLocation.toString();
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

//    public String storeFile(InputStream is, String originalFilename, String newPath) {
//        // Normalize file name
//        String fileName = StringUtils.cleanPath(originalFilename);
//
//        setFileStorageLocation(newPath);
//
//        try {
//            // Check if the file's name contains invalid characters
//            if(fileName.contains("..")) {
//                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
//            }
//
//            // Copy file to the target location (Replacing existing file with the same name)
//            Path targetLocation = this.fileStorageLocation.resolve(fileName);
//            Files.copy(is, targetLocation, StandardCopyOption.REPLACE_EXISTING);
//
//            return this.fileStorageLocation.toString();
//        } catch (IOException ex) {
//            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
//        }
//    }

    public boolean deleteFile(String filePath, String fileType){

        Path path = Paths.get(StringUtils.cleanPath(filePath));
        System.out.println(path.toString());
        try {
            FileUtils.deleteDirectory(new File(filePath));
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
//            throw new AppException("Faild delteing " + fileType);
        }
    }

}
