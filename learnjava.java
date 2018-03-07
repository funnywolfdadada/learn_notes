1 java基础
1.1 第一个java程序
1.1.1 代码
	Hello.java
	public class Hello {
		public static void main (String args[]) {
				System.out.println("Hello, world!");
		}
	}
1.1.2 编译
	javac Hello.java
1.1.3 执行
	java Hello

1.2 数据类型
1.2.1 基本数据类型
	|-----------------------------------------------------------------------|
	|数据类型    关键字    占用比特    取值范围                   缺省值    |
	|-----------------------------------------------------------------------|
	|布尔型      boolean   8           true、false                false     |
	|字节型	     byte      8           -128~127                   0         |
	|字符型	     char      16          0~65535                    '\u0'     |
	|短整型	     short     16          -32768~32767               0         |
	|整型	     int       32          -2147483648~2147483647     0         |
	|长整形	     long      64          -9.22E18~9.22E18           0         |
	|浮点型	     float     32          1.4013E-45~3.4028E+38      0.0f	|
	|双精度型    double    64          2.2255E-208~1.7977E+308    0.0d	|
	|-----------------------------------------------------------------------|
1.2.2 引用数据类型
	使用new来分配，不需要自己释放，把变量设为null即可
		int p[]=new int [10];
		p = null;
	1 数组
		int a[]=null;
		a = new int[10];
		int b[]=new int[10];
		int c[]={1,2,3,4,5};
	2 类
	3 接口
	4 null
1.2.3 数据类型转换
	1 自动转换
		数据不丢失的前提下可以自动转换，（整数默认是int，小数默认是double）
		int a = 30;
		long b = a;  // 自动转换，因为long的表示范围比int大
		float c = 3.1
		int d = (int)c; // 必须使用强制转换
	2 强制转换
	3 例子
		byte  b = 1;
		short s = 3;
		编译器会判断该值有无超过该变量的表示范围，如果超过就报错。

		short s = 1;
		s = s + 1; // 出错，因为对于byte，short的运算，为了保证精度，会自动转换为int类型

		s = (short)(s + 1);

		short s2 = 1;
		s = s + s2;  // 出错，理由同上
1.3 运算符、表达式、语句
	和C语言一样
	if, if...else..., if...else if...else, switch
	while, do...while, for
	break, continue
1.4 方法（函数）
1.4.1 格式
	public static 返回值类型 方法名称 (类型 参数1, 类型 参数2, ...){
		程序语句;
	   [return 表达式];

	}
	示例：
	public static int add(int x, int y) {
		 int sum;
		 sum = x + y;
		 return sum;
	}
1.4.2 重载
	方法名相同，参数类型或个数不同，返回值可相同也可不同
1.4.3 参数
	基础数据类型作为参数，方法内部对参数的修改不影响调用者
	引用数据类型作为参数，方法内部修改了堆，结果会保留下来

2 面向对象OOP
2.1 类的引入
2.1.1 C语言中的结构体（struct）
	1 类型定义
		struct Person {
			 char name[100];
			 int age;
			 int money;
			 char *(*getName)(struct Person per);
			 int (*getMoney)(struct Person per);

		};

		char *getName(struct Person per)
		{
			 return per.name;
		}

		int getMoney(struct Person per)
		{
			 return per.money;
		}
	2 创建初始化结构体
		struct Person per;
		struct Person *p = malloc(sizeof(struct Person));
		per.getName = getName;
		per.getMoney = getMoney;
		p->getName = getName;
		p->getMoney = getMoney;
	3 使用
		int i = per.age;
		char *str = per.getName();
2.1.2 java中的类（class）
	1 class定义
		class 类名称 {
			数据类型 属性;
			...

			public 返回值类型 方法名称(参数1, 参数2, ...){
				程序语句;
				[return 表达式;]
			}
		}
		class Person {
			String name;
			int age;
			int money;

			public String getName( );
			public int getMoney( );
		}
	2 创建对象
		Person per = null;
		per = new Person();
	3 访问属性和方法
		int i = per.age;
		int m = per.getMoney( );
2.1.3 构造方法
	class Person {
		String name;
		int age;
		int money;

		public String getName( );
		public int getMoney( );

		// 构造方法没有返回值类型，没有返回值
		访问权限 类名称 (类型1 参数1, 类型2 参数2, ...) {
			程序语句;
			... // 构造方法没有返回值
		}
	}
2.1.4 this关键字
	表示当前对象
2.1.5 static关键字
	方便在没有创建对象的情况下来进行调用（方法/变量）
	被static关键字修饰的方法或者变量不需要依赖于对象来进行访问，只要类被加载了，就可以通过类名去进行访问。
	static可以用来修饰类的成员方法、类的成员变量，另外可以编写static代码块来优化程序性能。
	另外记住，即使没有显示地声明为static，类的构造器实际上也是静态方法。
	static是不允许用来修饰局部变量
	1 static 属性
		static变量也称作静态变量，静态变量和非静态变量的区别是：
		静态变量被所有的对象所共享，在内存中只有一个副本，它当且仅当在类初次加载时会被初始化。
		而非静态变量是对象所拥有的，在创建对象的时候被初始化，存在多个副本，各个对象拥有的副本互不影响。
	2 static 方法
		static方法一般称作静态方法，由于静态方法不依赖于任何对象就可以进行访问，
		因此对于静态方法来说，是没有this的，因为它不依附于任何对象，既然都没有对象，就谈不上this了。
		并且由于这个特性，在静态方法中不能访问类的非静态成员属性和非静态成员方法，
		因为非静态成员方法/属性都是必须依赖具体的对象才能够被调用。
2.1.6 代码块
	1 普通代码块
		方法的{}就是普通代码块
	2 构造代码块
		class Person {
			{
				// 构造块
				// 每实例化一个对象前都执行；先于构造方法执行
			}
		}
	3 静态代码块
		class Person {
			static {
				// 静态块
				// 实例化第一个对象前（加载类时）执行；只执行一次
			}
		}
2.2 封装性
	2.2.1 权限
		private，本类可访问
		default，本包可访问
		protected，本包，其他包的子类可访问
		public，所有
	2.2.2 使用方法访问属性
		getter方法
			[非私有修饰符] 字段类型 get字段名称（首字母大写）()
			{
				return 字段名；
			}
		setter方法
			[非私有修饰符] void set字段名称（首字母大写）(字段类型 变量)
			{
				字段=变量；
			}
2.3 继承性
	2.3.1 继承性引入
		1 格式
			class 父类{ }
			class 子类 extends 父类 { }
		2 构造方法
			如果一个类没有构造方法，编译器会给它一个空的构造方法
			实例化子类时，先调用父类的构造方法，再调用子类的构造方法
		3 super关键字
			super表示父类，可以用super.xxx调用父类的方法，super()表示父类的构造方法
			子类的构造方法里会默认调用super()，没有参数
			可以在子类构造方法首行显式调用super()，可加参数
		4 子类继承父类的属性和方法，可以覆写
		5 final关键字
			final类不能有子类
			final方法不能覆写
			final变量变为常量，不能修改
	2.3.2 继承的限制
		私有的属性和方法不能被子类访问
		子类覆写的方法不能缩小权限
	2.3.3 抽象类
		规定子类必须实现的方法，起模板作用
		不能实例化对象
		子类必须覆写抽象方法
		abstract class 类名 {
			属性;
			普通方法 { }
			// 抽象方法
			访问权限 abstract 返回值类型 方法名 (参数) ; /* 只需要声明，不需要实现 */
		}
	2.3.4 接口
		接口由全局常量、公共的抽象方法组成；跟抽象类类似，起模板作用；
		子类可以继承多个接口，但只能继承一个父类				
		interface 接口名称 {
			全局常量;
			抽象方法;
		}

		interface A {
			public static final i = 10;
			public abstract int getNumber( );
		}
		interface B {
			public static final String name = "www.100ask.net";
			public abstract int getName( );
		}

		class C implements A,B {
			//覆写A中方法
			public int getNumber () { return i; }
			//覆写B中方法
			public int getName () { return name; }
		}
2.4 多态性
	2.4.1 方法的重载与覆写
		overload：定义多个同名方法，其参数的类型个数不同
		override：子类里实现与父类相同的方法
	2.4.2 对象的多态性
		1 向上转换
			父类 父类对象 = 子类实例;
			Son son = new Son();
			Father father = son;
			father只能调用Father类中定义过的方法，不能调用只在子类中定义的方法
		2 向下转换
			子类 子类对象 = (子类)父类实例;
			Father father = new Son(); 
			Son son = (Son)father;
			在进行对象的向下转换前，必须首先发生对象向上转换
		3 instanceof：判断一个对象是不是某个类的实例
			
2.5 异常
	2.5.1 异常
		1 java异常类
			Throwable
				Error
					VirtualMachineError
						StackOverFlowError
						OutOfMemmoryError
					AWTError
				Exception
					IOException
						EOFException
						FileNotFoundException
					RuntimeException
						ArrithmeticException
						MissingResourceException
						ClassNotFoundException
						NullPointerException
						IllegalArgumentException
						ArrayIndexOutOfBoundsException
						UnkownTypeException
		2 分为Error和Exception
			Error：java虚拟机本身的问题，跟程序无关，程序不需要处理
			Exception：程序本身可以处理的异常
		3 分为不可查异常和可查异常
			unchecked exceptions：RuntimeException（及其子类）和Error
			checked exceptions：需要用try...catch...显式捕获，或throws抛出
		4 运行时异常（不可查异常）和非运行时异常（编译不会通过，可查异常）
		5 处理原则
			对于可查异常必须捕获，或声明抛出；否则编译不会通过
			运行时异常都是不可查异常，程序中可以捕获处理，也可以不处理；
			允许忽略不可查的RuntimeException和Error
		6 处理方法
			try...catch...finally
				try {
					// 程序语句，可能发生异常  
				} catch (异常类型1 e) {
					// 处理语句
				} catch (异常类型2 e) {
					// 处理语句
				} catch (异常类型3 e) {
					...
				} 
				[ finally {
					//一定会运行到的代码
				} ]
			try或catch块中有return或throw语句，会先执行finally块，再回来执行return或throw
			或者向上throws
	2.5.2 throws
		1 写在方法的声明位置，表示如果发生异常，抛给调用者处理
			public 返回值 方法名(参数列表……) throws 异常类  {   }
			public int div (int m, int n)  throws Exception {
				...
			}
		2 对于“不可查异常”，系统也会抛出，可以不显式throws
		3 对于“可查异常”则必须处理
	2.5.3 throw
		1 人为抛出一个异常
			throw 异常类的实例化对象;
			throw new Exception("My Error");
		2 throw“不可查异常”时可以不处理
		3 throw“可查异常”时必须处理
	2.5.4 assert
		assert boolean表达式;
		assert boolean表达式 : 详细信息;

		表达式为true时，无错误；
		表达式为false时，打印“详细信息”

		assert x == 0;
		assert x == 0 : "X is not zero";
2.6 包和访问权限
	2.6.1 包
		1 package
			包实际上是一个文件夹（包含多级子目录）
			格式：package 包名称.子包名称;
			例子：package a.b.c.d;
			编译命令：javac -d <dir> <file.java>（表示在dir目录下生成包）
			执行命令：java 包名称.子包名称.类名称
			作用：解决类的同名冲突
		2 import
			使用不同包的类时需要import
			格式：
				import 包名称.子包名称.类名称; //手动导入某个类
				import 包名称.子包名称.*; //JVM自动加载需要的类
		3 jar
			生成：jar -cvf <file.jar> <dir>
			查看：jar -tvf <file.jar>
			解压：jar -xvf <file.jar>
		4 CLASSPATH
			指定编译时查找源码的目录
			指定运行时查找的“.class文件的目录”或“jar文件”
			设置方法：
				export CLASSPATH=<dir1>:<dir2>:...:<file1.jar>:<file2.jar>:...
	2.6.2 权限
		1 类
			public class可以被外包访问，class只能在包内访问
		2 类成员
			private：类内访问
			default：包内访问
			protected：包内或外包的子类访问
			public：所有
	2.6.3 内部类
		1 一般内部类
			在类的内部定义一个类
			class Outer {
				class Inner {
				
				}
			}
			Inner被称为内部类。不能直接实例化Inner，要先实例化Outer
			再通过Outer实例化Inner
			好处：内部类可以直接访问外部类的私有属性
			示例：
				Outer o = new Outer( );
				Outer.Inner i = o.new Inner( );
		2 静态内部类					
			class Outer {
				static class Inner {
				
				}
			}
			用static声明的内部类就变成了外部类，可以直接实例化Inner
			它可以访问外部类的static属性
			使用示例：
				Outer.Inner a = new Outer.Inner( );
		3 匿名内部类
			interface A {
				 public void printInfo( );
			}
			class B implements A {
				public void printInfo( ) {
					   System.out.println("Hello, world!");
				}
			}
			public class Anony {
				public static void testFunc(A a) {
					a.printInfo( );
				}

				public static void main(String args[]) {
					testFunc(new B());
					testFunc(new A() {
						public void printInfo() {
							System.out.println("Hello, world!");
						}
					});
				}
			}

3 JNI（Java Native Interface）
3.1 java调用C
3.1.1 java访问C库的方法
	1 加载C库
		System.loadLibrary
	2 建立java函数名与C库函数名的映射关系，隐式建立
		类a.b.c.d.JNIDemo中要调用hello函数，C语言那种要实现Java_a_b_c_d_JNIDemo_hello
		可以用工具查看JNI字段描述符：javap -s -p Var.class
	3 显式建立
		JNI_Onload：加载C库时会调用这个函数
		RgisterNatives：
			定义一个映射数组JNINativeMethod[]，每个数组项含有三个成员：
				java里调用的函数名
				JNI字段描述符：
					用“[”表示数组，比如“int []”表示为“[I”
					对于类，要用全称“L包/子包/类名;”（前面有“L”，后面有“;”）
					比如“Ljava/lang/String;”
					除String类外，其他的类都用Object表示，即“Ljava/lang/Object;”
				C语言实现的本地函数
			注册这个数组
				(*env)->RegisterNatives(JNIEnv *env, jclass cls, const JNINativeMethod *methods, jint nMethods)
	4 注意：C函数比java里的声明多两个参数：（JNIEnv *env，jclass cls）
		env提供一些辅助函数
		cls是对java类或实例的引用，如果是static方法，则cls是对java类的引用，否则是对类的实例化对象的引用
	5 在java程序里调用函数
3.1.2 java和C库传递参数
	1 基本数据类型直接使用，直接返回
	2 传递字符串
		以下函数从Java中获得一个字符串，再返回一个字符串给Java：
		JNIEXPORT jstring JNICALL
			Java_Prompt_getLine(JNIEnv *env, jobject obj, jstring prompt)
		{
			char buf[128];
			const jbyte *str;
			str = (*env)->GetStringUTFChars(env, prompt, NULL);
			if (str == NULL) {
				return NULL; /* OutOfMemoryError already thrown */
			}
			printf("%s", str);
			(*env)->ReleaseStringUTFChars(env, prompt, str);
			/* We assume here that the user does not type more than
			* 127 characters */
			scanf("%s", buf);
			return (*env)->NewStringUTF(env, buf);
		}
	3 传递数组
		以下函数从Java中获得一个int数组，再求和返回一个整数给Java：
		JNIEXPORT jint JNICALL
			Java_IntArray_sumArray(JNIEnv *env, jobject obj, jintArray arr)
		{
			jint *carr;
			jint i, sum = 0;
			carr = (*env)->GetIntArrayElements(env, arr, NULL);
			if (carr == NULL) {
				return 0; /* exception occurred */
			}
			for(i = 0; i < (*env)->GetArrayLength(env, arr); i++) {
				sum += carr[i];
			}
			(*env)->ReleaseIntArrayElements(env, arr, carr, 0);
			return sum;
		}
3.2 C调用java（jni.pdf P97）
3.2.1 创建虚拟机
	JavaVM* jvm;  
	JNIEnv* env;  
	jint create_vm(JavaVM** jvm, JNIEnv** env) 
	{  
		JavaVMInitArgs args;  
		JavaVMOption options[1];
		
		args.version = JNI_VERSION_1_6;  
		args.nOptions = 1;  
		
		options[0].optionString = "-Djava.class.path=.";
		
		args.options = options;  
		args.ignoreUnrecognized = JNI_FALSE;  
		
		return JNI_CreateJavaVM(jvm, (void **)env, &args);  
	}
3.2.2 获得class
	jclass cls = (*env)->FindClass(env, "Hello");
3.2.3 实例化对象
	jmethodID mid = (*env)->GetMethodID(env, cls, "<init>", "()V"); // 获得构造方法
	jobject obj = (*env)->NewObject(env, cls, mid); // 实例化对象
	静态方法不需要实例化对象
3.2.4 调用java方法
	1 获得方法ID
		jmethodID smid = (*env)->GetStaticMethodID(env, cls, "sayhello_to", "(Ljava/lang/String;)I");
		jmethodID mid = (*env)->GetMethodID(env, cls, "saygoodbye_to", "(Ljava/lang/String;)I");
	2 构造参数
		jstring jstr = (*env)->NewStringUTF(env, "weidongshan@qq.com");
	3 调用方法
		(*env)->CallStaticVoidMethod(env, jobj, smid, jstr);
		ret = (*env)->CallIntMethod(env, jobj, mid, jstr);
3.2.5 设置属性
	1 获得属性ID
		jfieldID fid = (*env)->GetFieldID(env, cls, "name", "Ljava/lang/String;");
	2 设置属性	
		jstring jstr = (*env)->NewStringUTF(env, "Bill");
		(*env)->SetObjectField(env, jobj, fid, jstr);
	3 读取属性

4 泛型
4.1 泛型类
	定义Container泛型类
	public class Container<K, V> {
		private K key;
		private V value;

		public Container(K k, V v) {
			key = k;
			value = v;
		}
		public K getKey() {
			return key;
		}
		public void setKey(K key) {
			this.key = key;
		}
		public V getValue() {
			return value;
		}
		public void setValue(V value) {
			this.value = value;
		}
	}
	泛型类的使用
	public class Main {

	    public static void main(String[] args) {
		Container<String, String> c1 = new Container<String, String>("name", "findingsea");
		Container<String, Integer> c2 = new Container<String, Integer>("age", 24);
		Container<Double, Double> c3 = new Container<Double, Double>(1.1, 2.2);
		System.out.println(c1.getKey() + " : " + c1.getValue());
		System.out.println(c2.getKey() + " : " + c2.getValue());
		System.out.println(c3.getKey() + " : " + c3.getValue());
	    }
	}
4.2 泛型接口
	泛型接口定义
	public interface Generator<T> {
		public T next();
	}
	实现这个接口
	public class FruitGenerator implements Generator<String> {
		private String[] fruits = new String[]{"Apple", "Banana", "Pear"};
		@Override
		public String next() {
			Random rand = new Random();
			return fruits[rand.nextInt(3)];
		}
	}
	调用
	public class Main {
		public static void main(String[] args) {
			FruitGenerator generator = new FruitGenerator();
			System.out.println(generator.next());
			System.out.println(generator.next());
			System.out.println(generator.next());
			System.out.println(generator.next());
		}
	}
4.3 泛型方法
	一个基本的原则是：无论何时，只要你能做到，你就应该尽量使用泛型方法。
	也就是说，如果使用泛型方法可以取代将整个类泛化，那么应该有限采用泛型方法。
	public class Main {
		public static <T> void out(T t) {
			System.out.println(t);
		}
		public static void main(String[] args) {
			out("findingsea");
			out(123);
			out(11.11);
			out(true);
		}
	}
	泛型方法和可变参数的例子
	public class Main {
		public static <T> void out(T... args) {
			for (T t : args) {
				System.out.println(t);
			}
		}
		public static void main(String[] args) {
			out("findingsea", 123, 11.11, true);
		}
	}
4.4 泛型的上下限
	上限 <T extends ClassA>，T必须是ClassA及其子类
	例如 <T extends Number>，则T必须是Number及其子类，如Integer，Float等
	下限 <? super String>，上限只能用通配符，这里的泛型必须只能是String及其父类	




