#使用方法：
##一.导入
###1.下载代码后，把framework依赖导入项目中
###2.app build.gradle中加入 
```
implementation project(path: ':framework')
```
###3.project build.gradle中加入 
```
mavenCentral()
maven { url 'https://jitpack.io' }
maven { url 'https://maven.google.com' }
```
###4.app build.gradle添加java1.8支持和databinding支持
```
//java1.8支持
compileOptions {
    sourceCompatibility JavaVersion.VERSION_1_8
    targetCompatibility JavaVersion.VERSION_1_8
}
//databinding支持
buildFeatures {
    dataBinding = true
}
```