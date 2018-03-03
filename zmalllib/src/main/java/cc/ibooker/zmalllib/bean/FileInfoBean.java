package cc.ibooker.zmalllib.bean;

/**
 * 文件相关信息
 * Created by 邹峰立 on 2018/3/3.
 */
public class FileInfoBean {
    private String fileName;// 文件名
    private String filePath;// 文件路径
    private long fileSize;// 文件大小 - 字节为单位

    public FileInfoBean() {
        super();
    }

    public FileInfoBean(String fileName, String filePath, long fileSize) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public String toString() {
        return "FileInfoBean{" +
                "fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileSize=" + fileSize +
                '}';
    }
}
