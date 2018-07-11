package com.mex.gt.mgr.common.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;
import com.google.common.collect.ImmutableList;
import com.google.common.io.Files;
import com.mex.gt.mgr.config.ApplicationProperties;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.FileNameMap;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by David
 * on 2017/4/25
 */
@Component
//@ConfigurationProperties(prefix = "application.oss")
public class OSSUtils {
    private Logger logger = LoggerFactory.getLogger(OSSUtils.class);
    private final ApplicationProperties applicationProperties;


    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private String prefix;
    private String domain;
    public final int partSize = 1024 * 100;//100k

    private OSSClient ossClient;

    @Autowired
    public OSSUtils(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
        setEndpoint(applicationProperties.getOss().getEndpoint());
        setAccessKeyId(applicationProperties.getOss().getAccessKeyId());
        setAccessKeySecret(applicationProperties.getOss().getAccessKeySecret());
        setBucketName(applicationProperties.getOss().getBucketName());
        setPrefix(applicationProperties.getOss().getPrefix());
        setDomain(applicationProperties.getOss().getDomain());
    }


    public static class Temp {
        private MultipartFile file;
        private File tempDir;
        private File tempFile;

        public Temp(MultipartFile file) {
            this.file = file;
        }

        public File getTempDir() {
            return tempDir;
        }

        public File getTempFile() {
            return tempFile;
        }

        public Temp invoke() {
            tempDir = Files.createTempDir();
            String originalFilename = file.getOriginalFilename();
            tempFile = new File(tempDir + File.separator + getFileNameWithoutExtend(originalFilename) + "-" + System.currentTimeMillis() + "." + getExtend(originalFilename));
            return this;
        }
    }

    @PostConstruct
    public void init() {
        ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }

    public String uploadWhithDatePath(File file) {
        String key = Stream.of(prefix, getDatePath(), file.getName()).collect(Collectors.joining("/"));
        return putObject(key, file);
    }

    /**
     * 下载文件 必须要close
     *
     * @param key
     * @return
     */
    public OSSObject getObject(String key) {
        OSSObject ossObject = ossClient.getObject(bucketName, key);
//        try {
//            ossObject.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return ossObject;
    }

    //简单上传
    public String putObject(String key, File file) {
        logger.info("upload to oss the key is : {}", key);
        ossClient.putObject(new PutObjectRequest(bucketName, key, file));
        return key;
    }

    //简单上传
    public String putObject(String key, InputStream is) {
        logger.info("upload to oss the key is : {}", key);
        ossClient.putObject(new PutObjectRequest(bucketName, key, is));
        return key;
    }

    //支持断点续传
    public String upload(String key, String path) {
        try {
            logger.info("upload to oss the key is : {}", key);
            UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName, key);
            // 待上传的本地文件
            uploadFileRequest.setUploadFile(path);
            // 设置并发下载数，默认1
            uploadFileRequest.setTaskNum(5);
            // 设置分片大小，默认100KB
            uploadFileRequest.setPartSize(partSize);
            // 开启断点续传，默认关闭
            uploadFileRequest.setEnableCheckpoint(true);

            UploadFileResult uploadResult = ossClient.uploadFile(uploadFileRequest);

            CompleteMultipartUploadResult multipartUploadResult = uploadResult.getMultipartUploadResult();

            logger.info("upload to oss end the eTag is : {}", multipartUploadResult.getETag());
        } catch (OSSException oe) {
            logger.error("upload to oss occur error", oe.getErrorCode());
        } catch (ClientException ce) {
            logger.error("upload to oss occur error", ce.getMessage());
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return key;
    }


    /**
     * https://help.aliyun.com/document_detail/44957.html?spm=5176.doc31850.6.949.QtvFqP
     *
     * @param waterImgKey 水印图片key
     * @param targetKey   目标key
     * @return 加过水印的key
     * @Param outputPath  输出文件的路径 如果没有 给null
     */
    public String getWaterMarkFileKey(String waterImgKey, String targetKey, String outputPath) {
        String waterMarkImg = Base64Utils.safeUrlBase64Encode((waterImgKey + "?x-oss-process=image/resize,p_10").getBytes());
        // 水印
        String style = "image/watermark,image_" + waterMarkImg + ",g_se,x_0,y_0";
        if (!StringUtils.isBlank(outputPath)) {
//            System.out.println(style);
            GetObjectRequest request = new GetObjectRequest(bucketName, targetKey);
            request.setProcess(style);
            ossClient.getObject(request, new File(outputPath));
        }
        return targetKey + "?x-oss-process=" + style;
    }

    public String getDatePath() {
        SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
        return formater.format(new Date());
    }

    public void deleteObjects(List<String> keys) {
        DeleteObjectsResult deleteObjectsResult = ossClient.deleteObjects(new DeleteObjectsRequest(bucketName).withKeys(keys));
        List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
    }

    /**
     * 生成UUID的文件名
     *
     * @param multipartFile
     * @return
     */
    public String getFileName(MultipartFile multipartFile) {
        String fileName = "";
        if (!multipartFile.isEmpty()) {
            String extend = getExtend(multipartFile.getOriginalFilename());
            fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + extend;
        }
        return fileName;
    }

    public File getTempFile(MultipartFile multipartFile, int width, int height, Boolean persent) {

        File tmmFile = null;
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String path = "/upload";
            path = request.getSession().getServletContext().getRealPath(path);
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            if (!multipartFile.isEmpty()) {
                // 随机生成文件名
                String saveFileName = getFileName(multipartFile);
                BufferedImage image = ImageIO.read(multipartFile.getInputStream());
                tmmFile = new File(path + "/" + saveFileName);
                if (persent) {
                    multipartFile.transferTo(tmmFile);
                } else {
                    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
                    Graphics graphics = bufferedImage.createGraphics();
                    graphics.drawImage(image, 0, 0, width, height, null);
                    ImageIO.write(bufferedImage, getExtend(multipartFile.getOriginalFilename()), tmmFile);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tmmFile;

    }

    public File getTempFile(MultipartFile multipartFile, int width, int height) {

        File tmmFile = null;
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String path = "/upload";
            path = request.getSession().getServletContext().getRealPath(path);
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            if (!multipartFile.isEmpty()) {
                // 随机生成文件名
                String saveFileName = getFileName(multipartFile);
                BufferedImage image = ImageIO.read(multipartFile.getInputStream());
                tmmFile = new File(path + "/" + saveFileName);
                BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
                Graphics graphics = bufferedImage.createGraphics();
                graphics.drawImage(image, 0, 0, width, height, null);
                ImageIO.write(bufferedImage, getExtend(multipartFile.getOriginalFilename()), tmmFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tmmFile;
    }


    public File getTempFile(MultipartFile multipartFile) {
        File tmmFile = null;
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String path = "/upload";
            path = request.getSession().getServletContext().getRealPath(path);
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            if (!multipartFile.isEmpty()) {
                // 随机生成文件名
                String saveFileName = getFileName(multipartFile);
                tmmFile = new File(path + "/" + saveFileName);
                byte[] bytes = multipartFile.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(tmmFile));
                stream.write(bytes);
                stream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tmmFile;
    }

//    /**
//     * 生成UUID的文件名
//     * @param file
//     * @return
//     */
//    public  String getFileName(File file) {
//        String fileName = "";
//        if (file.exists()) {
//            String extend = getExtend(file);
//            fileName = UUID.randomUUID().toString() + "." + extend;
//        }
//        return fileName;
//    }


    /**
     * 获取图片全路径
     *
     * @return
     */
    public String getFullUrl(String url) {
        return domain + url;
    }

    /**
     * 获取文件后缀
     *
     * @param file
     * @return
     */
    public String getExtend(File file) {
        return getExtend(file.getName());
    }

    public static String getExtend(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    }


    public static String getFileNameWithoutExtend(String fileName) {
        return fileName.substring(0, fileName.lastIndexOf("."));
    }

    /**
     * 是否是图片
     *
     * @param extend
     * @return
     */
    public boolean isImg(String extend) {
        ImmutableList<String> list = ImmutableList.of("jpg", "jpeg", "bmp", "gif", "png", "tif");
        return list.contains(extend);
    }

    /**
     * 是否是图片
     *
     * @param inputStream
     * @return
     */
    public boolean isImage(InputStream inputStream) {
        boolean flag = false;
        try {
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            if (width != 0 && height != 0) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 是否是视频
     *
     * @param extend
     * @return
     */
    public boolean isVedio(String extend) {
        ImmutableList<String> list = ImmutableList.of("avi", "rmvb", "rm", "asf", "divx", "mpg", "mpeg", "mpe", "wmv", "mp4", "mkv", "vob");
        return list.contains(extend);
    }

    /**
     * 是否是pdf
     *
     * @param extend
     * @return
     */
    public boolean isPdf(String extend) {
        ImmutableList<String> list = ImmutableList.of("pdf");
        return list.contains(extend);
    }

    public String getPrivateUrl(String key) {
        // 设置URL过期时间为10年  3600l* 1000*24*365*10
        Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
        // 生成URL
        URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);
        if (url != null) {
            return url.toString();
        }
        return null;
    }

    public String getMimeType(String fileName) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String type = fileNameMap.getContentTypeFor(fileName);
        return type;
    }


    /**
     * 分块多线程上传 适用于大文件
     *
     * @param key
     * @param partFile
     * @return
     */
    public String multipartUploadObject(String key, File partFile) {
        ExecutorService executorSrvice = Executors.newFixedThreadPool(5);
        String tag = null;
        String uploadid = null;
        int j = 0;
        // 初始化一个OSSClient
        ListMultipartUploadsRequest lmur = new ListMultipartUploadsRequest(bucketName);

        // 获取Bucket内所有上传事件
        MultipartUploadListing listing = ossClient.listMultipartUploads(lmur);
        // 新建一个List保存每个分块上传后的ETag和PartNumber
        List<PartETag> partETags = new ArrayList<PartETag>();

        // 遍历所有上传事件  设置UploadId
        for (MultipartUpload multipartUpload : listing.getMultipartUploads()) {
            if (multipartUpload.getKey().equals(key)) {
                uploadid = multipartUpload.getUploadId();
                break;
            }
        }

        if (StringUtils.isEmpty(uploadid)) {
            // 开始Multipart Upload,InitiateMultipartUploadRequest 来指定上传Object的名字和所属Bucke
            InitiateMultipartUploadRequest initiateMultipartUploadRequest = new InitiateMultipartUploadRequest(bucketName, key);
            InitiateMultipartUploadResult initiateMultipartUploadResult = ossClient.initiateMultipartUpload(initiateMultipartUploadRequest);
            uploadid = initiateMultipartUploadResult.getUploadId();
        } else {
            ListPartsRequest listPartsRequest = new ListPartsRequest(bucketName, key, uploadid);
            //listParts 方法获取某个上传事件所有已上传的块
            PartListing partListing = ossClient.listParts(listPartsRequest);
            // 遍历所有Part
            for (PartSummary part : partListing.getParts()) {
                partETags.add(new PartETag(part.getPartNumber(), part.getETag()));
                j++;
            }
        }
        // 设置每块为 5M（最小支持5M）
//        final int partSize = 1024 * 1024 * 5;

        // 计算分块数目
        int partCount = (int) (partFile.length() / partSize);
        if (partFile.length() % partSize != 0) {
            partCount++;
        }
        try {
            for (int i = j; i < partCount; i++) {

                // 跳到每个分块的开头
                long skipBytes = partSize * i;
                // 计算每个分块的大小
                long size = partSize < partFile.length() - skipBytes ? partSize : partFile.length() - skipBytes;

                executorSrvice.execute(new PartUploader(partETags, key, partFile, skipBytes, size, i + 1, uploadid));
            }
            executorSrvice.shutdown();
            while (!executorSrvice.isTerminated()) {
                try {
                    executorSrvice.awaitTermination(5, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        CompleteMultipartUploadRequest completeMultipartUploadRequest = new CompleteMultipartUploadRequest(bucketName, key, uploadid, partETags);
        // 完成分块上传
        CompleteMultipartUploadResult completeMultipartUploadResult = ossClient.completeMultipartUpload(completeMultipartUploadRequest);
        // 打印Object的ETag（返回的ETag不是md5.具体是什么不详）
        tag = completeMultipartUploadResult.getETag();
        return tag;
    }


    private class PartUploader implements Runnable {

        private File localFile;
        private long startPos;

        private long partSize;
        private int partNumber;
        private String uploadId;
        private String key;
        List<PartETag> partETags;

        public PartUploader(List<PartETag> partETags, String key, File localFile, long startPos, long partSize, int partNumber, String uploadId) {
            this.partETags = partETags;
            this.key = key;
            this.localFile = localFile;
            this.startPos = startPos;
            this.partSize = partSize;
            this.partNumber = partNumber;
            this.uploadId = uploadId;
        }

        @Override
        public void run() {
            InputStream instream = null;
            try {
                instream = new FileInputStream(this.localFile);
                instream.skip(this.startPos);

                UploadPartRequest uploadPartRequest = new UploadPartRequest();
                uploadPartRequest.setBucketName(bucketName);
                uploadPartRequest.setKey(key);
                uploadPartRequest.setUploadId(this.uploadId);
                uploadPartRequest.setInputStream(instream);
                uploadPartRequest.setPartSize(this.partSize);
                uploadPartRequest.setPartNumber(this.partNumber);

                UploadPartResult uploadPartResult = ossClient.uploadPart(uploadPartRequest);
                logger.info("Part# {} done", this.partNumber);
                partETags.add(uploadPartResult.getPartETag());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (instream != null) {
                    try {
                        instream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public int getPartSize() {
        return partSize;
    }
}

