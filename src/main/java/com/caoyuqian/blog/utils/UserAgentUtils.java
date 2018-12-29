package com.caoyuqian.blog.utils;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @author qian
 * @version V1.0
 * @Title: UserAgentUtils
 * @Package: com.caoyuqian.blog.utils
 * @Description: TOTO
 * @date 2018-12-29 09:58
 **/
public class UserAgentUtils {
    private static Logger logger = LoggerFactory.getLogger(UserAgentUtils.class);

    /**
     * 根据http获取userAgent信息
     *
     * @param request
     * @return
     */
    public static String getUserAgent(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        return userAgent;
    }

    /**
     * 根据request获取userAgent，然后解析出osVersion
     *
     * @param request
     * @return
     */
    public static String getOsVersion(HttpServletRequest request) {
        String userAgent = getUserAgent(request);
        return getOsVersion(userAgent);
    }

    /**
     * 根据userAgent解析出osVersion
     *
     * @param userAgent
     * @return
     */
    public static String getOsVersion(String userAgent) {
        String osVersion = "";
        if (StringUtils.isBlank(userAgent)) {
            return osVersion;
        }
        String[] strArr = userAgent.substring(userAgent.indexOf("(") + 1, userAgent.indexOf(")")).split(";");
        if (null == strArr || strArr.length == 0) {
            return osVersion;
        }
        osVersion = strArr[1];
        logger.info("osVersion is:{}", osVersion);
        return osVersion;
    }

    /**
     * 获取操作系统对象
     *
     * @param userAgent
     * @return
     */
    private static OperatingSystem getOperatingSystem(String userAgent) {
        UserAgent agent = UserAgent.parseUserAgentString(userAgent);
        OperatingSystem operatingSystem = agent.getOperatingSystem();
        return operatingSystem;
    }

    /**
     * 获取os：Windows/ios/Android
     *
     * @param request
     * @return
     */
    public static String getOs(HttpServletRequest request) {
        String userAgent = getUserAgent(request);
        return getOs(userAgent);
    }

    /**
     * 获取os：Windows/ios/Android
     *
     * @param userAgent
     * @return
     */
    public static String getOs(String userAgent) {
        OperatingSystem operatingSystem = getOperatingSystem(userAgent);
        String os = operatingSystem.getGroup().getName();
        logger.info("os is:{}", os);
        return os;
    }

    /**
     * 获取deviceType
     *
     * @param request
     * @return
     */
    public static String getDevicetype(HttpServletRequest request) {
        String userAgent = getUserAgent(request);
        return getDevicetype(userAgent);
    }

    /**
     * 获取deviceType
     *
     * @param userAgent
     * @return
     */
    public static String getDevicetype(String userAgent) {
        OperatingSystem operatingSystem = getOperatingSystem(userAgent);
        String deviceType = operatingSystem.getDeviceType().toString();
        logger.info("deviceType is:{}", deviceType);
        return deviceType;
    }

    /**
     * 获取操作系统的名字
     *
     * @param request
     * @return
     */
    public static String getOsName(HttpServletRequest request) {
        String userAgent = getUserAgent(request);
        return getOsName(userAgent);
    }

    /**
     * 获取操作系统的名字
     *
     * @param userAgent
     * @return
     */
    public static String getOsName(String userAgent) {
        OperatingSystem operatingSystem = getOperatingSystem(userAgent);
        String osName = operatingSystem.getName();
        logger.info("osName is:{}", osName);
        return osName;
    }

    /**
     * 获取device的生产厂家
     *
     * @param request
     */
    public static String getDeviceManufacturer(HttpServletRequest request) {
        String userAgent = getUserAgent(request);
        return getDeviceManufacturer(userAgent);
    }

    /**
     * 获取device的生产厂家
     *
     * @param userAgent
     */
    public static String getDeviceManufacturer(String userAgent) {
        OperatingSystem operatingSystem = getOperatingSystem(userAgent);
        String deviceManufacturer = operatingSystem.getManufacturer().toString();
        logger.info("deviceManufacturer is:{}", deviceManufacturer);
        return deviceManufacturer;
    }

    /**
     * 获取浏览器对象
     *
     * @param agent
     * @return
     */
    public static Browser getBrowser(String agent) {
        UserAgent userAgent = UserAgent.parseUserAgentString(agent);
        Browser browser = userAgent.getBrowser();
        return browser;
    }

    /**
     * 获取browser name
     *
     * @param request
     * @return
     */
    public static String getBorderName(HttpServletRequest request) {
        String userAgent = getUserAgent(request);
        return getBorderName(userAgent);
    }

    /**
     * 获取browser name
     *
     * @param userAgent
     * @return
     */
    public static String getBorderName(String userAgent) {
        Browser browser = getBrowser(userAgent);
        String borderName = browser.getName();
        logger.info("borderName is:{}", borderName);
        return borderName;
    }

    /**
     * 获取浏览器的类型
     *
     * @param request
     * @return
     */
    public static String getBorderType(HttpServletRequest request) {
        String userAgent = getUserAgent(request);
        return getBorderType(userAgent);
    }

    /**
     * 获取浏览器的类型
     *
     * @param userAgent
     * @return
     */
    public static String getBorderType(String userAgent) {
        Browser browser = getBrowser(userAgent);
        String borderType = browser.getBrowserType().getName();
        logger.info("borderType is:{}", borderType);
        return borderType;
    }

    /**
     * 获取浏览器组： CHROME、IE
     *
     * @param request
     * @return
     */
    public static String getBorderGroup(HttpServletRequest request) {
        String userAgent = getUserAgent(request);
        return getBorderGroup(userAgent);
    }

    /**
     * 获取浏览器组： CHROME、IE
     *
     * @param userAgent
     * @return
     */
    public static String getBorderGroup(String userAgent) {
        Browser browser = getBrowser(userAgent);
        String browerGroup = browser.getGroup().getName();
        logger.info("browerGroup is:{}", browerGroup);
        return browerGroup;
    }

    /**
     * 获取浏览器的生产厂商
     *
     * @param request
     * @return
     */
    public static String getBrowserManufacturer(HttpServletRequest request) {
        String userAgent = getUserAgent(request);
        return getBrowserManufacturer(userAgent);
    }

    /**
     * 获取浏览器的生产厂商
     *
     * @param userAgent
     * @return
     */
    public static String getBrowserManufacturer(String userAgent) {
        Browser browser = getBrowser(userAgent);
        String browserManufacturer = browser.getManufacturer().getName();
        logger.info("browserManufacturer is:{}", browserManufacturer);
        return browserManufacturer;
    }

    /**
     * 获取浏览器使用的渲染引擎
     *
     * @param request
     * @return
     */
    public static String getBorderRenderingEngine(HttpServletRequest request) {
        String userAgent = getUserAgent(request);
        return getBorderRenderingEngine(userAgent);
    }

    /**
     * 获取浏览器使用的渲染引擎
     *
     * @param userAgent
     * @return
     */
    public static String getBorderRenderingEngine(String userAgent) {
        Browser browser = getBrowser(userAgent);
        String renderingEngine = browser.getRenderingEngine().name();
        logger.info("renderingEngine is:{}", renderingEngine);
        return renderingEngine;
    }

    /**
     * 获取浏览器版本
     *
     * @param request
     * @return
     */
    public static String getBrowserVersion(HttpServletRequest request) {
        String userAgent = getUserAgent(request);
        return getBrowserVersion(userAgent);
    }

    /**
     * 获取浏览器版本
     *
     * @param userAgent
     * @return
     */
    public static String getBrowserVersion(String userAgent) {
        Browser browser = getBrowser(userAgent);
        String borderVersion = browser.getVersion(userAgent).toString();
        return borderVersion;
    }


}
