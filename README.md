### 使用方法
-----------------
## 一.导入
### 1.下载代码后，把framework依赖导入项目中
### 2.app build.gradle中加入 
```
implementation project(path: ':framework')
```
### 3.project build.gradle中加入 
```
mavenCentral()
maven { url 'https://jitpack.io' }
maven { url 'https://maven.google.com' }
```
### 4.app build.gradle添加java1.8支持和databinding支持
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
## 二、新建MyApplication
```
public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * 设置服务器地址集合
     *
     * @return 服务器地址集
     */
    @Override
    protected List<String> initBaseUrl() {
        List<String> urlList = new ArrayList<>();
        urlList.add("http://www.xxx.com");
        urlList.add("http://www.yyy.com");
        return urlList;
    }

}
```
*别忘了在AndroidManifest.xml中添加MyApplication*
## 三、创建新Activity或Fragment
### 1.新建网络数据操作接口
```
public interface IDemoNetworkSource {

}
```
### 2.新建网络数据操作实现类
```
public class DemoNetworkSource extends BaseNetworkSource implements IDemoNetworkSource {

}
```
### 3.新建网络数据仓库
```
public class DemoRepository extends BaseRepository implements IDemoNetworkSource {

    private DemoRepository() {
    }

    public static DemoRepository create() {
        return new DemoRepository();
    }

}
```
### 4.新建ViewModel操作类
```
public class DemoViewModel extends BaseViewModel<DemoRepository> {

    public DemoViewModel(DemoRepository repository) {
        super(repository);

    }

}
```
### 5.新建ViewModel简单工厂类
```
public class DemoModelFactory extends ViewModelProvider.NewInstanceFactory {

    /**
     * 简单工厂模式创建对象
     *
     * @return 对象
     */
    public static DemoModelFactory create() {
        return new DemoModelFactory();
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        DemoRepository demoRepository = DemoRepository.create()
                        .setNetworkSource(new DemoNetworkSource());

        return CastUtil.cast(new DemoViewModel(demoRepository));
    }
}
```
### 6.新建布局文件
```
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">

    <data>

        <import type="com.demo.mvvm.viewmodel.DemoViewModel" />

        <variable
            name="demoViewModel"
            type="DemoViewModel" />

    </data>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">

    </LinearLayout>

</layout>
```
### 7.新建Activity或Fragment
```
public class DemoActivity extends AppBaseActivity<ActivityDemoBinding, DemoViewModel> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_demo;
    }

    @Override
    protected DemoViewModel getViewModel() {
        return createViewModel(DemoViewModel.class, DemoModelFactory.create());
    }

    @Override
    protected SparseArray<Object> getVariableSparseArray() {
        SparseArray<Object> variableSA = new SparseArray<>();
        variableSA.put(BR.demoViewModel, mViewModel);
        return variableSA;
    }
}
```
*以上7步完成了一次Activity或Fragment的创建，另外，如果是创建Activity别忘了在AndroidManifest.xml中注册Activity*
