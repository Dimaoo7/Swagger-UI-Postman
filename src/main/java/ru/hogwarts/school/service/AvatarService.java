package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.StudentRepository;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarService {

    @Value("${path.to.avatars.folder}")
    private String avatarsDir;

    private final AvatarRepository avatarRepository;
    private final StudentRepository studentRepository;

    public AvatarService(AvatarRepository avatarRepository, StudentRepository studentRepository) {
        this.avatarRepository = avatarRepository;
        this.studentRepository = studentRepository;
    }

    public void uploadAvatarFromInternet(Long studentId, String path, String avatarName) throws IOException {
        File avatarFile = new File(path);
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        Student student = optionalStudent.orElse(null);

        if (avatarName == null) {
            throw new IllegalArgumentException();
        }


        Path filePath = Path.of(path);
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (
                InputStream is = Files.newInputStream(avatarFile.toPath());
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
        ) {
            bis.transferTo(bos);
        }

        Avatar avatar = findAvatar(studentId);
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(avatarFile.length());

        Path path1 = Paths.get(String.valueOf(filePath));
        String fileType = Files.probeContentType(path1);
        avatar.setMediaType(fileType);

        byte[] fileBytes = Files.readAllBytes(path1);
        avatar.setData(fileBytes);
        avatarRepository.save(avatar);

    }

    public void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent() && avatarFile == null) {
            throw new IllegalArgumentException();
        }
        Student student = optionalStudent.orElse(null);
        String originalFileName = avatarFile.getOriginalFilename();

        if (originalFileName == null) {
            throw new IllegalArgumentException();
        }

        Path filePath = Path.of(avatarsDir, studentId + getExtensions(originalFileName));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);//
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
        ) {
            bis.transferTo(bos);
        }
        Avatar avatar = findAvatar(studentId);
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());
        avatarRepository.save(avatar);
    }



    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    public Avatar findAvatar(Long studentId) {
        return avatarRepository.findAvatarByStudentId(studentId).orElse(new Avatar());
    }


    public Page<Avatar> listAvatars(PageRequest of) {
        return avatarRepository.findAll(of);
    }
}
