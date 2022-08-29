# gmsm-helper

BC库从1.59版本开始已经基本实现了国密算法（SM2、SM3、SM4），本项目是基于BC库做的一些功能的简单封装，也可以当成一个sample看，目前主要实现了以下几块功能：

1. SM2/SM3/SM4算法的简单封装
2. SM2 X509v3证书的签发
3. SM2 pfx证书的签发

### Maven

* Maven Center
    ```
    <dependency>
        <groupId>com.chain5j</groupId>
        <artifactId>gmsm-helper</artifactId>
        <version>Tag</version>
    </dependency>
    ```
### Gradle:

* Maven Center
    ```
    dependencies {
	        implementation 'com.chain5j:gmsm-helper:Tag'
	}
    ```

    
