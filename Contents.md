# **Contents**:

* * *

## 1. Activity的学习

### 1. FirstAPP

- 实现了HelloWorld的相关程序，编辑信息，并另一个页面打印出来

### 2. SecondApp

- 实现了几个基本常用布局组件的演示

### 3. ThirdApp

- 完善了SecondApp
- 增加了跟随手指小球的应用展示

### 4. FourthApp

- 演示了_GridLayout_，_TableLayout_布局的演示例子
- 增加了_PopupWindow_创建对话风格的窗口

### 5. FifthApp

- 演示了简单的用户登录注册页面利用_ConnstraintLayout_布局实现

### 6. TextViewUI

- 文本框_TextView_和编辑框_EditView_的基本，以及各种效果的演示，并给出了界面友好的输入界面
- 按钮_Button_组件的基本演示
- 单选钮_RadioButton_、复选框_CheckBox_，以及状态开关按钮_ToggleButton_和开关_Switch_的的基本演示
- 时钟（包括模拟时钟_AnalogClock_和文本时钟_TextClock_）以及计时器_Chronometer_的演示

### 7. ImageUiTest

- 演示了图片浏览器，利用按钮控制图片的透明度
- 演示了图片按钮

### 8. AdapterViewUi和AdapterViewSet

- **AdapterViewUi** 主要演示了使用_ArrayAdapter_创建实现_ListView_
- **AdapterViewSet** 
  - **SimpleAdapterTest** 主要演示了使用_SimpleAdapter_创建实现_ListView_
  - **BaseAdapterTest** 主要演示了使用_BaseAdapter_实现不存储列表项的_ListView_
  - **AutoCompleteTextViewUi** 主要演示了自动完成文本框_AutoCompleteTextView_的基本演示，方便用户快速填写
  - **GridViewUi** 主要演示了利用_GridView_实现的带预览的图片浏览器
  - **ExpanddbleListView** 主要实现了_ExpanddbleListView_的基本演示
  - **SpinnerUi** 主要演示了利用_Spinner_实现下拉列表
  - **AdapterViewFlipperUi** 实现了利用_AdapterViewFlipper_实现的多个View的切换，实现自动播放的图片库
  - **StackViewUi** 实现了利用_StackView_实现堆叠效果切换多个View

### 9. ProgressBarSet

- **ProgressBarUi** 实现了环形和栏形的进度条的展示
- **SeekBarUi** 实现了拖动条_SeekBar_的基本演示，完成利用拖动条改变图片的透明度

### 10. ViewAnimator

- **ViewSwitcherUi**和**ViewSwitcherUiDemo** 实现动画效果的仿Android的Lancher的切换效果，以及实现的_ImageSwitcher_和_TextSwitcher_的图片和文本切换器
- **ViewFlipper** 实现利用\_ViewFlippe_r实现图片自动播放，具有一定的动画效果

### 11. ToastUi

- 实现了利用_Toast_实现带图片的消息框和文本提示框的演示

### 12. PickerUiSet

- **DateTimerPickerUi** 实现了利用_CalendarView_组件实现日历演示，利用_DatePicker_和_TimerPicker_实现用户选择日期和选择时间
- **NumberPickerUi** 实现了利用_NumberPicker_实现价格范围选择的演示
- **ScorllView** 实现了利用_ScrollView_和_HorizontalScrollView_的垂直滚动视图和水平滚动视图的演示
- **NotificationUi** 实现了状态栏通知的Ui展示，给出了“加薪通知”的应用展示

### 13. DialogBoxSet

- **AlertDialogUi** 实现了利用_AlertDialog_实现以下几种对话框：

  | 简单文本对话框 | 简单列表对话框 | 单选/多选列表对话框 | 自定义列表项 | 自定义View对话框 |
  | :------ | ------- | ---------- | ------ | ---------: |

- **PopupWindow** 实现了使用_PopupWindow_创建的弹出式对话框，使用_DatePickerDialog_和_TimerPickerDialog_实现日期和时间的弹出式选择对话框
- **ProgressDialogUi** 实现了利用_progressDialog_创建进度对话框，显示任务执行的进度

### 14. MenuSet

- **ContextMenuUi** 实现与菜单项关联的_Activity_的演示，能够通过菜单项打开另一个ActivityView窗口
- **MenuUiTest** 实现了基本的菜单项以及子菜单的演示
- **MenuResTestUi** 实现使用XML文件定义菜单项的演示

### 15. ActionBarSet

- **ActionBarTest** 实现了利用_AndroidManifest.xml_实现活动条的显示于隐藏
- **ActionViewUi** 实现使用_ActionBar_完成显示选项菜单项
- **TabNavTest** 展示利用_Android studio_官方示例的_ActionBar_实现**Tab**导航的演示
- **ActionBarSwipNavUi** 实现_TabWidget_ + _ViewPager_实现导航的演示

* * *

## 2、Event的学习

### 16. EventSet

- **EventListener_plane** 介绍了基本的事件监听器处理机制，采用事件监听器机制实现触摸屏控制飞机移动
- **EventListener_diffType** 介绍了实现事件监听器的几种形式，介绍了外部类作为事件监听器，Activity本身作为事件监听器类
- **EventCallback** 介绍了基于回调的事件传播机制，并给出了演示
- **Configurationtest** 介绍了响应系统设置的事件，_Configuration_类，给出了利用其读取系统配置信息的演示，同时重写了onConfigurationChanged方法的响应函数监听按键控制的屏幕方向更改的事件
- **HandlerTest** 介绍了Handler在线程中使用的步骤，实现了使用新线程计算质数的方法
- **AsyncTaskTest** 介绍了异步任务的执行机制，介绍了异步任务的使用方法以及3个使用步骤，给出了网页下载的简单演示

* * *

## 3. Activity & Fragment的学习

### 17. ActivitySet

- **LancherActivity** 介绍了使用_LacherActivity_实现启动多个_Activity_界面，包括使用_ExpandableListActivity_实现的可展开的列表项的_Activity_界面，以及使用_PreferenceActivity_结合_PreferenceFragment_实现参数设置界面
- **StartCloseActivity** 介绍了_Activity_的启动、切换、和关闭的简单演示
- **BundleTest** 介绍了_Bundle_在_Activity_之间交换数据的机制，演示了用第二个Activity处理注册信息
- **ActivityForResult** 介绍了启动其他_Activity_并返回结果**startActivityForResult(...)**方法，演示了用第二个_Activity_让用户选择信息的例程演示
- **ActivityLifeCycle** 介绍了Activity的整个生命周期，并用简单的例程结合Log窗口进行演示
- **LancherMode** 介绍了Activity的启动模式，给出了_singleTask_模式（Task内单例模式）第三种形式，当要启动的目标Activity已经存在，但没有位于栈顶的情况

### 18. FragmentSet

- **FragementTest** 介绍了_Fragment_的机制，并借助显示图书详情的例子给出了_Fragment_与所属_Activity_传递数据，并进行实时通信的机制
- **SeniorFragment** 介绍了开发兼顾屏幕分辨率的应用，通过_res.xml_来引用不同的布局资源，并在主UI线程中，使用标志位来区分
- **FragmentLifeCycle** 简单介绍了Activity的生命周期，利用日志输出窗口进行观察

* * *

## 4. Intent和IntentFilter的学习

### 19. IntentSet

- **ComponentTest** 简单介绍了如何通过显示_Intent_(指定了_Component_)来启动另一个_Activity_
- **ActionCategoryTest** 简单的介绍了_Action_和_Category_的使用方法，可以通过这两个字符串属性，实现打开对应的_Activity_等操作
- **ActionCategoryTest2** 给出了利用_Action_调用其他应用软件的示例：查看并获取联系人信息和电话
- **DataTypeTest** 介绍了_Data_和_Type_的例子演示，演示如何利用这两个属性其他其他应用
- **ActionData** 介绍了_Action_和_Data_的属性一起使用的场景

* * *

## 5. Android应用资源的学习

### 20. ResourceSet

- **StringColorDimen** 介绍了Android的应用资源_String_和_Color_和_Dimension_的使用方法
- **ArrayResTest** 介绍了Android的应用资源_Array_的使用方法
- **DrawableTest** 介绍了如何使用_Drawable_资源，包括_StateListDrawable_、_LayerDrawable_、_shapeDrawable_、_ClipDrawable_、_AnimationDrawable_的简单的使用方法
- **AttributeTest** 介绍了如何使用属性资源的使用，可以用户帮助用户开发自定义的View组件，同时增加了使用原始资源的应用示例

* * *

## 6. Android数据存储与IO

## 21. Io & SQLite

- **SharedPerferencesTest** 介绍了_SharedPerferences_的简单的使用方法，存储一些数据量较少的数据，采用key-value的形式
- **FlilexxInstream** 介绍了Android使用IO流体系存储读取数据的简单演示，主要使用了_openFileOutput_和_openFileInput_进行
- **SdcardTest** 介绍了Android使用Io流体系读取SD卡中的内容，主要需要使用_Environment类_以及获取相应的读取权限，同时给出了Sd卡下的文件浏览器示例
- **DBTest** 介绍了Android使用**SQLite**数据库的基本使用方法，包括不常用的静态打开方法和使用_SQLiteOpenHelper_方法进行数据库读写等操作
- **Dict** 介绍了Android使用**SQLiteHelper**类实现数据库的读写，实现生词本的添加与查询
- **GestureTest** 介绍了使用**GestureDetector**类的基本使用方法，并手势的基本操作方式与操作函数，给出了利用手势去缩放图片
- **GestureFlip** 介绍了使用**GestureDetector**实现翻页的效果

* * *

## 7. ContentProvider的学习

### 22. ContentProvider

- **FirstProvider/Resolver** 介绍了使用**ContentProvider**进行不同应用之间的数据共享的简单演示，此应用只是单存的机制演示，并未涉及到具体的数据存储与读取
- **DictProvider/Resolver** 介绍了使用_contentProvider_实现的生词本的不同应用之间的数据共享，第一个应用作为数据的提供端，另一个应用作为数据的使用端_contentResolver_
- **ContentObserverTest** 介绍了使用_ContentObserver_ 的工作机制，并采用该方式去实现了去监听系统短信的内容发送

* * *

## 9、Service & BroadcastReceiver的学习

### 23. ServiceSet

- **BindService** 简单介绍了**Service**的生命周期，演示了 通过Context的bindService()方法 来启动Service，使用该方法时，访问者和Service绑定在一起，访问者一旦退出，Service也就终止了
- **IntentService** 简单介绍了**IntentService**的特点，以及使用场合，演示了**IntentService**同**Service**的不同方式，尤其是有耗时任务的处理机制
- **ADILService/ADILServiceClient** 简单介绍了_ADILService_在跨进程数据调用时的工作机制，同普通本地Service的工作机制的区别，实现简单的数据传递的演示
- **ParcelableServer/ParcelableClient** 简单演示了利用序列化与反序列化接口_Parcelable_实现自定义的数据类型的跨进程数据调用
