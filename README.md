# wps-weboffice

[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.txt)

基于 WPS WebOffice 的 Java SDK，提供文档编辑和预览功能的业务分发封装。

## 项目简介

`wps-weboffice` 是一个用于集成 WPS WebOffice 服务的 Java SDK，帮助开发者快速在 Java 应用中实现文档的在线编辑和预览功能。该 SDK 提供了完整的回调处理机制，支持文件保存、用户信息管理、在线状态监控等功能。

## 功能特性

- ✅ **文档编辑和预览 URL 生成** - 快速生成 WPS WebOffice 的编辑和预览链接
- ✅ **完整的回调处理机制** - 支持文件保存、用户信息、在线状态、文件重命名等回调
- ✅ **多文件格式支持** - 支持 Word、Excel、PPT、PDF 等多种文档格式
- ✅ **Spring Boot 自动配置** - 开箱即用，零配置集成
- ✅ **多版本支持** - 同时支持 Spring Boot 2.x 和 Spring Boot 3.x
- ✅ **业务隔离** - 通过业务代码（bizCode）实现多业务场景的隔离管理
- ✅ **灵活扩展** - 基于 SPI 机制，支持自定义回调处理逻辑

## 快速开始

### Maven 依赖

#### Spring Boot 2.x

```xml
<dependency>
    <groupId>io.github.cookiegege</groupId>
    <artifactId>wps-weboffice-springboot-starter</artifactId>
    <version>1.0.1</version>
</dependency>
```

#### Spring Boot 3.x

```xml
<dependency>
    <groupId>io.github.cookiegege</groupId>
    <artifactId>wps-weboffice-springboot-v3-starter</artifactId>
    <version>1.0.1</version>
</dependency>
```

### 基本配置

在 `application.yml` 或 `application.properties` 中配置 WPS 相关参数：

```yaml
wps:
  endpoint: https://wwo.wps.cn   # WPS 服务端地址
  accessKey: your-access-key     # 访问密钥
  secretKey: your-secret-key     # 密钥
  clientId: your-client-id       # 客户端 ID
  keyPrefix: _w_third_           # 键前缀，默认为 _w_third_
  verify: true                   # 是否验证回调签名，默认为 true
  callback:
    enabled: true                # 是否启用回调，默认为 false
    prefixUrl: /                 # 回调 URL 前缀，默认为 /
```

### 使用示例

#### 1. 生成编辑/预览 URL

```java
@RestController
public class DocumentController {
    
    @Resource
    private WpsDocumentService wpsDocumentService;
    
    @GetMapping("/document/edit")
    public String getEditUrl() {
        Map<String, Object> ext = new HashMap<>();
        ext.put("customParam", "value");
        
        String editUrl = wpsDocumentService.getEditUrl(
            "bizCode",      // 业务代码
            "document.docx", // 文件名
            "file123",       // 文件 ID
            "user456",       // 用户 ID
            ext              // 扩展参数
        );
        
        return editUrl;
    }
    
    @GetMapping("/document/preview")
    public String getPreviewUrl() {
        WpsDocumentContext context = new WpsDocumentContext();
        context.setBizCode("bizCode");
        context.setFileName("document.docx");
        context.setFileId("file123");
        context.setUserId("user456");
        
        String previewUrl = wpsDocumentService.getPreviewUrl(context);
        return previewUrl;
    }
}
```

#### 2. 实现回调处理

创建一个实现 `WpsCallbackService` 接口的类，并使用 `@WpsCallbackProvider` 注解指定业务代码：

```java
@Service
@WpsCallbackProvider(bizCode = "your-biz-code")
public class MyWpsCallbackHandler implements WpsCallbackService {
    
    @Override
    public WpsFileInfo fileInfo(WpsCallbackContext context) {
        // 返回文件信息
        WpsFileInfo fileInfo = new WpsFileInfo();
        fileInfo.setName("document.docx");
        fileInfo.setSize(1024L);
        fileInfo.setCreator("user123");
        fileInfo.setModifier("user123");
        fileInfo.setCreateTime(System.currentTimeMillis());
        fileInfo.setModifyTime(System.currentTimeMillis());
        fileInfo.setDownloadUrl("https://example.com/download/document.docx");
        return fileInfo;
    }
    
    @Override
    public List<WpsUser> userInfo(WpsCallbackContext context, List<String> userIdList) {
        // 返回用户信息列表
        List<WpsUser> users = new ArrayList<>();
        for (String userId : userIdList) {
            WpsUser user = new WpsUser();
            user.setId(userId);
            user.setName("用户" + userId);
            user.setAvatar("https://example.com/avatar/" + userId);
            users.add(user);
        }
        return users;
    }
    
    @Override
    public WpsFile fileSave(WpsCallbackContext context, UploadedFile uploadedFile) {
        // 保存文件逻辑
        // 将 uploadedFile 保存到你的存储系统
        // ...
        
        WpsFile file = new WpsFile();
        file.setId(context.getFileId());
        file.setName(uploadedFile.getName());
        file.setSize(uploadedFile.getSize());
        file.setDownloadUrl("https://example.com/download/" + context.getFileId());
        return file;
    }
    
    @Override
    public void rename(WpsCallbackContext context, String newName) {
        // 文件重命名逻辑
        System.out.println("文件重命名为: " + newName);
    }
    
    @Override
    public void fileOnline(WpsCallbackContext context, List<String> userIdList) {
        // 文件在线状态通知
        System.out.println("在线用户: " + userIdList);
    }
    
    @Override
    public void fileOnNotify(WpsCallbackContext context, WpsNotifyBody notifyBody) {
        // 文件操作通知
        System.out.println("通知类型: " + notifyBody.getCmd());
    }
    
    @Override
    public WpsFile fileVersion(WpsCallbackContext context, String version) {
        // 获取指定版本的文件
        WpsFile file = new WpsFile();
        file.setId(context.getFileId());
        file.setVersion(version);
        return file;
    }
    
    @Override
    public List<WpsHistoryItem> fileHistory(WpsCallbackContext context, WpsHistoryBody historyBody) {
        // 返回文件历史记录
        List<WpsHistoryItem> history = new ArrayList<>();
        // 填充历史记录...
        return history;
    }
}
```

## 配置说明

### 核心配置项

| 配置项 | 类型 | 必填 | 默认值 | 说明 |
|--------|------|------|--------|------|
| `wps.endpoint` | String | 是 | - | WPS WebOffice 服务端地址 |
| `wps.accessKey` | String | 是 | - | WPS 访问密钥 |
| `wps.secretKey` | String | 是 | - | WPS 密钥 |
| `wps.clientId` | String | 是 | - | WPS 客户端 ID |
| `wps.keyPrefix` | String | 否 | `_w_third_` | 键前缀，用于标识第三方参数 |
| `wps.verify` | Boolean | 否 | `true` | 是否验证回调请求的签名 |
| `wps.callback.enabled` | Boolean | 否 | `false` | 是否启用回调功能 |
| `wps.callback.prefixUrl` | String | 否 | `/` | 回调 URL 的前缀路径 |

## 模块说明

本项目采用多模块 Maven 结构：

- **wps-weboffice-core** - 核心功能模块，包含文档服务、回调处理、客户端等核心功能
- **wps-weboffice-springboot-autoconfig** - Spring Boot 自动配置模块，提供自动配置和 Web MVC 集成
- **wps-weboffice-springboot-starter** - Spring Boot 2.x Starter，一键集成 Spring Boot 2.x 项目
- **wps-weboffice-springboot-v3-starter** - Spring Boot 3.x Starter，一键集成 Spring Boot 3.x 项目
- **wps-weboffice-dependencies** - 依赖管理模块，统一管理项目依赖版本
- **wps-weboffice-bom** - BOM（Bill of Materials）模块，提供依赖版本管理
- **wps-weboffice-demos** - 示例代码模块，包含使用示例和演示项目

## 支持的文件格式

| 文档类型 | 编辑支持格式 | 预览支持格式 |
|---------|------------|------------|
| **文字文档（Word）** | doc, dot, wps, wpt, docx, dotx, docm, dotm | doc, dot, wps, wpt, docx, dotx, docm, dotm, rtf, txt, mht, mhtml, htm, html, uot3 |
| **表格文档（Excel）** | xls, xlt, et, xlsx, xltx, xlsm, xltm | xls, xlt, et, xlsx, xltx, xlsm, xltm, csv, ett |
| **演示文档（PPT）** | ppt, pptx, pptm, ppsx, ppsm, pps, potx, potm, dpt, dps | ppt, pptx, pptm, ppsx, ppsm, pps, potx, potm, dpt, dps, pot |
| **版式文档（PDF）** | pdf | pdf, ofd |
| **图片** | - | jpeg, jpg, png, gif, bmp, tif, tiff, svg, psd |
| **压缩包** | - | tar, zip, 7z, jar, rar, gz |
| **Markdown** | - | md |
| **邮件** | - | eml |
| **代码文件** | - | c, cpp, java, js, css, lrc, h, asm, s, asp, bat, bas, prg, cmd, xml |
| **其他** | - | log, ini, inf, cdr, vsd, vsdx |

## 开发指南

### 运行示例

示例代码位于 `wps-weboffice-demos` 模块中，可以运行示例项目查看具体使用方式。

## 许可证

本项目采用 [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0.txt) 许可证。


## 贡献

欢迎提交 Issue 和 Pull Request！


---

**注意：** 使用本 SDK 前，请确保已获得 WPS WebOffice 服务的相关授权和配置信息。
