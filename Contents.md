Contents:
=========
> **----------------2、Activity的学习----------------------**
### 1. FirstAPP 
* 实现了HelloWorld的相关程序，编辑信息，并另一个页面打印出来
### 2. SecondApp 
* 实现了几个基本常用布局组件的演示
### 3. ThirdApp 
* 完善了SecondApp
* 增加了跟随手指小球的应用展示
### 4. FourthApp
* 演示了*GridLayout*，*TableLayout*布局的演示例子
* 增加了*PopupWindow*创建对话风格的窗口
### 5. FifthApp
* 演示了简单的用户登录注册页面利用*ConnstraintLayout*布局实现
### 6. TextViewUI
* 文本框*TextView*和编辑框*EditView*的基本，以及各种效果的演示，并给出了界面友好的输入界面
* 按钮*Button*组件的基本演示
* 单选钮*RadioButton*、复选框*CheckBox*，以及状态开关按钮*ToggleButton*和开关*Switch*的的基本演示
* 时钟（包括模拟时钟*AnalogClock*和文本时钟*TextClock*）以及计时器*Chronometer*的演示
### 7. ImageUiTest
* 演示了图片浏览器，利用按钮控制图片的透明度
* 演示了图片按钮
### 8. AdapterViewUi和AdapterViewSet
* **AdapterViewUi** 主要演示了使用*ArrayAdapter*创建实现*ListView*
* **AdapterViewSet** 
  * **SimpleAdapterTest** 主要演示了使用*SimpleAdapter*创建实现*ListView*
  * **BaseAdapterTest** 主要演示了使用*BaseAdapter*实现不存储列表项的*ListView*
  * **AutoCompleteTextViewUi** 主要演示了自动完成文本框*AutoCompleteTextView*的基本演示，方便用户快速填写
  * **GridViewUi** 主要演示了利用*GridView*实现的带预览的图片浏览器
  * **ExpanddbleListView** 主要实现了*ExpanddbleListView*的基本演示
  * **SpinnerUi** 主要演示了利用*Spinner*实现下拉列表
  * **AdapterViewFlipperUi** 实现了利用*AdapterViewFlipper*实现的多个View的切换，实现自动播放的图片库
  * **StackViewUi** 实现了利用*StackView*实现堆叠效果切换多个View
### 9. ProgressBarSet
* **ProgressBarUi** 实现了环形和栏形的进度条的展示
* **SeekBarUi** 实现了拖动条*SeekBar*的基本演示，完成利用拖动条改变图片的透明度
### 10. ViewAnimator
* **ViewSwitcherUi**和**ViewSwitcherUiDemo** 实现动画效果的仿Android的Lancher的切换效果，以及实现的*ImageSwitcher*和*TextSwitcher*的图片和文本切换器
* **ViewFlipper** 实现利用*ViewFlippe*r实现图片自动播放，具有一定的动画效果
### 11. ToastUi
* 实现了利用*Toast*实现带图片的消息框和文本提示框的演示
### 12. PickerUiSet
* **DateTimerPickerUi** 实现了利用*CalendarView*组件实现日历演示，利用*DatePicker*和*TimerPicker*实现用户选择日期和选择时间
* **NumberPickerUi** 实现了利用*NumberPicker*实现价格范围选择的演示
* **ScorllView** 实现了利用*ScrollView*和*HorizontalScrollView*的垂直滚动视图和水平滚动视图的演示
* **NotificationUi** 实现了状态栏通知的Ui展示，给出了“加薪通知”的应用展示
### 13. DialogBoxSet
* **AlertDialogUi** 实现了利用*AlertDialog*实现以下几种对话框：

    简单文本对话框 | 简单列表对话框 | 单选/多选列表对话框 | 自定义列表项 | 自定义View对话框
    ------------- | ------------- | ----------------- | ----------- | --------------

* **PopupWindow** 实现了使用*PopupWindow*创建的弹出式对话框，使用*DatePickerDialog*和*TimerPickerDialog*实现日期和时间的弹出式选择对话框
* **ProgressDialogUi** 实现了利用*progressDialog*创建进度对话框，显示任务执行的进度
### 14. MenuSet
* **ContextMenuUi** 实现与菜单项关联的*Activity*的演示，能够通过菜单项打开另一个ActivityView窗口
* **MenuUiTest** 实现了基本的菜单项以及子菜单的演示
* **MenuResTestUi** 实现使用XML文件定义菜单项的演示
### 15. ActionBarSet
* **ActionBarTest** 实现了利用*AndroidManifest.xml*实现活动条的显示于隐藏
* **ActionViewUi** 实现使用*ActionBar*完成显示选项菜单项
* **TabNavTest** 展示利用*Android studio*官方示例的*ActionBar*实现**Tab**导航的演示
* **ActionBarSwipNavUi** 实现*TabWidget* + *ViewPager*实现导航的演示
****
> **---------------3、Event的学习-------------**
### 16. EventSet
* **EventListener_plane** 介绍了基本的事件监听器处理机制，采用事件监听器机制实现触摸屏控制飞机移动
* **EventListener_diffType** 介绍了实现事件监听器的几种形式，介绍了外部类作为事件监听器，Activity本身作为事件监听器类
* **EventCallback** 介绍了基于回调的事件传播机制，并给出了演示
* **Configurationtest** 介绍了响应系统设置的事件，*Configuration*类，给出了利用其读取系统配置信息的演示，同时重写了onConfigurationChanged方法的响应函数监听按键控制的屏幕方向更改的事件
* **HandlerTest** 介绍了Handler在线程中使用的步骤，实现了使用新线程计算质数的方法
* **AsyncTaskTest** 介绍了异步任务的执行机制，介绍了异步任务的使用方法以及3个使用步骤，给出了网页下载的简单演示
> **----------------4、Activity & Fragment的学习--------------**
### 17. ActivitySet
* **LancherActivity** 介绍了使用*LacherActivity*实现启动多个*Activity*界面，包括使用*ExpandableListActivity*实现的可展开的列表项的*Activity*界面，以及使用*PreferenceActivity*结合*PreferenceFragment*实现参数设置界面
* **StartCloseActivity** 介绍了*Activity*的启动、切换、和关闭的简单演示
* **BundleTest** 介绍了*Bundle*在*Activity*之间交换数据的机制，演示了用第二个Activity处理注册信息
* **ActivityForResult** 介绍了启动其他*Activity*并返回结果**startActivityForResult(...)**方法，演示了用第二个*Activity*让用户选择信息的例程演示
* **ActivityLifeCycle** 介绍了Activity的整个生命周期，并用简单的例程结合Log窗口进行演示
* **LancherMode** 介绍了Activity的启动模式，给出了*singleTask*模式（Task内单例模式）第三种形式，当要启动的目标Activity已经存在，但没有位于栈顶的情况
### 18. FragmentSet
* **FragementTest** 介绍了*Fragment*的机制，并借助显示图书详情的例子给出了*Fragment*与所属*Activity*传递数据，并进行实时通信的机制
* **SeniorFragment** 介绍了开发兼顾屏幕分辨率的应用，通过*res.xml*来引用不同的布局资源，并在主UI线程中，使用标志位来区分
* **FragmentLifeCycle** 简单介绍了Activity的生命周期，利用日志输出窗口进行观察
> **------------------5、Intent和IntentFilter的学习----------------**
### 19. IntentSet
* **ComponentTest** 简单介绍了如何通过显示*Intent*(指定了*Component*)来启动另一个*Activity*