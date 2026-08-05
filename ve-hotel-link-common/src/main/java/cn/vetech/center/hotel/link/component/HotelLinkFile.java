package cn.vetech.center.hotel.link.component;

import cn.vetech.center.hotel.link.api.constant.SymbolConstant;
import cn.vetech.center.hotel.link.api.enums.FyEnum;
import cn.vetech.center.hotel.link.enums.HotelStaticFileTypeEnum;
import cn.vetech.charge.cloud.safe.wenjian.WenJian;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * 酒店文件
 *
 * @author luqs
 * @version v1.0
 **/
@Component
public class HotelLinkFile {
    /**
     * 文件根目录
     */
    @Value("${file_path}")
    private String fileBasePath;
    /**
     * 酒店link供应商文件目录
     */
    private static final String HOTEL_LINK_FILE_DIR_PATH = File.separatorChar + "hotel/link/supplier";
    /**
     * 系统temp目录
     */
    private static final String SYSTEM_TEMP_DIR_PATH = System.getProperty("java.io.tmpdir");

    /**
     * 创建供应商文件目录
     * <p>
     * <br/>如：/hotel/link/supplier/elong_31200801
     *
     * @param fyEnum 供应商枚举
     * @return String 若文件目录已存在，则直接返回目录；若目录创建失败，则返回null
     */
    public String createSupplierFileDir(FyEnum fyEnum) {
        String dirPath = createSupplierFileDirPath(fyEnum);
        return createFileDir(dirPath);
    }

    /**
     * 创建供应商静态文件目录
     * <p>
     * <br/>如：/hotel/link/supplier/elong_31200801/hotelInfo
     *
     * @param fyEnum             供应商枚举
     * @param staticFileTypeEnum 静态文件类型枚举
     * @return String 若文件目录已存在，则直接返回目录；若目录创建失败，则返回null
     */
    public String createSupplierStaticFileDir(FyEnum fyEnum, HotelStaticFileTypeEnum staticFileTypeEnum) {
        String dirPath = createSupplierStaticFileDirPath(fyEnum, staticFileTypeEnum);
        return createFileDir(dirPath);
    }

    /**
     * 创建供应商静态文件temp目录
     * <p><strong>注：如无特殊情况，请勿随意使用此目录！！！</strong>
     * <br/>如：temp/hotel/link/supplier/elong_31200801/hotelInfo
     *
     * @param fyEnum             供应商枚举
     * @param staticFileTypeEnum 静态文件类型枚举
     * @return String 若文件目录已存在，则直接返回目录；若目录创建失败，则返回null
     */
    public String createSupplierStaticFileTempDir(FyEnum fyEnum, HotelStaticFileTypeEnum staticFileTypeEnum) {
        String dirPath = createSupplierStaticFileTempDirPath(fyEnum, staticFileTypeEnum);
        return createFileDir(dirPath);
    }

    /**
     * 清空供应商静态文件目录
     *
     * @param fyEnum             供应商枚举
     * @param staticFileTypeEnum 静态文件类型枚举
     * @return boolean
     */
    public boolean clearSupplierStaticFileDir(FyEnum fyEnum, HotelStaticFileTypeEnum staticFileTypeEnum) {
        String dirPath = createSupplierStaticFileDirPath(fyEnum, staticFileTypeEnum);
        return removeFile(dirPath);
    }

    /**
     * 创建文件目录
     *
     * @param dirPath 目录
     * @return String 若文件目录已存在，则直接返回目录；若目录创建失败，则返回null
     */
    public String createFileDir(String dirPath) {
        WenJian file = new WenJian(dirPath);
        if (file.exists()) {
            return dirPath;
        }

        boolean mkFlag = file.mkdirs();
        if (mkFlag) {
            return dirPath;
        }
        return null;
    }

    /**
     * 删除文件/目录
     *
     * @param path 需要删除的目录或者文件路径
     * @return boolean
     */
    public boolean removeFile(String path) {
        WenJian file = new WenJian(path);
        if (file.isDirectory()) {
            String[] childFileArray = file.list();
            if (ArrayUtils.isEmpty(childFileArray)) {
                // 若目录为空，则删除
                return file.delete();
            }

            // 递归删除目录中的子目录下
            for (String childFile : childFileArray) {
                String childPath = path + File.separatorChar + childFile;
                boolean removeFlag = removeFile(childPath);
                if (!removeFlag) {
                    return false;
                }
            }
            return true;
        } else {
        return file.delete();
        }
    }

    /**
     * 生成供应商文件目录路径
     *
     * @param fyEnum 供应商枚举
     * @return String
     */
    private String createSupplierFileDirPath(FyEnum fyEnum) {
        return fileBasePath + HOTEL_LINK_FILE_DIR_PATH + File.separatorChar + fyEnum.getFyen() + SymbolConstant.UNDER_LINE + fyEnum.getFybh();
    }

    /**
     * 生成供应商文件temp目录路径
     *
     * @param fyEnum 供应商枚举
     * @return String
     */
    public String createSupplierFileTempDirPath(FyEnum fyEnum) {
        String tempDirPath = SYSTEM_TEMP_DIR_PATH;
        tempDirPath = tempDirPath.endsWith(String.valueOf(File.separatorChar)) ? tempDirPath.substring(0, tempDirPath.lastIndexOf(String.valueOf(File.separatorChar))) : tempDirPath;
        return tempDirPath + HOTEL_LINK_FILE_DIR_PATH + File.separatorChar + fyEnum.getFyen() + SymbolConstant.UNDER_LINE + fyEnum.getFybh();
    }

    /**
     * 生成供应商静态文件目录路径
     *
     * @param fyEnum             供应商枚举
     * @param staticFileTypeEnum 静态文件类型枚举
     * @return String
     */
    private String createSupplierStaticFileDirPath(FyEnum fyEnum, HotelStaticFileTypeEnum staticFileTypeEnum) {
        return createSupplierFileDirPath(fyEnum) + File.separatorChar + staticFileTypeEnum.getCode();
    }

    /**
     * 生成供应商静态文件temp目录路径
     *
     * @param fyEnum             供应商枚举
     * @param staticFileTypeEnum 静态文件类型枚举
     * @return String
     */
    private String createSupplierStaticFileTempDirPath(FyEnum fyEnum, HotelStaticFileTypeEnum staticFileTypeEnum) {
        return createSupplierFileTempDirPath(fyEnum) + File.separatorChar + staticFileTypeEnum.getCode();
    }
}
    